package domain;

public class Version
{
	private int versionid;
	private int versioncode;
	private String versionname;
	private String updateinfo;
	private String downloadurl;
	public int getVersionid() {
		return versionid;
	}
	public void setVersionid(int versionid) {
		this.versionid = versionid;
	}
	public int getVersioncode() {
		return versioncode;
	}
	public void setVersioncode(int versioncode) {
		this.versioncode = versioncode;
	}
	public String getVersionname() {
		return versionname;
	}
	public void setVersionname(String versionname) {
		this.versionname = versionname;
	}
	public String getUpdateinfo() {
		return updateinfo;
	}
	public void setUpdateinfo(String updateinfo) {
		this.updateinfo = updateinfo;
	}
	public String getDownloadurl() {
		return downloadurl;
	}
	public void setDownloadurl(String downloadurl) {
		this.downloadurl = downloadurl;
	}
}