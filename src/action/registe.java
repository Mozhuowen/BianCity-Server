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
		LogUtil.v("registeQQinfo: "+registqqInfo.getOpenid());
		ResponseRegiste resobj = null;
		if (logintype == 0)
			resobj = user.regByWb(registInfo, ptuserid);
		else if (logintype == 1)
			resobj = user.regByQQ(registqqInfo, ptuserid);
		if (resobj != null)
			this.jsonstr = new Gson().toJson(resobj);
		return SUCCESS;

	}
	
}