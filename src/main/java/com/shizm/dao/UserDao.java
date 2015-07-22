package com.shizm.dao;

import java.util.List;

import org.hibernate.Query;

import com.shizm.model.User;

/**
 * 
 * @author shizm
 *
 */
public class UserDao extends BaseDao {

	public boolean save(User user) {
		System.out.println(user);
		if (user.getId() != null) {
			getSession().getTransaction().begin();
			User oldUser = (User) getSession().get(User.class, user.getId());
			oldUser.setName(user.name);
			getSession().update(oldUser);
			getSession().getTransaction().commit();
			return true;
		} else {
			getSession().save(user);
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		String hsql = "from User";
		Query query = getSession().createQuery(hsql);
		return query.list();
	}
}
