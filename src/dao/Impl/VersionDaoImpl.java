package dao.Impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.VersionDao;
import domain.Version;

public class VersionDaoImpl extends HibernateDaoSupport implements VersionDao
{

	@Override
	public Version get(Integer id) {		
		return (Version)this.getHibernateTemplate().get(Version.class, id);
	}

	@Override
	public Integer save(Version v) {	
		return (Integer)this.getHibernateTemplate().save(v);
	}

	@Override
	public void update(Version v) {
		this.getHibernateTemplate().saveOrUpdate(v);
	}

	@Override
	public Version getLatestVersion() {
		List<Version> list = this.getHibernateTemplate().find("from Version v order by v.versioncode desc");
		if (list.size() == 0)
			return null;
		else
			return list.get(0);
	}
	
}