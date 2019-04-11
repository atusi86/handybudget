package com.handybudget.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.handybudget.database.dao.MeasuringDeviceDao;
import com.handybudget.database.domains.MeasuringDevice;

public class MeasuringDeviceService {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public static JSONObject getMeasuringDeviceListByUserId(int userId) {

		JSONObject result = new JSONObject();
		JSONArray measuringDeviceArray = new JSONArray();

		MeasuringDeviceDao mdd = new MeasuringDeviceDao();
		List<MeasuringDevice> list = mdd.getMeasuringDeviceListByUser(userId);

		for (MeasuringDevice data : list) {

			JSONObject object = new JSONObject();
			object.put("id", data.getId());
			object.put("typeName", data.getMeasuringDeviceType().getName());
			object.put("identifier", data.getIdentifier());
			object.put("description", data.getDescription());
			object.put("createDate", sdf.format(data.getCreate_timestamp()));

			measuringDeviceArray.put(object);
		}

		result.put("measuringDeviceArray", measuringDeviceArray);

		return result;
	}

	public static JSONObject getMeasuringDeviceDetails(int id) {

		JSONObject result = new JSONObject();

		MeasuringDeviceDao mdd = new MeasuringDeviceDao();
		MeasuringDevice data = mdd.getMeasuringDevice(id);

		result.put("id", data.getId());
		result.put("typeName", data.getMeasuringDeviceType().getName());
		result.put("identifier", data.getIdentifier());
		result.put("description", data.getDescription());
		result.put("createDate", sdf.format(data.getCreate_timestamp()));

		return result;
	}

	public static void deleteMeasuringDevice(int id) {

		MeasuringDeviceDao mdd = new MeasuringDeviceDao();
		mdd.deleteMeasuringDevice(id);

	}

	public static void addMeasuringDevice(int userId, JSONObject postData) {

		MeasuringDeviceDao mdd = new MeasuringDeviceDao();
		mdd.addMeasuringDevice(userId, postData);

	}

	public static void updateMeasuringDevice(int id, JSONObject postData) {

		MeasuringDeviceDao mdd = new MeasuringDeviceDao();
		mdd.updateMeasuringDevice(id, postData);

	}

}
