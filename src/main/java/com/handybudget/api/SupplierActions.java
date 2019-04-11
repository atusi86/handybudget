package com.handybudget.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.handybudget.service.SupplierService;
import com.handybudget.servlet.JSONAction;
import com.handybudget.util.TokenAuthentication;

public class SupplierActions extends JSONAction {

	private JSONObject result = new JSONObject();

	@Override
	public String getAuthenticationClassName() {
		return TokenAuthentication.class.getCanonicalName();
	}

	@Override
	public JSONObject processRequest(HttpServletRequest request, HttpServletResponse response, JSONObject postData) {

		String operation = request.getParameter("operation");
		String supplierId = request.getParameter("id");
		int userId = postData.optInt("userId");

		switch (operation) {

		case "getlist":

			getSupplierList(userId);

			break;

		case "getdetails":

			getSupplierDetails(supplierId);

			break;

		case "add":

			addSupplier(userId, postData);

			break;

		case "delete":

			deleteSupplier(supplierId);

			break;

		case "update":

			updateSupplier(supplierId, postData);

			break;

		}

		return result;
	}

	private void updateSupplier(String supplierId, JSONObject postData) {
		SupplierService.updateSupplier(Integer.valueOf(supplierId), postData);

	}

	private void deleteSupplier(String supplierId) {
		SupplierService.deleteSupplier(Integer.valueOf(supplierId));

	}

	private void addSupplier(int userId, JSONObject postData) {
		SupplierService.addSupplier(userId, postData);
	}

	private void getSupplierDetails(String supplierId) {
		result = SupplierService.getSupplierDetails(Integer.valueOf(supplierId));
	}

	private void getSupplierList(int userId) {
		result = SupplierService.getSupplierListByUserId(userId);
	}

}
