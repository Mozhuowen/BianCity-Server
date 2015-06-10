package domain;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class Tie
{
	private int tieid;	//帖子id
	private Calendar time;	//发帖时间
	private int goodcou;		//赞数量
	private String content;		//内容
	private int imagecou;		//包含的图片数量
	private town parentown;		//隶属的边城 	1-N双向
	private users user;		//发帖回复者 N-1双向
	private List<Image> images;	//包含的图片，上传用
	private Set<TieReply> replys;	//本帖包含的快速回复	1-N双向
	private TieTheme tieth;			//隶属的主题贴	 N-1双向
	private int visible;
	public int getTieid() {
		return tieid;
	}
	public void setTieid(int tieid) {
		this.tieid = tieid;
	}
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
	public town getParentown() {
		return parentown;
	}
	public void setParentown(town parentown) {
		this.parentown = parentown;
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
	public Set<TieReply> getReplys() {
		return replys;
	}
	public void setReplys(Set<TieReply> replys) {
		this.replys = replys;
	}
	public TieTheme getTieth() {
		return tieth;
	}
	public void setTieth(TieTheme tieth) {
		this.tieth = tieth;
	}
	public int getVisible() {
		return visible;
	}
	public void setVisible(int visible) {
		this.visible = visible;
	}
}