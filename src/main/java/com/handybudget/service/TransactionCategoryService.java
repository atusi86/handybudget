package com.handybudget.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.handybudget.database.dao.TransactionCategoryDao;
import com.handybudget.database.domains.TransactionCategory;

public class TransactionCategoryService {

	public static JSONObject getTransactionCategoryList(int userId, int typeId) {

		JSONObject result = new JSONObject();
		JSONArray array = new JSONArray();

		TransactionCategoryDao tcd = new TransactionCategoryDao();
		List<TransactionCategory> categoryList = tcd.getTransactionCategoryList(userId, typeId);

		for (TransactionCategory category : categoryList) {
			JSONObject categoryObject = new JSONObject();
			categoryObject.put("id", category.getId());
			categoryObject.put("name", category.getName());
			categoryObject.put("desc", category.getDescription());
			array.put(categoryObject);
		}

		result.put("categories", array);

		return result;

	}

}
