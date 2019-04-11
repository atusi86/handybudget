package com.handybudget.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.handybudget.service.MeasuringDevicePositionService;
import com.handybudget.servlet.JSONAction;
import com.handybudget.util.TokenAuthentication;

public class MeasuringDevicePositionActions extends JSONAction {

	private JSONObject result = new JSONObject();

	@Override
	public String getAuthenticationClassName() {
		return TokenAuthentication.class.getCanonicalName();
	}

	@Override
	public JSONObject processRequest(HttpServletRequest request, HttpServletResponse response, JSONObject postData) {

		String operation = request.getParameter("operation");
		String positionId = request.getParameter("id");
		String deviceId = request.getParameter("deviceId");

		switch (operation) {

		case "getlist":

			getMeasuringDevicePositionList(deviceId);

			break;

		case "getdetails":

			getMeasuringDevicePositionDetails(positionId);

			break;

		case "add":

			addMeasuringDevicePosition(deviceId, postData);

			break;

		case "delete":

			deleteMeasuringDevicePosition(positionId);

			break;

		case "update":

			updateMeasuringDevicePosition(positionId, postData);

			break;

		}

		return result;
	}

	private void updateMeasuringDevicePosition(String positionId, JSONObject postData) {
		MeasuringDevicePositionService.updateMeasuringDevicePosition(Integer.valueOf(positionId), postData);

	}

	private void deleteMeasuringDevicePosition(String positionId) {
		MeasuringDevicePositionService.deleteMeasuringDevicePosition(Integer.valueOf(positionId));

	}

	private void addMeasuringDevicePosition(String deviceId, JSONObject postData) {
		MeasuringDevicePositionService.addMeasuringDevicePosition(Integer.valueOf(deviceId), postData);
	}

	private void getMeasuringDevicePositionDetails(String positionId) {
		result = MeasuringDevicePositionService.getMeasuringDevicePositionDetails(Integer.valueOf(positionId));
	}

	private void getMeasuringDevicePositionList(String deviceId) {
		result = MeasuringDevicePositionService.getMeasuringDevicePositionListByDeviceId(Integer.valueOf(deviceId));
	}

}
