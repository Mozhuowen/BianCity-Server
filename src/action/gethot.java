package action;

import java.io.BufferedReader;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

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
	
	private HttpServletRequest request;

	@Override
	public String execute() throws Exception {
		this.request = ServletActionContext.getRequest();
		String signature = request.getHeader("signature");
		String timestamp = request.getHeader("timestamp");
		LogUtil.v("signature: "+signature+" timestamp: "+timestamp);
		
		ResponseHotTown res = town.getHotTown(rejectid);
		jsonstr = new Gson().toJson(res);
		
		return SUCCESS;
	}
	
	@Override
	public boolean needInterceptCheck() {
		return true;
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
	
}