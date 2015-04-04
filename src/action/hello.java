package action;

import com.opensymphony.xwork2.Action;


public class hello implements Action
{
	public String test;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		test = "hello world! Struts is working";
		return SUCCESS;
	}
	
}