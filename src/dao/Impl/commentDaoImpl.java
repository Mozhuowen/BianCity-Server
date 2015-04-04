package dao.Impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.commentDao;
import domain.comment;
import domain.putao;

public class commentDaoImpl extends HibernateDaoSupport implements  commentDao 
{
	@Override
	public comment get(Integer i) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(comment.class, i);
	}

	@Override
	public Integer save(comment c) {
		// TODO Auto-generated method stub
		Integer i = (Integer)this.getHibernateTemplate().save(c);
		return i;
	}

	@Override
	public void update(comment c) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().saveOrUpdate(c);		
	}

	@Override
	public List<comment> loadMoreComments(putao pt,int position) {
		DetachedCriteria criteria = DetachedCriteria.forClass(comment.class);
		criteria.add(Restrictions.eq("putaox", pt)).addOrder(Order.desc("time"));
		List<comment> list = this.getHibernateTemplate().findByCriteria(criteria, position, 10);
		return list;
	}

	@Override
	public Integer getGoods(int commentid) {
		Integer good = (Integer)this.getHibernateTemplate().find("select goods from comment c where c.commentid=?", commentid).get(0);
		return good;
	}
	
}