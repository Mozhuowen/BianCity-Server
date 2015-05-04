package action;

import service.commentService;
import tools.LogUtil;
import tools.objects.ResponseComment;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

public class submitcomment extends BaseAction implements Action
{
	private commentService comment;
	private int putaoid;
	private int townid;
	private String content;
	private String ptoken;
	private int ptuserid;
	public String jsonstr;
	
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
	public commentService getComment() {
		return comment;
	}

	public void setComment(commentService comment) {
		this.comment = comment;
	}

	public int getPutaoid() {
		return putaoid;
	}

	public void setPutaoid(int putaoid) {
		this.putaoid = putaoid;
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

	@Override
	public String execute() throws Exception {
//		LogUtil.v("submitcomment info: content:"+content+" putaoid:"+putaoid+" userid:"+userid+" townid:"+townid);
		ResponseComment resobj = comment.submitComment(townid, putaoid, ptuserid, content);
		jsonstr = new Gson().toJson(resobj);
		return SUCCESS;
	}
	
}