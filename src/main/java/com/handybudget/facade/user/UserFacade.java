package com.handybudget.facade.user;

import java.util.List;


import com.handybudget.domains.view.UserView;
import com.handybudget.service.UserService;

public class UserFacade {

	private UserService userService = new UserService();
	private UserConverter userConverter = new UserConverter();
	
	public List<UserView> getUsers(){
		
		return userConverter.convert(userService.getUsers());
		
	}
}
