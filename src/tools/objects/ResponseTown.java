package tools.objects;

import java.util.Calendar;

import domain.GeoInfo;

public class ResponseTown
{
	private boolean stat;
	private int errcode;
	private int townid;
	private String townname;
	private String descri;
	private String cover;
	private String createtime;
	private int subscriptions = 0;
	private GeoInfo geoinfo;
	private boolean dosubscri;
	private int storycount;
	private int userid;			//边城创建者id
	private String username;	//边城创建者用户名
	private String usercover;	//边城创建者封面
	private int good;			//边城获赞数
	public void setDosubscri(boolean s) {
		this.dosubscri = s;
	}
	public boolean getDosubscri(){
		return this.dosubscri;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public int getSubscriptions() {
		return subscriptions;
	}
	public void setSubscriptions(int subscriptions) {
		this.subscriptions = subscriptions;
	}
	public boolean isStat() {
		return stat;
	}
	public void setStat(boolean stat) {
		this.stat = stat;
	}
	public int getErrcode() {
		return errcode;
	}
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
	public int getTownid() {
		return townid;
	}
	public void setTownid(int townid) {
		this.townid = townid;
	}
	public String getTownname() {
		return townname;
	}
	public void setTownname(String townname) {
		this.townname = townname;
	}
	public String getDescri() {
		return descri;
	}
	public void setDescri(String descri) {
		this.descri = descri;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public GeoInfo getGeoinfo() {
		return geoinfo;
	}
	public void setGeoinfo(GeoInfo geoinfo) {
		this.geoinfo = geoinfo;
	}
	public int getStorycount() {
		return storycount;
	}
	public void setStorycount(int storycount) {
		this.storycount = storycount;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsercover() {
		return usercover;
	}
	public void setUsercover(String usercover) {
		this.usercover = usercover;
	}
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	
	
}