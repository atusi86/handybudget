package com.handybudget.database.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import com.handybudget.database.PersistenceManager;
import com.handybudget.database.domains.TransactionCategory;
import com.handybudget.database.domains.TransactionType;
import com.handybudget.database.domains.User;

public class TransactionCategoryDao {

	private EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();

	public List<TransactionCategory> getTransactionCategoryList(int userId, int typeId) {

		EntityManager em = emf.createEntityManager();
		User user = em.find(User.class, userId);

		TransactionType type = em.find(TransactionType.class, typeId);

		TypedQuery<TransactionCategory> query = em.createQuery("SELECT t FROM TransactionCategory t WHERE t.user = :user and t.transactionType = :type and t.deleted = 0 ", TransactionCategory.class);
		query.setParameter("user", user);
		query.setParameter("type", type);
		List<TransactionCategory> categoryList = query.getResultList();
		em.close();

		return categoryList;

	}

}
