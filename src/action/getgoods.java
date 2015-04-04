package action;

import service.goodService;
import tools.objects.ResponseGood;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

public class getgoods extends BaseAction implements Action
{
	private goodService good;
	/**被赞对象类型0-town 1-putao 2-comment 3-mess*/
	private int type;
	/**0-加赞 1-减赞*/
	private int action;
	public String jsonstr;
	private int targetid;
	private String ptoken;
	private int ptuserid;
	@Override
	public boolean needInterceptCheck() {
		return false;
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
	public void setTargetid(int t){
		this.targetid = t;
	}
	public int getTargetid(){
		return this.targetid;
	}
	public void setGood(goodService g) {
		this.good = g;
	}
	public goodService getGood(){
		return this.good;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}

	@Override
	public String execute() throws Exception {
		ResponseGood res = good.getGoods(type,ptuserid, targetid);
		jsonstr = new Gson().toJson(res);
		return SUCCESS;
	}
}