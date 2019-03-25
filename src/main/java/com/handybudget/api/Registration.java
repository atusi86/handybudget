package com.handybudget.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.handybudget.service.UserService;
import com.handybudget.servlet.WebAction;
import com.handybudget.util.GeneralHelper;

public class Registration extends WebAction {

	@Override
	public JSONObject processRequest(HttpServletRequest request, HttpServletResponse response, JSONObject postData) {

		JSONObject object = new JSONObject();

		String name = postData.optString("name");
		String email = postData.optString("email");
		String password = postData.optString("password");
		String passwordAgain = postData.optString("password_again");

		if (GeneralHelper.isEmptyString(name) && GeneralHelper.isEmptyString(email) && GeneralHelper.isEmptyString(password) && GeneralHelper.isEmptyString(passwordAgain)) {
			object.put("type", "dispatch");
			object.put("jsp", "registration");
		} else {
			if (GeneralHelper.isEmptyString(name) || GeneralHelper.isEmptyString(email) || GeneralHelper.isEmptyString(password) || GeneralHelper.isEmptyString(passwordAgain)) {

				request.setAttribute("msg", "Please fill in all fields.");

			} else if (!password.equals(passwordAgain)) {

				request.setAttribute("msg", "Passwords not equals.");

			} else if (isUserExists(email)) {

				request.setAttribute("msg", "User already exists.");

			} else {

				JSONObject regResult = UserService.registrateUser(name, email, password);

				request.setAttribute("msg", regResult.optString("msg"));

			}

			object.put("type", "dispatch");
			object.put("jsp", "registration");
		}

		request.setAttribute("randomString", GeneralHelper.getRandomString());

		return object;
	}

	private boolean isUserExists(String userName) {

		UserService us = new UserService();
		return us.isExistingUser(userName);

	}

	@Override
	public String getAuthenticationClassName() {
		// TODO Auto-generated method stub
		return null;
	}

}
