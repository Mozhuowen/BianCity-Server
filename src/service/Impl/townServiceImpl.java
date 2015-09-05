package service.Impl;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import dao.townDao;
import dao.usersDao;
import domain.GeoInfo;
import domain.town;
import domain.users;
import service.CommunityService;
import service.townService;
import service.usersService;
import tools.Geohash;
import tools.LogUtil;
import tools.NetErrorUtil;
import tools.SortTownAction;
import tools.TownSort;
import tools.objects.ApplyTown;
import tools.objects.ResGeoInfo;
import tools.objects.ResponseHotTown;
import tools.objects.ResponseTown;

public class townServiceImpl implements townService
{
	private final int GEOHASHLENTH = 6;	//查询附近需要的位数长度
	private final int HOTONCESHOW = 16;	//控制每次请求传送多少个小镇
	private usersDao user;
	private townDao towndao;
	private CommunityService community;
	
	public void setTowndao(townDao t) {
		this.towndao = t;
	}
	public townDao getTowndao() {
		return this.towndao;
	}
	public void setUser(usersDao u) {
		this.user = u;
	}
	public usersDao getUser(){
		return this.user;
	}

	@Override
	public ResponseTown apply(int userid, String townname, String descri,
			String cover, GeoInfo geoinfo) {
		ResponseTown resobj = new ResponseTown();
		if (!checkUserid(userid)) {
			resobj.setStat(false);
			resobj.setErrcode(NetErrorUtil.USER_NOTEXIST);
		} else if (!checkTownname(townname)) {
			resobj.setStat(false);
			resobj.setErrcode(NetErrorUtil.TOWNNAME_EXIST);
		} else {
			town t = new town();
			users u = user.get(userid);
			t.setOwner(u);
			t.setName(townname);
			t.setDescri(descri);
			t.setCover(cover);
			t.setGeo(geoinfo);
			t.setCreatetime(Calendar.getInstance());
			t.setMembercount(1);
			//geohash
			String geohash = new Geohash().encode(t.getGeo().getLatitude(), t.getGeo().getLongitude());
			t.setGeohash(geohash);
			
			if (towndao.save(t)>0) {
				//把自己加入社区
				u.getJoincommunity().add(t);
				resobj.setStat(true);
				resobj.setTownname(townname);
				resobj.setDescri(descri);
				resobj.setTownid(t.getTownid());
				resobj.setCover(cover);
				resobj.setGeoinfo(geoinfo);
				resobj.setCreatetime(getFormatDate(t.getCreatetime()));
				resobj.setStorycount(0);
				resobj.setUserid(u.getUsersid());
				resobj.setUsername(u.getName());
				resobj.setUsercover(u.getCover());
				resobj.setGood(0);
			} else {
				resobj.setErrcode(NetErrorUtil.SERVER_ERROR);
				resobj.setStat(false);
			}
		}
		
		return resobj;
	}

	@Override
	public boolean checkUserid(int id) {
		users u = user.get(id);
		if (u == null)
			return false;
		else
			return true;
	}

	@Override
	public boolean checkTownname(String townname) {
		// TODO Auto-generated method stub
		town t = towndao.getBytownname(townname);
		if (t == null){
			System.out.println("town is null!");
			return true;
		}
		else{
			System.out.println("town is not null! name is "+t.getName());
			return false;
		}
	}
	
