package action;

import service.townService;
import tools.objects.ResponseLogin;
import tools.objects.ResponseTown;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;

import domain.GeoInfo;

public class applytown extends BaseAction implements Action
{
	private townService town;
	
	private int ptuserid;
	private String ptoken;
	private String townname;
	private String descri;
	private String cover;
	private GeoInfo geoinfo = new GeoInfo();
	
	public String jsonstr;

	@Override
	public boolean needInterceptCheck() {
		return true;
	}
	public void setJsonstr(String j) {
		this.jsonstr = j;
	}
	public void setPtoken(String t) {
		this.ptoken = t;
	}
	public String getPtoken() {
		return this.ptoken;
	}
	public void setTown(townService town) {
		this.town = town;
	}
	public townService getTown() {
		return this.town;
	}
	public void setPtuserid(int u) {
		this.ptuserid = u;
	}
	public int getPtuserid() {
		return this.ptuserid;
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

	@Override
	public String execute() throws Exception {
		System.out.println("townname: "+townname+" descri: "+descri+" cover: "+cover+" "+geoinfo.toString());
		ResponseTown resobj = town.apply(this.getPtuserid(), this.getTownname(), this.getDescri(), this.getCover(), this.getGeoinfo());
		Gson gson = new Gson();
		jsonstr = gson.toJson(resobj);
		return SUCCESS;
	}
	
}