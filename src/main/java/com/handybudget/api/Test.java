package com.handybudget.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.handybudget.service.TokenService;
import com.handybudget.servlet.JSONAction;
import com.handybudget.util.TokenAuthentication;

public class Test extends JSONAction{

	@Override
	public JSONObject processRequest(HttpServletRequest request, HttpServletResponse response, JSONObject postData) {

		JSONObject result = new JSONObject();
		
		String token = TokenService.getTokenForUser("atusi86@gmail.com");
		
		result.put("token", token);
		
		return result;
	
	}

	@Override
	public String getAuthenticationClassName() {
		return TokenAuthentication.class.getCanonicalName();
	}

}
