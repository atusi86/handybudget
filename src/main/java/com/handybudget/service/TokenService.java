package com.handybudget.service;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.json.JSONObject;

import com.handybudget.database.dao.TokenDao;
import com.handybudget.database.dao.UserDao;
import com.handybudget.database.domains.Token;
import com.handybudget.database.domains.User;

public class TokenService {

	public static String generateNewToken() {

		String tokenIdentifier = UUID.randomUUID().toString();

		return tokenIdentifier;
	}

	public static JSONObject createTokenForUser(int userId) {

		JSONObject result = new JSONObject();

		TokenDao tokenDao = new TokenDao();
		String token = tokenDao.addToken(userId);

		result.put("token", token);

		return result;

	}

	public static String getTokenForUser(String email) {

		UserDao ud = new UserDao();
		User user = ud.getUserByEmail(email);

		if (user != null) {
			TokenDao td = new TokenDao();
			Token tokenObject = td.getTokenByUser(user);

			if (tokenObject != null) {
				int expiresIn = tokenObject.getExpiresIn();
				Date createDate = tokenObject.getCreate_timestamp();

				Calendar createdCal = Calendar.getInstance();
				createdCal.setTime(createDate);
				createdCal.add(Calendar.HOUR_OF_DAY, expiresIn);

				Calendar currentCal = Calendar.getInstance();

				if (createdCal.after(currentCal)) {
					return tokenObject.getIdentifier();
				} else {
					String newToken = generateNewToken();
					td.updateTokenForUser(user, newToken);

					return newToken;

				}
			} else {

				return TokenService.createTokenForUser(user.getId()).optString("token");

			}

		}
		return null;

	}

	public static JSONObject checkToken(String authToken) {

		JSONObject result = new JSONObject();

		TokenDao td = new TokenDao();
		Token token = td.getTokenByIdentifier(authToken);
		if (token != null) {

			User user = token.getUser();
			int userId = user.getId();

			int expiresIn = token.getExpiresIn();

			Date createDate = token.getCreate_timestamp();

			Calendar createdCal = Calendar.getInstance();
			createdCal.setTime(createDate);
			createdCal.add(Calendar.HOUR_OF_DAY, expiresIn);

			Calendar currentCal = Calendar.getInstance();

			if (createdCal.after(currentCal)) {
				result.put("isAuthenticated", true);

			} else {
				result.put("isAuthenticated", false);

			}

			result.put("userId", userId);

		} else {
			result.put("isAuthenticated", false);
		}

		return result;
	}

}
