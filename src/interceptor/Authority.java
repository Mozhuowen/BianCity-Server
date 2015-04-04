package interceptor;

import service.usersService;
import tools.NetErrorUtil;
import tools.objects.ResponseSimple;
import action.BaseAction;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class Authority extends AbstractInterceptor
{
	//依赖注入
	private usersService user;

	public usersService getUser() {
		return user;
	}
	public void setUser(usersService user) {
		this.user = user;
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		BaseAction targetaction = (BaseAction)arg0.getAction();
		int userid = targetaction.getPtuserid();
		String token = targetaction.getPtoken();
		ResponseSimple res = new ResponseSimple();
		//校验
		if (targetaction.needInterceptCheck()) {
			System.out.println("Enter interceptor,userid: "+userid+" token: "+token);
			if (!user.checkUserid(userid)) {		
				System.out.println("not pass auth check! userid not exist!");
				res.setStat(false);
				res.setErrcode(NetErrorUtil.NAME_NOTEXIST);
				
				String jsonstr = new Gson().toJson(res);
				targetaction.setJsonstr(jsonstr);
				return Action.SUCCESS;
			} else if(!user.checkToken(userid, token)) {
				System.out.println("net pass auth check! token not correct!");
				res.setStat(false);
				res.setErrcode(NetErrorUtil.LOGIN_ANOTHERPLACE);
				
				String jsonstr = new Gson().toJson(res);
				targetaction.setJsonstr(jsonstr);
				return Action.SUCCESS;
			} else {
				System.out.println("pass authority check!");
				return arg0.invoke();
			}
		} else {
			return arg0.invoke();
		}
		
	}
	
}