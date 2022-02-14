package com.icinbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icinbank.model.PersonalTransaction;

public interface PersonalTransactionRepo extends JpaRepository<PersonalTransaction, Integer> {

}
