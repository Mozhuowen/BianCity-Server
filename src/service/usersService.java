package service;

import java.util.Calendar;

import tools.objects.ModelRegisteQQ;
import tools.objects.ModelRegisteWb;
import tools.objects.ResponseCName;
import tools.objects.ResponseLogin;
import tools.objects.ResponsePutao;
import tools.objects.ResponseRegiste;
import tools.objects.ResponseSimple;
import tools.objects.ResponseSubscri;
import tools.objects.ResponseTown;
import tools.objects.ResponseUser;
import domain.*;
import dao.*;
import dao.Impl.*;

public interface usersService
{
	public ResponseRegiste registe(String name,String password,String email,String cover,String imei,String sv,String phonemodel,String brand);
	public boolean checkUsername(String name);
	public boolean checkEmail(String email);
	public ResponseLogin checklogin(String name,String passwordmd5,String emei,String sv,String phonemodel,String brand);
	public boolean checkUserid(int id);
	public boolean checkToken(int userid,String token);
	
	public ResponseLogin checkloginByWb(String uid,String wbtoken,Calendar expire,String emei,String sv,String phonemodel,String brand);
	public ResponseLogin checkloginByQQ(String openid,String qqtoken,Calendar expire,String emei,String sv,String phonemodel,String brand);
	public ResponseRegiste regByWb(ModelRegisteWb userinfo,int puid);
	public ResponseRegiste regByQQ(ModelRegisteQQ userinfo,int puid);
	public boolean checkPtoken(int puid,String ptoken);
	public ResponseCName cname(int puid,String name);
	public int getFans(int userid);
	public ResponseUser getUserInfo(int userid,boolean onlystatis);
	public ResponseSubscri getSubscris(int userid);
	public ResponsePutao getFavorite(int userid);
	public ResponseSimple cwall(int userid,String wall);
	public ResponseSimple cuserinfo(int userid,String cover,String username,String location,String sex);
}