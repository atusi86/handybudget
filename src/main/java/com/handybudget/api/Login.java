package com.handybudget.api;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.handybudget.servlet.IServletAction;

public class Login implements IServletAction {

	@Override
	public String getAuthenticationClassName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject processRequest(HttpServletRequest request, HttpServletResponse response, JSONObject postData) {

		String userName = postData.optString("username");
		String password = postData.optString("password");

		JSONObject object = new JSONObject();

		if ("abcd1234".equals(password) && "test@example.com".equals(userName)) {
			object.put("msg", "succesfully logged in");
		} else {
			object.put("msg", "access denied");
		}

		return object;
	}

}
