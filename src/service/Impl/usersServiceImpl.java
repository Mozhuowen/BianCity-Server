package service.Impl;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import dao.*;
import service.*;
import tools.CharacterUtil;
import tools.CopyObject;
import tools.LogUtil;
import tools.MD5Util;
import tools.NetErrorUtil;
import tools.objects.ApplyTown;
import tools.objects.ModelRegisteQQ;
import tools.objects.ModelRegisteWb;
import tools.objects.ModelUser;
import tools.objects.PackagePutao;
import tools.objects.ResGeoInfo;
import tools.objects.ResponseCName;
import tools.objects.ResponseLogin;
import tools.objects.ResponsePutao;
import tools.objects.ResponseRegiste;
import tools.objects.ResponseSimple;
import tools.objects.ResponseSubscri;
import tools.objects.ResponseTown;
import tools.objects.ResponseUser;
import dao.Impl.*;
import domain.*;

public class usersServiceImpl implements usersService
{
	private usersDao user;
	private WeiboUserDao weibo;
	private QQUserDao qquser;
	
	public void setQquser(QQUserDao q){
		this.qquser = q;
	}
	public QQUserDao getQquser() {
		return this.qquser;
	}
	public void setWeibo(WeiboUserDao wb) {
		this.weibo = wb;
	}
	public WeiboUserDao getWeibo() {
		return this.weibo;
	}
	public void setUser(usersDao u) {
		this.user = u;
	}
	public usersDao getUser(){
		return this.user;
	}

