package tools.objects.community;

import domain.TieReply;

/**
 * 快速回复数据模型
 * @author awen
 *
 */
public class ModelTieReply
{
	private int parentie;	//隶属那个帖子的id
	private int tieid;		//帖子id
	private int userid;		//用户id
	private String username;	//用户名
	private String usercover;	//用户头像
	private long time;		//发表时间
	private String content;		//回复内容
	private int bereplyid;	//被回复贴的id
	
	public ModelTieReply(TieReply tie) {
		this.parentie = tie.getTie().getTieid();
		this.tieid = tie.getTiereplyid();
		this.userid = tie.getUser().getUsersid();
		this.username = tie.getUser().getName();
		this.usercover = tie.getUser().getCover();
		this.time = tie.getTime().getTimeInMillis();
		this.content = tie.getContent();
	}
	
	public int getTieid() {
		return tieid;
	}
	public void setTieid(int tieid) {
		this.tieid = tieid;
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
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getParentie() {
		return parentie;
	}
	public void setParentie(int parentie) {
		this.parentie = parentie;
	}

	public int getBereplyid() {
		return bereplyid;
	}

	public void setBereplyid(int bereplyid) {
		this.bereplyid = bereplyid;
	}
}