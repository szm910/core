package com.shizm.service;

import com.shizm.dao.UserDao;
import com.shizm.model.User;

public class UserService {
	
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public int userCount() {
		return userDao.getAllUsers().size();
	}
	
	public boolean saveUser(User user){
		userDao.save(user);
		return true;
	}

}
