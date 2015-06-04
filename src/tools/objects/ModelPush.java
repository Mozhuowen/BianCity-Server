package tools.objects;

public class ModelPush
{
	private int type;	//0-评论 1-回复 2-赞
	private Object mess;
	private ModelPushGood goodmess;
	private int messid;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Object getMess() {
		return mess;
	}
	public void setMess(Object mess) {
		this.mess = mess;
	}
	public int getMessid() {
		return messid;
	}
	public void setMessid(int messid) {
		this.messid = messid;
	}
	public ModelPushGood getGoodmess() {
		return goodmess;
	}
	public void setGoodmess(ModelPushGood goodmess) {
		this.goodmess = goodmess;
	}
}