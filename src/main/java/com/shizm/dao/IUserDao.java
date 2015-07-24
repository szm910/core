package com.shizm.dao;

import java.util.List;

import com.shizm.dao.base.IBaseDao;
import com.shizm.model.User;

public interface IUserDao extends IBaseDao<User, String> {
	public List<User> getAllUsers();
}
