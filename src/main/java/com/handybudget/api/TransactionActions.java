package com.handybudget.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.handybudget.service.TransactionService;
import com.handybudget.servlet.JSONAction;
import com.handybudget.util.TokenAuthentication;

public class TransactionActions extends JSONAction {

	private JSONObject result = new JSONObject();

	@Override
	public JSONObject processRequest(HttpServletRequest request, HttpServletResponse response, JSONObject postData) {
		String operation = request.getParameter("operation");
		String accountId = request.getParameter("accountId");
		String transactionId = request.getParameter("id");
		String date = postData.optString("date");

		// int userId = postData.optInt("userId");

		switch (operation) {

		case "getlist":

			getTransactionList(accountId, date);

			break;

		case "getdetails":

			getTransactionDetails(transactionId);

			break;

		case "add":

			addTransaction(postData);

			break;

		case "delete":

			deleteTransaction(transactionId);

			break;

		case "update":

			updateTransaction(transactionId, postData);

			break;

		case "changeispaid":

			changeIsPaid(transactionId);

			break;

		case "getmonths":

			getTransactionMonths(accountId);

			break;

		case "getalltransactions":

			getAllTransactions(postData);

			break;

		}

		return result;
	}

	private void getAllTransactions(JSONObject postData) {

		result = TransactionService.getAllTransactionsForSearchScreen(postData);

	}

	private void getTransactionMonths(String accountId) {
		result = TransactionService.getTransactionMonths(accountId);
	}

	private void changeIsPaid(String transactionId) {
		TransactionService.changeIsChange(transactionId);

	}

	private void getTransactionDetails(String transactionId) {
		TransactionService ts = new TransactionService();
		result = ts.getTransactionDetails(transactionId);
	}

	private void getTransactionList(String accountId, String date) {

		result = TransactionService.getTransactionList(accountId, date);

	}

	private void addTransaction(JSONObject postData) {

		TransactionService.addTransaction(postData);

	}

	private void deleteTransaction(String transactionId) {

		TransactionService.deleteTransaction(transactionId);

	}

	private void updateTransaction(String transactionId, JSONObject postData) {

		TransactionService.updateTransaction(transactionId, postData);

	}

	@Override
	public String getAuthenticationClassName() {
		return TokenAuthentication.class.getCanonicalName();
	}

}
