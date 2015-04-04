package action;

import java.util.List;

import service.townService;
import tools.LogUtil;
import tools.objects.ResponseHotTown;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

public class gethot extends BaseAction implements Action
{
	private townService town;
	private String ptoken;
	private int ptuserid;
	private List<Integer> rejectid;
	public String jsonstr;
	@Override
	public boolean needInterceptCheck() {
		return false;
	}
	public townService getTown() {
		return town;
	}
	public void setTown(townService townx) {
		this.town = townx;
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
	public List<Integer> getRejectid() {
		return rejectid;
	}
	public void setRejectid(List<Integer> rejectid) {
		this.rejectid = rejectid;
	}

	@Override
	public String execute() throws Exception {
//		for (Integer i:rejectid) {
//			LogUtil.v("reject id: "+i);
//		}
		
		ResponseHotTown res = town.getHotTown(rejectid);
		jsonstr = new Gson().toJson(res);
		return SUCCESS;
	}
	
}