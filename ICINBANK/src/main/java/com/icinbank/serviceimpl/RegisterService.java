package com.icinbank.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.icinbank.model.User;
import com.icinbank.repository.MyRepo;
import com.icinbank.service.PrimaryAccountService;
import com.icinbank.service.SavingAccountService;

public class RegisterService {
	@Autowired
	MyRepo repo;
	@Autowired
	private SavingAccountService savingsAccountService;
	
	@Autowired
	private PrimaryAccountService primaryAccountService; 
		
	
	public User addUser(User user)
	{
		user.setPrimaryAccount(primaryAccountService.createPrimaryAccount());
        user.setSavingsAccount(savingsAccountService.createSavingsAccount());
        return repo.save(user);
	}
	
	public User matchEmailPassword(String mailid, String password)
	{
		return repo.findByMailidAndPassword(mailid, password);
	}
	
	public List<User> getUserDetails() {
		return repo.findAll();
	}
}
