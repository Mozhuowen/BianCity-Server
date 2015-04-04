package dao.Impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.MessBoardDao;
import domain.MessBoard;
import domain.comment;
import domain.town;

public class MessBoardDaoImpl extends HibernateDaoSupport implements MessBoardDao
{

	@Override
	public MessBoard get(Integer id) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(MessBoard.class, id);
	}

	@Override
	public Integer save(MessBoard mess) {
		// TODO Auto-generated method stub
		return (Integer)this.getHibernateTemplate().save(mess);
	}

	@Override
	public void update(MessBoard mess) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().saveOrUpdate(mess);
	}

	@Override
	public List<MessBoard> loadMoreMess(town townx, int position) {
		DetachedCriteria criteria = DetachedCriteria.forClass(MessBoard.class);
		criteria.add(Restrictions.eq("townx", townx)).addOrder(Order.desc("time"));
		List<MessBoard> list = this.getHibernateTemplate().findByCriteria(criteria, position, 10);
		return list;
	}

	@Override
	public Integer getGoods(int messboardid) {
		Integer good = (Integer)this.getHibernateTemplate().find("select goods from MessBoard m where m.messboardid=?", messboardid).get(0);
		return good;
	}
	
}