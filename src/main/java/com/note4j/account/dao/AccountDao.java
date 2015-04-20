package com.note4j.account.dao;

import com.note4j.account.model.Account;

public interface AccountDao {
	public int add(Account account);

	public Account get(int id);

	public Account get(String name);

	public int update(Account account);

	public Account del(int id);
}
