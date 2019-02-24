package com.handybudget.service;

import java.util.List;

import com.handybudget.database.dao.AccountDao;
import com.handybudget.database.domains.Account;

public class AccountService {

	public static List<Account> getAccountListByUserId(int userId) {

		AccountDao ad = new AccountDao();
		return ad.getAccountsByUserId(userId);

	}
}
