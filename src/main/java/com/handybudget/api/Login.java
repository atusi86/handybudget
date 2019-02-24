package com.handybudget.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.handybudget.service.TokenService;
import com.handybudget.service.UserService;
import com.handybudget.servlet.WebAction;
import com.handybudget.util.GeneralHelper;

public class Login extends WebAction {

	@Override
	public JSONObject processRequest(HttpServletRequest request, HttpServletResponse response, JSONObject postData) {

		String email = postData.optString("email");
		String password = postData.optString("password");

		JSONObject object = new JSONObject();

		Boolean isValidPassword = null;
		if (!GeneralHelper.isEmptyString(email) && !GeneralHelper.isEmptyString(password)) {
			isValidPassword = UserService.checkPassword(email, password);

			if (isValidPassword) {
				String token = TokenService.getTokenForUser(email);
				object.put("authHeader", token);
			}

		}

		if (isValidPassword != null && isValidPassword) {
			object.put("type", "forward");
			object.put("uri", "/api/main");
		} else if (isValidPassword != null && !isValidPassword) {
			object.put("type", "dispatch");
			object.put("jsp", "login");
			request.setAttribute("isValidPassword", false);
		} else {
			object.put("type", "dispatch");
			object.put("jsp", "login");
		}

		return object;
	}

	@Override
	public String getAuthenticationClassName() {
		// TODO Auto-generated method stub
		return null;
	}

}
