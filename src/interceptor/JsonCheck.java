package interceptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.JSONInterceptor;

import tools.LogUtil;

import com.opensymphony.xwork2.ActionInvocation;

public class JsonCheck extends JSONInterceptor
{
	@Override
	public String intercept(ActionInvocation invocation)
		    throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		PushbackReader bf = new PushbackReader(request.getReader(),1024*8);
		String toread = getPostData(bf);
		LogUtil.v(toread);
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
	    }

	    return buffer.toString();
	  }
}