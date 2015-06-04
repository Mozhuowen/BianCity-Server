package push;

import java.util.Calendar;

import org.eclipse.swt.internal.C;

import tools.LogUtil;
import tools.objects.ModelPush;
import tools.objects.ModelPushComment;
import tools.objects.PackageComment;
import tools.objects.PackagePutao;

import com.google.gson.Gson;
import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;

public class CommentPushRunnable implements Runnable
{
	PackageComment comment;
	PackagePutao story;
	int bereplyuserid;
	int storyownerid;
	int logindevice;
	
	public CommentPushRunnable(PackageComment comment,PackagePutao story,int bereplyuserid,int storyownerid,int logindevice)
	{
		this.comment = comment;
		this.story = story;
		this.bereplyuserid = bereplyuserid;
		this.storyownerid = storyownerid;
		this.logindevice = logindevice;
	}

	@Override
	public void run() {
		try {
			sendMessageToAlias();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Message buildMessage() throws Exception {
		ModelPushComment pushcomment = new ModelPushComment();
		pushcomment.parse(story, comment);
		pushcomment.setTime(Calendar.getInstance().getTimeInMillis());
		ModelPush pushmess = new ModelPush();
		if (this.bereplyuserid != 0) {
			pushmess.setType(1);
			pushcomment.setRetype(1);
		} else{
			pushcomment.setRetype(0);
			pushmess.setType(0);
		}
	  pushmess.setMess(pushcomment);
		
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
	
	private Message buildIOSMessage() throws Exception
	{
		ModelPushComment pushcomment = new ModelPushComment();
		pushcomment.parse(story, comment);
		pushcomment.setTime(Calendar.getInstance().getTimeInMillis());
		int type = 0;
		if (this.bereplyuserid != 0) {
			type = 1;
			pushcomment.setRetype(1);
		} else{
			pushcomment.setRetype(0);
			type = 0;
		}
		String messMain = new Gson().toJson(pushcomment);
		
		String description = "收到一条信息";
		Message message = new Message.IOSBuilder()
             .description(description)
             .soundURL("default")    // 消息铃声
             .badge(1)               // 数字角标
             .category("action")     // 快速回复类别
             .extra("type", type+"")  // 自定义键值对
             .extra("content", messMain)
             .build();
		
		return message;
	}
	
	private void sendMessageToAlias() throws Exception {
		LogUtil.v(this,"start to send message!");
		LogUtil.v(this, "login device: "+this.logindevice);
		//ios测试环境
//		Constants.useOfficial();
		Message message = null;
		//判断消息发送类型，android or IOS
		if (this.logindevice == 0)	//android
			message = buildMessage();
		else								//ios
			message = buildIOSMessage();
	   LogUtil.v("Message info: storyownerid: "+storyownerid+" message content: "+message.getPayload());
	   Sender sender = null;
	   if (this.logindevice == 0)
		   sender = new Sender("DxBCH7FvGmmESAzSr0/WqA==");
	   else{
		   LogUtil.v(this, "send to iphone");
		   sender = new Sender("bJZyK19S50InYa2LySf25Q==");
	   }
	   Result result = sender.sendToAlias(message, String.valueOf(this.storyownerid), 2); //根据alias，发送消息到指定设备上，重试2次。
	   LogUtil.v("Server response: " + "MessageId: " + result.getMessageId()
	            + " ErrorCode: " + result.getErrorCode().toString()
	            + " Reason: " + result.getReason());
	}
	
}