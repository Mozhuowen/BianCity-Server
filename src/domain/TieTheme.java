package domain;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class TieTheme
{
	private int tiethemeid;	//帖子id
	private Calendar time;	//发帖时间(操作时间)
	private int goodcou;	//赞数量
	private int commentcou;	//回复数量
	private String title;	//标题
	private String content;	//帖子内容
	private int top;			//是否置顶 0-no 1-yes
	private int imagecou;	//该帖子包含的照片数量
	private town parentown;		//隶属的边城 	1-N双向
	private Calendar lastretime;	//最新回复时间
	private users user;		//楼主 N-1双向
	private List<Image> images;	//包含的图片，上传时用
	private Set<users> dogoodusers;	//点赞用户	 N-N双向
	private Set<Tie> replytie;	//回复的帖子	1-N双向
	private int visible;

	public Calendar getTime() {
		return time;
	}
	public void setTime(Calendar time) {
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
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public int getImagecou() {
		return imagecou;
	}
	public void setImagecou(int imagecou) {
		this.imagecou = imagecou;
	}
	public town getParentown() {
		return parentown;
	}
	public void setParentown(town parentown) {
		this.parentown = parentown;
	}
	public Calendar getLastretime() {
		return lastretime;
	}
	public void setLastretime(Calendar lastretime) {
		this.lastretime = lastretime;
	}
	public users getUser() {
		return user;
	}
	public void setUser(users user) {
		this.user = user;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	public Set<users> getDogoodusers() {
		return dogoodusers;
	}
	public void setDogoodusers(Set<users> dogoodusers) {
		this.dogoodusers = dogoodusers;
	}
	public Set<Tie> getReplytie() {
		return replytie;
	}
	public void setReplytie(Set<Tie> replytie) {
		this.replytie = replytie;
	}
	public int getTiethemeid() {
		return tiethemeid;
	}
	public void setTiethemeid(int tiethemeid) {
		this.tiethemeid = tiethemeid;
	}
	public int getVisible() {
		return visible;
	}
	public void setVisible(int visible) {
		this.visible = visible;
	}
	
}