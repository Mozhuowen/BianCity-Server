package dao.Impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import tools.objects.community.ModelTie;
import dao.TieDao;
import domain.Tie;
import domain.TieTheme;

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
	public List<ModelTie> getTies(TieTheme tieth) {		
		return (List<ModelTie>)this.getHibernateTemplate().find("select new tools.objects.community.ModelTie(t) from Tie t where t.tieth=? order by t.time asc", tieth);
	}
	
}