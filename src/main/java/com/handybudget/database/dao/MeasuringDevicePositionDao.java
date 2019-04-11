package com.handybudget.database.dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.json.JSONObject;

import com.handybudget.database.PersistenceManager;
import com.handybudget.database.domains.MeasuringDevicePosition;

public class MeasuringDevicePositionDao {

	private EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();

	public List<MeasuringDevicePosition> getMeasuringDevicePositionListByDeviceId(int deviceId) {

		return null;
	}

	public MeasuringDevicePosition getMeasuringDevicePosition(int id) {

		return null;
	}

	public void deleteMeasuringDevicePosition(int id) {

	}

	public void addMeasuringDevicePosition(int deviceId, JSONObject postData) {

	}

	public void updateMeasuringDevicePosition(int id, JSONObject postData) {

	}

}
