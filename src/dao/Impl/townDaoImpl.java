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

import tools.LogUtil;
import tools.SortTownAction.SortData;
import tools.TownSort;
import domain.*;
import dao.*;

public class townDaoImpl extends HibernateDaoSupport implements townDao
{

	@Override
	public town get(Integer id) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(town.class, id);
	}

	@Override
	public Integer save(town t) {
		// TODO Auto-generated method stub
		return (Integer)this.getHibernateTemplate().save(t);
	}

	@Override
	public void update(town t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().saveOrUpdate(t);
	}

	@Override
	public town getBytownname(String name) {
		// TODO Auto-generated method stub
		List list = this.getHibernateTemplate().find("from town t where t.name=?", name);
		if (list.size() == 0)
			return null;
		else
			return (town)list.get(0);
	}

	@Override
	public Integer getGoods(int townid) {
		Integer good = (Integer)this.getHibernateTemplate().find("select goods from town t where t.townid=?", townid).get(0);
		return good;
	}

	@Override
	public Integer doGoods(int townid, int action) {
		town t = get(townid);
		Integer goods = 0;
		if (t == null)
			return 0;
		else {
			if (action == 0) {
				int x = t.getGoods();
				t.setGoods(++x);
			} else if(action == 1) {
				int x = t.getGoods();
				t.setGoods(--x);
			}
		}
		save(t);
		return t.getGoods();
	}

	@Override
	public List<TownSort> getHotTownByTime() {
		final String hql = "select new tools.TownSort(t.townid,0,0) from town t order by t.createtime desc";
		@SuppressWarnings("unchecked")
		List<TownSort> t = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				List<TownSort> result = session.createQuery(hql)
						.setMaxResults(200)
						.list();
				return result;
			}			
		});
		return t;
	}

	@Override
	public List<TownSort> getHotTownByGood() {
		final String hql = "select new tools.TownSort(t.townid,1,t.goods) from town t order by t.goods desc";
		@SuppressWarnings("unchecked")
		List<TownSort> t = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				List<TownSort> result = session.createQuery(hql)
						.setMaxResults(200)
						.list();
				return result;
			}			
		});
		return t;
	}

	@Override
	public List<TownSort> getHotTownByFans() {
		final String hql = "select new tools.TownSort(t.townid,2,t.subscris) from town t order by t.subscris desc";
		@SuppressWarnings("unchecked")
		List<TownSort> t = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				List<TownSort> result = session.createQuery(hql)
						.setMaxResults(200)
						.list();
				return result;
			}			
		});
		return t;
	}

	@Override
	public List<town> getTargetTown(List<SortData> targetdata) {
		//拼接字符串
		StringBuffer intstr = new StringBuffer();
		for (int i=0;i<targetdata.size();i++) {
			if (intstr.length()>0)
				intstr.append(",");
			intstr.append(targetdata.get(i).getTownid());
		}
//		String sql = "select * from pt_town t where t.town_id in ("+ intstr.toString() + ") order by instr('" + intstr.toString() + "',t.town_id)";
		String sql = "select * from pt_town t where t.town_id in ("+ intstr.toString() + ") order by find_in_set(t.town_id,'" + intstr.toString() + "')";
		LogUtil.v("townDaoImpl info: sql "+sql);
		@SuppressWarnings("unchecked")
		List<town> l = this.getHibernateTemplate().getSessionFactory().getCurrentSession()
				.createSQLQuery(sql)
				.addEntity(town.class)
				.list();
		return l;
	}

	@Override
	public List<TownSort> getNearTown(String geohash) {
		final String geostr = geohash + "%";
		final String hql = "select new tools.TownSort(t.townid,3,t.subscris+t.goods) from town t where t.geohash like :geohash order by (t.subscris+t.goods) desc";
		@SuppressWarnings("unchecked")
		List<TownSort> t = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				List<TownSort> result = session.createQuery(hql)
						.setString("geohash", geostr)
						.setMaxResults(200)
						.list();
				return result;
			}			
		});
		return t;
	}
	
}