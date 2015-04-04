package tools;

public class TownSort
{
	private int townid;
	private int type;	//处理的类型 0-发表时间 1-集赞数量 2-粉丝数（订阅数）
	int data;			//需要处理的数据
	public TownSort(int townid,int type,int data){
		this.townid = townid;
		this.type = type;
		this.data = data;
	}
	public int getTownid() {
		return townid;
	}
	public void setTownid(int townid) {
		this.townid = townid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}	
}