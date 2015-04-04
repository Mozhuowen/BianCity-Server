package service;

import domain.GeoInfo;
import tools.objects.ResponseMess;

public interface messService
{
	ResponseMess submitMess(int townid,int userid,String content,GeoInfo geo);
	ResponseMess getMess(int townid);
	ResponseMess loadMoreMess(int townid,int position,int userid);
}