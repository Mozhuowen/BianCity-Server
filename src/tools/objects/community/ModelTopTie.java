package tools.objects.community;

import java.util.Calendar;

/**
 * 社区列表头置顶贴模型
 * @author awen
 *
 */
public class ModelTopTie
{
	private int tieid;	//帖子ID
	private String title;	//帖子标题
	private long time;		//发帖时间
	
	public ModelTopTie(int tieid,String title,Calendar time) {
		this.tieid = tieid;
		this.title = title;
		this.time = time.getTimeInMillis();
	}
	public int getTieid() {
		return tieid;
	}
	public void setTieid(int tieid) {
		this.tieid = tieid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
}