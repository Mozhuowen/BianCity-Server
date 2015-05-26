package action.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import push.CommentPushRunnable;

import com.opensymphony.xwork2.Action;

public class tologin implements Action
{
	private String LOGIN_OK = "loginok";
	private String username;
	private String password;
	
	private HttpSession session;
	private HttpServletRequest request;

	@Override
	public String execute() throws Exception {
		if (username == null || password == null || !username.equals("putaotown") || !password.equals("putao321")) {
			return SUCCESS;
		}
		
		this.request = ServletActionContext.getRequest();
		this.session = request.getSession();
		session.setAttribute("username", this.getUsername());
		
		//testcode
		new Thread(new CommentPushRunnable()).start();
		return LOGIN_OK;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}