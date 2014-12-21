package com.qiheng.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class Test2 {
	public static SessionFactory sessionFactory;

	static {
		try {
			Configuration cfg = new Configuration().configure();
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
					.applySettings(cfg.getProperties()).buildServiceRegistry();
			sessionFactory = cfg.buildSessionFactory(serviceRegistry);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;

		Person person1 = null;

		Person person2 = null;

		IdCard idCard1 = null;

		IdCard idCard2 = null;

		try {
			tx = session.beginTransaction();
			person1 = (Person) session.get(Person.class , "402880e54a6b35a3014a6b35a4a00000");

			System.out.println(person1.getIdCard());
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}

		} finally {
			session.close();
		}

	}
}
