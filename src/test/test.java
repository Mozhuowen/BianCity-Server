package test;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.townDao;
import dao.Impl.townDaoImpl;
import domain.putao;
import domain.town;
import domain.users;

public class test
{
	public static void main(String[] args) {
		test t = new test();
		t.testputao();
		HibernateUtil.sessionFactory.close();
	}
	
	private void testputao()
	{
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();	
		users u = new users();
		u.setName("awen");
		session.save(u);
		town t = new town();
		t.setName("awentest");
		t.setOwner(u);
		session.save(t);
		putao p = new putao();
		p.setTitle("tste");
		p.setParenttown(t);
		List<String> list = new ArrayList<String>();
		list.add("test");
		list.add("fewr3");
//		p.setImages(list);
		System.out.println(session.save(p));
		tx.commit();
		HibernateUtil.closeSession();
	}
}