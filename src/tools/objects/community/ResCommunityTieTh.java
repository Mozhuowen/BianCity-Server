package tools.objects.community;

import java.util.List;

public class ResCommunityTieTh
{
	private boolean stat;
	private int errcode;
	private List<ModelTieTheme> ties;
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
	public List<ModelTieTheme> getTies() {
		return ties;
	}
	public void setTies(List<ModelTieTheme> ties) {
		this.ties = ties;
	}
}