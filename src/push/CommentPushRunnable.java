package push;

import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Sender;

public class CommentPushRunnable implements Runnable
{

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
	     String PACKAGENAME = "com.putaotown";
	     String messagePayload = "This is a message";
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
	    Message message = buildMessage();
	    Sender sender = new Sender("DxBCH7FvGmmESAzSr0/WqA==");
	    sender.sendToAlias(message, String.valueOf(1), 0); //根据alias，发送消息到指定设备上，不重试。
	}
	
}