	public String getFormatDate(Calendar time) {
		String datestr = DateFormat.getDateInstance(DateFormat.MEDIUM).format(time.getTime());
		return datestr;
	}
	@Override
	public ResponseHotTown getHotTown(List<Integer> rejectlist) {
		ResponseHotTown resobj = new ResponseHotTown();
		resobj.setStat(true);
		try{
			//获取hot列表
			List<TownSort> s1 = towndao.getHotTownByTime();
			List<TownSort> s2 = towndao.getHotTownByGood();
			List<TownSort> s3 = towndao.getHotTownByFans();
//			LogUtil.v("s1: "+s1);
//			LogUtil.v("s2: "+s2);
//			LogUtil.v("s3: "+s3);
			List<SortTownAction.SortData> sortdata = new SortTownAction(s1,s2,s3).sort();
			LogUtil.v("sortdata: "+sortdata);
			//排除客户端已经显示的内容
			if (rejectlist.size() != 0 ){
				for (Iterator<SortTownAction.SortData> it = sortdata.iterator();it.hasNext();) {
					SortTownAction.SortData tmpdata = it.next();
					for (int j=0;j < rejectlist.size(); j++) {
						if (rejectlist.get(j).intValue() == tmpdata.getTownid()){
							LogUtil.v("removing id： "+rejectlist.get(j).intValue());
							it.remove();
						}
					}
				}
			}
			//获取指定的townlist集合
			List<town> townlist = null;
			if(sortdata.size() == 0)
				townlist = new ArrayList<town>();
			else if (sortdata.size() < HOTONCESHOW)
				townlist = towndao.getTargetTown(sortdata);
			else
				townlist = towndao.getTargetTown(sortdata.subList(0, HOTONCESHOW));
			//转换
			List<ApplyTown> returnlist  = new ArrayList<ApplyTown>();
			for (int i=0;i<townlist.size();i++) {
				ApplyTown at = new ApplyTown();
				town t = townlist.get(i);
				users u = t.getOwner();
				at.setTownid(t.getTownid());
				at.setTownname(t.getName());
				at.setCover(t.getCover());
				at.setDescri(t.getDescri());
				at.setCreatetime(getFormatDate(t.getCreatetime()));
				at.setGeoinfo(new ResGeoInfo(t.getGeo()));
				at.setGood(t.getGoods());
				at.setUserid(u.getUsersid());		
				at.setUsername(u.getName());
				at.setUsercover(u.getCover());
				at.setStorycount(t.getPutao().size());
				returnlist.add(at);
			} 
			resobj.setTowns(returnlist);
		} catch (Exception e) {
			e.printStackTrace();
			resobj.setStat(false);
			resobj.setErrcode(tools.NetErrorUtil.SERVER_ERROR);
		}		
		return resobj;
	}
	@Override
	public ResponseHotTown getNearTwon(GeoInfo geo,List<Integer> rejectlist) {
		double lat = geo.getLatitude();
		double lon = geo.getLongitude();
		ResponseHotTown resobj = new ResponseHotTown();
		resobj.setStat(true);
		try{
			//获取geohash
			Geohash geohash = new Geohash();
			String geohashstr = geohash.encode(lat, lon).substring(0, GEOHASHLENTH); 
			String[] geohashexpand = geohash.getGeoHashExpand(geohashstr);
			LogUtil.v("geohash: "+geohashstr);
			//装载附近小镇
			List<TownSort>[] townnear = new List[geohashexpand.length];
			for (int i=0;i<geohashexpand.length;i++) {
				townnear[i] = towndao.getNearTown(geohashexpand[i]);
			}
			//排序
			List<SortTownAction.SortData> sortdata = new SortTownAction(townnear).sort();
			//排除客户端已经显示的内容
			if (rejectlist.size() != 0 ){
				for (Iterator<SortTownAction.SortData> it = sortdata.iterator();it.hasNext();) {
					SortTownAction.SortData tmpdata = it.next();
					for (int j=0;j < rejectlist.size(); j++) {
						if (rejectlist.get(j).intValue() == tmpdata.getTownid()){
							LogUtil.v("removing id： "+rejectlist.get(j).intValue());
							it.remove();
						}
					}
				}
			}
			//获取指定的townlist集合
			List<town> townlist = null;
			if(sortdata.size() == 0)
				townlist = new ArrayList<town>();
			else if (sortdata.size() < HOTONCESHOW)
				townlist = towndao.getTargetTown(sortdata);
			else
				townlist = towndao.getTargetTown(sortdata.subList(0, HOTONCESHOW));
			//转换
			List<ApplyTown> returnlist  = new ArrayList<ApplyTown>();
			for (int i=0;i<townlist.size();i++) {
				ApplyTown at = new ApplyTown();
				town t = townlist.get(i);
				users u = t.getOwner();
				at.setTownid(t.getTownid());
				at.setTownname(t.getName());
				at.setCover(t.getCover());
				at.setDescri(t.getDescri());
				at.setCreatetime(getFormatDate(t.getCreatetime()));
				at.setGeoinfo(new ResGeoInfo(t.getGeo()));
				at.setGood(t.getGoods());
				at.setUserid(u.getUsersid());		
				at.setUsername(u.getName());
				at.setUsercover(u.getCover());
				at.setStorycount(t.getPutao().size());
				returnlist.add(at);
			} 
			resobj.setTowns(returnlist);
		} catch (Exception e) {
			e.printStackTrace();
			resobj.setStat(false);
			resobj.setErrcode(tools.NetErrorUtil.SERVER_ERROR);
		}
		//testcode
//		resobj.setTowns(new ArrayList());
		return resobj;
	}
	@Override
	public List<town> getTownByTime(int position) {	
		return this.towndao.getTownByTime(position);
	}
	@Override
	public Long getTownCount() {		
		return this.towndao.getTownCount();
	}
	public CommunityService getCommunity() {
		return community;
	}
	public void setCommunity(CommunityService community) {
		this.community = community;
	}
	
}
