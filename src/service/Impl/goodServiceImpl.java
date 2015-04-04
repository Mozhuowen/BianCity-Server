package service.Impl;

import java.util.Set;

import org.apache.catalina.User;

import dao.MessBoardDao;
import dao.commentDao;
import dao.putaoDao;
import dao.townDao;
import dao.usersDao;
import domain.MessBoard;
import domain.comment;
import domain.putao;
import domain.town;
import domain.users;
import service.goodService;
import tools.objects.ModelGood;
import tools.objects.ResponseGood;

public class goodServiceImpl implements goodService
{
	private townDao townx;
	private putaoDao putaox;
	private commentDao commentx;
	private MessBoardDao mess;
	private usersDao user;
	public void setUser(usersDao u) {
		this.user = u;
	}
	public usersDao getUser(){
		return this.user;
	}
	public void setPutaox(putaoDao p) {
		this.putaox = p;
	}
	public putaoDao getPutaox(){
		return this.putaox;
	}
	public townDao getTownx() {
		return townx;
	}
	public void setTownx(townDao townx) {
		this.townx = townx;
	}
	public commentDao getCommentx() {
		return commentx;
	}
	public void setCommentx(commentDao comment) {
		this.commentx = comment;
	}

	public MessBoardDao getMess() {
		return mess;
	}
	public void setMess(MessBoardDao mess) {
		this.mess = mess;
	}

	@Override
	public ResponseGood getGoods(int type,int userid, int id) {
		ResponseGood resobj = new ResponseGood();
		resobj.setStat(true);
		ModelGood mg = new ModelGood();
		
		int goods = 0;
		try {
			switch(type) {
			case 0:
				goods = townx.getGoods(id);		
				break;
			case 1:
				goods = putaox.getgoods(id);		
				break;
			case 2:
				goods = commentx.getGoods(id);				
				break;
			case 3:
				goods = mess.getGoods(id);
				break;
			}
		}catch(Exception e) {
			e.printStackTrace();
			resobj.setStat(false);
		}
		if (user.checkdoGood(type, userid,id))
			mg.setDogood(true);
		else
			mg.setDogood(false);
		mg.setGoods(goods);
		resobj.setGood(mg);
		
		return resobj;
	}

	@Override
	public ResponseGood doGood(int type,int userid,int id,int action) {
		ResponseGood resobj = new ResponseGood();
		resobj.setStat(true);
		ModelGood mg = new ModelGood();
		users u = user.get(userid);
		
		int goods = 0;
		try {
			switch(type) {
			case 0:				//do good for town
				town t = (town)townx.get(id);
				Set<users> set = t.getDogoodusers();
				if (action==0){
					set.add(u);				
					int tmp = t.getGoods();
					t.setGoods(++tmp);
					mg.setDogood(true);
				} else {
					set.remove(u);				
					int tmp = t.getGoods();
					t.setGoods(--tmp);
					mg.setDogood(false);
				}
				t.setDogoodusers(set);
				townx.update(t);
				goods = t.getGoods();
				break;
			case 1:			//do good for putao
				putao p = (putao)putaox.get(id);
				Set<users> setp = p.getDogoodusers();
				if (action==0) {
					setp.add(u);
					int tmp = p.getGoods();
					p.setGoods(++tmp);
					mg.setDogood(true);
				} else {
					setp.remove(u);
					int tmp = p.getGoods();
					p.setGoods(--tmp);
					mg.setDogood(false);
				}
				p.setDogoodusers(setp);
				putaox.update(p);
				goods = p.getGoods();
				break;
			case 2:		//do good for comment
				comment c = (comment)commentx.get(id);
				Set<users> setc = c.getDogoodusers();
				if (action==0) {
					setc.add(u);
					int tmp = c.getGoods();
					c.setGoods(++tmp);
					mg.setDogood(true);
				} else {
					setc.remove(u);
					int tmp = c.getGoods();
					c.setGoods(--tmp);
					mg.setDogood(false);
				}
				c.setDogoodusers(setc);
				commentx.update(c);
				goods = c.getGoods();
				break;
			case 3:		//do good for mess
				MessBoard m = (MessBoard)mess.get(id);
				Set<users> setm = m.getDogoodusers();
				if (action==0) {
					setm.add(u);
					int tmp = m.getGoods();
					m.setGoods(++tmp);
					mg.setDogood(true);
				} else {
					setm.remove(u);
					int tmp = m.getGoods();
					m.setGoods(--tmp);
					mg.setDogood(false);
				}
				m.setDogoodusers(setm);
				mess.update(m);
				goods = m.getGoods();
				break;
			}
		}catch(Exception e) {
			e.printStackTrace();
			resobj.setStat(false);
		}
		mg.setGoods(goods);
		resobj.setGood(mg);
		return resobj;
	}
	
}