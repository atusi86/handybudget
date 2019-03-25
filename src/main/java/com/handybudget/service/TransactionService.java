package com.handybudget.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

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
		String selectedMonth = postData.optString("selectedMonth");

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
		td.addTransaction(transaction, Integer.parseInt(accountId), Integer.parseInt(categoryId), Integer.parseInt(amount), selectedMonth);

	}

	public static JSONObject getTransactionList(String accountId, String date) {

		JSONObject result = new JSONObject();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");

		Date startDate = null;
		Date endDate = null;
		if (GeneralHelper.isEmptyString(date)) {
			startDate = GeneralHelper.getFirstDayOfMonth(new Date());

			Calendar cal = Calendar.getInstance();
			cal.setTime(GeneralHelper.getLastDayOfMonth(new Date()));
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			endDate = cal.getTime();
		} else {
			try {
				startDate = GeneralHelper.getFirstDayOfMonth(sdf2.parse(date));

				Calendar cal = Calendar.getInstance();
				cal.setTime(GeneralHelper.getLastDayOfMonth(sdf2.parse(date)));
				cal.set(Calendar.HOUR_OF_DAY, 23);
				cal.set(Calendar.MINUTE, 59);
				cal.set(Calendar.SECOND, 59);
				endDate = cal.getTime();

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
				transactionObject.put("isCredited", isPaid == 1 ? true : false);
				regularIncomesArray.put(transactionObject);

				incomesSum += amount;
				if (isPaid == 1) {
					balance += amount;
				}

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

		double previousBalance = getPreviousBalance(accountId, date);

		result.put("regularIncomes", regularIncomesArray);
		result.put("regularExpenses", regularExpensesArray);
		result.put("adHocTransactions", adHocTransactionsArray);

		result.put("incomesSum", incomesSum);
		result.put("expensesSum", expensesSum);
		result.put("adhocSum", adhocSum);
		result.put("prevbalance", previousBalance);
		result.put("currentbalance", balance);
		result.put("aggregatedbalance", previousBalance + balance);

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

	public static void changeIsChange(String transactionId) {

		TransactionDao td = new TransactionDao();
		td.changeIsPaid(Integer.valueOf(transactionId));

	}

	public static JSONObject getTransactionMonths(String accountId) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		TreeSet<String> yearMonthsSet = new TreeSet<String>();
		yearMonthsSet.add(sdf.format(new Date()));

		TransactionDao td = new TransactionDao();
		List<Transaction> transactionList = td.getAllTransactionByAccount(Integer.valueOf(accountId));

		for (Transaction transaction : transactionList) {

			String yearMonth = sdf.format(transaction.getCreate_timestamp());
			yearMonthsSet.add(yearMonth);
		}

		yearMonthsSet.add("2018-08");
		yearMonthsSet.add("2018-09");
		yearMonthsSet.add("2018-10");
		yearMonthsSet.add("2018-11");
		yearMonthsSet.add("2018-12");
		yearMonthsSet.add("2019-01");
		yearMonthsSet.add("2019-02");

		JSONArray monthsArray = new JSONArray();

		for (String s : yearMonthsSet) {
			monthsArray.put(s);
		}

		JSONObject result = new JSONObject();
		result.put("months", monthsArray);

		return result;
	}

	public static double getPreviousBalance(String accountId, String date) {

		TransactionDao td = new TransactionDao();
		List<Transaction> transactionList = td.getAllTransactionByAccount(Integer.valueOf(accountId));

		Date firstDayOfMonth = null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		if (!GeneralHelper.isEmptyString(date)) {
			try {
				firstDayOfMonth = sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			firstDayOfMonth = GeneralHelper.getFirstDayOfMonth(new Date());
		}

		double balance = 0;

		for (Transaction transaction : transactionList) {

			Date createDate = transaction.getCreate_timestamp();
			if (createDate.before(firstDayOfMonth)) {

				TransactionCategory tc = transaction.getTransactionCategory();
				TransactionType tt = tc.getTransactionType();
				String typeName = tt.getName();

				if (typeName.toLowerCase().contains("regular")) {

					int isPaid = transaction.getIspaid();
					if (isPaid == 1) {
						int amount = transaction.getAmount();
						balance += amount;
					}

				} else {

					int amount = transaction.getAmount();
					balance += amount;

				}

			}

		}

		return balance;

	}

	public static JSONObject getAllTransactionsForSearchScreen(JSONObject postData) {

		String startDateString = postData.optString("startDate");
		String endDateString = postData.optString("endDate");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date startDate = null;
		Date endDate = null;

		if (!GeneralHelper.isEmptyString(startDateString) && !GeneralHelper.isEmptyString(endDateString)) {

			try {
				startDate = sdf.parse(startDateString);
				endDate = sdf.parse(endDateString);
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}

		TransactionDao td = new TransactionDao();
		List<Transaction> transactionList = td.getAllTransactionForSearchScreen(startDate, endDate);

		JSONArray transactionArray = new JSONArray();

		for (Transaction transaction : transactionList) {

			int id = transaction.getId();
			int amount = transaction.getAmount();
			Date createDate = transaction.getCreate_timestamp();
			String desc = transaction.getDescription();
			Date dueDate = transaction.getDue_date();
			int isPaid = transaction.getIspaid();
			TransactionCategory tc = transaction.getTransactionCategory();
			String categoryName = tc.getName();
			String typeName = tc.getTransactionType().getName();
			String accountName = transaction.getAccount().getName();

			JSONObject transactionObject = new JSONObject();
			transactionObject.put("id", id);
			transactionObject.put("amount", amount);
			transactionObject.put("createDate", sdf.format(createDate));
			transactionObject.put("desc", GeneralHelper.isEmptyString(desc) ? "" : desc);
			if (dueDate != null) {
				transactionObject.put("dueDate", sdf.format(dueDate));
			}
			transactionObject.put("isPaid", isPaid == 1 ? true : false);
			transactionObject.put("categoryName", categoryName);
			transactionObject.put("typeName", typeName);
			transactionObject.put("accountName", accountName);

			transactionArray.put(transactionObject);
		}

		JSONObject result = new JSONObject();
		result.put("transactions", transactionArray);

		return result;
	}

}
