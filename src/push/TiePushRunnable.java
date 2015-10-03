package push;

import java.util.Calendar;

import com.google.gson.Gson;
import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;

import tools.LogUtil;
import tools.objects.ModelPush;
import tools.objects.ModelPushSys;
import tools.objects.ModelPushTie;
import tools.objects.community.ModelTie;
import tools.objects.community.ModelTieTheme;

public class TiePushRunnable implements Runnable
{
	int tietype;
	int floot;
	ModelTieTheme tieth;
	ModelTie tie;
	int besenduserid;
	int logindevice;
	int admintId;
	String tieth_title;
	
	public TiePushRunnable(ModelTieTheme tiethe,ModelTie tie,int type,int floot,int bereplyuserid,int logindevice,int adminid,String tieth_title)
	{
		this.tietype = type;
		this.tieth = tiethe;
		this.floot = floot;
		this.tie = tie;
		this.besenduserid = bereplyuserid;
		this.logindevice = logindevice;
		this.admintId = adminid;
		this.tieth_title = tieth_title;
	}
	
	public TiePushRunnable(ModelTieTheme tiethe,ModelTie tie,int type,int floot,int bereplyuserid,int logindevice,int adminid) {
		this.tietype = type;
		this.tieth = tiethe;
		this.floot = floot;
		this.tie = tie;
		this.besenduserid = bereplyuserid;
		this.logindevice = logindevice;
		this.admintId = adminid;
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
		ModelPushTie model = new ModelPushTie();
		model.setTietype(tietype);
		model.setTieth(tieth);
		model.setTie(tie);
		model.setFloot(floot);
		model.setAdminid(admintId);
		model.setTime(Calendar.getInstance().getTimeInMillis());
		model.setTieth_title(tieth_title);
		
		ModelPush pushmess = new ModelPush();
		pushmess.setType(4);
		pushmess.setTiemess(model);
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
		ModelPushTie model = new ModelPushTie();
		model.setTietype(tietype);
		model.setTieth(tieth);
		model.setTie(tie);
		model.setFloot(floot);
		model.setAdminid(admintId);
		model.setTime(Calendar.getInstance().getTimeInMillis());
		//构建消息
		String messMain = new Gson().toJson(model);
		
		String description = "收到一条信息";
		Message message = new Message.IOSBuilder()
             .description(description)
             .soundURL("default")    // 消息铃声
             .badge(1)               // 数字角标
             .category("action")     // 快速回复类别
             .extra("type", 4+"")  // 自定义键值对
             .extra("content", messMain)
             .build();
		
		return message;
	}
	
	private void sendMessage() throws Exception  {
		LogUtil.v(this,"start to send message!");
		LogUtil.v(this, "login device: "+this.logindevice);
		//ios测试环境
//		Constants.useSandbox();
//		Constants.useOfficial();
		Message message = null;		
	   Sender sender = null;
	   if (this.logindevice == 0) {	
		   Constants.useOfficial();
		   sender = new Sender("DxBCH7FvGmmESAzSr0/WqA==");
	   	message = buildMessage();
	   }else{
		   LogUtil.v(this, "is going to send to iphone besenduserid "+ besenduserid);
//		   Constants.useSandbox();
		   sender = new Sender("bJZyK19S50InYa2LySf25Q==");
		   message = buildIOSMessage();
	   }
	   Result result = null;

	   LogUtil.v(this,"Message info: " +" message content: "+message.getPayload() + "besendid: "+besenduserid);
	   if (this.besenduserid > 0)
		   result = sender.sendToAlias(message, String.valueOf(this.besenduserid), 2);
//	   Result result = sender.sendToAlias(message, String.valueOf(this.besenduserid), 2); //根据alias，发送消息到指定设备上，重试2次。
	   LogUtil.v("Server response: " + "MessageId: " + result.getMessageId()
	            + " ErrorCode: " + result.getErrorCode().toString()
	            + " Reason: " + result.getReason());
	}
	
}