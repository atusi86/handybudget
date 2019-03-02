package com.handybudget.database.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.json.JSONObject;

import com.handybudget.database.PersistenceManager;
import com.handybudget.database.domains.Account;
import com.handybudget.database.domains.User;

public class AccountDao {

	private EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();

	public List<Account> getAccountsByUserId(int userId) {

		EntityManager em = emf.createEntityManager();
		User user = em.find(User.class, userId);

		TypedQuery<Account> query = em.createQuery("SELECT a FROM Account a WHERE a.user = :user and a.deleted = 0 ", Account.class);
		query.setParameter("user", user);
		List<Account> accountList = query.getResultList();
		em.close();

		if (accountList.size() == 0) {

			createAccountForUser(user);
			em = emf.createEntityManager();
			TypedQuery<Account> query2 = em.createQuery("SELECT a FROM Account a WHERE a.user = :user and a.deleted = 0", Account.class);
			query2.setParameter("user", user);
			accountList = query2.getResultList();
			em.close();

		}

		return accountList;
	}

	private void createAccountForUser(User user) {

		EntityManager em = emf.createEntityManager();
		Account account = new Account("Default account", "", user);
		account.setCreate_timestamp(new Date());

		try {
			em.getTransaction().begin();
			em.persist(account);
			em.getTransaction().commit();
		} finally {
			em.close();
		}

	}

	public void addAccount(Account account, int userId) {

		EntityManager em = emf.createEntityManager();
		User user = em.find(User.class, userId);

		account.setUser(user);
		account.setCreate_timestamp(new Date());

		try {
			em.getTransaction().begin();
			em.persist(account);
			em.getTransaction().commit();
		} finally {
			em.close();
		}

	}

	public void deleteAccount(int accountId) {

		EntityManager em = emf.createEntityManager();

		Account account = em.find(Account.class, accountId);
		account.setDeleted(1);
		account.setUpdate_timestamp(new Date());

		try {
			em.getTransaction().begin();
			em.persist(account);
			em.getTransaction().commit();
		} finally {
			em.close();
		}

	}

	public Account getAccountDetails(int accountId) {

		EntityManager em = emf.createEntityManager();
		return em.find(Account.class, accountId);

	}

	public void updateAccount(int accountId, JSONObject postData) {

		EntityManager em = emf.createEntityManager();
		Account account = em.find(Account.class, accountId);

		String name = postData.optString("name");
		String desc = postData.optString("desc");

		account.setName(name);
		account.setDescription(desc);
		account.setUpdate_timestamp(new Date());

		try {
			em.getTransaction().begin();
			em.persist(account);
			em.getTransaction().commit();
		} finally {
			em.close();
		}

	}

}
