package com.handybudget.database.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.handybudget.database.PersistenceManager;
import com.handybudget.database.domains.Token;
import com.handybudget.database.domains.User;
import com.handybudget.service.TokenService;

public class TokenDao {

	private EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();

	public String addToken(int userId) {

		EntityManager em = emf.createEntityManager();
		User user = em.find(User.class, userId);

		String token = TokenService.generateNewToken();

		Token newToken = new Token();
		newToken.setIdentifier(token);
		newToken.setExpiresIn(48);
		newToken.setUser(user);
		newToken.setCreate_timestamp(new Date());

		try {
			em.getTransaction().begin();
			em.persist(newToken);
			em.getTransaction().commit();
		} finally {
			em.close();
		}

		return token;
	}

	public Token getTokenByUser(User user) {

		EntityManager em = emf.createEntityManager();
		TypedQuery<Token> query = em.createQuery("SELECT t FROM Token t WHERE t.user = :user and t.deleted = 0", Token.class);
		query.setParameter("user", user);
		Token tokenObject = null;
		try {
			tokenObject = query.getSingleResult();
		} catch (NoResultException e) {
		} finally {
			em.close();
		}

		return tokenObject;

	}

	public void updateTokenForUser(User user, String identifier) {

		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			Date currentDate = new Date();
			Query query = em.createQuery("UPDATE Token t set t.identifier = :identifier, t.update_timestamp = : currentdate where t.user = :user");
			query.setParameter("identifier", identifier);
			query.setParameter("currentdate", currentDate);
			query.setParameter("user", user);
			query.executeUpdate();
			em.getTransaction().commit();
		} finally {
			em.close();
		}

	}

	public Token getTokenByIdentifier(String authToken) {

		EntityManager em = emf.createEntityManager();
		TypedQuery<Token> query = em.createQuery("SELECT t FROM Token t WHERE t.identifier = :identifier and t.deleted = 0", Token.class);
		query.setParameter("identifier", authToken);
		try {
			Token token = query.getSingleResult();
			return token;
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}

}
