package push;

import java.util.Calendar;

import tools.LogUtil;
import tools.objects.ModelPush;
import tools.objects.ModelPushGood;
import tools.objects.ModelPushSys;

import com.google.gson.Gson;
import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;

public class SysPushRunnable implements Runnable
{
	private int systype;	//0-系统全局消息 1-别名消息
	private int besendid;
	private String content;
	private int logindevice;
	
	public SysPushRunnable(int systype,int besendid,String content,int lodgindevice) {
		this.systype = systype;
		this.besendid = besendid;
		this.content = content;
		this.logindevice = lodgindevice;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			this.sendMessage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Message buildMessage() throws Exception {
		ModelPushSys model = new ModelPushSys();
		model.setSystype(systype);
		model.setContent(content);
		model.setTime(Calendar.getInstance().getTimeInMillis());
		
		ModelPush pushmess = new ModelPush();
		pushmess.setType(3);
		pushmess.setSysmess(model);
		//构建消息
		String PACKAGENAME = "com.putaotown";
      String messagePayload = new Gson().toJson(pushmess);
      String title = "notification title";
      String description = "notification description";
      Message message = new Message.Builder()
             .title(title)
             .description(description)
             .payload(messagePayload)
             .restrictedPackageName(PACKAGENAME)
             .passThrough(1)  //消息使用透传方式
             .build();
      LogUtil.v("push message lenght: "+messagePayload.length());
      return message;
	}
	
	private Message buildIOSMessage() throws Exception {
		ModelPushSys model = new ModelPushSys();
		model.setSystype(systype);
		model.setContent(content);
		model.setTime(Calendar.getInstance().getTimeInMillis());
		//构建消息
		String messMain = new Gson().toJson(model);
		
		String description = "收到一条信息";
		Message message = new Message.IOSBuilder()
             .description(description)
             .soundURL("default")    // 消息铃声
             .badge(1)               // 数字角标
             .category("action")     // 快速回复类别
             .extra("type", 3+"")  // 自定义键值对
             .extra("content", messMain)
             .build();
		
		return message;
	}
	
	private void sendMessage() throws Exception  {
		LogUtil.v(this,"start to send message!");
		LogUtil.v(this, "login device: "+this.logindevice);
		//ios测试环境
//		Constants.useSandbox();
		Message message = null;		
	   Sender sender = null;
	   if (this.logindevice == 0) {
		   Constants.useOfficial();
		   sender = new Sender("DxBCH7FvGmmESAzSr0/WqA==");
	   	message = buildMessage();
	   }else{
		   LogUtil.v(this, "send to iphone");
		   Constants.useSandbox();
		   sender = new Sender("bJZyK19S50InYa2LySf25Q==");
		   message = buildIOSMessage();
	   }
	   Result result = null;
	   //判断发全局还是别名
	   if (this.systype == 0){	//全局消息
		   //先发android 消息
		   sender = new Sender("DxBCH7FvGmmESAzSr0/WqA==");
		   message = buildMessage();
		   result = sender.broadcastAll(message, 2);
		   //再发ios消息
		   		   
	   } else if (this.systype ==1 ) {	//别名消息
		   LogUtil.v(this,"Message info: " +" message content: "+message.getPayload() + "besendid: "+besendid);
		   if (this.besendid > 0)
			   result = sender.sendToAlias(message, String.valueOf(this.besendid), 2);
	   }
//	   Result result = sender.sendToAlias(message, String.valueOf(this.besenduserid), 2); //根据alias，发送消息到指定设备上，重试2次。
	   LogUtil.v("Server response: " + "MessageId: " + result.getMessageId()
	            + " ErrorCode: " + result.getErrorCode().toString()
	            + " Reason: " + result.getReason());
	}
}