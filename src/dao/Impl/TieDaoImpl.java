package dao.Impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import tools.TownSort;
import tools.objects.community.ModelTie;
import dao.TieDao;
import domain.Tie;
import domain.TieTheme;
import domain.putao;

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

	@Override
	public List<ModelTie> getTies(final TieTheme tieth,final int position) {		
//		DetachedCriteria criteria = DetachedCriteria.forClass(Tie.class);
//		criteria.add(Restrictions.eq("tieth", tieth))
//			.add(Restrictions.eq("visible", 0))
//			.addOrder(Order.asc("time"));
//		return (List<ModelTie>)this.getHibernateTemplate().find("select new tools.objects.community.ModelTie(t) from Tie t where t.tieth=? order by t.time asc limit ?,15", tieth,position);
		final String hql = "select new tools.objects.community.ModelTie(t) from Tie t where t.tieth=:tt order by t.time asc";
		@SuppressWarnings("unchecked")
		List<ModelTie> list = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				List<ModelTie> result = session.createQuery(hql)
						.setParameter("tt", tieth)
						.setFirstResult(position)
						.setMaxResults(15)
						.list();
				return result;
			}			
		});
		return list;
	}
	
}