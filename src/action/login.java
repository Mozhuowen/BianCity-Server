package action;

import java.util.Calendar;
import java.util.Date;

import service.usersService;
import tools.LogUtil;
import tools.objects.ResponseLogin;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

public class login implements Action
{
	private usersService user;
	
	//http param
	/**登录类型，0微博 1QQ 2微信*/
	private int logintype;
	private int ptuserid;
	private String ptoken;
	private String uid;
	private String token;
	private long expire;
	private String imei;
	private String sv;
	private String phonemodel;
	private String brand;
	
	//VIEW
	public String jsonstr;

	public void setPtuserid(int p) {
		this.ptuserid = p;
	}
	public int getPtuserid(){
		return this.ptuserid;
	}
	public void setPtoken(String t) {
		this.ptoken = t;
	}
	public String getPtoken(){
		return this.ptoken;
	}
	public usersService getUser() {
		return user;
	}
	public void setUser(usersService user) {
		this.user = user;
	}
	public int getLogintype() {
		return logintype;
	}
	public void setLogintype(int logintype) {
		this.logintype = logintype;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public long getExpire() {
		return expire;
	}
	public void setExpire(long expire) {
		this.expire = expire;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getSv() {
		return sv;
	}
	public void setSv(String sv) {
		this.sv = sv;
	}
	public String getPhonemodel() {
		return phonemodel;
	}
	public void setPhonemodel(String phonemodel) {
		this.phonemodel = phonemodel;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getJsonstr() {
		return jsonstr;
	}
	public void setJsonstr(String jsonstr) {
		this.jsonstr = jsonstr;
	}

	@Override
	public String execute() throws Exception {
		/*System.out.println("login info: username: "+name+"\n"+"auth: "+auth);
		ResponseLogin resobj = user.checklogin(name, password, imei, sv, phonemodel,brand);
		Gson gson = new Gson();
		this.jsonstr = gson.toJson(resobj);
		System.out.println("login Response info: "+jsonstr.toString());*/
		LogUtil.v("login info: uid: "+uid+" token: "+token+" expire: "+new Date(expire)+" logintype: "+logintype);
		ResponseLogin resobj = null;
		Calendar exp = Calendar.getInstance();
		exp.setTimeInMillis(expire);
		if (logintype == 0)
			resobj = user.checkloginByWb(uid, token,exp, imei, sv, phonemodel, brand);
		else if (logintype == 1)
			resobj = user.checkloginByQQ(uid, token, exp, imei, sv, phonemodel, brand);
		if (resobj != null)
			this.jsonstr = new Gson().toJson(resobj);
		return SUCCESS;
	}
	
}