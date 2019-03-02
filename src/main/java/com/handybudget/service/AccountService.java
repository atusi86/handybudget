package com.handybudget.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.handybudget.database.dao.AccountDao;
import com.handybudget.database.domains.Account;

public class AccountService {

	public static JSONObject getAccountListByUserId(int userId) {

		AccountDao ad = new AccountDao();
		List<Account> accountList = ad.getAccountsByUserId(userId);

		JSONObject result = new JSONObject();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		JSONArray accountArray = new JSONArray();
		for (Account account : accountList) {

			JSONObject accountObject = new JSONObject();
			accountObject.put("id", account.getId());
			accountObject.put("name", account.getName());
			accountObject.put("desc", account.getDescription());
			accountObject.put("created", sdf.format(account.getCreate_timestamp()));

			accountArray.put(accountObject);

		}

		result.put("accounts", accountArray);
		return result;

	}

	public static void addAccount(int userId, JSONObject postData) {

		String name = postData.optString("name");
		String desc = postData.optString("desc");

		Account account = new Account();
		account.setName(name);
		account.setDescription(desc);

		AccountDao ad = new AccountDao();
		ad.addAccount(account, userId);

	}

	public static void deleteAccount(int accountId) {

		AccountDao ad = new AccountDao();
		ad.deleteAccount(accountId);

	}

	public static JSONObject getAccountDetails(int accountId) {

		AccountDao ad = new AccountDao();
		Account account = ad.getAccountDetails(accountId);

		JSONObject result = new JSONObject();
		result.put("name", account.getName());
		result.put("desc", account.getDescription());

		return result;

	}

	public static void updateAccount(int accountId, JSONObject postData) {

		AccountDao ad = new AccountDao();
		ad.updateAccount(accountId, postData);

	}

}
