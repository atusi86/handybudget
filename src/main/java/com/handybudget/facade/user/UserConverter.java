package com.handybudget.facade.user;

import java.util.ArrayList;
import java.util.List;
import com.handybudget.database.domains.User;
import com.handybudget.domains.view.UserView;


public class UserConverter {

	public List<UserView> convert(List<User> userList) {

		List<UserView> userViewList = new ArrayList<UserView>();

		for (User user : userList) {
			UserView userView = new UserView();
			userView.setEmail(user.getEmail());
			userView.setName(user.getName());
			userViewList.add(userView);
		}
		return userViewList;
	}

}
