package service.Impl;

import java.util.Calendar;
import java.util.List;

import dao.TieThemeDao;
import dao.townDao;
import dao.usersDao;
import domain.Image;
import domain.TieTheme;
import domain.town;
import domain.users;
import service.TieThemeService;
import tools.NetErrorUtil;
import tools.objects.ResponseSimple;

public class TieThemeServiceImpl implements TieThemeService
{
	private usersDao user;
	private TieThemeDao tieth;
	private townDao townx;
	@Override
	public ResponseSimple createNew(int townid, int userid, String title,
			String content, List<Image> images) {
		ResponseSimple res = new ResponseSimple();
		if (!user.checkIfJoinCommunity(townid, userid)) {
			res.setStat(false);
			res.setErrcode(NetErrorUtil.NOTJOIN_COMMUNITY);
		} else {
			town t = townx.get(townid);
			users u = user.get(userid);
			TieTheme tie = new TieTheme();
			tie.setTitle(title);
			tie.setContent(content);
			tie.setTime(Calendar.getInstance());
			tie.setLastretime(Calendar.getInstance());
			tie.setImagecou(images.size());
			tie.setImages(images);
			for (int i=0;i<images.size();i++) {
				images.get(i).setTiet(tie);
			}
			tie.setUser(u);
			tie.setParentown(t);
			int count = t.getTiecount();
			t.setTiecount(++count);
			if (tieth.save(tie) > 0) {
				res.setStat(true);
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
	public TieThemeDao getTieth() {
		return tieth;
	}
	public void setTieth(TieThemeDao tieth) {
		this.tieth = tieth;
	}
	public townDao getTownx() {
		return townx;
	}
	public void setTownx(townDao town) {
		this.townx = town;
	}
	
}