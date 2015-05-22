package action.web;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import service.putaoService;
import tools.LogUtil;

import com.opensymphony.xwork2.Action;

public class createStory implements Action
{
	private HttpSession session;
	private String content;
	private String covername;
	private String title;
	private int userid;
	private int townid;
	private putaoService putaox;
	
	public String hint;	//提示

	@Override
	public String execute() throws Exception {		
		session = ServletActionContext.getRequest().getSession();
		String username = (String)session.getAttribute("username");
		if (username == null || username.length() < 0)
			return ERROR;
		
		LogUtil.v("contetn and covername: " + content + " " + covername);
//		System.out.println("test!");
		if ( content != null && covername != null ) {			
			putaox.create(townid, title, covername, content, new ArrayList());
			hint = "创建成功";
		}
		
		return SUCCESS;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCovername() {
		return covername;
	}

	public void setCovername(String covername) {
		this.covername = covername;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getTownid() {
		return townid;
	}

	public void setTownid(int townid) {
		this.townid = townid;
	}

	public putaoService getPutaox() {
		return putaox;
	}

	public void setPutaox(putaoService putaox) {
		this.putaox = putaox;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}