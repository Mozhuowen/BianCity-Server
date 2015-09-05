package interceptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.JSONInterceptor;

import tools.LogUtil;
import tools.NetErrorUtil;
import tools.encrypt.AesException;
import tools.encrypt.MsgCrypt;
import tools.objects.ResponseSimple;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.util.ValueStack;

/*
 * 解析加密的post数据并检测timetamp
 */
public class JsonCheckV2 extends JSONInterceptor
{
	@Override
	public String intercept(ActionInvocation invocation)
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String timestamp = request.getHeader("timestamp");
		//检测timestamp
		long time = Long.parseLong(timestamp);
		LogUtil.v(this, "timestamp is: "+time);
		if (Calendar.getInstance().getTimeInMillis() - time > 60 *1000) {
			errReturn();
			return null;
		}
		PushbackReader bf = null;
		String decJson = null;
		try {
			bf = new PushbackReader(request.getReader(),1024*16);
			decJson = getPostData(request.getReader());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errReturn();
		}catch (AesException ae) {
			// TODO Auto-generated catch block
			ae.printStackTrace();
			errReturn();
		}
		LogUtil.v(this, "decJson content: "+decJson);
		if (decJson != null && decJson.length() > 0) {
			//推回流
			try {
				bf.unread(decJson.toCharArray());
			} catch (IOException e) {
				e.printStackTrace();
				errReturn();
			}
			pushbackreader = bf;
		} else {
			errReturn();
		}		
		String result = null;
		try {
			invocation.addPreResultListener(new BCPreResultListener());
			result =  super.intercept(invocation);
		} catch (Exception e) {
			e.printStackTrace();
			errReturn();
			return null;
		}
		
		return result;
	}
	
	public void errReturn() {
		ResponseSimple res = new ResponseSimple();
		res.setStat(false);
		res.setErrcode(NetErrorUtil.SIGNATURE_ERROR);
		String str = new Gson().toJson(res);
		//直接输出结果，返回null
		try {
			ServletActionContext.getResponse().getOutputStream().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getPostData(Reader reader) throws AesException
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
	    String baseb64Str = buffer.toString();   
	    LogUtil.v(this, "Request base64 string: "+baseb64Str);
	    return MsgCrypt.simpleDecBase64(baseb64Str);
	  }
}