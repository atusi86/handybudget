package com.handybudget.database.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import com.handybudget.database.PersistenceManager;
import com.handybudget.database.domains.TransactionType;

public class TransactionTypeDao {

	private EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();

	public List<TransactionType> getTransactionTypeList() {

		EntityManager em = emf.createEntityManager();

		TypedQuery<TransactionType> query = em.createQuery("SELECT t FROM TransactionType t WHERE t.deleted = 0 ", TransactionType.class);

		List<TransactionType> typeList = query.getResultList();
		em.close();

		return typeList;
	}

}
