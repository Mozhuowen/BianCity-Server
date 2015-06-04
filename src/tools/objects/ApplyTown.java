package tools.objects;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import domain.GeoInfo;
import domain.town;
/**
 * 该类和android端request一致，用于返回到android端的town列表
 * @author root
 *
 */
public class ApplyTown
{
	private int townid;
	private String townname;
	private String descri;
	private String cover;
	private String createtime;
	private int subscriptions = 0;
	private ResGeoInfo geoinfo;
	private int good;
	private boolean dosubscri;
	private int userid;
	private String username;
	private String usercover;
	private int storycount;
	
	public static ApplyTown build(town t) {
		ApplyTown at = new ApplyTown();
		at.townid = t.getTownid();
		at.townname = t.getName();
		at.descri = t.getDescri();
		at.cover = t.getCover();
		at.createtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(t.getCreatetime().getTime());
		at.subscriptions = t.getSubscris();
		at.geoinfo = new ResGeoInfo(t.getGeo());
		at.good = t.getGoods();
		at.userid = t.getOwner().getUsersid();
		at.username = t.getOwner().getName();
		at.usercover = t.getOwner().getCover();
		at.storycount = t.getPutao().size();
		return at;
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
	public void setUserid(int u) {
		this.userid = u;
	}
	public int getUserid() {
		return this.userid;
	}
	public void setDosubscri(boolean s) {
		this.dosubscri = s;
	}
	public boolean getDosubscri(){
		return this.dosubscri;
	}
	public void setGood(int g) {
		this.good = g;
	}
	public int getGood() {
		return this.good;
	}
	public ApplyTown(){}
	
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
	public void setTownid(int t) {
		this.townid = t;
	}
	public int getTownid() {
		return this.townid;
	}
	public String getCover() {
		return this.cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
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
	public ResGeoInfo getGeoinfo() {
		return geoinfo;
	}
	public void setGeoinfo(ResGeoInfo geoinfo) {
		this.geoinfo = geoinfo;
	}
	
	public String getCoordstr() {
		/*double lon_second = this.geoinfo.getLon_second();
		double lat_second = this.geoinfo.getLat_second();
		BigDecimal bg1 = new BigDecimal(lon_second);
		BigDecimal bg2 = new BigDecimal(lat_second);
		double lon_second_2 = bg1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		double lat_second_2 = bg2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		
		String tmpstr = "E"+geoinfo.getLon_degree()+"°"+geoinfo.getLon_minute()+"'"+lon_second_2+"''  "+"N"+geoinfo.getLat_degree()+"°"+geoinfo.getLat_minute()+"'"+lat_second_2+"''";
		return tmpstr;*/
		return null;
	}
	public int getStorycount() {
		return storycount;
	}
	public void setStorycount(int storycount) {
		this.storycount = storycount;
	}
}