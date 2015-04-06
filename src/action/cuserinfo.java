package action;

import service.usersService;
import tools.objects.ResponseSimple;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

public class cuserinfo extends BaseAction implements Action
{
	private usersService user;
	private String ptoken;
	private int ptuserid;
	private String name;
	private String location;
	private String sex;
	private String cover;
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
	public String getName() {
		return name;
	}
	public void setName(String username) {
		this.name = username;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}

	@Override
	public String execute() throws Exception {
		ResponseSimple res = user.cuserinfo(ptuserid, cover, name, location, sex);
		jsonstr = new Gson().toJson(res);
		
		return SUCCESS;
	}
	
}