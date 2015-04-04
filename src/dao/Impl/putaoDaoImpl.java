package dao.Impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.putaoDao;
import domain.comment;
import domain.putao;
import domain.town;

public class putaoDaoImpl extends HibernateDaoSupport implements putaoDao
{

	@Override
	public putao get(Integer id) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(putao.class, id);
	}

	@Override
	public Integer save(putao t) {
		// TODO Auto-generated method stub
		Integer i = (Integer)this.getHibernateTemplate().save(t);
		return i;
	}

	@Override
	public void update(putao t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().saveOrUpdate(t);
	}
	@Override
	public boolean checkPutao(int putaoid) {
		putao p = get(putaoid);
		if (p != null)
			return true;
		else
			return false;
	}

	@Override
	public List<putao> loadMorePutao(town townx, int position) {
		DetachedCriteria criteria = DetachedCriteria.forClass(putao.class);
		criteria.add(Restrictions.eq("parenttown", townx)).addOrder(Order.desc("createtime"));
		List<putao> list = this.getHibernateTemplate().findByCriteria(criteria, position, 10);
		return list;
	}

	@Override
	public Integer getgoods(int putaoid) {
		Integer good = (Integer)this.getHibernateTemplate().find("select goods from putao p where p.putaoid=?", putaoid).get(0);
		return good;
	}
}