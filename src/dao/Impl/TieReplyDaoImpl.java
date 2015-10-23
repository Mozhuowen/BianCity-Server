package dao.Impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import tools.objects.community.ModelTieReply;
import dao.TieReplyDao;
import domain.Tie;
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

	@Override
	public List<ModelTieReply> getreplys(Tie tie) {		
		return (List<ModelTieReply>)this.getHibernateTemplate().find("select new tools.objects.community.ModelTieReply(tr) from TieReply tr where tr.tie=? order by time asc", tie);
	}
	
}