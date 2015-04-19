package domain;

import java.util.Calendar;
import java.util.Set;

public class MessBoard
{
	private int messboardid;
	private String content;
	private Calendar time;
	private int goods;
	private users user;
	private town townx;
	private GeoInfo geoinfo;
	private int distance;
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
	public int getMessboardid() {
		return messboardid;
	}
	public void setMessboardid(int messboardid) {
		this.messboardid = messboardid;
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
	public town getTownx() {
		return townx;
	}
	public void setTownx(town townx) {
		this.townx = townx;
	}
	public GeoInfo getGeoinfo() {
		return geoinfo;
	}
	public void setGeoinfo(GeoInfo geoinfo) {
		this.geoinfo = geoinfo;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
}