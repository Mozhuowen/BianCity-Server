package tools.objects;

import tools.objects.community.ModelTie;
import tools.objects.community.ModelTieTheme;
public class ModelPushTie
{
	private int tietype;	//0-主题帖 1-普通贴
	private long time;
	private int floot;
	private ModelTieTheme tieth;
	private ModelTie tie;
	private int adminid;
	private String tieth_title;		//新增，主题贴题目
	public int getTietype() {
		return tietype;
	}
	public void setTietype(int tietype) {
		this.tietype = tietype;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getFloot() {
		return floot;
	}
	public void setFloot(int floot) {
		this.floot = floot;
	}
	public ModelTieTheme getTieth() {
		return tieth;
	}
	public void setTieth(ModelTieTheme tieth) {
		this.tieth = tieth;
	}
	public ModelTie getTie() {
		return tie;
	}
	public void setTie(ModelTie tie) {
		this.tie = tie;
	}
	public int getAdminid() {
		return adminid;
	}
	public void setAdminid(int adminid) {
		this.adminid = adminid;
	}
	public String getTieth_title() {
		return tieth_title;
	}
	public void setTieth_title(String tieth_title) {
		this.tieth_title = tieth_title;
	}
}