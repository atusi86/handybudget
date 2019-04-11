package com.handybudget.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.handybudget.service.MeasuringDeviceService;
import com.handybudget.servlet.JSONAction;
import com.handybudget.util.TokenAuthentication;

public class MeasuringDeviceActions extends JSONAction {

	private JSONObject result = new JSONObject();

	@Override
	public String getAuthenticationClassName() {
		return TokenAuthentication.class.getCanonicalName();
	}

	@Override
	public JSONObject processRequest(HttpServletRequest request, HttpServletResponse response, JSONObject postData) {

		String operation = request.getParameter("operation");
		String measuringDeviceId = request.getParameter("id");
		int userId = postData.optInt("userId");

		switch (operation) {

		case "getlist":

			getMeasuringDeviceList(userId);

			break;

		case "getdetails":

			getMeasuringDeviceDetails(measuringDeviceId);

			break;

		case "add":

			addMeasuringDevice(userId, postData);

			break;

		case "delete":

			deleteMeasuringDevice(measuringDeviceId);

			break;

		case "update":

			updateMeasuringDevice(measuringDeviceId, postData);

			break;

		}

		return result;
	}

	private void updateMeasuringDevice(String measuringDeviceId, JSONObject postData) {
		MeasuringDeviceService.updateMeasuringDevice(Integer.valueOf(measuringDeviceId), postData);

	}

	private void deleteMeasuringDevice(String measuringDeviceId) {
		MeasuringDeviceService.deleteMeasuringDevice(Integer.valueOf(measuringDeviceId));

	}

	private void addMeasuringDevice(int userId, JSONObject postData) {
		MeasuringDeviceService.addMeasuringDevice(userId, postData);

	}

	private void getMeasuringDeviceDetails(String measuringDeviceId) {
		result = MeasuringDeviceService.getMeasuringDeviceDetails(Integer.valueOf(measuringDeviceId));
	}

	private void getMeasuringDeviceList(int userId) {
		result = MeasuringDeviceService.getMeasuringDeviceListByUserId(userId);
	}

}
