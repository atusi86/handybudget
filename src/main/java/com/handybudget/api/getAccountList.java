package com.handybudget.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.handybudget.database.domains.Account;
import com.handybudget.service.AccountService;
import com.handybudget.servlet.JSONAction;
import com.handybudget.util.TokenAuthentication;

public class getAccountList extends JSONAction {

	@Override
	public JSONObject processRequest(HttpServletRequest request, HttpServletResponse response, JSONObject postData) {

		JSONObject result = new JSONObject();
		int userId = postData.optInt("userId");

		List<Account> accountList = AccountService.getAccountListByUserId(userId);

		JSONArray accountArray = new JSONArray();
		for (Account account : accountList) {

			JSONObject accountObject = new JSONObject();
			accountObject.put("id", account.getId());
			accountObject.put("name", account.getName());
			accountObject.put("desc", account.getDescription());
			accountObject.put("created", account.getCreate_timestamp());

			accountArray.put(accountObject);

		}

		result.put("accounts", accountArray);
		return result;
	}

	@Override
	public String getAuthenticationClassName() {
		return TokenAuthentication.class.getCanonicalName();
	}

}
