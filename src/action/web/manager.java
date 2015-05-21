package action.web;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;

public class manager implements Action
{
	private HttpSession session;

	@Override
	public String execute() throws Exception {
		session = ServletActionContext.getRequest().getSession();
		String username = (String)session.getAttribute("username");
		if (username == null || username.length() < 0)
			return ERROR;
		
		
		return SUCCESS;
	}
	
}