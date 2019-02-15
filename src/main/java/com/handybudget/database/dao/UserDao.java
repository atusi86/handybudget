package com.handybudget.database.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import com.handybudget.database.domains.User;
import com.handybudget.database.PersistenceManager;


public class UserDao {

	
	public List<User> getUsers(){
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		TypedQuery<User> users = em.createQuery("SELECT u FROM User u", User.class);
		ArrayList<User> userList = new ArrayList<User>(users.getResultList());
		em.close();
		return userList;
	}
	
}
