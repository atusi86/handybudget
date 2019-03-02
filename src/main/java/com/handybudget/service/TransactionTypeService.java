package com.handybudget.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.handybudget.database.dao.TransactionTypeDao;
import com.handybudget.database.domains.TransactionType;

public class TransactionTypeService {

	public static JSONObject getTransactionTypeList() {

		JSONObject result = new JSONObject();

		TransactionTypeDao ttd = new TransactionTypeDao();
		List<TransactionType> typeList = ttd.getTransactionTypeList();

		JSONArray array = new JSONArray();

		for (TransactionType type : typeList) {
			JSONObject typeObject = new JSONObject();
			typeObject.put("id", type.getId());
			typeObject.put("name", type.getName());
			array.put(typeObject);
		}

		result.put("types", array);

		return result;
	}

}
