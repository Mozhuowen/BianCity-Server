package interceptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.JSONInterceptor;

import tools.LogUtil;
import tools.NetErrorUtil;
import tools.encrypt.MsgCrypt;
import tools.encrypt.SHA1;
import tools.objects.ResponseSimple;
import action.BaseAction;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.util.ValueStack;

public class JsonCheck extends JSONInterceptor
{
	
	@Override
	public String intercept(ActionInvocation invocation)
		    throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String signature = request.getHeader("signature");
		String timestamp = request.getHeader("timestamp");
		PushbackReader bf = new PushbackReader(request.getReader(),1024*16);	//16k的缓冲区应该足够了
		//获取Post过来的json数据
		String toread = getPostData(bf);		
		//获取signature
		String result = "";
		if (timestamp != null && signature != null)
			result = MsgCrypt.encryptMsg(timestamp.trim(),toread.trim());
		LogUtil.v(this, "request API: "+request.getRequestURI());
		LogUtil.v(toread + toread.length() + " " + timestamp +" "+ signature +" "+result);
		
		if (signature == null || !result.equals(signature)) {
			LogUtil.v("not pass sinature check!");
			ResponseSimple res = new ResponseSimple();
			res.setStat(false);
			res.setErrcode(NetErrorUtil.SIGNATURE_ERROR);
			String str = new Gson().toJson(res);
			//直接输出结果，返回null
			ServletActionContext.getResponse().getOutputStream().print(str);
			bf.close();
			return null;
		}
		//推回流
		bf.unread(toread.toCharArray());
		pushbackreader = bf;
		
		return super.intercept(invocation);
		
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
	    	e.printStackTrace();
	    }

	    return buffer.toString();
	  }
}