	@Override
	public ResponseRegiste registe(String name, String password, String email,String cover,String imei,String sv,String phonemodel,String brand) {
		// TODO Auto-generated method stub
		ResponseRegiste resobj = new ResponseRegiste();
		if (!checkUsername(name)) {
			resobj.setErrcode(NetErrorUtil.NAME_EXIST);
			resobj.setStat(false);
		} else if (!checkEmail(email)) {
			resobj.setErrcode(NetErrorUtil.EMAIL_EXIST);
			resobj.setStat(false);
		} else {
			String token = CharacterUtil.getRandomString(32);
			Calendar registetime = Calendar.getInstance();
			users u = new users();
			u.setName(name);
			u.setPassword(password);
			u.setEmail(email);
			u.setCover(cover);
			u.setPtoken(token);
			u.setImei(imei);
			u.setSv(sv);
			u.setBrand(brand);
			u.setPhonemodel(phonemodel);
			u.setRegistetime(registetime);
			u.setLastlogin(registetime);
			
			if(user.save(u)>0){
				/*resobj.setUserid(u.getUsersid());
				resobj.setStat(true);
				resobj.setName(name);
				resobj.setCover(cover);
				resobj.setToken(token);
				resobj.setEmail(email);*/
			}
			else{
				resobj.setErrcode(NetErrorUtil.SERVER_ERROR);
				resobj.setStat(false);
			}
		}
		
		return resobj;
	}
	@Override
	public boolean checkUsername(String name) {
		// TODO Auto-generated method stub
		users u = user.getByUsername(name);
		if (u == null)
			return true;
		else
			return false;
	}
	@Override
	public boolean checkEmail(String email) {
		// TODO Auto-generated method stub
		users u = user.getByEmail(null);
		if (u == null)
			return true;
		else
			return false;
	}
	@Override
	public ResponseLogin checklogin(String name, String passwordmd5, String imei,String sv,String phonemodel,String brand) {
		ResponseLogin resobj = new ResponseLogin();
		String cover = null;
		String email = null;
		users u = user.getByUsername(name);
		if (u == null) {
			resobj.setStat(false);
			resobj.setErrcode(NetErrorUtil.NAME_NOTEXIST);
		} else if (!passwordmd5.equals(MD5Util.getMD5(u.getPassword()))) {
			resobj.setStat(false);
			resobj.setErrcode(NetErrorUtil.PASSWORD_ERROR);
		} else {
			cover = u.getCover();
			email = u.getEmail();
			String token = CharacterUtil.getRandomString(32);
			Calendar currtime = Calendar.getInstance();
			//更新user
			u.setPtoken(token);
			u.setImei(imei);
			u.setSv(sv);
			u.setBrand(brand);
			u.setPhonemodel(phonemodel);
			u.setLastlogin(currtime);
			
			user.update(u);
			resobj.setPtuserid(u.getUsersid());
			resobj.setStat(true);
			resobj.setName(name);
			resobj.setCover(cover);
			resobj.setPtoken(token);
			List<town> townlist = new ArrayList<town>(u.getMytowns());
			//遍历处理整合出town列表
			List<ApplyTown> returnlist  = new ArrayList<ApplyTown>();
			for (int i=0;i<townlist.size();i++) {
				ApplyTown at = new ApplyTown();
				town t = townlist.get(i);
				at.setTownid(t.getTownid());
				at.setTownname(t.getName());
				at.setCover(t.getCover());
				at.setDescri(t.getDescri());
				at.setCreatetime(getFormatDate(t.getCreatetime()));
				at.setGeoinfo(new ResGeoInfo(t.getGeo()));
				at.setGood(t.getGoods());
				returnlist.add(at);
			}
			resobj.setMytowns(returnlist);
		}
		
		return resobj;
	}
	public String getFormatDate(Calendar time) {
		String datestr = DateFormat.getDateInstance(DateFormat.MEDIUM).format(time.getTime());
		return datestr;
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
	public boolean checkToken(int userid,String token) {
		users u = user.get(userid);
		if (u.getPtoken().equals(token.trim()))
			return true;
		else
			return false;
	}	
	@Override
	public ResponseLogin checkloginByQQ(String openid,String qqtoken,Calendar expire,String imei,String sv,String phonemodel,String brand)
	{
		ResponseLogin resobj = new ResponseLogin();
		if (checkQQExiste(openid)) {			//用户存在
			if (checkQQToken(openid,qqtoken,expire)) {	//token 合法
				//更新token后获取用户信息反馈到客户端
				String token = CharacterUtil.getRandomString(32);
				QQUser qq = qquser.getByOpenid(openid);
				users u = qq.getPtuser();
				u.setPtoken(token);
				u.setLogintype(1);
				u.setLastlogin(Calendar.getInstance());
				user.update(u);
				resobj.setStat(true);
				resobj.setNeedregiste(false);
				resobj.setLogintype(1);
				resobj.setPtuserid(u.getUsersid());
				resobj.setPtoken(token);
				if (u.getName()==null || u.getName().equals("")) {
					resobj.setNeedcname(true);
					resobj.setName("");
				}
				else {
					resobj.setNeedcname(false);
					resobj.setName(u.getName());
				}
				resobj.setCover(u.getCover());
				resobj.setUid(qq.getOpenid());
				resobj.setSex(qq.getGender());
				resobj.setLocation(qq.getGender());
				List<town> townlist = new ArrayList<town>(u.getMytowns());
				//遍历处理整合出town列表
				List<ApplyTown> returnlist  = new ArrayList<ApplyTown>();
				for (int i=0;i<townlist.size();i++) {
					ApplyTown at = new ApplyTown();
					town t = townlist.get(i);
					at.setTownid(t.getTownid());
					at.setTownname(t.getName());
					at.setCover(t.getCover());
					at.setDescri(t.getDescri());
					at.setCreatetime(getFormatDate(t.getCreatetime()));
					at.setGeoinfo(new ResGeoInfo(t.getGeo()));
					at.setGood(t.getGoods());
					at.setUserid(u.getUsersid());
					returnlist.add(at);
				}
				resobj.setMytowns(returnlist);
			} else {
				resobj.setStat(false);
				resobj.setErrcode(NetErrorUtil.TOKEN_ERROR);
			}
			
		} else {				//用户不存在，
			//生成putao user
			String token = CharacterUtil.getRandomString(32);
			Calendar registetime = Calendar.getInstance();
			users u = new users();
			u.setQqtoken(qqtoken);
			u.setExpirestime(expire);
			u.setLogintype(1);
			u.setPtoken(token);
			u.setImei(imei);
			u.setSv(sv);
			u.setBrand(brand);
			u.setPhonemodel(phonemodel);
			u.setRegistetime(registetime);
			u.setLastlogin(registetime);
			if(user.save(u)>0){
				resobj.setStat(true);
				resobj.setNeedregiste(true);
				resobj.setLogintype(1);
				resobj.setPtuserid(u.getUsersid());
				resobj.setPtoken(token);
			}
			else{
				resobj.setErrcode(NetErrorUtil.SERVER_ERROR);
				resobj.setStat(false);
			}
		}
		
		return resobj;
	}
	@Override
	public ResponseLogin checkloginByWb(String uid,String wbtoken,
			Calendar expire, String imei, String sv, String phonemodel,
			String brand) {
		ResponseLogin resobj = new ResponseLogin();
		if (checkWeiboExiste(uid)) {	//用户存在
			if (checkWeiboToken(uid,wbtoken,expire)) {	//token 合法
				//更新token后获取用户信息反馈到客户端
				String token = CharacterUtil.getRandomString(32);
				WeiboUser wbu = weibo.getByUid(uid);
				users u = wbu.getPtuser();
				u.setPtoken(token);
				u.setLogintype(0);
				u.setLastlogin(Calendar.getInstance());
				user.update(u);
				resobj.setStat(true);
				resobj.setNeedregiste(false);
				resobj.setLogintype(0);
				resobj.setPtuserid(u.getUsersid());
				resobj.setPtoken(token);
				if (u.getName()==null || u.getName().equals("")) {
					resobj.setNeedcname(true);
					resobj.setName("");
				}
				else {
					resobj.setNeedcname(false);
					resobj.setName(u.getName());
				}
				resobj.setCover(u.getCover());
				resobj.setUid(wbu.getId());
				resobj.setSex(wbu.getGender());
				resobj.setLocation(wbu.getLocation());
				List<town> townlist = new ArrayList<town>(u.getMytowns());
				//遍历处理整合出town列表
				List<ApplyTown> returnlist  = new ArrayList<ApplyTown>();
				for (int i=0;i<townlist.size();i++) {
					ApplyTown at = new ApplyTown();
					town t = townlist.get(i);
					at.setTownid(t.getTownid());
					at.setTownname(t.getName());
					at.setCover(t.getCover());
					at.setDescri(t.getDescri());
					at.setCreatetime(getFormatDate(t.getCreatetime()));
					at.setGeoinfo(new ResGeoInfo(t.getGeo()));
					at.setGood(t.getGoods());
					at.setUserid(u.getUsersid());
					returnlist.add(at);
				}
				resobj.setMytowns(returnlist);
				
			} else {
				resobj.setStat(false);
				resobj.setErrcode(NetErrorUtil.TOKEN_ERROR);
			}
		} else {	//用户不存在
			//生成putao user
			String token = CharacterUtil.getRandomString(32);
			Calendar registetime = Calendar.getInstance();
			users u = new users();
			u.setWbtoken(wbtoken);
			u.setExpirestime(expire);
			u.setLogintype(0);
			u.setPtoken(token);
			u.setImei(imei);
			u.setSv(sv);
			u.setBrand(brand);
			u.setPhonemodel(phonemodel);
			u.setRegistetime(registetime);
			u.setLastlogin(registetime);
			if(user.save(u)>0){
				resobj.setStat(true);
				resobj.setNeedregiste(true);
				resobj.setLogintype(0);
				resobj.setPtuserid(u.getUsersid());
				resobj.setPtoken(token);
			}
			else{
				resobj.setErrcode(NetErrorUtil.SERVER_ERROR);
				resobj.setStat(false);
			}
		}
		return resobj;
	}
	
	public boolean checkWeiboExiste(String uid) {
		WeiboUser wb = weibo.getByUid(uid);
		if (wb != null)
			return true;
		else
			return false;
	}
	
	public boolean checkQQExiste(String openid) {
		QQUser qq = qquser.getByOpenid(openid);
		if (qq != null)
			return true;
		else
			return false;
	}
	
	public boolean checkWeiboToken(String uid,String wbtoken,Calendar expirestime) {
		WeiboUser wb = weibo.getByUid(uid);
		users u = wb.getPtuser();
		String servwbToken = u.getWbtoken();
		String servpToken = u.getPtoken();
		Calendar tokenExpire = u.getExpirestime();
		if (tokenExpire.getTimeInMillis()>Calendar.getInstance().getTimeInMillis()) {	//weibo token 没有过期
			if (wbtoken.equals(servwbToken)) 
				return true;
		} else 
				return true;
		return false;		
	}
	public boolean checkQQToken(String openid,String qqtoken,Calendar expirestime) {
		QQUser qq = qquser.getByOpenid(openid);
		users u = qq.getPtuser();
		String servqqToken = u.getQqtoken();
		String servpToken = u.getPtoken();
		Calendar tokenExpire = u.getExpirestime();
		if (tokenExpire.getTimeInMillis()>Calendar.getInstance().getTimeInMillis()) {	//weibo token 没有过期
			if (qqtoken.equals(servqqToken))
				return true;
		} else 
			return true;
		return false;
	}
	@Override
	public ResponseRegiste regByQQ(ModelRegisteQQ userinfo, int puid) {
		ResponseRegiste resobj = new ResponseRegiste();
		users u = user.get(puid);
		QQUser qq = new QQUser();
		qq = (QQUser)CopyObject.copyProperties(qq,userinfo);
		qq.setPtuser(u);
		//persist
		qquser.save(qq);
		//检查用户名
		if(checkNeedCName(userinfo.getNickname())) {	//需要修改设置用户名
			u.setCover(userinfo.getFigureurl_qq_1());
			user.update(u);
			//set response
			resobj.setStat(true);
			resobj.setNeedchangename(true);
			resobj.setCover(u.getCover());
			resobj.setUid(userinfo.getOpenid());
			resobj.setSex(userinfo.getGender());
			resobj.setLocation(userinfo.getCity());
		} else {
			u.setName(userinfo.getNickname());
			u.setCover(userinfo.getFigureurl_qq_1());
			user.update(u);
			//设置response用户信息
			resobj.setStat(true);
			resobj.setNeedchangename(false);
			resobj.setName(u.getName());
			resobj.setCover(u.getCover());
			resobj.setUid(userinfo.getOpenid());
			resobj.setSex(userinfo.getGender());
			resobj.setLocation(userinfo.getCity());
		}
		
		return resobj;
	}
	@Override
	public ResponseRegiste regByWb(ModelRegisteWb userinfo,int puid) {
		ResponseRegiste resobj = new ResponseRegiste();
		users u = user.get(puid);
		WeiboUser wbuser = new WeiboUser();
		wbuser = (WeiboUser)CopyObject.copyProperties(wbuser, userinfo);
		wbuser.setPtuser(u);
		//持久化weibo user
		weibo.save(wbuser);
		//检查用户名
		if(checkNeedCName(userinfo.getScreen_name())) {	//需要修改设置用户名
			u.setCover(userinfo.getAvatar_hd());
			user.update(u);
			//set response
			resobj.setStat(true);
			resobj.setNeedchangename(true);
			resobj.setCover(u.getCover());
			resobj.setUid(userinfo.getId());
			resobj.setSex(userinfo.getGender());
			resobj.setLocation(userinfo.getLocation());
		} else {
			u.setName(userinfo.getScreen_name());
			u.setCover(userinfo.getAvatar_hd());
			user.update(u);
			//设置response用户信息
			resobj.setStat(true);
			resobj.setNeedchangename(false);
			resobj.setName(u.getName());
			resobj.setCover(u.getCover());
			resobj.setUid(userinfo.getId());
			resobj.setSex(userinfo.getGender());
			resobj.setLocation(userinfo.getLocation());
		}
		
		return resobj;
	}
	
	public boolean checkNeedCName(String name) {
		users u = user.getByUsername(name);
		if (u == null)
			return false;
		else
			return true;
	}
	@Override
	public boolean checkPtoken(int puid, String ptoken) {
		users u = user.get(puid);
		if (ptoken.equals(u.getPtoken()))
			return true;
		else
			return false;
	}
	@Override
	public ResponseCName cname(int puid, String name) {
		ResponseCName resobj = new ResponseCName();
		if (user.getByUsername(name) == null) {	//通过
			users us = user.get(puid);
			us.setName(name);
			user.update(us);
			resobj.setStat(true);
			resobj.setName(name);
		} else {
			resobj.setStat(false);
		}
		return resobj;
	}
	@Override
	public int getFans(int userid) {
		return user.getFans(userid);
	}
	
	@Override
	public ResponseUser getUserInfo(int userid,boolean onlystatis) {
		ResponseUser resobj = new ResponseUser();
		ModelUser mu = new ModelUser();
		try {
			users u = user.get(userid);
			mu.setUserid(u.getUsersid());
			mu.setCover(u.getCover());
			mu.setName(u.getName());
			if (u.getLogintype() == 0) {
				mu.setSex(u.getWeibo().getGender());
				mu.setLocation(u.getWeibo().getLocation());
			} else {
				mu.setSex(u.getQqinfo().getGender());
				mu.setLocation(u.getQqinfo().getCity());
			}
			mu.setTowncount(u.getMytowns().size());
			mu.setPutaocount(u.getMyputao().size());
			mu.setFans(u.getFans());
			mu.setSubscricount(u.getSubscritown().size());
			mu.setFavoritecount(u.getFavorite().size());
			mu.setBegoodcount(user.getBegoodCount(userid));
			mu.setWallimage(u.getWallimage());
			if (!onlystatis) {
				//整理创建的小镇
				List<town> townlist = new ArrayList<town>(u.getMytowns());
				//遍历处理整合出town列表
				List<ApplyTown> returnlist  = new ArrayList<ApplyTown>();
				for (int i=0;i<townlist.size();i++) {
					ApplyTown at = new ApplyTown();
					town t = townlist.get(i);
					at.setTownid(t.getTownid());
					at.setTownname(t.getName());
					at.setCover(t.getCover());
					at.setDescri(t.getDescri());
					at.setCreatetime(getFormatDate(t.getCreatetime()));
					at.setGeoinfo(new ResGeoInfo(t.getGeo()));
					at.setGood(t.getGoods());
					at.setUserid(u.getUsersid());					
					at.setUsercover(u.getCover());
					at.setUsername(u.getName());
					returnlist.add(at);
				} 
				mu.setOnlystatis(false);
				mu.setMytowns(returnlist);
			}else 
				mu.setOnlystatis(true);
			resobj.setStat(true);
			resobj.setUser(mu);
		}catch (Exception e) {
			resobj.setStat(false);
			e.printStackTrace();
		}
		return resobj;
	}
	@Override
	public ResponseSubscri getSubscris(int userid) {
		ResponseSubscri resobj = new ResponseSubscri();
		users u = user.get(userid);
		//整理订阅的town
		List<town> townlist = new ArrayList<town>(u.getSubscritown());
		List<ApplyTown> returnlist  = new ArrayList<ApplyTown>();
		for (int i=0;i<townlist.size();i++) {
			ApplyTown at = new ApplyTown();
			town t = townlist.get(i);
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
			returnlist.add(at);
		} 
		resobj.setStat(true);
		resobj.setTowns(returnlist);
		return resobj;
	}
	@Override
	public ResponsePutao getFavorite(int userid) {
		ResponsePutao resobj = new ResponsePutao();
		users u = user.get(userid);
		//整理收藏的putao
		List<PackagePutao> putaolist = new ArrayList<PackagePutao>();
		List<putao> putaos = new ArrayList<putao>(u.getFavorite());
		for (int i=0;i<putaos.size();i++) {
			PackagePutao pt = new PackagePutao();
			town t = putaos.get(i).getParenttown();
			pt.setTownid(t.getTownid());
			pt.setPutaoid(putaos.get(i).getPutaoid());
			pt.setTitle(putaos.get(i).getTitle());
			pt.setCover(putaos.get(i).getCover());
			pt.setContent(putaos.get(i).getContent());
			pt.setUsercover(t.getOwner().getCover());
			pt.setUsername(t.getOwner().getName());
			pt.setCreatetime(getFormatDate(putaos.get(i).getCreatetime()));
			pt.setUserid(t.getOwner().getUsersid());
			pt.setGoods(putaos.get(i).getGoods());
			//处理包含的图片
			List <String> imagenames = new ArrayList<String>();
			List<Image> is = putaos.get(i).getImages();
			for (int j=0;j<is.size();j++) {
				imagenames.add(is.get(j).getImagename());
			}
			pt.setImagenames(imagenames);
			putaolist.add(pt);
		}
		resobj.setStat(true);
		resobj.setPutao(putaolist);
		return resobj;
	}
	@Override
	public ResponseSimple cwall(int userid,String wall) {
		ResponseSimple resobj = new ResponseSimple();
		resobj.setStat(true);
		users u = user.get(userid);
		u.setWallimage(wall);
		user.update(u);
		return resobj;
	}
	@Override
	public ResponseSimple cuserinfo(int userid, String cover, String username,
			String location, String sex) {
		LogUtil.v("Start to change info: "+cover+" "+username+" "+location+" "+sex);
		ResponseSimple res = new ResponseSimple();
		res.setStat(true);
		try{
			users u = user.get(userid);
			if (cover != null && cover.length()>0)
				u.setCover(cover);
			if (username != null && username.length() > 0)
				u.setName(username);
			if (location !=null && location.length() > 0)
				if (u.getLogintype() == 0)
					u.getWeibo().setLocation(location);
				else
					u.getQqinfo().setCity(location);
			if (sex != null && sex.length()>0)
				if (u.getLogintype() ==0)
					u.getWeibo().setGender(sex);
				else
					u.getQqinfo().setGender(sex);
			user.update(u);
		}catch(Exception e) {
			res.setStat(false);
			e.printStackTrace();
		}
		
		return res;
	}
	
}