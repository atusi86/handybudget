package com.handybudget.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.handybudget.service.TransactionCategoryService;
import com.handybudget.servlet.JSONAction;
import com.handybudget.util.TokenAuthentication;

public class TransactionCategoryActions extends JSONAction {

	private JSONObject result = new JSONObject();

	@Override
	public JSONObject processRequest(HttpServletRequest request, HttpServletResponse response, JSONObject postData) {

		String operation = request.getParameter("operation");
		String typeId = request.getParameter("typeId");
		int userId = postData.optInt("userId");

		switch (operation) {

		case "getlist":

			getTransactionCategoryList(userId, typeId);

			break;
		/*
		 * case "getdetails":
		 * 
		 * getAccountDetails(accountId);
		 * 
		 * break;
		 * 
		 * case "add":
		 * 
		 * addAccount(userId, postData);
		 * 
		 * break;
		 * 
		 * case "delete":
		 * 
		 * deleteAccount(accountId);
		 * 
		 * break;
		 * 
		 * case "update":
		 * 
		 * updateAccount(accountId, postData);
		 * 
		 * break;
		 */
		}

		return result;
	}

	private void getTransactionCategoryList(int userId, String typeId) {

		result = TransactionCategoryService.getTransactionCategoryList(userId, Integer.valueOf(typeId));

	}

	@Override
	public String getAuthenticationClassName() {
		return TokenAuthentication.class.getCanonicalName();
	}

}
