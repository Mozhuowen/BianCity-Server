package service;

import tools.objects.ResponseSubscri;

public interface subscriService
{
	ResponseSubscri getSubscri(int userid,int townid);
	ResponseSubscri doSubscri(int userid,int townid,int action);
}