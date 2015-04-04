package service.Impl;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dao.MessBoardDao;
import dao.townDao;
import dao.usersDao;
import domain.GeoInfo;
import domain.MessBoard;
import domain.comment;
import domain.town;
import domain.users;
import service.messService;
import tools.NetErrorUtil;
import tools.objects.ModelMessboard;
import tools.objects.PackageComment;
import tools.objects.ResponseMess;

public class messServiceImpl implements messService
{
	private townDao townx;
	private MessBoardDao mess;
	private usersDao user;
	public townDao getTownx() {
		return townx;
	}
	public void setTownx(townDao townx) {
		this.townx = townx;
	}
	public MessBoardDao getMess() {
		return mess;
	}
	public void setMess(MessBoardDao mess) {
		this.mess = mess;
	}
	public usersDao getUser() {
		return user;
	}
	public void setUser(usersDao user) {
		this.user = user;
	}

	@Override
	public ResponseMess submitMess(int townid, int userid, String content,
			GeoInfo geo) {
		ResponseMess resobj = new ResponseMess();
		town t = townx.get(townid);
		users u = user.get(userid);
		MessBoard mb = new MessBoard();
		mb.setTownx(t);
		mb.setUser(u);
		mb.setGeoinfo(geo);
		mb.setContent(content);
		mb.setTime(Calendar.getInstance());
		if (mess.save(mb) > 0) {
			resobj.setStat(true);
			List<ModelMessboard> messlist = new ArrayList<ModelMessboard>();
			List<MessBoard> list = mess.loadMoreMess(t, 0);
			for (int i=0;i<list.size();i++) {
				ModelMessboard m = new ModelMessboard();
				users us = list.get(i).getUser();
				MessBoard msb = list.get(i);
				m.setContent(msb.getContent());
				m.setCover(us.getCover());
				m.setUsername(us.getName());
				m.setMessid(msb.getMessboardid());
				m.setTime(getFormatDate(msb.getTime()));
				m.setGoods(msb.getGoods());
				m.setUserid(us.getUsersid());
				if (user.checkdoGood(3, userid, msb.getMessboardid()))
					m.setDogood(true);
				else
					m.setDogood(false);
				messlist.add(m);
			}
			resobj.setMess(messlist);
		} else {
			resobj.setStat(false);
			resobj.setErrcode(NetErrorUtil.SERVER_ERROR);
		}
		return resobj;
	}

	@Override
	public ResponseMess getMess(int townid) {
		ResponseMess resobj = new ResponseMess();
		town t = townx.get(townid);
		if ( t != null ){
			resobj.setStat(true);
			List<ModelMessboard> messlist = new ArrayList<ModelMessboard>();
			List<MessBoard> mess = new ArrayList(t.getMess());
			for (int i=0;i<mess.size();i++) {
				ModelMessboard mmess = new ModelMessboard();
				MessBoard m = mess.get(i);
				users us = m.getUser();
				mmess.setPtuserid(us.getUsersid());
				mmess.setUsername(us.getName());
				mmess.setCover(us.getCover());
				mmess.setContent(m.getContent());
				mmess.setTime(getFormatDate(m.getTime()));
				
				messlist.add(mmess);
			}
			resobj.setMess(messlist);
		} else {
			resobj.setStat(false);
			resobj.setErrcode(NetErrorUtil.SERVER_ERROR);
		}
		return resobj;
	}

	@Override
	public ResponseMess loadMoreMess(int townid, int position,int userid) {
		ResponseMess resobj = new ResponseMess();
		town t = townx.get(townid);
		resobj.setStat(true);
		List<ModelMessboard> messlist = new ArrayList<ModelMessboard>();
		List<MessBoard> list = mess.loadMoreMess(t, position);
		for (int i=0;i<list.size();i++) {
			ModelMessboard m = new ModelMessboard();
			users us = list.get(i).getUser();
			MessBoard msb = list.get(i);
			m.setContent(msb.getContent());
			m.setCover(us.getCover());
			m.setUsername(us.getName());
			m.setMessid(msb.getMessboardid());
			m.setTime(getFormatDate(msb.getTime()));
			m.setGoods(msb.getGoods());
			m.setUserid(us.getUsersid());
			if (user.checkdoGood(3, userid, msb.getMessboardid()))
				m.setDogood(true);
			else
				m.setDogood(false);
			messlist.add(m);
		}
		resobj.setMess(messlist);
		
		return resobj;
	}
	public String getFormatDate(Calendar time) {
		String datestr = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM).format(time.getTime());
		return datestr;
	}
}