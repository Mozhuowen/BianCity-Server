package dao.Impl;

import dao.*;
import domain.putao;
import domain.town;
import domain.users;

import java.util.*;

import org.apache.tomcat.jni.User;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import tools.LogUtil;

public class usersDaoImpl extends HibernateDaoSupport implements usersDao
{

	@Override
	public users get(Integer u) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(users.class, u);
	}

	@Override
	public Integer save(users u) {
		// TODO Auto-generated method stub
		return (Integer)this.getHibernateTemplate().save(u);
	}

	@Override
	public void update(users u) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().saveOrUpdate(u);
//		this.getSession().beginTransaction().commit();
	}

	@Override
	public users getByUsername(String username) {
		// TODO Auto-generated method stub
		List list = this.getHibernateTemplate().find("from users where name=?", username);
		if (list.size()==0)
			return null;
		else 
			return (users)list.get(0);
	}

	@Override
	public users getByEmail(String email) {
		// TODO Auto-generated method stub
		List list = this.getHibernateTemplate().find("from users where email=?", email);
		if (list.size()==0)
			return null;
		else 
			return (users)list.get(0);
	}

	@Override
	public Boolean checkdoGood(int type,int userid, int id) {
		// TODO Auto-generated method stub
		List list = null;
		switch(type){
		case 0:
			list = this.getHibernateTemplate().find("select 1 from users u,town t where t.townid=? and u.usersid = ? and u in elements(t.dogoodusers)",id,userid);
			break;
		case 1:
			list = this.getHibernateTemplate().find("select 1 from users u,putao p where p.putaoid=? and u.usersid = ? and u in elements(p.dogoodusers)",id,userid);
			break;
		case 2:
			list = this.getHibernateTemplate().find("select 1 from users u,comment c where c.commentid=? and u.usersid = ? and u in elements(c.dogoodusers)",id,userid);
			break;
		case 3:
			list = this.getHibernateTemplate().find("select 1 from users u,MessBoard m where m.messboardid=? and u.usersid = ? and u in elements(m.dogoodusers)",id,userid);
			break;
		}
		if (list.size()==0)
			return false;
		else
			return true;
	}

	@Override
	public Boolean checkSubscirTown(int userid, int townid) {
		List list = this.getHibernateTemplate().find("select 1 from users u,town t where t.townid=? and u.usersid=? and u in elements(t.subscriusers)",townid,userid);
		if (list.size()==0)
			return false;
		else
			return true;
	}

	@Override
	public Boolean checkFavorite(int userid, int putaoid) {
		List list = this.getHibernateTemplate().find("select 1 from users u,putao p where p.putaoid=? and u.usersid=? and u in elements(p.favoriteusers)",putaoid,userid);
		if (list.size()==0)
			return false;
		else
			return true;
	}

	@Override
	public Integer getFans(int userid) {
		LogUtil.v("userid: "+userid);
		Integer fans = 0;
		try{
		fans = (Integer)this.getHibernateTemplate().find("select u.fans from users u where u.usersid=?",userid).get(0);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return fans;
	}
	/**为提高性能使用原始sql查询*/
	@Override
	public Integer getBegoodCount(int userid) {
		String sql = "select sum(test) as count from "
				+ "(select t.goods as test from pt_users u left join pt_town t "
				+ "on (t.town_id in (select t1.town_id from pt_town t1 where t1.users_id=u.users_id)) where u.users_id=" + userid
				+ " union all select p.goods as test from pt_users u1 left join pt_putao p "
				+ "on (p.putao_id in (select p1.putao_id from pt_putao p1 where p1.users_id=u1.users_id)) where u1.users_id=" + userid 
				+ " union all select c.goods as test from pt_users u2 left join pt_comment c "
				+ "on (c.comment_id in (select c1.comment_id from pt_comment c1 where c1.users_id=u2.users_id)) where u2.users_id=" + userid
				+ " union all select m.goods as test from pt_users u3 left join pt_messboard m "
				+ "on (m.messboard_id in (select m1.messboard_id from pt_messboard m1 where m1.users_id=u3.users_id)) where u3.users_id="+ userid+") t";
		List l = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).addScalar("count").list();
		if (l.size() == 0 || l.get(0) == null)
			return 0;
		Integer i = Integer.valueOf(l.get(0).toString());
		LogUtil.v("SQL result : "+i);
		return i;
	}

	@Override
	public Boolean checkOwnTown(int userid, int townid) {
		List<town> list = this.getHibernateTemplate().find("from users u,town t where u.usersid=? and t.townid=? and t in elements(u.mytowns)",userid,townid);
		if (list.size() == 0)
			return false;
		else
			return true;
	}

	@Override
	public Boolean checkOwnPutao(int userid, int putaoid) {
		List<town> list = this.getHibernateTemplate().find("from users u,putao p where u.usersid=? and p.putaoid=? and p in elements(u.myputao)",userid,putaoid);
		if (list.size() == 0)
			return false;
		else
			return true;
	}

	@Override
	public Boolean checkOwnComment(int userid, int commentid) {
		List<town> list = this.getHibernateTemplate().find("from users u,comment c where u.usersid=? and c.commentid=? and c in elements(u.comments)",userid,commentid);
		if (list.size() == 0)
			return false;
		else
			return true;
	}

	@Override
	public Boolean checkOwnMess(int userid, int messid) {
		List<town> list = this.getHibernateTemplate().find("from users u,MessBoard m where u.usersid=? and m.messboardid=? and m in elements(u.mess)",userid,messid);
		if (list.size() == 0)
			return false;
		else
			return true;
	}
	
}