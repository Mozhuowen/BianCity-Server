package action;

import service.usersService;
import tools.objects.ResponseCName;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

public class cname extends BaseAction implements Action
{
	private usersService user;
	private String ptoken;
	private int ptuserid;
	private String username;
	
	public String jsonstr;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public usersService getUser() {
		return user;
	}
	public void setUser(usersService user) {
		this.user = user;
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

	@Override
	public String execute() throws Exception {
		ResponseCName resobj = user.cname(ptuserid, username);
		this.jsonstr = new Gson().toJson(resobj);
		return SUCCESS;
	}
	
}