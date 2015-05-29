package push;

import org.eclipse.swt.internal.C;

import tools.LogUtil;
import tools.objects.ModelPush;
import tools.objects.ModelPushComment;
import tools.objects.PackageComment;
import tools.objects.PackagePutao;

import com.google.gson.Gson;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;

public class CommentPushRunnable implements Runnable
{
	PackageComment comment;
	PackagePutao story;
	int bereplyuserid;
	int storyownerid;
	
	public CommentPushRunnable(PackageComment comment,PackagePutao story,int bereplyuserid,int storyownerid)
	{
		this.comment = comment;
		this.story = story;
		this.bereplyuserid = bereplyuserid;
		this.storyownerid = storyownerid;
	}

	@Override
	public void run() {
		try {
			sendMessageToAlias();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Message buildMessage() throws Exception {
		ModelPushComment pushcomment = new ModelPushComment();
		pushcomment.parse(story, comment);
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
             .description(description).payload(messagePayload)
             .restrictedPackageName(PACKAGENAME)
             .passThrough(1)  //消息使用透传方式
             .build();
     return message;
	}
	
	private void sendMessageToAlias() throws Exception {
		LogUtil.v("start to send message!");		
		
	    Message message = buildMessage();
	    LogUtil.v("Message info: storyownerid: "+storyownerid+" message content: "+message.getPayload());
	    Sender sender = new Sender("DxBCH7FvGmmESAzSr0/WqA==");
	    Result result = sender.sendToAlias(message, String.valueOf(this.storyownerid), 2); //根据alias，发送消息到指定设备上，不重试。
	    LogUtil.v("Server response: " + "MessageId: " + result.getMessageId()
                + " ErrorCode: " + result.getErrorCode().toString()
                + " Reason: " + result.getReason());
	}
	
}