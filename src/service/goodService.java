package service;

import tools.objects.ResponseGood;

public interface goodService
{
	ResponseGood getGoods(int type,int userid,int id);
	ResponseGood doGood(int type,int userid,int id,int action);
}