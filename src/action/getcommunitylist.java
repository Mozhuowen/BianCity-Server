package action;

import service.usersService;
import tools.objects.community.ResCommunity;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

public class getcommunitylist extends BaseAction implements Action
{
	private usersService userservice;
	private String ptoken;
	private int ptuserid;
	public String jsonstr;

	@Override
	public String execute() throws Exception {
		ResCommunity res = userservice.getJoinCommunity(ptuserid);
		jsonstr = new Gson().toJson(res);
		return SUCCESS;
	}

	@Override
	public boolean needInterceptCheck() {
		return true;
	}
	public usersService getUserservice() {
		return userservice;
	}

	public void setUserservice(usersService userservice) {
		this.userservice = userservice;
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
	
}