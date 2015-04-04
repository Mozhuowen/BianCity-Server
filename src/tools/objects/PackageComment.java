package tools.objects;

import java.io.Serializable;

public class PackageComment implements Serializable
{
	private String token;
	private int userid;
	private int townid;
	private int putaoid;
	private String content;
	private String username;
	private String cover;
	private String time;
	private int goods;
	private boolean dogood;
	private int commentid;
	private int commentposition;
	
	public void setCommentposition(int c) {
		this.commentposition = c;
	}
	public int getCommentposition() {
		return this.commentposition;
	}
	public void setCommentid(int c) {
		this.commentid = c;
	}
	public int getCommentid() {
		return this.commentid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getTownid() {
		return townid;
	}
	public void setTownid(int townid) {
		this.townid = townid;
	}
	public int getPutaoid() {
		return putaoid;
	}
	public void setPutaoid(int putaoid) {
		this.putaoid = putaoid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getGoods() {
		return goods;
	}
	public void setGoods(int goods) {
		this.goods = goods;
	}
	public boolean isDogood() {
		return dogood;
	}
	public void setDogood(boolean dogood) {
		this.dogood = dogood;
	}
}