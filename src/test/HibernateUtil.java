package test;

import org.hibernate.*;
import org.hibernate.cfg.*;

public class HibernateUtil
{
	public static final SessionFactory sessionFactory;

	static
	{
		try
		{
			//
			Configuration configuration=new Configuration().configure();
			//
			sessionFactory = configuration.buildSessionFactory();
		}
		catch (Throwable ex)
		{
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	
	//
	public static final ThreadLocal session = new ThreadLocal();

	public static Session currentSession() throws HibernateException
	{
		Session s = (Session) session.get();
		//
		if (s == null)
		{
			s = sessionFactory.openSession();
			//
			session.set(s);
		}
		return s;
	}

	public static void closeSession() throws HibernateException 
	{
		Session s = (Session) session.get();
		if (s != null)
		s.close();
		session.set(null);
	}
}