package dao.Impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import tools.LogUtil;
import tools.ModelSort;
import tools.SortAction.SortData;
import tools.TownSort;
import tools.objects.community.ModelTieTheme;
import tools.objects.community.ModelTopTie;
import dao.TieThemeDao;
import domain.TieTheme;
import domain.town;

public class TieThemeDaoImpl extends HibernateDaoSupport implements TieThemeDao
{

	@Override
	public TieTheme get(Integer id) {
		return this.getHibernateTemplate().get(TieTheme.class, id);
	}

	@Override
	public Integer save(TieTheme tie) {
		return (Integer)this.getHibernateTemplate().save(tie);
	}

	@Override
	public void update(TieTheme tie) {
		this.getHibernateTemplate().saveOrUpdate(tie);
	}

	@Override
	public List<ModelTieTheme> getTopTie(town t) {		
		return (List<ModelTieTheme>)this.getHibernateTemplate().find("select new tools.objects.community.ModelTieTheme(t) from TieTheme t where t.parentown=? and t.top=1 and t.visible=0 order by time desc",t);
	}

	@Override
	public List<ModelTieTheme> getTies(town t) {
		return (List<ModelTieTheme>)this.getHibernateTemplate().find("select new tools.objects.community.ModelTieTheme(t) from TieTheme t where t.parentown=? and t.top=0 and t.visible=0 order by time desc", t);
	}

	@Override
	public List<ModelSort> sortByTime(final town t) {
		final String hql = "select new tools.ModelSort(t.tiethemeid,0,0) from TieTheme t where t.parentown=:tt and t.top=0 and t.visible=0 order by time desc";
		@SuppressWarnings("unchecked")
		List<ModelSort> list = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				List<TownSort> result = session.createQuery(hql)
						.setParameter("tt", t)
						.setMaxResults(1000)
						.list();
				return result;
			}			
		});
		return list;
	}

	@Override
	public List<ModelSort> sortByReply(final town t) {
		final String hql = "select new tools.ModelSort(t.tiethemeid,1,t.goodcou) from TieTheme t where t.parentown=:tt and t.top=0 and t.visible=0 order by t.goodcou desc";
		@SuppressWarnings("unchecked")
		List<ModelSort> list = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				List<TownSort> result = session.createQuery(hql)
						.setParameter("tt", t)
						.setMaxResults(1000)
						.list();
				return result;
			}			
		});
		return list;
	}

	@Override
	public List<ModelSort> sortByGoodCou(final town t) {
		final String hql = "select new tools.ModelSort(t.tiethemeid,2,t.commentcou) from TieTheme t where t.parentown=:tt and t.top=0 and t.visible=0 order by t.commentcou desc";
		@SuppressWarnings("unchecked")
		List<ModelSort> list = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				List<TownSort> result = session.createQuery(hql)
						.setParameter("tt", t)
						.setMaxResults(1000)
						.list();
				return result;
			}			
		});
		return list;
	}

	@Override
	public List<TieTheme> getTargetTie(List<SortData> targetdata) {
		//拼接字符串
		StringBuffer intstr = new StringBuffer();
		for (int i=0;i<targetdata.size();i++) {
			if (intstr.length()>0)
				intstr.append(",");
			intstr.append(targetdata.get(i).getTownid());
		}
		String sql = "select * from pt_tietheme tt where tt.tietheme_id in ("+ intstr.toString() + ") order by find_in_set(tt.tietheme_id,'" + intstr.toString() + "')";
		LogUtil.v("getTargetTie info: sql "+sql);
		@SuppressWarnings("unchecked")
		List<TieTheme> l = this.getHibernateTemplate().getSessionFactory().getCurrentSession()
				.createSQLQuery(sql)
				.addEntity(TieTheme.class)
				.list();
		return l;
	}	
}