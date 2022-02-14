package com.icinbank.service;

import java.util.List;

import com.icinbank.model.SavingAccount;

public interface SavingAccountService {
	
	public SavingAccount createSavingsAccount();
	public List<SavingAccount> getAllSavingsAccounts();
	public void deposit(Integer accNo, Long amount);
	public String withdraw(Integer accNo, Long amount);
    public SavingAccount getAccount(int accNo);
	Long retrieveAccountBalance(long accountId);
}
