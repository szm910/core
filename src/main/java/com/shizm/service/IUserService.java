package com.shizm.service;

import java.util.List;

import com.shizm.dao.IUserDao;
import com.shizm.model.User;

public interface IUserService {
	public IUserDao getUserDao();

	public void setUserDao(IUserDao userDao);
	
	public int userCount();
	
	public boolean saveUser(User user);
	
	public User getUser(User user);
	public List<User> getUsers();
}
