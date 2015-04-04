package service.Impl;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dao.commentDao;
import dao.putaoDao;
import dao.townDao;
import dao.usersDao;
import domain.comment;
import domain.putao;
import domain.town;
import domain.users;
import service.commentService;
import tools.LogUtil;
import tools.NetErrorUtil;
import tools.objects.PackageComment;
import tools.objects.ResponseComment;

public class commentServiceImpl implements commentService
{
	//依赖注入
	private commentDao commentx;
	private usersDao user;
	private putaoDao putaox;
	private townDao townx;
	public commentDao getCommentx() {
		return commentx;
	}
	public void setCommentx(commentDao commentx) {
		this.commentx = commentx;
	}
	public usersDao getUser() {
		return user;
	}
	public void setUser(usersDao user) {
		this.user = user;
	}
	public putaoDao getPutaox() {
		return putaox;
	}
	public void setPutaox(putaoDao putaox) {
		this.putaox = putaox;
	}
	public townDao getTownx() {
		return townx;
	}

	public void setTownx(townDao townx) {
		this.townx = townx;
	}

	@Override
	public ResponseComment submitComment(int townid, int putaoid, int userid,
			String content) {
		ResponseComment res = new ResponseComment();
		town t = townx.get(townid);
		putao p = putaox.get(putaoid);
		users u = user.get(userid);
		comment c = new comment();
		c.setUser(u);
		c.setPutaox(p);
		c.setTownx(t);
		c.setContent(content);
		c.setTime(Calendar.getInstance());
		if (commentx.save(c)>0) {
			res.setStat(true);
			List<PackageComment> commentlist = new ArrayList<PackageComment>();
			List<comment> comments = commentx.loadMoreComments(putaox.get(putaoid), 0);
			for (int i=0;i<comments.size();i++) {
				PackageComment pc = new PackageComment();
				users us = comments.get(i).getUser();
				comment cm = comments.get(i);
				pc.setUserid(us.getUsersid());
				pc.setUsername(us.getName());
				pc.setCover(us.getCover());
				pc.setPutaoid(putaoid);
				pc.setCommentid(cm.getCommentid());
				pc.setContent(cm.getContent());
				pc.setTime(getFormatDate(cm.getTime()));
				pc.setGoods(cm.getGoods());
				if (user.checkdoGood(2, userid, cm.getCommentid()))
					pc.setDogood(true);
				else
					pc.setDogood(false);
				commentlist.add(pc);
			}
			res.setComments(commentlist);
		} else {
			res.setStat(false);
			res.setErrcode(NetErrorUtil.SERVER_ERROR);
		}
		return res;
	}
	public String getFormatDate(Calendar time) {
		String datestr = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM).format(time.getTime());
		return datestr;
	}
	@Override
	public ResponseComment getComment(int putaoid,int userid) {
		ResponseComment res = new ResponseComment();
		putao p = putaox.get(putaoid);
		if (p != null ) {
			res.setStat(true);
			List<PackageComment> commentlist = new ArrayList<PackageComment>();
			List<comment> comments = new ArrayList(p.getComments());
			for (int i=0;i<comments.size();i++) {
				PackageComment pc = new PackageComment();
				users us = comments.get(i).getUser();
				comment cm = comments.get(i);
				pc.setUserid(us.getUsersid());
				pc.setUsername(us.getName());
				pc.setCover(us.getCover());
				pc.setPutaoid(putaoid);
				pc.setCommentid(cm.getCommentid());
				pc.setContent(cm.getContent());
				pc.setTime(getFormatDate(cm.getTime()));
				pc.setGoods(cm.getGoods());
				if (user.checkdoGood(2, userid, cm.getCommentid()))
					pc.setDogood(true);
				else
					pc.setDogood(false);
				commentlist.add(pc);
			}
			res.setComments(commentlist);
		} else {
			res.setStat(false);
			res.setErrcode(NetErrorUtil.SERVER_ERROR);
		}
		return res;
	}
	@Override
	public ResponseComment loadMoreComments(int putaoid, int position,int userid) {
		ResponseComment res = new ResponseComment();
		if (putaox.checkPutao(putaoid)) {
			res.setStat(true);
			List<PackageComment> commentlist = new ArrayList<PackageComment>();
			List<comment> comments = commentx.loadMoreComments(putaox.get(putaoid), position);
			for (int i=0;i<comments.size();i++) {
				PackageComment pc = new PackageComment();
				users us = comments.get(i).getUser();
				comment cm = comments.get(i);
				pc.setUserid(us.getUsersid());
				pc.setUsername(us.getName());
				pc.setCover(us.getCover());
				pc.setPutaoid(putaoid);
				pc.setCommentid(cm.getCommentid());
				pc.setContent(cm.getContent());
				pc.setTime(getFormatDate(cm.getTime()));
				pc.setGoods(cm.getGoods());
				LogUtil.v("commentServiceImpl info: userid:"+userid+"commentid:"+cm.getCommentid());
				if (user.checkdoGood(2, userid, cm.getCommentid()))
					pc.setDogood(true);					
				else
					pc.setDogood(false);
				commentlist.add(pc);
			}
			res.setComments(commentlist);
		} else {
			res.setStat(false);
			res.setErrcode(NetErrorUtil.SERVER_ERROR);
		}
		return res;
	}
}