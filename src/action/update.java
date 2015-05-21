package action;

import service.versionService;
import tools.objects.ResponseVersion;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

public class update extends BaseAction implements Action
{
	private versionService version;
	private String versioncode;
	public String jsonstr;
	private String ptoken;
	private int ptuserid;
	@Override
	public boolean needInterceptCheck() {
		return true;
	}

	@Override
	public String execute() throws Exception {
		ResponseVersion res = version.getLastestVersion();
		jsonstr = new Gson().toJson(res);
		return SUCCESS;
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

	public versionService getVersion() {
		return version;
	}

	public void setVersion(versionService version) {
		this.version = version;
	}

	public String getVersioncode() {
		return versioncode;
	}

	public void setVersioncode(String versioncode) {
		this.versioncode = versioncode;
	}
	
}