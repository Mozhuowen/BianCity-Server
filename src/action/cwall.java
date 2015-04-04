package action;

import service.usersService;
import tools.objects.ResponseSimple;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

public class cwall extends BaseAction implements Action
{
	private usersService user;
	private String ptoken;
	private int ptuserid;
	private String wallimage;
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
	public String getWallimage() {
		return wallimage;
	}
	public void setWallimage(String wallimage) {
		this.wallimage = wallimage;
	}

	@Override
	public String execute() throws Exception {
		ResponseSimple res = user.cwall(ptuserid, wallimage);
		jsonstr = new Gson().toJson(res);
		return SUCCESS;
	}
	
}