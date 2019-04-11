package com.handybudget.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.handybudget.service.MeasuringDeviceTypeService;
import com.handybudget.servlet.JSONAction;
import com.handybudget.util.TokenAuthentication;

public class MeasuringDeviceTypeActions extends JSONAction {

	private JSONObject result = new JSONObject();

	@Override
	public String getAuthenticationClassName() {
		return TokenAuthentication.class.getCanonicalName();
	}

	@Override
	public JSONObject processRequest(HttpServletRequest request, HttpServletResponse response, JSONObject postData) {

		String operation = request.getParameter("operation");

		switch (operation) {

		case "getlist":

			getMeasuringDeviceTypeList();

			break;

		}

		return result;
	}

	private void getMeasuringDeviceTypeList() {
		result = MeasuringDeviceTypeService.getMeasuringDeviceTypeList();

	}

}
