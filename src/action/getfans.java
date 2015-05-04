package action;

import service.usersService;
import tools.objects.ModelUser;
import tools.objects.ResponseUser;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

public class getfans extends BaseAction implements Action
{
	private usersService user;
	private String ptoken;
	private int ptuserid;
	private int userid;	//目标用户的ptuserid
	public String jsonstr;

	@Override
	public boolean needInterceptCheck() {
		return true;
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
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	@Override
	public String execute() throws Exception {
		ResponseUser res = new ResponseUser();
		res.setStat(true);
		ModelUser obj = new ModelUser();
		obj.setUserid(userid);
		obj.setFans(user.getFans(userid));
		res.setUser(obj);
		jsonstr = new Gson().toJson(res);
		return SUCCESS;
	}
	
}