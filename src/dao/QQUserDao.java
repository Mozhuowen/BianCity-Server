package dao;

import domain.QQUser;

public interface QQUserDao
{
	QQUser get(Integer id);
	Integer save (QQUser user);
	void update(QQUser user);
	QQUser getByOpenid(String openid);
}