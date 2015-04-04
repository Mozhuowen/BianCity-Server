package tools.objects;

public class ModelGood
{
	/**被赞对象类型0-town 1-putao 2-comment 3-mess*/
	private int type;
	/**0-加赞 1-减赞*/
	private int action;
	/**赞总数*/
	private int goods;
	private boolean dogood;
	public void setDogood(boolean d) {
		this.dogood = d;
	}
	public boolean getDogood(){
		return this.dogood;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	public int getGoods() {
		return goods;
	}
	public void setGoods(int goods) {
		this.goods = goods;
	}
}