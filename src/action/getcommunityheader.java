package action;

import service.CommunityService;
import tools.LogUtil;
import tools.objects.community.ResCommunityHeader;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

public class getcommunityheader extends BaseAction implements Action
{
	private CommunityService community;
	private String ptoken;
	private int ptuserid;
	private int communityid;
	public String jsonstr;

	@Override
	public String execute() throws Exception {
		ResCommunityHeader res = community.getCommunityHeader(communityid, ptuserid);
		jsonstr = new Gson().toJson(res);
		LogUtil.v(this, "getcoummunity response: "+jsonstr);
		return SUCCESS;
	}

	public CommunityService getCommunity() {
		return community;
	}

	public void setCommunity(CommunityService community) {
		this.community = community;
	}

	public String getPtoken() {
		return ptoken;
	}

	public void setPtoken(String ptoken) {
		this.ptoken = ptoken;
	}

	public int getPtuserid() {
		return ptuserid;
	}

	public void setPtuserid(int ptuserid) {
		this.ptuserid = ptuserid;
	}

	public int getCommunityid() {
		return communityid;
	}

	public void setCommunityid(int communityid) {
		this.communityid = communityid;
	}	
}