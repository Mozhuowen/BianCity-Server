package action;

import service.messService;
import tools.LogUtil;
import tools.objects.ResponseMess;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

import domain.GeoInfo;

public class getmess extends BaseAction implements Action
{
	private messService mess;
	private String ptoken;
	private int ptuserid;
	private int townid;
	private String content;
	private String username;
	private String cover;
	private String time;
	private int goods;
	private boolean dogood;
	private int messid;
	private int messposition;
	private GeoInfo geo;
	public String jsonstr;
	
	@Override
	public boolean needInterceptCheck() {
		return false;
	}
	public messService getMess() {
		return mess;
	}

	public void setMess(messService mess) {
		this.mess = mess;
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

	public int getTownid() {
		return townid;
	}

	public void setTownid(int townid) {
		this.townid = townid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getGoods() {
		return goods;
	}

	public void setGoods(int goods) {
		this.goods = goods;
	}

	public boolean isDogood() {
		return dogood;
	}

	public void setDogood(boolean dogood) {
		this.dogood = dogood;
	}

	public int getMessid() {
		return messid;
	}

	public void setMessid(int messid) {
		this.messid = messid;
	}

	public int getMessposition() {
		return messposition;
	}

	public void setMessposition(int messposition) {
		this.messposition = messposition;
	}

	public GeoInfo getGeo() {
		return geo;
	}

	public void setGeo(GeoInfo geo) {
		this.geo = geo;
	}

	@Override
	public String execute() throws Exception {
		ResponseMess resobj = mess.loadMoreMess(townid, messposition,ptuserid);
		jsonstr = new Gson().toJson(resobj);
		LogUtil.v("getmess return info: "+jsonstr);
		return SUCCESS;
	}
	
}