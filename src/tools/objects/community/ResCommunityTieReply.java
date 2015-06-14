package tools.objects.community;

import java.util.List;

public class ResCommunityTieReply
{
	private boolean stat;
	private int errcode;
	private List<ModelTieReply> tiereplys;
	public boolean isStat() {
		return stat;
	}
	public void setStat(boolean stat) {
		this.stat = stat;
	}
	public int getErrcode() {
		return errcode;
	}
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
	public List<ModelTieReply> getTiereplys() {
		return tiereplys;
	}
	public void setTiereplys(List<ModelTieReply> tiereplys) {
		this.tiereplys = tiereplys;
	}
}