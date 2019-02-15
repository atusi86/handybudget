package com.handybudget.service;

import java.util.List;
import com.handybudget.database.dao.UserDao;
import com.handybudget.database.domains.User;


public class UserService {

	private UserDao userDao = new UserDao();
	
	public List<User> getUsers(){
		return userDao.getUsers();
	}
	
	
	
}
