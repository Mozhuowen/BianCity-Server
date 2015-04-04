package service.Impl;

import java.util.Set;

import dao.putaoDao;
import dao.usersDao;
import domain.putao;
import domain.users;
import service.favoriteService;
import tools.objects.ModelFavorite;
import tools.objects.ResponseFavori;

public class favoriteServiceImpl implements favoriteService
{
	private usersDao user;
	private putaoDao putaox;
	
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

	@Override
	public ResponseFavori getFavorite(int userid, int putaoid) {
		ResponseFavori resobj = new ResponseFavori();
		ModelFavorite mf = new ModelFavorite();
		if (user.checkFavorite(userid, putaoid))
			mf.setDofavori(true);
		else
			mf.setDofavori(false);
		resobj.setFavori(mf);
		resobj.setStat(true);
		return resobj;
	}

	@Override
	public ResponseFavori doFavorite(int userid, int putaoid,int action) {
		ResponseFavori resobj = new ResponseFavori();
		ModelFavorite mf = new ModelFavorite();
		resobj.setStat(true);
		users u = user.get(userid);
		putao p = putaox.get(putaoid);
		Set<users> set = p.getFavoriteusers();
		if (action == 0) {	//yes
			set.add(u);
			mf.setDofavori(true);
		} else if(action == 1) {	//no
			set.remove(u);
			mf.setDofavori(false);
		}
		p.setFavoriteusers(set);
		putaox.update(p);
		resobj.setFavori(mf);
		return resobj;
	}
	
}