package action;

import service.CommunityService;
import service.TieThemeService;
import tools.objects.ResponseSimple;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

public class deltie extends BaseAction implements Action
{
	private TieThemeService tieth;
	private String ptoken;
	private int ptuserid;
	private int zhulouid;
	public String jsonstr;

	@Override
	public String execute() throws Exception {
		ResponseSimple res = tieth.delete(ptuserid, zhulouid);
		jsonstr = new Gson().toJson(res);
		return SUCCESS;
	}
	
	@Override
	public boolean needInterceptCheck() {
		return true;
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

	public int getZhulouid() {
		return zhulouid;
	}

	public void setZhulouid(int zhulouid) {
		this.zhulouid = zhulouid;
	}

	public TieThemeService getTieth() {
		return tieth;
	}

	public void setTieth(TieThemeService tieth) {
		this.tieth = tieth;
	}
	
}