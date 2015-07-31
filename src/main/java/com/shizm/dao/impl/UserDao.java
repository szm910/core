package com.shizm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.shizm.dao.IUserDao;
import com.shizm.dao.base.BaseDao;
import com.shizm.model.User;

/**
 * 
 * @author shizm
 *
 */
@SuppressWarnings("unchecked")
@Repository("userDao")
public class UserDao extends BaseDao<User, String>implements IUserDao {
	public List<User> getAllUsers() {
		String hsql = "from User";
		Session session = getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hsql);
		return query.list();
	}

	public User getUser(User user) {
		if (user != null) {
			if (user.getId() != null && !user.getId().equals("")) {
				return this.get(user.getId());
			} else {
				StringBuilder sbHql = new StringBuilder();
				sbHql.append("from User where 1=1 ");
				ArrayList<Object> params = new ArrayList<Object>();
				if (user.getUserName() != null && !user.getUserName().equals("")) {
					sbHql.append("and userName = ?");
					params.add(user.getUserName().trim());
				}
				if (user.getPassword() != null && !user.getPassword().equals("")) {
					sbHql.append("and password = ?");
					params.add(user.getPassword().trim());
				}
				return this.getByHQL(sbHql.toString(), params.toArray());
			}
		} else {
			return null;
		}
	}
}
