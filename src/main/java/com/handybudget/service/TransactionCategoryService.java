package com.handybudget.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.handybudget.database.dao.TransactionCategoryDao;
import com.handybudget.database.domains.TransactionCategory;
import com.handybudget.util.GeneralHelper;

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

	public static JSONObject getAllCategories(int userId) {

		JSONObject result = new JSONObject();
		JSONArray array = new JSONArray();

		TransactionCategoryDao tcd = new TransactionCategoryDao();
		List<TransactionCategory> categoryList = tcd.getTransactionCategoryList(userId);

		for (TransactionCategory category : categoryList) {
			JSONObject categoryObject = new JSONObject();
			categoryObject.put("id", category.getId());
			categoryObject.put("type", category.getTransactionType().getName());
			categoryObject.put("name", category.getName());
			categoryObject.put("desc", !GeneralHelper.isEmptyString(category.getDescription()) ? category.getDescription() : "");
			array.put(categoryObject);
		}

		result.put("categories", array);

		return result;
	}

	public static void addCategory(int userId, JSONObject postData) {

		String name = postData.optString("name");
		String desc = postData.optString("desc");
		String typeId = postData.optString("typeId");

		TransactionCategory tc = new TransactionCategory();
		tc.setName(name);
		tc.setDescription(desc);

		TransactionCategoryDao tcd = new TransactionCategoryDao();
		tcd.addCategory(tc, userId, Integer.valueOf(typeId));

	}

	public static JSONObject getCategoryDetails(String categoryId) {

		TransactionCategoryDao tcd = new TransactionCategoryDao();
		TransactionCategory tc = tcd.getCategoryDetails(Integer.valueOf(categoryId));

		int typeId = tc.getTransactionType().getId();
		String categoryName = tc.getName();
		String categoryDesc = tc.getDescription();

		JSONObject result = new JSONObject();
		result.put("name", categoryName);
		result.put("desc", categoryDesc);
		result.put("typeId", typeId);

		return result;
	}

	public static void updateCategory(String categoryId, JSONObject postData) {

		TransactionCategoryDao tcd = new TransactionCategoryDao();
		tcd.updateCategory(Integer.valueOf(categoryId), postData);

	}

	public static void deleteCategory(String categoryId) {

		TransactionCategoryDao tcd = new TransactionCategoryDao();
		tcd.deleteCategory(Integer.valueOf(categoryId));

	}

}
