package action;

import service.CommunityService;
import tools.objects.ResponseSimple;
import tools.objects.community.ResJoinBBS;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

public class joincommunity extends BaseAction implements Action
{
	private CommunityService community;
	private String ptoken;
	private int ptuserid;
	private int communityid;
	public String jsonstr;

	@Override
	public String execute() throws Exception {
		ResJoinBBS res = community.joinCommunity(communityid, ptuserid);
		jsonstr = new Gson().toJson(res);
		return SUCCESS;
	}

	@Override
	public boolean needInterceptCheck() {
		return true;
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

	public String getJsonstr() {
		return jsonstr;
	}

	public void setJsonstr(String jsonstr) {
		this.jsonstr = jsonstr;
	}
	
}