package action;

import service.usersService;
import tools.objects.ResponseUser;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

public class getuserinfo extends BaseAction implements Action
{
	private usersService user;
	private String ptoken;
	private int ptuserid;
	private int userid;
	public String jsonstr;
	private boolean onlystatis = true;	//是否只获取统计数据
	public void setOnlystatis(boolean o) {
		this.onlystatis = o;
	}
	public boolean getOnlystatis() {
		return this.onlystatis;
	}
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
		ResponseUser resobj = user.getUserInfo(userid,onlystatis);
		jsonstr = new Gson().toJson(resobj);
		return SUCCESS;
	}
	
}