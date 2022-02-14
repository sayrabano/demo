package com.icinbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.icinbank.model.ChequeBook;

public interface ChequeBookRepo extends JpaRepository<ChequeBook,Long> {
	@Query("select c from Chequebook c where user.status = 'enabled'")
	List<ChequeBook> fetchAllActiveChequebooks();
	
}
