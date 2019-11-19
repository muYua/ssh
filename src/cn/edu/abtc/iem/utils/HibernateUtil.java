package cn.edu.abtc.iem.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	public static SessionFactory getSessionFactory()
	{
		return new Configuration().configure().buildSessionFactory();
	}
}
