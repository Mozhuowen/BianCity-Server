package dao.Impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.WeiboUserDao;
import domain.WeiboUser;

public class WeiboUserDaoImpl extends HibernateDaoSupport implements WeiboUserDao
{

	@Override
	public WeiboUser get(Integer id) {
		return this.getHibernateTemplate().get(WeiboUser.class, id);
	}

	@Override
	public Integer save(WeiboUser ws) {
		// TODO Auto-generated method stub
		return (Integer)this.getHibernateTemplate().save(ws);
	}

	@Override
	public void update(WeiboUser ws) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().saveOrUpdate(ws);
	}

	@Override
	public WeiboUser getByUid(String uid) {
		// TODO Auto-generated method stub
		List<WeiboUser> list = this.getHibernateTemplate().find("from WeiboUser w where w.id=?",uid);
		if (list.size() == 0)
			return null;
		else
			return list.get(0);
	}
	
}