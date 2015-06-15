package action;

import service.CommunityService;
import tools.objects.ResponseSimple;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

public class totop extends BaseAction implements Action
{
	private CommunityService community;
	private String ptoken;
	private int ptuserid;
	private int zhulouid;
	public String jsonstr;

	@Override
	public String execute() throws Exception {
		ResponseSimple res = community.toTop(zhulouid, ptuserid);
		jsonstr = new Gson().toJson(res);
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
	
}