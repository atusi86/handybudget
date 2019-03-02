package com.handybudget.database.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.handybudget.database.PersistenceManager;
import com.handybudget.database.domains.User;

public class UserDao {

	private EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();

	public List<User> getUsers() {

		EntityManager em = emf.createEntityManager();

		TypedQuery<User> users = em.createQuery("SELECT u FROM User u", User.class);
		ArrayList<User> userList = new ArrayList<User>(users.getResultList());
		em.close();
		return userList;
	}

	public String getPasswordByEmail(String email) {

		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT u.password FROM User u WHERE u.email = :email and u.deleted = 0");
		query.setParameter("email", email.toLowerCase());
		List<String> result = query.getResultList();
		em.close();
		if (result.size() > 0) {
			return result.get(0);
		}
		return null;

	}

	public User getUserByEmail(String email) {

		EntityManager em = emf.createEntityManager();

		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email and u.deleted = 0", User.class);
		query.setParameter("email", email);

		User user = (User) query.getSingleResult();
		em.close();

		return user;
	}

	public void addUser(User user) {

		EntityManager em = emf.createEntityManager();
		user.setCreate_timestamp(new Date());

		try {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
		} finally {
			em.close();
		}

	}

}
