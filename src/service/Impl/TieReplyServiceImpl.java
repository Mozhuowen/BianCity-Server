package service.Impl;

import java.util.Calendar;

import push.TiePushRunnable;
import dao.TieDao;
import dao.TieReplyDao;
import dao.TieThemeDao;
import dao.townDao;
import dao.usersDao;
import domain.Tie;
import domain.TieReply;
import domain.TieTheme;
import domain.town;
import domain.users;
import service.TieReplyService;
import service.usersService;
import tools.NetErrorUtil;
import tools.objects.community.ModelTie;
import tools.objects.community.ModelTieReply;
import tools.objects.community.ModelTieTheme;
import tools.objects.community.ResSubmiTieReply;

public class TieReplyServiceImpl implements TieReplyService
{
	private usersDao user;
	private TieDao tie;
	private townDao townx;
	private TieReplyDao tiereply;
	private usersService userservice;

	@Override
	public ResSubmiTieReply createNew(int parentie, int userid, int bereplyid,
			String content) {
		ResSubmiTieReply res = new ResSubmiTieReply();
		Tie ti = tie.get(parentie);
		town t = ti.getParentown();
		boolean isJoinBBS = false;
		if (userservice.checkUserIsTownOwner(t.getTownid(), userid))
			isJoinBBS = true;
		if (user.checkIfJoinCommunity(t.getTownid(), userid))
			isJoinBBS = true;
		
		if (!isJoinBBS) {
			res.setStat(false);
			res.setErrcode(NetErrorUtil.NOTJOIN_COMMUNITY);
		} else {
			users u = user.get(userid);
			TieTheme tieth = ti.getTieth();
			TieReply tr = new TieReply();
			tr.setParentown(t);
			tr.setContent(content);
			tr.setTie(ti);
			tr.setTime(Calendar.getInstance());
			tr.setUser(u);
			int recount = t.getTiecount();
			t.setTiecount(++recount);
			recount = tieth.getCommentcou();
			tieth.setCommentcou(++recount);
			if (tiereply.save(tr) > 0) {
				res.setStat(true);
				res.setTiereply(new ModelTieReply(tr));
				//推送消息
				if (ti.getUser().getUsersid() != userid)
				new Thread(new TiePushRunnable(null,new ModelTie(ti),1,0,ti.getUser().getUsersid(),userservice.getLoginDevice(ti.getUser().getUsersid()),t.getOwner().getUsersid())).start();
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

	public townDao getTownx() {
		return townx;
	}

	public void setTownx(townDao townx) {
		this.townx = townx;
	}

	public TieReplyDao getTiereply() {
		return tiereply;
	}

	public void setTiereply(TieReplyDao tiereply) {
		this.tiereply = tiereply;
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