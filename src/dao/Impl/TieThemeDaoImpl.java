package dao.Impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

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
		return (List<ModelTieTheme>)this.getHibernateTemplate().find("select new tools.objects.community.ModelTieTheme(t) from TieTheme t where t.parentown=? and t.top=1 order by time desc",t);
	}

	@Override
	public List<ModelTieTheme> getTies(town t) {
		return (List<ModelTieTheme>)this.getHibernateTemplate().find("select new tools.objects.community.ModelTieTheme(t) from TieTheme t where t.parentown=? and t.top=0 order by time desc", t);
	}	
}