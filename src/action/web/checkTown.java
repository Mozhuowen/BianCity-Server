package action.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import service.generalService;
import service.townService;
import tools.LogUtil;

import com.opensymphony.xwork2.Action;

import domain.town;

public class checkTown implements Action
{
	private townService townx;	
	private generalService genseral;
	
	private int position;
	private int[] delid = new int[0];	
	private HttpSession session;
	public List<town> townlist;
	public int currPage;
	public int startpage;
	public List<Integer> pagelist = new ArrayList<Integer>();

	@Override
	public String execute() throws Exception {
		session = ServletActionContext.getRequest().getSession();
		String username = (String)session.getAttribute("username");
		if (username == null || username.length() < 0)
			return ERROR;
		
//		LogUtil.v("delid value: " + delid.length);
		Long towncount = townx.getTownCount();
		position = currPage * 10;
		if (towncount >= position) {
			makePage(currPage);
			makeDel();
			townlist = townx.getTownByTime(position);
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
				this.genseral.setInvisible(0, i);
			}
		}
	}

	public townService getTownx() {
		return townx;
	}

	public void setTownx(townService town) {
		this.townx = town;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int postion) {
		this.position = postion;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int[] getDelid() {
		return delid;
	}

	public void setDelid(int[] delid) {
		this.delid = delid;
	}

	public generalService getGenseral() {
		return genseral;
	}

	public void setGenseral(generalService genseral) {
		this.genseral = genseral;
	}
	
}