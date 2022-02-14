package com.icinbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.icinbank.model.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer>{
	@Query("SELECT transaction FROM Transaction transaction "
            + "WHERE transaction.sourceAccountnumber = ?1"
            + "OR transaction.destinationAccountnumber = ?1")
    List<Transaction> retrieveTransactionsForAccount(int accountId);
}
