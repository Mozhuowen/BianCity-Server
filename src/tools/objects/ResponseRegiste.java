package tools.objects;

import java.util.List;

public class ResponseRegiste
{
	private boolean stat;
	private boolean needchangename;
	private int errcode;
	private String name;
	private String cover;
	private String uid;
	private List<ApplyTown> mytowns;
	private String sex;
	private String location;	
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public boolean isStat() {
		return stat;
	}
	public void setStat(boolean stat) {
		this.stat = stat;
	}
	public boolean isNeedchangename() {
		return needchangename;
	}
	public void setNeedchangename(boolean needchangename) {
		this.needchangename = needchangename;
	}
	public int getErrcode() {
		return errcode;
	}
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public List<ApplyTown> getMytowns() {
		return mytowns;
	}
	public void setMytowns(List<ApplyTown> mytowns) {
		this.mytowns = mytowns;
	}
	
}