package com.icinbank.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.icinbank.model.ChequeBook;
import com.icinbank.model.User;
import com.icinbank.repository.ChequeBookRepo;
import com.icinbank.repository.MyRepo;

public class ChequeBookServiceImpl {

	
	@Autowired 
	private ChequeBookRepo chequeBook;
	@Autowired
	private MyRepo repo;

	
	public ChequeBook addchequebook(ChequeBook chequebook)
	{
		User user =repo.findById(chequebook.getUser().getId());
		System.out.println(user.getId());
		chequebook.setUser(user);
		return chequeBook.save(chequebook);
	}
}
