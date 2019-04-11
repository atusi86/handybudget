package com.handybudget.database.dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.json.JSONObject;

import com.handybudget.database.PersistenceManager;
import com.handybudget.database.domains.Supplier;

public class SupplierDao {

	private EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();

	public List<Supplier> getSupplierListByUser(int userId) {

		return null;
	}

	public Supplier getSupplier(int id) {

		return null;
	}

	public void deleteSupplier(int id) {

	}

	public void addSupplier(int userId, JSONObject postData) {

	}

	public void updateSupplier(int id, JSONObject postData) {

	}

}
