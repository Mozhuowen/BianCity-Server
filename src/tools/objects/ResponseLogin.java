package tools.objects;

import java.util.List;
import java.util.Set;

import domain.town;

public class ResponseLogin
{
	private boolean stat;
	private boolean needregiste;
	private int errcode;
	private int ptuserid;
	private String name;
	private String cover;
	private String ptoken;
	private int logintype = 0;
	private String uid;
	private List mytowns;
	private String sex;
	private String location;
	private boolean needcname;
	private String wallimage;	//墙纸
	
	public void setWallimage(String w) {
		this.wallimage = w;
	}
	public String getWallimage() {
		return this.wallimage;
	}
	public void setNeedcname (boolean n) {
		this.needcname = n;
	}
	public boolean getNeedcname(){
		return this.needcname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public boolean isStat() {
		return stat;
	}
	public void setStat(boolean stat) {
		this.stat = stat;
	}
	public boolean isNeedregiste() {
		return needregiste;
	}
	public void setNeedregiste(boolean needregiste) {
		this.needregiste = needregiste;
	}
	public int getErrcode() {
		return errcode;
	}
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
	public int getPtuserid() {
		return ptuserid;
	}
	public void setPtuserid(int ptuserid) {
		this.ptuserid = ptuserid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getPtoken() {
		return ptoken;
	}
	public void setPtoken(String ptoken) {
		this.ptoken = ptoken;
	}
	public int getLogintype() {
		return logintype;
	}
	public void setLogintype(int logintype) {
		this.logintype = logintype;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public List getMytowns() {
		return mytowns;
	}
	public void setMytowns(List mytowns) {
		this.mytowns = mytowns;
	}
}