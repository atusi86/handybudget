package com.handybudget.database.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.json.JSONObject;

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

	public List<TransactionCategory> getTransactionCategoryList(int userId) {

		EntityManager em = emf.createEntityManager();
		User user = em.find(User.class, userId);
		TypedQuery<TransactionCategory> query = em.createQuery("SELECT t FROM TransactionCategory t WHERE t.user = :user and t.deleted = 0 ", TransactionCategory.class);
		query.setParameter("user", user);
		List<TransactionCategory> categoryList = query.getResultList();
		em.close();

		return categoryList;
	}

	public void addCategory(TransactionCategory tc, int userId, int typeId) {

		EntityManager em = emf.createEntityManager();
		User user = em.find(User.class, userId);

		TransactionType type = em.find(TransactionType.class, typeId);

		tc.setUser(user);
		tc.setTransactionType(type);
		tc.setCreate_timestamp(new Date());

		try {
			em.getTransaction().begin();
			em.persist(tc);
			em.getTransaction().commit();
		} finally {
			em.close();
		}

	}

	public TransactionCategory getCategoryDetails(int categoryId) {

		EntityManager em = emf.createEntityManager();
		TransactionCategory category = em.find(TransactionCategory.class, categoryId);

		return category;
	}

	public void updateCategory(int categoryId, JSONObject postData) {

		EntityManager em = emf.createEntityManager();
		TransactionCategory category = em.find(TransactionCategory.class, categoryId);

		String name = postData.optString("name");
		String desc = postData.optString("desc");
		String typeId = postData.optString("typeId");

		category.setName(name);
		category.setDescription(desc);

		TransactionType tc;
		if (!Integer.valueOf(typeId).equals(category.getTransactionType().getId())) {
			tc = em.find(TransactionType.class, Integer.valueOf(typeId));
			category.setTransactionType(tc);
		}

		category.setUpdate_timestamp(new Date());

		try {
			em.getTransaction().begin();
			em.persist(category);
			em.getTransaction().commit();
		} finally {
			em.close();
		}

	}

	public void deleteCategory(int categoryId) {

		EntityManager em = emf.createEntityManager();
		TransactionCategory category = em.find(TransactionCategory.class, categoryId);

		category.setDeleted(1);
		category.setUpdate_timestamp(new Date());

		try {
			em.getTransaction().begin();
			em.persist(category);
			em.getTransaction().commit();
		} finally {
			em.close();
		}

	}
}
