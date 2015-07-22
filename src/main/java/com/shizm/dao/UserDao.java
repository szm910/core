package com.shizm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.shizm.model.User;

/**
 * 
 * @author shizm
 *
 */
public class UserDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	public boolean save(User user) {
		System.out.println(user);
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		String hsql = "from User";
		Query query = getSession().createQuery(hsql);
		return query.list();
	}
}
