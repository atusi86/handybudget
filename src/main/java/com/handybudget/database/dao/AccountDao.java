package com.handybudget.database.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import com.handybudget.database.PersistenceManager;
import com.handybudget.database.domains.Account;
import com.handybudget.database.domains.User;

public class AccountDao {

	private EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();

	public List<Account> getAccountsByUserId(int userId) {

		EntityManager em = emf.createEntityManager();
		User user = em.find(User.class, userId);

		TypedQuery<Account> query = em.createQuery("SELECT a FROM Account a WHERE a.user = :user", Account.class);
		query.setParameter("user", user);
		List<Account> accountList = query.getResultList();
		em.close();

		if (accountList.size() == 0) {

			createAccountForUser(user);
			em = emf.createEntityManager();
			TypedQuery<Account> query2 = em.createQuery("SELECT a FROM Account a WHERE a.user = :user", Account.class);
			query2.setParameter("user", user);
			accountList = query2.getResultList();
			em.close();

		}

		return accountList;
	}

	private void createAccountForUser(User user) {

		EntityManager em = emf.createEntityManager();
		Account account = new Account("Default account", "", user);

		try {
			em.getTransaction().begin();
			em.persist(account);
			em.getTransaction().commit();
		} finally {
			em.close();
		}

	}

}
