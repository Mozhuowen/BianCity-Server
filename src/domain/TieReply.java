package domain;

import java.util.Calendar;

public class TieReply
{
	private int tiereplyid;		//帖子id	
	private Calendar time;		//发表时间
	private String content;		//回复内容
	private users user;			//发帖者 1-N双向
	private Tie tie;				//回复的是哪一个普通贴 1-N双向
	private town parentown;		//隶属的边城 	1-N双向
	private int visible;			//是否可见
	public int getTiereplyid() {
		return tiereplyid;
	}
	public void setTiereplyid(int tiereplyid) {
		this.tiereplyid = tiereplyid;
	}
	public Calendar getTime() {
		return time;
	}
	public void setTime(Calendar time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public users getUser() {
		return user;
	}
	public void setUser(users user) {
		this.user = user;
	}
	public Tie getTie() {
		return tie;
	}
	public void setTie(Tie tie) {
		this.tie = tie;
	}
	public int getVisible() {
		return visible;
	}
	public void setVisible(int visible) {
		this.visible = visible;
	}
	public town getParentown() {
		return parentown;
	}
	public void setParentown(town parentown) {
		this.parentown = parentown;
	}
}