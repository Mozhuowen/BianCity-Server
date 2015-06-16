package tools;

public class ModelSort
{
	private int id;
	private int type;
	private int data;
	public ModelSort(int id,int type,int data) {
		this.id = id;
		this.type = type;
		this.data = data;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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