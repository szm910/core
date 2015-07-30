package com.shizm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shizm.dao.IUserDao;
import com.shizm.model.User;
@Transactional
@Service("userService")
public class UserService {
	@Resource(name="userDao")
	private IUserDao userDao;
	
	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	
	public int userCount() {
		return userDao.getAllUsers().size();
	}
	
	public boolean saveUser(User user){
		userDao.save(user);
		return true;
	}
	
	public User getUser(User user){
		return userDao.getUser(user);
	}

}
