package tools.objects.community;

/**
 * 快速回复数据模型
 * @author awen
 *
 */
public class ModelTieReply
{
	private int tieid;		//帖子id
	private int userid;		//用户id
	private String username;	//用户名
	private String usercover;	//用户头像
	private long time;		//发表时间
	private String content;		//回复内容
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
}