package com.handybudget.database.dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import com.handybudget.database.PersistenceManager;
import com.handybudget.database.domains.MeasuringDeviceType;

public class MeasuringDeviceTypeDao {

	private EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();

	public List<MeasuringDeviceType> getMeasuringDeviceTypeList() {

		return null;
	}

}
