package com.icinbank.repository;

import org.springframework.data.repository.CrudRepository;

import com.icinbank.model.PrimaryAccount;

public interface PrimaryAccountRepo  extends CrudRepository<PrimaryAccount,Long> {
    PrimaryAccount findByAccountNumber (int accountNumber);
}
