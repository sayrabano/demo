package com.icinbank.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.icinbank.model.InvalidAccountException;
import com.icinbank.model.PrimaryAccount;
import com.icinbank.model.Transaction;
import com.icinbank.repository.PrimaryAccountRepo;
import com.icinbank.repository.TransactionRepo;
import com.icinbank.service.TransactionService;

public class TransactionServiceImpl implements TransactionService {
	 
    @Autowired
    private TransactionRepo transactionRepository;
    
    @Autowired
    private PrimaryAccountRepo primaryAccount;

    @Override
    public List<Transaction> retrieveTransactionsForAccount(int accountNumber) {
        
        return transactionRepository.retrieveTransactionsForAccount(accountNumber);
    }
    
    @Override
    public void transferMoney(Transaction transaction) {

       PrimaryAccount sourceAccount = 
        		primaryAccount.findByAccountNumber(transaction.getSourceAccountnumber());
        PrimaryAccount destinationAccount = 
        		primaryAccount.findByAccountNumber(transaction.getDestinationAccountnumber());
        if (sourceAccount==null || destinationAccount==null) {
            throw new InvalidAccountException(
                    "Invalid source account " + "or destination account ");
        }

        transaction.runValidationTests(
                sourceAccount, destinationAccount);

        Long transactionAmount = 
                transaction.getTransactionAmount();
        sourceAccount.setAccountBalance(
                sourceAccount.getAccountBalance() - transactionAmount); // subtraction logic
        destinationAccount.setAccountBalance(
                destinationAccount.getAccountBalance() + transactionAmount); // addition logic

        transactionRepository.save(transaction);

        primaryAccount.save(sourceAccount);
        primaryAccount.save(destinationAccount);

    }

	@Override
	public int retreiveAccountNumber(Long id) {
		Optional<PrimaryAccount> account =  primaryAccount.findById(id);
		int accNo = account.get().getAccountNumber();
		return accNo;
	}

}
