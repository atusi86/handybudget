package com.handybudget.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.handybudget.database.dao.MeasuringDevicePositionDao;
import com.handybudget.database.domains.MeasuringDevicePosition;

public class MeasuringDevicePositionService {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public static JSONObject getMeasuringDevicePositionListByDeviceId(int deviceId) {

		JSONObject result = new JSONObject();
		JSONArray measuringDevicePositionArray = new JSONArray();

		MeasuringDevicePositionDao mdpd = new MeasuringDevicePositionDao();
		List<MeasuringDevicePosition> list = mdpd.getMeasuringDevicePositionListByDeviceId(deviceId);

		for (MeasuringDevicePosition data : list) {

			JSONObject object = new JSONObject();
			object.put("id", data.getId());
			object.put("position", data.getPosition());
			object.put("createDate", sdf.format(data.getCreate_timestamp()));

			measuringDevicePositionArray.put(object);
		}

		result.put("measuringDevicePositionArray", measuringDevicePositionArray);

		return result;
	}

	public static JSONObject getMeasuringDevicePositionDetails(int id) {

		JSONObject result = new JSONObject();

		MeasuringDevicePositionDao mdpd = new MeasuringDevicePositionDao();
		MeasuringDevicePosition data = mdpd.getMeasuringDevicePosition(id);

		result.put("id", data.getId());
		result.put("position", data.getPosition());
		result.put("createDate", sdf.format(data.getCreate_timestamp()));

		return result;
	}

	public static void deleteMeasuringDevicePosition(int id) {

		MeasuringDevicePositionDao mdpd = new MeasuringDevicePositionDao();
		mdpd.deleteMeasuringDevicePosition(id);

	}

	public static void addMeasuringDevicePosition(int deviceId, JSONObject postData) {

		MeasuringDevicePositionDao mdpd = new MeasuringDevicePositionDao();
		mdpd.addMeasuringDevicePosition(deviceId, postData);

	}

	public static void updateMeasuringDevicePosition(int id, JSONObject postData) {

		MeasuringDevicePositionDao mdpd = new MeasuringDevicePositionDao();
		mdpd.updateMeasuringDevicePosition(id, postData);

	}

}
