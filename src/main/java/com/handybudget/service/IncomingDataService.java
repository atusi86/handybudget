package com.handybudget.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.handybudget.database.dao.IncomingDataDao;
import com.handybudget.database.domains.IncomingData;

public class IncomingDataService {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static JSONObject getIncomingDataListByUserId(int userId) {

		JSONObject result = new JSONObject();
		JSONArray incomingDataArray = new JSONArray();

		IncomingDataDao idd = new IncomingDataDao();
		List<IncomingData> list = idd.getIncomingDataListByUser(userId);

		for (IncomingData data : list) {

			JSONObject object = new JSONObject();
			object.put("id", data.getId());
			object.put("content", data.getContent());
			object.put("createDate", sdf.format(data.getCreate_timestamp()));

			incomingDataArray.put(object);
		}

		result.put("incomingDataArray", incomingDataArray);

		return result;
	}

	public static JSONObject getIncomingDataDetails(int id) {

		JSONObject result = new JSONObject();

		IncomingDataDao idd = new IncomingDataDao();
		IncomingData data = idd.getIncomingData(id);

		result.put("id", data.getId());
		result.put("content", data.getContent());
		result.put("createDate", sdf.format(data.getCreate_timestamp()));

		return result;
	}

	public static void deleteIncomingData(int id) {

		IncomingDataDao idd = new IncomingDataDao();
		idd.deleteIncomingData(id);

	}

}
