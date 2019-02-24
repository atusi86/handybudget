package com.handybudget.service;

import java.util.List;

import org.json.JSONObject;

import com.handybudget.database.dao.UserDao;
import com.handybudget.database.domains.User;
import com.handybudget.util.GeneralHelper;
import com.handybudget.util.PasswordUtil;

public class UserService {

	private UserDao userDao = new UserDao();

	public List<User> getUsers() {
		return userDao.getUsers();
	}

	public static boolean checkPassword(String email, String password) {

		UserDao ud = new UserDao();
		String storedPassword = (String) ud.getPasswordByEmail(email);

		if (!GeneralHelper.isEmptyString(storedPassword)) {
			return PasswordUtil.validatePassword(password, storedPassword);
		}
		return false;

	}

	public static boolean isExistingUser(String email) {

		UserDao ud = new UserDao();
		String storedPassword = (String) ud.getPasswordByEmail(email);

		if (GeneralHelper.isEmptyString(storedPassword)) {
			return false;
		}
		return true;
	}

	public static JSONObject registrateUser(String name, String email, String password) {

		JSONObject result = new JSONObject();

		User newUser = new User();
		newUser.setName(name);
		newUser.setEmail(email);

		String encryptedPassword = PasswordUtil.hashPassword(password);

		newUser.setPassword(encryptedPassword);

		try {
			UserDao ud = new UserDao();
			ud.addUser(newUser);

			result.put("msg", "Registration was successfull. Please log in.");

		} catch (Exception e) {
			result.put("msg", "Registration failed, try again later.");
		}

		return result;

	}

}
