package com.handybudget.util;

import org.json.JSONObject;

import com.handybudget.service.TokenService;

public class TokenAuthentication {

	public JSONObject checkToken(String token) {

		if (!GeneralHelper.isEmptyString(token)) {
			TokenService ts = new TokenService();
			return ts.checkToken(token);
		} else {
			return new JSONObject().put("isAuthenticated", false);
		}

	}

}
