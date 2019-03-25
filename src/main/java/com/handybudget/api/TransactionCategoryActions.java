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
		String categoryId = request.getParameter("id");

		switch (operation) {

		case "getlist":

			getTransactionCategoryListByType(userId, typeId);

			break;

		case "getallcategories":

			getAllCategories(userId);

			break;

		case "getdetails":

			getCategoryDetails(categoryId);

			break;

		case "add":

			addCategory(userId, postData);

			break;

		case "delete":

			deleteCategory(categoryId);

			break;

		case "update":

			updateCategory(categoryId, postData);

			break;

		}

		return result;
	}

	private void deleteCategory(String categoryId) {
		TransactionCategoryService.deleteCategory(categoryId);

	}

	private void updateCategory(String categoryId, JSONObject postData) {
		TransactionCategoryService.updateCategory(categoryId, postData);

	}

	private void getCategoryDetails(String categoryId) {

		result = TransactionCategoryService.getCategoryDetails(categoryId);

	}

	private void addCategory(int userId, JSONObject postData) {
		TransactionCategoryService.addCategory(userId, postData);

	}

	private void getAllCategories(int userId) {

		result = TransactionCategoryService.getAllCategories(userId);

	}

	private void getTransactionCategoryListByType(int userId, String typeId) {

		result = TransactionCategoryService.getTransactionCategoryList(userId, Integer.valueOf(typeId));

	}

	@Override
	public String getAuthenticationClassName() {
		return TokenAuthentication.class.getCanonicalName();
	}

}
