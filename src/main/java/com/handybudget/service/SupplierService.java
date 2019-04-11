package com.handybudget.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.handybudget.database.dao.SupplierDao;
import com.handybudget.database.domains.Supplier;

public class SupplierService {

	public static JSONObject getSupplierListByUserId(int userId) {

		JSONObject result = new JSONObject();
		JSONArray supplierArray = new JSONArray();

		SupplierDao sd = new SupplierDao();
		List<Supplier> list = sd.getSupplierListByUser(userId);

		for (Supplier data : list) {

			JSONObject object = new JSONObject();
			object.put("id", data.getId());
			object.put("name", data.getName());
			object.put("phone", data.getPhone());
			object.put("description", data.getDescription());
			object.put("customerId", data.getCustomer_id());

			supplierArray.put(object);
		}

		result.put("supplierArray", supplierArray);

		return result;
	}

	public static JSONObject getSupplierDetails(int id) {

		JSONObject result = new JSONObject();

		SupplierDao sd = new SupplierDao();
		Supplier data = sd.getSupplier(id);

		result.put("id", data.getId());
		result.put("name", data.getName());
		result.put("phone", data.getPhone());
		result.put("description", data.getDescription());
		result.put("customerId", data.getCustomer_id());

		return result;
	}

	public static void deleteSupplier(int id) {

		SupplierDao sd = new SupplierDao();
		sd.deleteSupplier(id);

	}

	public static void addSupplier(int userId, JSONObject postData) {

		SupplierDao sd = new SupplierDao();
		sd.addSupplier(userId, postData);

	}

	public static void updateSupplier(int id, JSONObject postData) {

		SupplierDao sd = new SupplierDao();
		sd.updateSupplier(id, postData);

	}

}
