package com.handybudget.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.handybudget.service.TransactionTypeService;
import com.handybudget.servlet.JSONAction;
import com.handybudget.util.TokenAuthentication;

public class TransactionTypeActions extends JSONAction {

	private JSONObject result = new JSONObject();

	@Override
	public JSONObject processRequest(HttpServletRequest request, HttpServletResponse response, JSONObject postData) {
		String operation = request.getParameter("operation");

		switch (operation) {

		case "getlist":

			getTransactionTypeList();

			break;

		}

		return result;
	}

	private void getTransactionTypeList() {

		result = TransactionTypeService.getTransactionTypeList();

	}

	@Override
	public String getAuthenticationClassName() {
		return TokenAuthentication.class.getCanonicalName();
	}

}
