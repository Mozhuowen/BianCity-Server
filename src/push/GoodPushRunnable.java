package push;

import java.util.Calendar;

import com.google.gson.Gson;
import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;

import tools.LogUtil;
import tools.objects.ApplyTown;
import tools.objects.ModelPush;
import tools.objects.ModelPushGood;
import tools.objects.PackageComment;
import tools.objects.PackagePutao;

public class GoodPushRunnable implements Runnable
{
	private PackagePutao story;
	private ApplyTown town;
	private int userid;
	private String username;
	private String usercover;
	private int besenduserid;
	private int logindevice;
	private int goodtype;
	
	public GoodPushRunnable(int goodtype,int userid,String username,String usercover,int besenduserid,int logindevice,PackagePutao story,ApplyTown town) {
		this.goodtype = goodtype;
		this.userid = userid;
		this.username = username;
		this.usercover = usercover;
		this.besenduserid = besenduserid;
		this.logindevice = logindevice;
		this.story = story;
		this.town = town;
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
		ModelPushGood model = new ModelPushGood();
		model.setUserid(userid);
		model.setUsername( username);
		model.setUsercover(usercover);
		model.setGoodtype(goodtype);
		model.setTime(Calendar.getInstance().getTimeInMillis());
		switch (goodtype) {
		case 0:
			if (story != null)
				model.setStory(story);
			else
				throw new Exception();
			break;
		case 1:
			if (town !=null)
				model.setTown(town);
			else
				throw new Exception();
			break;
		case 2:
			if (story != null)
				model.setStory(story);
			else
				throw new Exception();
			break;
		}
		ModelPush pushmess = new ModelPush();
		pushmess.setType(2);
		pushmess.setGoodmess(model);
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
		
		return null;
	}
	
	private void sendMessageToAlias() throws Exception {
		LogUtil.v(this,"start to send message!");
		LogUtil.v(this, "login device: "+this.logindevice);
		//ios测试环境
//		Constants.useSandbox();
		Message message = null;
		//判断消息发送类型，android or IOS
		if (this.logindevice == 0)	//android
			message = buildMessage();
		else								//ios
			message = buildIOSMessage();
	   LogUtil.v(this,"Message info: " +" message content: "+message.getPayload());
	   Sender sender = new Sender("DxBCH7FvGmmESAzSr0/WqA==");
	   Result result = sender.sendToAlias(message, String.valueOf(this.besenduserid), 2); //根据alias，发送消息到指定设备上，重试2次。
	   LogUtil.v("Server response: " + "MessageId: " + result.getMessageId()
	            + " ErrorCode: " + result.getErrorCode().toString()
	            + " Reason: " + result.getReason());
	}
	
}