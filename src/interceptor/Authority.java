package interceptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import service.usersService;
import tools.LogUtil;
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
		
		HttpServletRequest request = ServletActionContext.getRequest();
//		Map<String,String[]> tmpmap = request.getParameterMap();
//		Set keyset = tmpmap.keySet();
//		for (Iterator<String> it = keyset.iterator();it.hasNext();) {
//			String[] arr = tmpmap.get(it.next());
//			System.out.println(arr);
// 		}
		
//		BufferedReader br = request.getReader();
//		String toread = this.getPostData(br);		
//		br.close();
//		LogUtil.v("post data: "+toread.trim());
		
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
	
	public String getPostData(Reader reader)
		  {
		    BufferedReader bufferReader = new BufferedReader(reader);

		    StringBuilder buffer = new StringBuilder();
		    try
		    {
		      String line;
		      while ((line = bufferReader.readLine()) != null)
		        buffer.append(line);
		    }
		    catch (IOException e) {		      
		    }

		    return buffer.toString();
		  }
	
}