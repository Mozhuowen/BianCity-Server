package domain;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class users
{
	private int usersid;
	private String name;
	private String password;
	private String email;
	private String phonenumber;
	private String wechat;
	private String qq;
	private Calendar registetime;
	private int exist;
	private String cover;
	private String ptoken;
	private String imei;
	private String sv;	//software version
	private String phonemodel;
	private String brand;
	private Calendar lastlogin;
	private String extend;
	public Set<town> mytowns = new HashSet<town>();	//1-N双向
	private Set<comment> comments = new HashSet<comment>();	//1-N双向
	private WeiboUser weibo;	//1-1双向影射微博信息
	private String wbtoken;
	private QQUser qqinfo;
	private String qqtoken;
	private Calendar expirestime;
	private int logintype;
	private Set<MessBoard> mess;	//1-N双向
	private Set<town> dogoodtown;	//N-N双向，下同
	private Set<putao> dogoodputao;
	private Set<comment> dogoodcomment;
	private Set<MessBoard> dogoodmess;
	private Set<town> subscritown;
	private Set<putao> favorite;
	private int fans;
	private Set<putao> myputao;	//1-N双向
	private String wallimage;	//墙纸
	private String sex;
	private String location;
	private Set<town> joincommunity;		//加入的社区,n-n双向
	private Set<TieTheme> tiethemes;	//发表的主题贴,1-N双向
	private Set<TieTheme> dogoodtieth;	//点赞的主题贴 N-n双向
	private Set<Tie> ties;				//发表的普通贴 1-N双向
	private Set<TieReply> tiereplys;	//发表的回复贴 1-N双向
	
	public QQUser getQqinfo() {
		return qqinfo;
	}
	public void setQqinfo(QQUser qqinfo) {
		this.qqinfo = qqinfo;
	}
	public String getQqtoken() {
		return qqtoken;
	}
	public void setQqtoken(String qqtoken) {
		this.qqtoken = qqtoken;
	}
	public void setWallimage(String w) {
		this.wallimage = w;
	}
	public String getWallimage() {
		return this.wallimage;
	}
	public void setMyputao(Set<putao> m) {
		this.myputao = m;
	}
	public Set<putao> getMyputao() {
		return this.myputao;
	}
	public void setFans(int f){
		this.fans = f;
	}
	public int getFans(){
		return this.fans;
	}
	public void setFavorite(Set<putao> f) {
		this.favorite = f;
	}
	public Set<putao> getFavorite(){
		return this.favorite;
	}
	public void setSubscritown(Set<town> s){
		this.subscritown = s;
	}
	public Set<town> getSubscritown() {
		return this.subscritown;
	}
 	public Set<town> getDogoodtown() {
		return dogoodtown;
	}
	public void setDogoodtown(Set<town> dogoodtown) {
		this.dogoodtown = dogoodtown;
	}
	public Set<putao> getDogoodputao() {
		return dogoodputao;
	}
	public void setDogoodputao(Set<putao> dogoodputao) {
		this.dogoodputao = dogoodputao;
	}
	public Set<comment> getDogoodcomment() {
		return dogoodcomment;
	}
	public void setDogoodcomment(Set<comment> dogoodcomment) {
		this.dogoodcomment = dogoodcomment;
	}
	public Set<MessBoard> getDogoodmess() {
		return dogoodmess;
	}
	public void setDogoodmess(Set<MessBoard> dogoodmess) {
		this.dogoodmess = dogoodmess;
	}
	public void setMess(Set<MessBoard> m) {
		this.mess = m;
	}
	public Set<MessBoard> getMess(){
		return this.mess;
	}
	public void setLogintype(int l){
		this.logintype = l;
	}
	public int getLogintype() {
		return this.logintype;
	}
	public void setWbtoken(String w) {
		this.wbtoken = w;
	}
	public String getWbtoken() {
		return this.wbtoken;
	}
	public void setExpirestime(Calendar e) {
		this.expirestime = e;
	}
	public Calendar getExpirestime() {
		return this.expirestime;
	}
	public void setWeibo(WeiboUser w) {
		this.weibo = w;
	}
	public WeiboUser getWeibo(){
		return this.weibo;
	}
	public void setComments(Set<comment> com) {
		this.comments = com;
	}
	public Set<comment> getComments() {
		return this.comments;
	}
	public void setBrand(String b) {
		this.brand = b;
	}
	public String getBrand() {
		return this.brand;
	}
	public void setSv(String s) {
		this.sv = s;
	}
	public String getSv() {
		return this.sv;
	}
	public void setPhonemodel(String p) {
		this.phonemodel = p;
	}
	public String getPhonemodel() {
		return this.phonemodel;
	}
	public void setImei(String i) {
		this.imei = i;
	}
	public String getImei() {
		return this.imei;
	}
	public void setPtoken(String t) {
		this.ptoken = t;
	}
	public String getPtoken() {
		return this.ptoken;
		
	}
	public void setLastlogin(Calendar l) {
		this.lastlogin = l;
	}
	public Calendar getLastlogin() {
		return this.lastlogin;
	}
	public void setCover(String c) {
		this.cover = c;
	}
	public String getCover() {
		return this.cover;
	}
	public int getUsersid() {
		return usersid;
	}
	public void setUsersid(int id) {
		this.usersid = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public Calendar getRegistetime() {
		return registetime;
	}
	public void setRegistetime(Calendar registetime) {
		this.registetime = registetime;
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
	public Set<town> getMytowns() {
		return mytowns;
	}
	public void setMytowns(Set<town> mytowns) {
		this.mytowns = mytowns;
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
	public Set<TieTheme> getTiethemes() {
		return tiethemes;
	}
	public void setTiethemes(Set<TieTheme> tiethemes) {
		this.tiethemes = tiethemes;
	}
	public Set<TieTheme> getDogoodtieth() {
		return dogoodtieth;
	}
	public void setDogoodtieth(Set<TieTheme> dogoodtieth) {
		this.dogoodtieth = dogoodtieth;
	}
	public Set<Tie> getTies() {
		return ties;
	}
	public void setTies(Set<Tie> ties) {
		this.ties = ties;
	}
	public Set<TieReply> getTiereplys() {
		return tiereplys;
	}
	public void setTiereplys(Set<TieReply> tiereplys) {
		this.tiereplys = tiereplys;
	}
	public Set<town> getJoincommunity() {
		return joincommunity;
	}
	public void setJoincommunity(Set<town> joincommunity) {
		this.joincommunity = joincommunity;
	}
	
	
}