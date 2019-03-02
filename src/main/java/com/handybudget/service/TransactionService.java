package com.handybudget.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.handybudget.database.dao.TransactionDao;
import com.handybudget.database.domains.Transaction;
import com.handybudget.database.domains.TransactionCategory;
import com.handybudget.database.domains.TransactionType;
import com.handybudget.util.GeneralHelper;

public class TransactionService {

	public static void addTransaction(JSONObject postData) {

		String accountId = postData.optString("accountId");
		String categoryId = postData.optString("categoryId");
		String amount = postData.optString("amount");
		String desc = postData.optString("desc");
		String duedateString = postData.optString("duedate");

		Date dueDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (!GeneralHelper.isEmptyString(duedateString)) {
			try {
				dueDate = sdf.parse(duedateString);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Transaction transaction = new Transaction();
		transaction.setDescription(desc);
		if (dueDate != null) {
			transaction.setDue_date(dueDate);
		}

		TransactionDao td = new TransactionDao();
		td.addTransaction(transaction, Integer.parseInt(accountId), Integer.parseInt(categoryId), Integer.parseInt(amount));

	}

	public static JSONObject getTransactionList(String accountId, String date) {

		JSONObject result = new JSONObject();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date startDate = null;
		Date endDate = null;
		if (GeneralHelper.isEmptyString(date)) {
			startDate = GeneralHelper.getFirstDayOfMonth(new Date());
			endDate = GeneralHelper.getLastDayOfMonth(new Date());
		} else {
			try {
				startDate = GeneralHelper.getFirstDayOfMonth(sdf.parse(date));
				endDate = GeneralHelper.getLastDayOfMonth(sdf.parse(date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		TransactionDao td = new TransactionDao();
		List<Transaction> transactionList = td.getTransactionList(Integer.parseInt(accountId), startDate, endDate);

		JSONArray regularIncomesArray = new JSONArray();
		JSONArray regularExpensesArray = new JSONArray();
		JSONArray adHocTransactionsArray = new JSONArray();

		double incomesSum = 0;
		double expensesSum = 0;
		double adhocSum = 0;
		double balance = 0;

		for (Transaction transaction : transactionList) {

			int id = transaction.getId();
			String desc = transaction.getDescription();
			int amount = transaction.getAmount();
			Date dueDate = transaction.getDue_date();
			int isPaid = transaction.getIspaid();
			Date createDate = transaction.getCreate_timestamp();

			TransactionCategory tc = transaction.getTransactionCategory();
			TransactionType tt = tc.getTransactionType();
			String typeName = tt.getName();

			JSONObject transactionObject = new JSONObject();

			switch (typeName.toLowerCase()) {

			case "regular income":

				transactionObject.put("id", id);
				transactionObject.put("desc", desc);
				transactionObject.put("amount", amount);
				transactionObject.put("category", tc.getName());
				regularIncomesArray.put(transactionObject);

				incomesSum += amount;
				balance += amount;

				break;

			case "regular expense":

				transactionObject.put("id", id);
				transactionObject.put("desc", desc);
				transactionObject.put("amount", amount);
				transactionObject.put("category", tc.getName());
				transactionObject.put("isPaid", isPaid == 1 ? true : false);
				transactionObject.put("dueDate", sdf.format(dueDate));
				regularExpensesArray.put(transactionObject);

				expensesSum += amount;
				if (isPaid == 1) {
					balance += amount;
				}

				break;

			case "ad-hoc income":

				transactionObject.put("id", id);
				transactionObject.put("desc", desc);
				transactionObject.put("amount", amount);
				transactionObject.put("category", tc.getName());
				transactionObject.put("createDate", sdf.format(createDate));
				adHocTransactionsArray.put(transactionObject);

				adhocSum += amount;
				balance += amount;

				break;

			case "ad-hoc expense":

				transactionObject.put("id", id);
				transactionObject.put("desc", desc);
				transactionObject.put("amount", amount);
				transactionObject.put("category", tc.getName());
				transactionObject.put("createDate", sdf.format(createDate));
				adHocTransactionsArray.put(transactionObject);

				adhocSum += amount;
				balance += amount;

				break;
			}

		}

		result.put("regularIncomes", regularIncomesArray);
		result.put("regularExpenses", regularExpensesArray);
		result.put("adHocTransactions", adHocTransactionsArray);

		result.put("incomesSum", incomesSum);
		result.put("expensesSum", expensesSum);
		result.put("adhocSum", adhocSum);
		result.put("balance", balance);

		return result;
	}

	public static void deleteTransaction(String transactionId) {

		TransactionDao td = new TransactionDao();
		td.deleteTransaction(Integer.valueOf(transactionId));

	}

	public static JSONObject getTransactionDetails(String transactionId) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		TransactionDao td = new TransactionDao();
		Transaction transaction = td.getTransaction(Integer.valueOf(transactionId));

		TransactionCategory tc = transaction.getTransactionCategory();
		TransactionType tt = tc.getTransactionType();

		int transactionCategoryId = tc.getId();
		int transactionTypeId = tt.getId();
		String transactionTypeName = tt.getName();
		int amount = transaction.getAmount();
		Date dueDate = transaction.getDue_date();
		String descrition = transaction.getDescription();

		JSONObject transactionObject = new JSONObject();
		transactionObject.put("typeId", transactionTypeId);
		transactionObject.put("typeName", transactionTypeName);
		transactionObject.put("categoryId", transactionCategoryId);
		transactionObject.put("amount", amount < 0 ? amount * -1 : amount);
		transactionObject.put("desc", descrition);
		if (dueDate != null) {
			transactionObject.put("dueDate", sdf.format(dueDate));
		}

		return transactionObject;

	}

	public static void updateTransaction(String transactionId, JSONObject postData) {

		TransactionDao td = new TransactionDao();
		td.updateTransaction(Integer.valueOf(transactionId), postData);

	}

}
