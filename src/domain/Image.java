package domain;

public class Image
{
	private int imageid;
	private String imagename;
	private String md5;
	private int size;
	private int viewtimes;
	private int isvisible = 0;	//默认0可见
	private putao p;
	private TieTheme tiet;
	private int list_index;
	
	public void setList_index(int l) {
		this.list_index = l;
	}
	public int getList_index() {
		return this.list_index;
	}
	public void setP(putao p) {
		this.p = p;
	}
	public putao getP() {
		return this.p;
	}
	public int getImageid() {
		return imageid;
	}
	public void setImageid(int imageid) {
		this.imageid = imageid;
	}
	public String getImagename() {
		return imagename;
	}
	public void setImagename(String imagename) {
		this.imagename = imagename;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getViewtimes() {
		return viewtimes;
	}
	public void setViewtimes(int viewtimes) {
		this.viewtimes = viewtimes;
	}
	public int getIsvisible() {
		return isvisible;
	}
	public void setIsvisible(int isvisible) {
		this.isvisible = isvisible;
	}
	public TieTheme getTiet() {
		return tiet;
	}
	public void setTiet(TieTheme tiet) {
		this.tiet = tiet;
	}
	
	
}