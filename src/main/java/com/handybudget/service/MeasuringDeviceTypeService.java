package com.handybudget.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.handybudget.database.dao.MeasuringDeviceTypeDao;
import com.handybudget.database.domains.MeasuringDeviceType;

public class MeasuringDeviceTypeService {

	public static JSONObject getMeasuringDeviceTypeList() {

		JSONObject result = new JSONObject();
		JSONArray measuringDeviceTypeArray = new JSONArray();

		MeasuringDeviceTypeDao mdtd = new MeasuringDeviceTypeDao();
		List<MeasuringDeviceType> list = mdtd.getMeasuringDeviceTypeList();

		for (MeasuringDeviceType data : list) {

			JSONObject object = new JSONObject();
			object.put("id", data.getId());
			object.put("name", data.getName());

			measuringDeviceTypeArray.put(object);
		}

		result.put("measuringDeviceTypeArray", measuringDeviceTypeArray);

		return result;
	}

}
