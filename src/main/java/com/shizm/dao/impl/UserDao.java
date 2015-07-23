package com.shizm.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.shizm.dao.IUserDao;
import com.shizm.dao.base.BaseDao;
import com.shizm.model.User;

/**
 * 
 * @author shizm
 *
 */
@Repository("userDao")
public class UserDao extends BaseDao<User,String> implements IUserDao {
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		String hsql = "from User";
		Session session = getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hsql);
		return query.list();
	}
}
