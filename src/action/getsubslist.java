package action;

import service.usersService;
import tools.objects.ResponseSubscri;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

public class getsubslist extends BaseAction implements Action
{
	private usersService user;
	private String ptoken;
	private int ptuserid;
	
	public String jsonstr;

	@Override
	public boolean needInterceptCheck() {
		return false;
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
		ResponseSubscri resobj = user.getSubscris(ptuserid);
		jsonstr = new Gson().toJson(resobj);
		return SUCCESS;
	}
	
}