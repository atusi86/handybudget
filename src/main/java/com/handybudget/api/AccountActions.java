package com.handybudget.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.handybudget.service.AccountService;
import com.handybudget.servlet.JSONAction;
import com.handybudget.util.TokenAuthentication;

public class AccountActions extends JSONAction {

	private JSONObject result = new JSONObject();

	@Override
	public JSONObject processRequest(HttpServletRequest request, HttpServletResponse response, JSONObject postData) {

		String operation = request.getParameter("operation");
		String accountId = request.getParameter("id");
		int userId = postData.optInt("userId");

		switch (operation) {

		case "getlist":

			getAccountList(userId);

			break;

		case "getdetails":

			getAccountDetails(accountId);

			break;

		case "add":

			addAccount(userId, postData);

			break;

		case "delete":

			deleteAccount(accountId);

			break;

		case "update":

			updateAccount(accountId, postData);

			break;

		}

		return result;
	}

	private void updateAccount(String accountId, JSONObject postData) {

		AccountService.updateAccount(Integer.valueOf(accountId), postData);

	}

	private void getAccountDetails(String accountId) {

		result = AccountService.getAccountDetails(Integer.parseInt(accountId));

	}

	private void deleteAccount(String accountId) {

		AccountService.deleteAccount(Integer.parseInt(accountId));

	}

	private void addAccount(int userId, JSONObject postData) {

		AccountService.addAccount(userId, postData);

	}

	private void getAccountList(int userId) {

		result = AccountService.getAccountListByUserId(userId);

	}

	@Override
	public String getAuthenticationClassName() {
		return TokenAuthentication.class.getCanonicalName();
	}

}
