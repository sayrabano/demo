package com.icinbank.repository;

import org.springframework.data.repository.CrudRepository;

import com.icinbank.model.SavingAccount;

public interface SavingAccountRepo  extends CrudRepository<SavingAccount,Long> {
	SavingAccount findByAccountNumber (int accountNumber);

}
