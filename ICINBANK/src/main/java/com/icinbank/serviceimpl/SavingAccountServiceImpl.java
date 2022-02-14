package com.icinbank.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icinbank.model.PersonalTransaction;
import com.icinbank.model.SavingAccount;
import com.icinbank.model.AccountNotFoundException;
import com.icinbank.repository.PersonalTransactionRepo;
import com.icinbank.repository.SavingAccountRepo;
import com.icinbank.service.SavingAccountService;

@Service
public class SavingAccountServiceImpl implements SavingAccountService {

	
	@Autowired
	private SavingAccountRepo repo;
	
	@Autowired
	private com.icinbank.service.UserService UserService;
	private static int nextAccountNumber =11223344;
	
	
	@Autowired 
	private PersonalTransactionRepo personalRepo;
	
	
	@Override
	public SavingAccount createSavingsAccount() {
		 SavingAccount savingsAccount = new SavingAccount();
	        savingsAccount.setAccountBalance(new Long(0));
	        nextAccountNumber += 1;
	        savingsAccount.setAccountNumber(nextAccountNumber);
	        System.out.println(savingsAccount);
	        repo.save(savingsAccount);

	        return repo.findByAccountNumber(savingsAccount.getAccountNumber());
	}

	@Override
	public void deposit( Integer accNo, Long amount) {
		SavingAccount savingsAccount = repo.findByAccountNumber(accNo);
		Long prevBal = savingsAccount.getAccountBalance();
		Long newBal = savingsAccount.getAccountBalance() + amount;
        savingsAccount.setAccountBalance((long) (savingsAccount.getAccountBalance() + amount) );
       repo.save(savingsAccount);
        Date date = new Date();
        PersonalTransaction personalTransaction = new PersonalTransaction(accNo , date, prevBal, newBal, "Deposit" ,"Savings");
        personalRepo.save(personalTransaction);
	}

	

	 public String withdraw(Integer accNo, Long amount) {
 	    SavingAccount savingsAccount = repo.findByAccountNumber(accNo);
 	    if(savingsAccount.getAccountBalance()>=amount) {
 	    	Long prevBal = savingsAccount.getAccountBalance();
 	    	Long newBal = savingsAccount.getAccountBalance() - amount;
 	    	savingsAccount.setAccountBalance(newBal);
	            repo.save(savingsAccount);
	            Date date = new Date();
		        PersonalTransaction personalTransaction = new PersonalTransaction(accNo ,date, prevBal, newBal, "Withdraw" ,"Savings");
		        personalRepo.save(personalTransaction);
		        return "Done";
 	    }
 	    else {
 	    	return "Insufficient balance";
 	    }
         
 }

	

	
	@Override
	public SavingAccount getAccount(int accNo) {
		return repo.findByAccountNumber(accNo);
	}
	
	
	 @Override
	    public Long retrieveAccountBalance(long accountId) {
	        Optional<SavingAccount> account = repo.findById(accountId);
	        if (!account.isPresent()) {
	            throw new AccountNotFoundException(
	                  String.format("Account %s not found.", accountId));
	        }
	        return account.get().getAccountBalance();
	    }

	@Override
	public List<SavingAccount> getAllSavingsAccounts() {
		// TODO Auto-generated method stub
		return null;
	}
	 
}
