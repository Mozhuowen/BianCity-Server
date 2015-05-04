package action;

import service.messService;
import tools.LogUtil;
import tools.objects.ResponseMess;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

import domain.GeoInfo;

public class submitmess extends BaseAction implements Action
{
	private messService mess;
	private String ptoken;
	private int ptuserid;
	private int townid;
	private String content;
	private int messposition;
	private GeoInfo geo;
	
	public String jsonstr;

	@Override
	public boolean needInterceptCheck() {
		return true;
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
		LogUtil.v("townid: "+townid+" ptuserid: "+ptuserid+" content: "+content);
		ResponseMess resobj = mess.submitMess(townid, ptuserid, content, geo);
		jsonstr = new Gson().toJson(resobj);
		LogUtil.v("submitmess Response info: "+jsonstr);
		return SUCCESS;
	}
	
}