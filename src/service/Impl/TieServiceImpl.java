package service.Impl;

import java.util.Calendar;
import java.util.List;

import push.TiePushRunnable;
import dao.TieDao;
import dao.TieThemeDao;
import dao.townDao;
import dao.usersDao;
import domain.Image;
import domain.Tie;
import domain.TieTheme;
import domain.town;
import domain.users;
import service.TieService;
import service.usersService;
import tools.NetErrorUtil;
import tools.objects.ResponseSimple;
import tools.objects.community.ModelTieTheme;

public class TieServiceImpl implements TieService
{
	private usersDao user;
	private TieDao tie;
	private TieThemeDao tieth;
	private townDao townx;
	private usersService userservice;

	@Override
	public ResponseSimple submitTie(int townid, int tiethid, int userid,
			String content, List<Image> images) {
		ResponseSimple res = new ResponseSimple();
		boolean isJoinBBS = false;
		if (userservice.checkUserIsTownOwner(townid, userid))
			isJoinBBS = true;
		if (user.checkIfJoinCommunity(townid, userid))
			isJoinBBS = true;
		
		
		if (!isJoinBBS) {
			res.setStat(false);
			res.setErrcode(NetErrorUtil.NOTJOIN_COMMUNITY);
		} else {
			town t = townx.get(townid);
			users u = user.get(userid);
			TieTheme tiet = tieth.get(tiethid);
			Tie ti = new Tie();
			ti.setContent(content);
			ti.setImages(images);
			ti.setImagecou(images.size());
			for (int i=0;i<images.size();i++) {
				images.get(i).setTie(ti);
			}
			ti.setUser(u);
			ti.setParentown(t);
			ti.setTieth(tiet);
			ti.setTime(Calendar.getInstance());
			int count = tiet.getCommentcou();
			tiet.setCommentcou(++count);
			count = t.getTiecount();
			t.setTiecount(++count);
			if (tie.save(ti) > 0) {
				res.setStat(true);
				//推送消息
				if (tiet.getUser().getUsersid() != userid)
				new Thread(new TiePushRunnable(new ModelTieTheme(tiet),null,0,0,tiet.getUser().getUsersid(),userservice.getLoginDevice(tiet.getUser().getUsersid()),t.getOwner().getUsersid())).start();
			} else {
				res.setStat(false);
				res.setErrcode(NetErrorUtil.SERVER_ERROR);
			}
		}
		
		return res;
	}

	public usersDao getUser() {
		return user;
	}

	public void setUser(usersDao user) {
		this.user = user;
	}

	public TieDao getTie() {
		return tie;
	}

	public void setTie(TieDao tie) {
		this.tie = tie;
	}

	public townDao getTownx() {
		return townx;
	}

	public void setTownx(townDao townx) {
		this.townx = townx;
	}

	public TieThemeDao getTieth() {
		return tieth;
	}

	public void setTieth(TieThemeDao tieth) {
		this.tieth = tieth;
	}

	public usersService getUserservice() {
		return userservice;
	}

	public void setUserservice(usersService userservice) {
		this.userservice = userservice;
	}
	
}