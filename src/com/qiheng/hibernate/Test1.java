package com.qiheng.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class Test1 {
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
		
		Person person1 = new Person();
		person1.setName("zhangsan");
		person1.setAge(21);
		person1.setAddress("Beijing");
		
		Person person2 = new Person();
		person2.setName("lisi");
		person2.setAge(40);
		person2.setAddress("Shanghai");
		
		IdCard idCard1 = new IdCard();
		idCard1.setNumber((long) 12345678);
		
		IdCard idCard2 = new IdCard();
		idCard2.setNumber((long) 87654321);
		
		person1.setIdCard(idCard1);
		person2.setIdCard(idCard2);
		
		idCard1.setPerson(person1);
		idCard2.setPerson(person2);
		
		try{
			tx = session.beginTransaction();
			session.save(person1);
			session.save(person2);
			
			
			tx.commit();
		}catch (Exception e) {
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
		
		
		}finally{
			session.close();
		}
		
	}		
}

