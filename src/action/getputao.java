package action;

import service.putaoService;
import tools.objects.ResponsePutao;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

public class getputao extends BaseAction implements Action
{
	private putaoService putao;
	private int townid;
	private int ptuserid;
	private String ptoken;
	
	public String jsonstr;
	private int position = 0;

	public void setPosition(int p) {
		this.position = p;
	}
	public int getPosition() {
		return this.position;
	}	
	public int getPtuserid() {
		return ptuserid;
	}

	public void setPtuserid(int userid) {
		this.ptuserid = userid;
	}
	public String getPtoken() {
		return ptoken;
	}
	public void setPtoken(String token) {
		this.ptoken = token;
	}
	@Override
	public boolean needInterceptCheck() {
		return false;
	}
	public putaoService getPutao() {
		return putao;
	}
	public void setPutao(putaoService putao) {
		this.putao = putao;
	}
	public int getTownid() {
		return townid;
	}
	public void setTownid(int townid) {
		this.townid = townid;
	}
	@Override
	public String execute() throws Exception {
		ResponsePutao resobj = putao.getPutao(townid,position);
		jsonstr = new Gson().toJson(resobj);
		System.out.println("getputao response info:"+jsonstr);
		return SUCCESS;
	}
	
}