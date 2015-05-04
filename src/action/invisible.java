package action;

import service.generalService;
import tools.NetErrorUtil;
import tools.objects.ResponseSimple;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

public class invisible extends BaseAction implements Action
{
	private generalService tools;
	private String ptoken;
	private int ptuserid;
	private int id;
	private int type;
	public String jsonstr;
	@Override
	public boolean needInterceptCheck() {
		return true;
	}

	@Override
	public String execute() throws Exception {
		ResponseSimple res = new ResponseSimple();
		if (tools.setInvisible(type, ptuserid, id))
		{
			res.setStat(true);
		} else {
			res.setStat(false);
			res.setErrcode(NetErrorUtil.SERVER_ERROR);
		}
		jsonstr = new Gson().toJson(res);
		
		return SUCCESS;
	}

	public generalService getTools() {
		return tools;
	}

	public void setTools(generalService tools) {
		this.tools = tools;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}