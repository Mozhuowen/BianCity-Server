package tools.objects;

public class ModelPushGood
{
	private int goodtype;
	private int userid;
	private String username;
	private String usercover;
	private PackagePutao story;
	private ApplyTown town;
	private PackageComment comment;
	public int getGoodtype() {
		return goodtype;
	}
	public void setGoodtype(int goodtype) {
		this.goodtype = goodtype;
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
	public PackagePutao getStory() {
		return story;
	}
	public void setStory(PackagePutao story) {
		this.story = story;
	}
	public ApplyTown getTown() {
		return town;
	}
	public void setTown(ApplyTown town) {
		this.town = town;
	}
	public PackageComment getComment() {
		return comment;
	}
	public void setComment(PackageComment comment) {
		this.comment = comment;
	}
}