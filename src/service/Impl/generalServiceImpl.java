package service.Impl;

import dao.MessBoardDao;
import dao.commentDao;
import dao.putaoDao;
import dao.townDao;
import dao.usersDao;
import domain.putao;
import domain.town;
import domain.users;
import service.generalService;

public class generalServiceImpl implements generalService
{
	private townDao townx;
	private usersDao userx;
	private putaoDao putaox;
	private commentDao commentx;
	private MessBoardDao mess;

	@Override
	public boolean setInvisible(int type,int ptuserid, int id) {
		users u = userx.get(ptuserid);
		switch(type) {
		case 0:	//town
			if (!userx.checkOwnTown(ptuserid, id))
				return false;
			else {
				town t = townx.get(id);
				t.setExist(1);
				townx.update(t);
			}
			break;
		case 1:
			if (!userx.checkOwnPutao(ptuserid, id))
				return false;
			else {
				putao p = putaox.get(id);
				p.setVisible(1);
				putaox.update(p);
			}
			break;
		case 2:
			if (!userx.checkOwnComment(ptuserid, id)) {
				return false;
			} else {
				
			}
			break;
		case 3:
			if (!userx.checkOwnMess(ptuserid, id)) {
				return false;
			} else {
				
			}
			break;
		default:
			return false;
		}
		return true;
	}

	public townDao getTownx() {
		return townx;
	}

	public void setTownx(townDao townx) {
		this.townx = townx;
	}

	public usersDao getUserx() {
		return userx;
	}

	public void setUserx(usersDao userx) {
		this.userx = userx;
	}

	public putaoDao getPutaox() {
		return putaox;
	}

	public void setPutaox(putaoDao putaox) {
		this.putaox = putaox;
	}

	public commentDao getCommentx() {
		return commentx;
	}

	public void setCommentx(commentDao commentx) {
		this.commentx = commentx;
	}

	public MessBoardDao getMess() {
		return mess;
	}

	public void setMess(MessBoardDao mess) {
		this.mess = mess;
	}
	
}