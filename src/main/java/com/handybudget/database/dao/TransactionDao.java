package com.handybudget.database.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.json.JSONObject;

import com.handybudget.database.PersistenceManager;
import com.handybudget.database.domains.Account;
import com.handybudget.database.domains.Transaction;
import com.handybudget.database.domains.TransactionCategory;
import com.handybudget.util.GeneralHelper;

public class TransactionDao {

	private EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();

	public void addTransaction(Transaction transaction, int accountId, int categoryId, int amount, String selectedMonth) {

		EntityManager em = emf.createEntityManager();
		Account account = em.find(Account.class, accountId);
		TransactionCategory transactionCategory = em.find(TransactionCategory.class, categoryId);

		String typeName = transactionCategory.getTransactionType().getName();
		if (typeName.toLowerCase().contains("expense")) {
			amount = amount * -1;
		}

		transaction.setAccount(account);
		transaction.setTransactionCategory(transactionCategory);
		transaction.setAmount(amount);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		if (!GeneralHelper.isEmptyString(selectedMonth)) {
			try {
				Date selectedMonthDate = sdf.parse(selectedMonth);
				Date currentFirstDay = GeneralHelper.getFirstDayOfMonth(new Date());
				if (selectedMonthDate.before(currentFirstDay)) {

					Date selectedMonthDateLastDay = GeneralHelper.getLastDayOfMonth(selectedMonthDate);
					transaction.setCreate_timestamp(selectedMonthDateLastDay);

				} else {
					transaction.setCreate_timestamp(new Date());
				}

			} catch (ParseException e) {
				e.printStackTrace();
			}

		} else {
			transaction.setCreate_timestamp(new Date());
		}

		try {
			em.getTransaction().begin();
			em.persist(transaction);
			em.getTransaction().commit();
		} finally {
			em.close();
		}

	}

	public List<Transaction> getTransactionList(int accountId, Date startDate, Date endDate) {

		EntityManager em = emf.createEntityManager();

		Account account = em.find(Account.class, accountId);

		TypedQuery<Transaction> query = em.createQuery("SELECT t FROM Transaction t WHERE t.account = :account and t.deleted = 0 and t.create_timestamp BETWEEN :startDate AND :endDate ", Transaction.class);
		query.setParameter("account", account);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		List<Transaction> transactionList = query.getResultList();
		em.close();

		return transactionList;

	}

	public void deleteTransaction(int transactionId) {

		EntityManager em = emf.createEntityManager();

		Transaction transaction = em.find(Transaction.class, transactionId);
		transaction.setDeleted(1);
		transaction.setUpdate_timestamp(new Date());

		try {
			em.getTransaction().begin();
			em.persist(transaction);
			em.getTransaction().commit();
		} finally {
			em.close();
		}

	}

	public Transaction getTransaction(int transactionId) {

		EntityManager em = emf.createEntityManager();
		Transaction transaction = em.find(Transaction.class, transactionId);

		return transaction;
	}

	public void updateTransaction(int transactionId, JSONObject postData) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		EntityManager em = emf.createEntityManager();
		Transaction transaction = em.find(Transaction.class, transactionId);
		int currentCategoryId = transaction.getTransactionCategory().getId();

		String categoryId = postData.optString("categoryId");
		String amount = postData.optString("amount");
		String desc = postData.optString("desc");
		String dueDate = postData.optString("duedate");

		if (currentCategoryId != Integer.valueOf(categoryId)) {
			EntityManager em2 = emf.createEntityManager();
			TransactionCategory transactionCategory = em2.find(TransactionCategory.class, Integer.valueOf(categoryId));
			em2.close();
			transaction.setTransactionCategory(transactionCategory);
		}

		transaction.setAmount(Integer.valueOf(amount));
		transaction.setDescription(desc);
		try {
			if (!GeneralHelper.isEmptyString(dueDate)) {
				transaction.setDue_date(sdf.parse(dueDate));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		transaction.setUpdate_timestamp(new Date());

		try {
			em.getTransaction().begin();
			em.persist(transaction);
			em.getTransaction().commit();
		} finally {
			em.close();
		}

	}

	public void changeIsPaid(int transactionId) {

		EntityManager em = emf.createEntityManager();
		Transaction transaction = em.find(Transaction.class, transactionId);

		int isPaid = transaction.getIspaid();

		if (isPaid == 0) {
			transaction.setIspaid(1);
		} else {
			transaction.setIspaid(0);
		}

		try {
			em.getTransaction().begin();
			em.persist(transaction);
			em.getTransaction().commit();
		} finally {
			em.close();
		}

	}

	public List<Transaction> getAllTransactionByAccount(int accountId) {

		EntityManager em = emf.createEntityManager();
		Account account = em.find(Account.class, accountId);

		TypedQuery<Transaction> query = em.createQuery("SELECT t FROM Transaction t WHERE t.account = :account and t.deleted = 0", Transaction.class);
		query.setParameter("account", account);
		List<Transaction> transactionList = query.getResultList();
		em.close();

		return transactionList;

	}

	public List<Transaction> getAllTransactionForSearchScreen(Date startDate, Date endDate) {

		EntityManager em = emf.createEntityManager();

		String hql;
		if (startDate != null && endDate != null) {

			Calendar calEnd = Calendar.getInstance();
			calEnd.setTime(endDate);
			calEnd.set(Calendar.HOUR_OF_DAY, 23);
			calEnd.set(Calendar.MINUTE, 59);
			calEnd.set(Calendar.SECOND, 59);
			endDate = calEnd.getTime();

			hql = "SELECT t FROM Transaction t WHERE t.deleted = 0 and t.create_timestamp BETWEEN :startDate AND :endDate";
		} else {
			hql = "SELECT t FROM Transaction t WHERE t.deleted = 0";
		}

		TypedQuery<Transaction> query = em.createQuery(hql, Transaction.class);

		if (startDate != null && endDate != null) {
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		List<Transaction> transactionList = query.getResultList();
		em.close();

		return transactionList;

	}

}
