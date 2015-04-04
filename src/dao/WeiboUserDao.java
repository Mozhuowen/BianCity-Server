package dao;

import domain.WeiboUser;

public interface WeiboUserDao
{
	WeiboUser get(Integer id);
	Integer save(WeiboUser ws);
	void update(WeiboUser ws);
	WeiboUser getByUid(String uid);
}