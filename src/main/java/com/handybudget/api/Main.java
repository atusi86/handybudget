package com.handybudget.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.handybudget.service.TokenService;
import com.handybudget.servlet.WebAction;
import com.handybudget.util.GeneralHelper;

public class Main extends WebAction {

	@Override
	public JSONObject processRequest(HttpServletRequest request, HttpServletResponse response, JSONObject postData) {

		JSONObject result = new JSONObject();

		String authHeader = postData.optString("Authorization");

		if (!GeneralHelper.isEmptyString(authHeader)) {
			result.put("isAuthenticated", true);
			request.setAttribute("token", authHeader);
		} else {
			result.put("isAuthenticated", false);
		}

		
		return result;

	}

	@Override
	public String getAuthenticationClassName() {
		// TODO Auto-generated method stub
		return null;
	}

}
