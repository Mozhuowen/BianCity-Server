package action;

import service.commentService;
import tools.LogUtil;
import tools.objects.ResponseComment;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

public class getcomment extends BaseAction implements Action
{
	private commentService comment;
	private int putaoid;	
	private int commentposition;
	public String jsonstr;
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
	public void setCommentposition(int c) {
		this.commentposition = c;
	}
	public int getCommentposition() {
		return this.commentposition;
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

	@Override
	public String execute() throws Exception {
		ResponseComment resobj = comment.loadMoreComments(putaoid, commentposition,ptuserid);
		jsonstr = new Gson().toJson(resobj);
		LogUtil.v("getcomment return info: "+jsonstr);
		return SUCCESS;
	}
	
}