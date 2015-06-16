package service.Impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import dao.TieDao;
import dao.TieThemeDao;
import dao.townDao;
import dao.usersDao;
import domain.TieTheme;
import domain.town;
import domain.users;
import service.CommunityService;
import service.usersService;
import tools.LogUtil;
import tools.ModelSort;
import tools.NetErrorUtil;
import tools.SortAction;
import tools.SortTownAction;
import tools.SortAction.SortData;
import tools.objects.ApplyTown;
import tools.objects.ResponseSimple;
import tools.objects.community.ModelCommuHeader;
import tools.objects.community.ModelTieTheme;
import tools.objects.community.ResCommunityHeader;
import tools.objects.community.ResCommunityTie;
import tools.objects.community.ResCommunityTieTh;

public class CommunityServiceImpl implements CommunityService
{
	private townDao townx;
	private usersDao user;
	private TieThemeDao tieth;
	private TieDao tie;
	private usersService userservice;
	
	@Override
	public ResCommunityHeader getCommunityHeader(int townid,int userid) {
		ResCommunityHeader res = new ResCommunityHeader();
		ModelCommuHeader header = new ModelCommuHeader();
		try{
			town t = townx.get(townid);
			header.setCommunityid(t.getTownid());
			header.setAdminid(t.getOwner().getUsersid());
			header.setCommunityname(t.getName());
			header.setCover(t.getCover());
			header.setMemberscount(t.getMembercount());
			header.setTiecount(t.getTiecount());
			header.setCreatetime(t.getCreatetime().getTimeInMillis());
			header.setHasjoin(user.checkIfJoinCommunity(townid, userid));
			header.setTops(tieth.getTopTie(t));
			res.setStat(true);
			res.setHeader(header);
		}catch (Exception e ) {
			e.printStackTrace();
			res.setStat(false);
			res.setErrcode(NetErrorUtil.SERVER_ERROR);
		}
		
		return res;
	}

	@Override
	public ResCommunityTieTh getCommunityTieTh(int townid,List<Integer> Rejectids) {
		ResCommunityTieTh res = new ResCommunityTieTh();
		try {
			town t = townx.get(townid);
			List<ModelSort> s1 = tieth.sortByTime(t);
			List<ModelSort> s2 = tieth.sortByGoodCou(t);
			List<ModelSort> s3 = tieth.sortByReply(t);
			List<SortAction.SortData> sortdata = new SortAction(s1,s2,s3).sort();
			//排除客户端已经显示的内容
			if (Rejectids.size() != 0 ){
				for (Iterator<SortAction.SortData> it = sortdata.iterator();it.hasNext();) {
					SortAction.SortData tmpdata = it.next();
					for (int j=0;j < Rejectids.size(); j++) {
						if (Rejectids.get(j).intValue() == tmpdata.getTownid()){
							LogUtil.v("removing id： "+Rejectids.get(j).intValue());
							it.remove();
						}
					}
				}
			}
			//获取指定的tiethemelist集合
			List<TieTheme> datalist = null;
			if(sortdata.size() == 0)
				datalist = new ArrayList<TieTheme>();
			else if (sortdata.size() < 10)
				datalist = tieth.getTargetTie(sortdata);
			else
				datalist = tieth.getTargetTie(sortdata.subList(0, 10));
			//转换
			List<ModelTieTheme> returnlist  = new ArrayList<ModelTieTheme>();
			for (int i=0;i<datalist.size();i++) {
				returnlist.add(new ModelTieTheme(datalist.get(i)));
			}
			
			res.setTies(returnlist);
			res.setStat(true);		
		} catch (Exception e ) {
			e.printStackTrace();
			res.setStat(false);
			res.setErrcode(NetErrorUtil.SERVER_ERROR);
		}
		return res;
	}
	@Override
	public ResCommunityTie getCommunityTie(int zhulouid,int position) {
		ResCommunityTie res = new ResCommunityTie();
		try{
			res.setTies(tie.getTies(tieth.get(zhulouid),position));
			res.setStat(true);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStat(false);
			res.setErrcode(NetErrorUtil.SERVER_ERROR);
		}
		
		return res;
	}

	
	@Override
	public ResponseSimple joinCommunity(int townid, int userid) {
		ResponseSimple res = new ResponseSimple();
		try{
			town t = townx.get(townid);
			users u = user.get(userid);
			t.getCommunitymembers().add(u);
			int count = t.getMembercount();
			t.setMembercount(++count);
			townx.update(t);
			user.update(u);
			res.setStat(true);
		} catch (Exception e) {
			res.setStat(false);
			res.setErrcode(NetErrorUtil.SERVER_ERROR);
			e.printStackTrace();
		}
		
		return res;
	}
	@Override
	public ResponseSimple toTop(int tiethid, int userid) {
		ResponseSimple res = new ResponseSimple();
		TieTheme tt = tieth.get(tiethid);
		if (!userservice.checkUserIsTownOwner(tt.getParentown().getTownid(), userid)) {
			res.setStat(false);
			res.setErrcode(NetErrorUtil.NOTJOIN_COMMUNITY);
		} else {
			int i = tt.getTop();
			if (i==0) {
				tt.setTop(1);
				tt.setTime(Calendar.getInstance());
			}else
				tt.setTop(0);
			res.setStat(true);
		}
		
		return res;
	}

	public townDao getTownx() {
		return townx;
	}

	public void setTownx(townDao townx) {
		this.townx = townx;
	}

	public usersDao getUser() {
		return user;
	}

	public void setUser(usersDao user) {
		this.user = user;
	}

	public TieThemeDao getTieth() {
		return tieth;
	}

	public void setTieth(TieThemeDao tieth) {
		this.tieth = tieth;
	}
	public TieDao getTie() {
		return tie;
	}

	public void setTie(TieDao tie) {
		this.tie = tie;
	}

	public usersService getUserservice() {
		return userservice;
	}

	public void setUserservice(usersService userservice) {
		this.userservice = userservice;
	}

	
}