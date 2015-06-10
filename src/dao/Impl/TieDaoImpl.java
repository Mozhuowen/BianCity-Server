package dao.Impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.TieDao;
import domain.Tie;

public class TieDaoImpl extends HibernateDaoSupport implements TieDao
{

	@Override
	public Tie get(Integer id) {
		return this.getHibernateTemplate().get(Tie.class, id);
	}

	@Override
	public Integer save(Tie tie) {
		return (Integer)this.getHibernateTemplate().save(tie);
	}

	@Override
	public void update(Tie tie) {
		this.getHibernateTemplate().saveOrUpdate(tie);
	}
	
}