package action;

import service.CommunityService;
import tools.LogUtil;
import tools.objects.community.ResCommunityTie;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

public class getcommunitytie extends BaseAction implements Action
{
	private CommunityService community;
	private String ptoken;
	private int ptuserid;
	private int zhulouid;
	private int position;
	public String jsonstr;

	@Override
	public String execute() throws Exception {
		ResCommunityTie res = community.getCommunityTie(zhulouid,position);
		jsonstr = new Gson().toJson(res);
		LogUtil.v(this, "getcommunitytie json result: "+jsonstr);
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

	public int getZhulouid() {
		return zhulouid;
	}

	public void setZhulouid(int zhulouid) {
		this.zhulouid = zhulouid;
	}

	public String getJsonstr() {
		return jsonstr;
	}

	public void setJsonstr(String jsonstr) {
		this.jsonstr = jsonstr;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
}