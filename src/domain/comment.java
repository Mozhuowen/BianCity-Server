package domain;

import java.util.Calendar;
import java.util.Set;

public class comment
{
	private int commentid;
	private String content;
	private Calendar time;
	private int goods;
	private users user;
	private putao putaox;
	private town townx;
	public Set<users> dogoodusers;
	private int visible;
	public void setVisible(int v) {
		this.visible = v;
	}
	public int getVisible() {
		return visible;
	}
	public void setDogoodusers(Set<users> u) {
		this.dogoodusers = u;
	}
	public Set<users> getDogoodusers() {
		return this.dogoodusers;
	}
	public int getCommentid() {
		return commentid;
	}
	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Calendar getTime() {
		return time;
	}
	public void setTime(Calendar time) {
		this.time = time;
	}
	public int getGoods() {
		return goods;
	}
	public void setGoods(int goods) {
		this.goods = goods;
	}
	public users getUser() {
		return user;
	}
	public void setUser(users user) {
		this.user = user;
	}
	public putao getPutaox() {
		return putaox;
	}
	public void setPutaox(putao putaox) {
		this.putaox = putaox;
	}
	public town getTownx() {
		return townx;
	}
	public void setTownx(town townx) {
		this.townx = townx;
	}
	
}