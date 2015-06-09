package domain;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class town
{
	private int townid;
	private String name;
	private String descri;
	private String cover;
	private Calendar createtime;
	private int exist;
	private String extend;
	private users owner;
	private GeoInfo geo;
	private Set<putao> putao;
	private Set<comment> comments = new HashSet<comment>();
	private Set<MessBoard> mess;
	private int goods;
	public Set<users> dogoodusers;
	private Set<users> subscriusers;
	private int subscris;
	private String geohash;
	private Set<users> communitymembers;
	private Set<TieTheme> communityties;	//社区主题贴
	
	public void setGeohash(String g) {
		this.geohash = g;
	}
	public String getGeohash() {
		return this.geohash;
	}
	public void setSubscris(int s) {
		this.subscris = s;
	}
	public int getSubscris() {
		return this.subscris;
	}
	public void setSubscriusers(Set<users> s) {
		this.subscriusers = s;
	}
	public Set<users> getSubscriusers() {
		return this.subscriusers;
	}
	public void setDogoodusers(Set<users> u) {
		this.dogoodusers = u;
	}
	public Set<users> getDogoodusers() {
		return this.dogoodusers;
	}
	public void setGoods(int g){
		this.goods = g;
	}
	public int getGoods(){
		return this.goods;
	}
	public void setMess(Set<MessBoard> m) {
		this.mess = m;
	}
	public Set<MessBoard> getMess(){
		return this.mess;
	}
	public void setComments(Set<comment> com) {
		this.comments = com;
	}
	public Set<comment> getComments() {
		return this.comments;
	}
	public Set<putao> getPutao() {
		return this.putao;
	}
	public void setPutao(Set<putao> p) {
		this.putao = p;
	}
	public void setGeo(GeoInfo g) {
		this.geo = g;
	}
	public GeoInfo getGeo() {
		return this.geo;
	}
	public int getTownid() {
		return townid;
	}

	public void setTownid(int townid) {
		this.townid = townid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Calendar getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Calendar createtime) {
		this.createtime = createtime;
	}

	public int getExist() {
		return exist;
	}

	public void setExist(int exist) {
		this.exist = exist;
	}

	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	public users getOwner() {
		return owner;
	}

	public void setOwner(users owner) {
		this.owner = owner;
	}
	public Set<users> getCommunitymembers() {
		return communitymembers;
	}
	public void setCommunitymembers(Set<users> comunitymembers) {
		this.communitymembers = comunitymembers;
	}
	public Set<TieTheme> getCommunityties() {
		return communityties;
	}
	public void setCommunityties(Set<TieTheme> communityties) {
		this.communityties = communityties;
	}
	
	
}