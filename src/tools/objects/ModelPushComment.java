package tools.objects;

public class ModelPushComment
{
	private int userid;
	private String usercover;
	private String username;
	private String content;
	private int commentid;
	private PackagePutao story;
	
	public void parse(PackagePutao s,PackageComment c) {
		this.usercover = c.getCover();
		this.userid = c.getUserid();
		this.username = c.getUsername();
		this.content = c.getContent();
		this.commentid = c.getCommentid();
		this.story = s;
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsercover() {
		return usercover;
	}
	public void setUsercover(String usercover) {
		this.usercover = usercover;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCommentid() {
		return commentid;
	}
	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}
	public PackagePutao getStory() {
		return story;
	}
	public void setStory(PackagePutao story) {
		this.story = story;
	}
}