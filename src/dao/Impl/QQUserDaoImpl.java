package dao.Impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.QQUserDao;
import domain.QQUser;

public class QQUserDaoImpl extends HibernateDaoSupport implements QQUserDao
{
	@Override
	public QQUser get(Integer id) {
		return this.getHibernateTemplate().get(QQUser.class,id);
	}

	@Override
	public Integer save(QQUser user) {
		return (Integer)this.getHibernateTemplate().save(user);
	}

	@Override
	public void update(QQUser user) {
		this.getHibernateTemplate().saveOrUpdate(user);
	}

	@Override
	public QQUser getByOpenid(String openid) {
		List<QQUser> list = this.getHibernateTemplate().find("from QQUser q where q.openid=?",openid);
		if (list.size() == 0)
			return null;
		else 
			return list.get(0);
	}
	
}