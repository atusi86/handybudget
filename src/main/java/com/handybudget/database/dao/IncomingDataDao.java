package com.handybudget.database.dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import com.handybudget.database.PersistenceManager;
import com.handybudget.database.domains.IncomingData;

public class IncomingDataDao {

	private EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();

	public List<IncomingData> getIncomingDataListByUser(int userId) {

		return null;
	}

	public IncomingData getIncomingData(int id) {

		return null;
	}

	public void deleteIncomingData(int id) {

	}

}
