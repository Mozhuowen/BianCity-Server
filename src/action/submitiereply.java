package action;


import service.TieReplyService;
import tools.objects.community.ResSubmiTieReply;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

public class submitiereply extends BaseAction implements Action
{
	private TieReplyService tiereply;
	private String ptoken;
	private int ptuserid;
	private String content;
	private int parentie;	//隶属那个帖子的id
	private int bereplyid;	//被回复贴的id

	public String jsonstr;

	@Override
	public String execute() throws Exception {
		ResSubmiTieReply res = tiereply.createNew(parentie, ptuserid, bereplyid, content);
		jsonstr = new Gson().toJson(res);
		
		return SUCCESS;
	}

	@Override
	public boolean needInterceptCheck() {
		return true;
	}
	public TieReplyService getTiereply() {
		return tiereply;
	}

	public void setTiereply(TieReplyService tiereply) {
		this.tiereply = tiereply;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getParentie() {
		return parentie;
	}

	public void setParentie(int parentie) {
		this.parentie = parentie;
	}

	public int getBereplyid() {
		return bereplyid;
	}

	public void setBereplyid(int bereplyid) {
		this.bereplyid = bereplyid;
	}

	public String getJsonstr() {
		return jsonstr;
	}

	public void setJsonstr(String jsonstr) {
		this.jsonstr = jsonstr;
	}
	
}