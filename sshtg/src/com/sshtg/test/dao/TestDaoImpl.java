package com.sshtg.test.dao;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.sshtg.test.model.Model;

@Repository("testDao")
public class TestDaoImpl implements TestDao {
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	@Resource(name="sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public void testSave(Model model) {
		System.out.println("testDao..testSave..begin..");
		Session session = sessionFactory.openSession();
		System.out.println(session);
		session.beginTransaction();
		session.save(model);
		session.getTransaction().commit();
		System.out.println("testDao..testSave..end!");
	}

}
