package dao.Impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.TieReplyDao;
import domain.TieReply;

public class TieReplyDaoImpl extends HibernateDaoSupport implements TieReplyDao
{

	@Override
	public TieReply get(Integer id) {
		return this.getHibernateTemplate().get(TieReply.class, id);
	}

	@Override
	public Integer save(TieReply tie) {
		return (Integer)this.getHibernateTemplate().save(tie);
	}

	@Override
	public void update(TieReply tie) {
		this.getHibernateTemplate().update(tie);
	}
	
}