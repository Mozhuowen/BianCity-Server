package tools.objects.community;

import java.util.ArrayList;
import java.util.List;

import domain.TieTheme;
/**
 * 主题贴模型
 * @author awen
 *
 */
public class ModelTieTheme
{
	private int communityid;	//社区id
	private int tieid;	//帖子id
	private long time;	//发帖时间
	private int goodcou;	//赞数量
	private int commentcou;	//回复数量
	private String title;	//标题
	private String content;	//帖子内容
	private int imagecou;	//该帖子包含的照片数量
	private List<String> imagenames;	//包含的图片名，接收时用
	private int floot;			//帖子楼层
	private int userid;			//楼主id
	private String username;	//楼主用户名
	private String usercover;	//楼主头像
	
	public ModelTieTheme(TieTheme tie) {
		this.communityid = tie.getParentown().getTownid();
		this.tieid = tie.getTiethemeid();
		this.time = tie.getTime().getTimeInMillis();
		this.goodcou = tie.getGoodcou();
		this.commentcou = tie.getCommentcou();
		this.title = tie.getTitle();
		this.content = tie.getContent();
		this.imagenames = new ArrayList<String>();
		for (int i=0;i<tie.getImages().size();i++)
			imagenames.add(tie.getImages().get(i).getImagename());
		this.imagecou = this.imagenames.size();
		this.userid = tie.getUser().getUsersid();
		this.username = tie.getUser().getName();
		this.usercover = tie.getUser().getCover();
	}
	
	public int getTieid() {
		return tieid;
	}
	public void setTieid(int tieid) {
		this.tieid = tieid;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getGoodcou() {
		return goodcou;
	}
	public void setGoodcou(int goodcou) {
		this.goodcou = goodcou;
	}
	public int getCommentcou() {
		return commentcou;
	}
	public void setCommentcou(int commentcou) {
		this.commentcou = commentcou;
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
	public int getImagecou() {
		return imagecou;
	}
	public void setImagecou(int imagecou) {
		this.imagecou = imagecou;
	}
	public List<String> getImagenames() {
		return imagenames;
	}
	public void setImagenames(List<String> imagenames) {
		this.imagenames = imagenames;
	}
	public int getFloot() {
		return floot;
	}
	public void setFloot(int floot) {
		this.floot = floot;
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
	public int getCommunityid() {
		return communityid;
	}
	public void setCommunityid(int communityid) {
		this.communityid = communityid;
	}
}