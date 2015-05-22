package action.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import service.generalService;
import service.putaoService;

import com.opensymphony.xwork2.Action;

import domain.putao;
import domain.town;

public class checkStory implements Action
{
	private putaoService putaox;
	private generalService genseral;
	
	private int position;
	private int[] delid = new int[0];	
	private HttpSession session;
	public List<putao> storylist;
	public int currPage;
	public int startpage;
	public List<Integer> pagelist = new ArrayList<Integer>();

	@Override
	public String execute() throws Exception {
		session = ServletActionContext.getRequest().getSession();
		String username = (String)session.getAttribute("username");
		if (username == null || username.length() < 0)
			return ERROR;
		
		Long storyCount = this.putaox.getStoryCount();
		position = currPage * 10;
		if (storyCount >= position) {
			makePage(currPage);
			makeDel();
			storylist = putaox.getStoryByTime(position);
			return SUCCESS;
		} else
			return ERROR;
	}
	
	private void makePage(int page) {
		startpage = (page / 10) * 10 + 1;
		for (int i=0;i<10;i++) {
			pagelist.add(startpage + i);
		}
	}
	
	private void makeDel() {
		if (delid.length > 0) {
			for (int i:delid) {
				this.genseral.setInvisible(1, i);
			}
		}
	}

	public putaoService getPutaox() {
		return putaox;
	}

	public void setPutaox(putaoService putaox) {
		this.putaox = putaox;
	}

	public generalService getGenseral() {
		return genseral;
	}

	public void setGenseral(generalService genseral) {
		this.genseral = genseral;
	}

	public int[] getDelid() {
		return delid;
	}

	public void setDelid(int[] delid) {
		this.delid = delid;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	
}