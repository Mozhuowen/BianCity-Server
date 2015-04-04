package action;

import service.subscriService;
import tools.objects.ResponseSubscri;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

public class getsubscri extends BaseAction implements Action
{
	private subscriService subscri;
	private int townid;
	/**订阅动作 0-订阅 1-取消订阅*/
	private int action;
	private String ptoken;
	private int ptuserid;
	
	public String jsonstr;

	@Override
	public boolean needInterceptCheck() {
		return false;
	}
	public subscriService getSubscri() {
		return subscri;
	}
	public void setSubscri(subscriService subscri) {
		this.subscri = subscri;
	}
	public int getTownid() {
		return townid;
	}
	public void setTownid(int townid) {
		this.townid = townid;
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
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

	@Override
	public String execute() throws Exception {
		ResponseSubscri resobj =  subscri.getSubscri(ptuserid, townid);
		jsonstr = new Gson().toJson(resobj);
		return SUCCESS;
	}
	
}