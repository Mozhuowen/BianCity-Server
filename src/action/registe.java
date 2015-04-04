package action;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import service.usersService;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

import tools.CharacterUtil;
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
	//VIEW
	public String jsonstr;
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
		/*System.out.println("registe info: username: "+name+" email: "+email+"\n"+"auth: "+auth);
		ResponseRegiste resobj = user.registe(name, password, email, cover,imei,sv,phonemodel,brand);
		Gson gson = new Gson();
		this.jsonstr = gson.toJson(resobj);*/
		ResponseRegiste resobj = user.regByWb(registInfo, ptuserid);
		this.jsonstr = new Gson().toJson(resobj);
		return SUCCESS;

	}
	
}