package com.icinbank.service;

import java.util.List;

import com.icinbank.model.Transaction;

public interface TransactionService {
	
public void transferMoney(Transaction transaction);
    
    public int retreiveAccountNumber(Long id);

	List<Transaction> retrieveTransactionsForAccount(int accountNumber);

}
