package com.icinbank.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.icinbank.model.AccountHandler;
import com.icinbank.model.PersonalTransaction;
import com.icinbank.model.PrimaryAccount;
import com.icinbank.model. AccountNotFoundException;
import com.icinbank.repository.PersonalTransactionRepo;
import com.icinbank.repository.PrimaryAccountRepo;
import com.icinbank.service.PrimaryAccountService;
import com.icinbank.service.TransactionService;
import com.icinbank.service.UserService;

public class PrimaryAccountServiceImpl implements PrimaryAccountService{
	 @Autowired
	 private PrimaryAccountRepo primaryAccountDao;
	   
	 @Autowired
	 private UserService userService;
	 
	 @Autowired
	 private TransactionService transactionService;
	 
	 @Autowired
	 private PersonalTransactionRepo personalTransactionRepo;
	 
     private static int nextAccountNumber = 22113344;

	@Override
	public PrimaryAccount createPrimaryAccount() {
		PrimaryAccount primaryAccount = new PrimaryAccount();
        primaryAccount.setAccountBalance(new Long(0));
        nextAccountNumber += 1;
        primaryAccount.setAccountNumber(nextAccountNumber);
        primaryAccountDao.save(primaryAccount);
        return primaryAccountDao.findByAccountNumber(primaryAccount.getAccountNumber());
	}

	@Override
	public List<PrimaryAccount> getAllPrimaryAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String withdraw(Integer accNo, Long amount) {
PrimaryAccount primaryAccount = primaryAccountDao.findByAccountNumber(accNo);
		
		if(primaryAccount.getAccountBalance()>=amount) {
			Long prevBal = primaryAccount.getAccountBalance();
			Long newBal = primaryAccount.getAccountBalance() - amount;
	        primaryAccount.setAccountBalance(newBal);
	        primaryAccountDao.save(primaryAccount);
	        Date date = new Date();
	        PersonalTransaction personalTransaction = new PersonalTransaction(accNo ,date, prevBal, newBal, "Withdraw" ,"Primary");
	        personalTransactionRepo.save(personalTransaction);

	        return "Done";
		} else {
			return "Insufficient Balance";
		}

		
	}

	@Override
	public void deposit(Integer accNo, Long amount) {
		PrimaryAccount primaryAccount = primaryAccountDao.findByAccountNumber(accNo);
      	Long prevBal = primaryAccount.getAccountBalance();
      	Long newBal = primaryAccount.getAccountBalance() + amount;
        primaryAccount.setAccountBalance(newBal );
        primaryAccountDao.save(primaryAccount);
        
        Date date = new Date();
        PersonalTransaction personalTransaction = new PersonalTransaction(accNo ,date, prevBal , newBal, "Deposit" ,"Primary");
        personalTransactionRepo.save(personalTransaction);
		
	}

	@Override
	public PrimaryAccount getAccount(int accNo) {
		return primaryAccountDao.findByAccountNumber(accNo);
	}

	@Override
    public Long retrieveAccountBalance(long accountId) {
        Optional<PrimaryAccount> account = primaryAccountDao.findById(accountId);
        if (!account.isPresent()) {
            throw new AccountNotFoundException(
                  String.format("Account %s not found.", accountId));
        }
        return account.get().getAccountBalance();
    }
    
    
    @Override
    public AccountHandler retrieveAccountBalanceAndListOfTransactions(int accountno) {

        PrimaryAccount account = primaryAccountDao.findByAccountNumber(accountno);
        if (account==null) {
            throw new AccountNotFoundException(
                  String.format("Account %s not found.", accountno));
        }
        AccountHandler accountSnapshot =
                new AccountHandler(
                        account.getAccountNumber(), 
                        account.getAccountBalance(), 
                        transactionService.retrieveTransactionsForAccount(accountno));
        return accountSnapshot;
     
    }


}
