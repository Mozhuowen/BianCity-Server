package interceptor;

import tools.LogUtil;
import tools.encrypt.AesException;
import tools.encrypt.MsgCrypt;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.PreResultListener;
import com.opensymphony.xwork2.util.ValueStack;

public class BCPreResultListener implements PreResultListener
{

	@Override
	public void beforeResult(ActionInvocation invocation, String arg1) {
		// TODO Auto-generated method stub
		ValueStack valuestack = invocation.getInvocationContext().getValueStack();
		String value = valuestack.findString("jsonstr");
		LogUtil.v(this, "jsonstr value before encrypt: "+value);
		try {
//			valuestack.setValue("jsonstr", MsgCrypt.simpleEncBase64(value));
			valuestack.set("jsonstr", MsgCrypt.simpleEncBase64(value));
			value = valuestack.findString("jsonstr");
			LogUtil.v(this,"response intercept! jsonstr after encrypt value: length: "+value.length()+" "+ value);
		} catch (AesException e) {
			e.printStackTrace();
		}
	}
	
}