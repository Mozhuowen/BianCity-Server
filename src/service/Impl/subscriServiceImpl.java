package service.Impl;

import java.util.Set;

import dao.townDao;
import dao.usersDao;
import domain.town;
import domain.users;
import service.subscriService;
import tools.objects.ModelSubscriTown;
import tools.objects.ResponseSubscri;

public class subscriServiceImpl implements subscriService
{
	private usersDao user;
	private townDao townx;
	
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

	@Override
	public ResponseSubscri getSubscri(int userid, int townid) {
		ResponseSubscri resobj = new ResponseSubscri();
		ModelSubscriTown subscri = new ModelSubscriTown();
		resobj.setStat(true);
		if (user.checkSubscirTown(userid, townid))
			subscri.setDosubscri(true);
		else
			subscri.setDosubscri(false);
		resobj.setSubscri(subscri);
		return resobj;
	}

	@Override
	public ResponseSubscri doSubscri(int userid, int townid, int action) {
		ResponseSubscri resobj = new ResponseSubscri();
		ModelSubscriTown subscri = new ModelSubscriTown();
		resobj.setStat(true);
		users u = user.get(userid);
		town t = townx.get(townid);
		Set<users> set = t.getSubscriusers();
		if(action == 0) {
			int tmp = t.getSubscris();
			t.setSubscris(++tmp);
			set.add(u);
			subscri.setDosubscri(true);
			int f = u.getFans();
			u.setFans(++f);
		} else if (action==1) {
			int tmp = t.getSubscris();
			t.setSubscris(--tmp);
			set.remove(u);
			subscri.setDosubscri(false);
			int f = u.getFans();
			u.setFans(--f);
		}
		t.setSubscriusers(set);
		townx.update(t);
		user.update(u);
		resobj.setSubscri(subscri);
		return resobj;
	}
	
}