package service.Impl;

import java.util.List;

import dao.TieThemeDao;
import dao.townDao;
import dao.usersDao;
import domain.TieTheme;
import domain.town;
import domain.users;
import service.CommunityService;
import tools.NetErrorUtil;
import tools.objects.ResponseSimple;
import tools.objects.community.ModelCommuHeader;
import tools.objects.community.ResCommunityHeader;
import tools.objects.community.ResCommunityTieTh;

public class CommunityServiceImpl implements CommunityService
{
	private townDao townx;
	private usersDao user;
	private TieThemeDao tieth;
	
	@Override
	public ResCommunityHeader getCommunityHeader(int townid,int userid) {
		ResCommunityHeader res = new ResCommunityHeader();
		ModelCommuHeader header = new ModelCommuHeader();
		try{
			town t = townx.get(townid);
			header.setCommunityid(t.getTownid());
			header.setCommunityname(t.getName());
			header.setCover(t.getCover());
			header.setMemberscount(t.getMembercount());
			header.setTiecount(t.getTiecount());
			header.setCreatetime(t.getCreatetime().getTimeInMillis());
			header.setHasjoin(user.checkIfJoinCommunity(townid, userid));
			header.setTops(tieth.getTopTie(t));
			res.setStat(true);
			res.setHeader(header);
		}catch (Exception e ) {
			e.printStackTrace();
			res.setStat(false);
			res.setErrcode(NetErrorUtil.SERVER_ERROR);
		}
		
		return res;
	}

	@Override
	public ResCommunityTieTh getCommunityTieTh(int townid) {
		ResCommunityTieTh res = new ResCommunityTieTh();
		try {
			res.setTies(tieth.getTies(townx.get(townid)));
			res.setStat(true);		
		} catch (Exception e ) {
			e.printStackTrace();
			res.setStat(false);
			res.setErrcode(NetErrorUtil.SERVER_ERROR);
		}
		return res;
	}
	
	@Override
	public ResponseSimple joinCommunity(int townid, int userid) {
		ResponseSimple res = new ResponseSimple();
		try{
			town t = townx.get(townid);
			users u = user.get(userid);
			u.setJoincommunity(t);
//			user.update(u);
			res.setStat(true);
		} catch (Exception e) {
			res.setStat(false);
			res.setErrcode(NetErrorUtil.SERVER_ERROR);
			e.printStackTrace();
		}
		
		return res;
	}

	public townDao getTownx() {
		return townx;
	}

	public void setTownx(townDao townx) {
		this.townx = townx;
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

	
}