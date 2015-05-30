package action;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import service.usersService;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

import tools.CharacterUtil;
import tools.LogUtil;
import tools.objects.ModelRegisteQQ;
import tools.objects.ModelRegisteWb;
import tools.objects.ResponseRegiste;


public class registe implements Action
{
	private usersService user;	
	//http param
	public int ptuserid;
	public String ptoken;
	public int logintype;
	public ModelRegisteWb registInfo;	
	public ModelRegisteQQ registqqInfo;
	public String username;		//用户名
	public String password;		//密码
	private String imei;
	private String sv;
	private String phonemodel;
	private String brand;
	//VIEW
	public String jsonstr;
	public void setRegistqqInfo(ModelRegisteQQ m) {
		this.registqqInfo = m;
	}
	public ModelRegisteQQ getRegistqqInfo(){
		return this.registqqInfo;
	}
	public usersService getUser() {
		return user;
	}
	public void setUser(usersService user) {
		this.user = user;
	}
	public int getPtuserid() {
		return ptuserid;
	}
	public void setPtuserid(int ptuserid) {
		this.ptuserid = ptuserid;
	}
	public String getPtoken() {
		return ptoken;
	}
	public void setPtoken(String ptoken) {
		this.ptoken = ptoken;
	}
	public int getLogintype() {
		return logintype;
	}
	public void setLogintype(int logintype) {
		this.logintype = logintype;
	}
	public ModelRegisteWb getRegistInfo() {
		return registInfo;
	}
	public void setRegistInfo(ModelRegisteWb registInfo) {
		this.registInfo = registInfo;
	}
	public String getJsonstr() {
		return jsonstr;
	}
	public void setJsonstr(String jsonstr) {
		this.jsonstr = jsonstr;
	}
	
	@Override
	public String execute() throws Exception {
		if (registqqInfo != null ) {
			LogUtil.v("registeQQinfo: "+registqqInfo.getOpenid());
		}
		ResponseRegiste resobj = null;
		if (logintype == 0)
			resobj = user.regByWb(registInfo, ptuserid);
		else if (logintype == 1)
			resobj = user.regByQQ(registqqInfo, ptuserid);
		else if (logintype ==2)
			resobj = user.regByBian(username, password, imei, sv, phonemodel, brand);
		if (resobj != null)
			this.jsonstr = new Gson().toJson(resobj);
		return SUCCESS;

	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	
}