package com.handybudget.database.dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.json.JSONObject;

import com.handybudget.database.PersistenceManager;
import com.handybudget.database.domains.MeasuringDevice;

public class MeasuringDeviceDao {

	private EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();

	public List<MeasuringDevice> getMeasuringDeviceListByUser(int userId) {

		return null;
	}

	public MeasuringDevice getMeasuringDevice(int id) {

		return null;
	}

	public void deleteMeasuringDevice(int id) {

	}

	public void addMeasuringDevice(int userId, JSONObject postData) {

	}

	public void updateMeasuringDevice(int id, JSONObject postData) {

	}

}
