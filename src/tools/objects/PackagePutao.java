package tools.objects;

import java.util.List;

public class PackagePutao
{
	private int townid;
	private int putaoid;
	private String title;
	private String content;
	private String cover;
	private String usercover;
	private String username;
	private String createtime;
	private List<String> imagenames;
	private int goods;
	private int userid;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public void setGoods(int g) {
		this.goods = g;
	}
	public int getGoods(){
		return this.goods;
	}
	public void setUsercover(String u) {
		this.usercover = u;
	}
	public String getUsercover() {
		return this.usercover;
	}
	public void setCreatetime(String c) {
		this.createtime = c;
	}
	public String getCreatetime() {
		return this.createtime;
	}
	public void setUsername(String u) {
		this.username = u;
	}
	public String getUsername() {
		return this.username;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public List<String> getImagenames() {
		return imagenames;
	}
	public void setImagenames(List<String> images) {
		this.imagenames = images;
	}
	
}