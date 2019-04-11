package com.handybudget.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.handybudget.service.IncomingDataService;
import com.handybudget.servlet.JSONAction;
import com.handybudget.util.TokenAuthentication;

public class IncomingDataActions extends JSONAction {

	private JSONObject result = new JSONObject();

	@Override
	public String getAuthenticationClassName() {
		return TokenAuthentication.class.getCanonicalName();
	}

	@Override
	public JSONObject processRequest(HttpServletRequest request, HttpServletResponse response, JSONObject postData) {

		String operation = request.getParameter("operation");
		int userId = postData.optInt("userId");
		String dataId = request.getParameter("id");

		switch (operation) {

		case "getlist":

			getIncomingDataList(userId);

			break;

		case "getdetails":

			getIncomingDataDetails(dataId);

			break;

		case "delete":

			deleteIncomingData(dataId);

			break;

		}

		return result;

	}

	private void deleteIncomingData(String dataId) {
		IncomingDataService.deleteIncomingData(Integer.valueOf(dataId));

	}

	private void getIncomingDataDetails(String dataId) {
		result = IncomingDataService.getIncomingDataDetails(Integer.valueOf(dataId));

	}

	private void getIncomingDataList(int userId) {
		result = IncomingDataService.getIncomingDataListByUserId(userId);

	}

}
