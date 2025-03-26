package com.banking.accountService.serviceImplementation;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.banking.accountService.entity.Account;
import com.banking.accountService.exception.AccountNotFoundException;
import com.banking.accountService.exception.InsufficentBalanceException;
import com.banking.accountService.repository.AccountRepository;
import com.banking.accountService.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Account createAccount(Account account) {
		account.setCreatedAt(LocalDate.now());
		return accountRepository.save(account);
		
	}

	@Override
	public Account getAccountById(int accountId) throws AccountNotFoundException {
		return accountRepository.findById(accountId).
		orElseThrow(()->new AccountNotFoundException("Account with Id :"+accountId+" is Not Found..."));
		
	}

	@Override
	public Account updateAccount(int accountId, Account account) throws AccountNotFoundException {
		Account accountById = accountRepository.findById(accountId).
		orElseThrow(()->new AccountNotFoundException("Account with Id :"+accountId+" is Not Found..."));
		
		if(accountById!=null) {
			accountById.setUserId(account.getUserId());
			accountById.setAccountType(account.getAccountType());
			accountById.setBalance(account.getBalance());
			accountById.setStatus(account.getStatus());
			accountById.setCreatedAt(LocalDate.now());
			}
		return accountById;
	}

	@Override
	public String deleteAccount(int accountId) throws AccountNotFoundException{
		Account account = accountRepository.findById(accountId).
		orElseThrow(()->new AccountNotFoundException("Account with Id :"+accountId+" is Not Found..."));
	 accountRepository.deleteById(account.getAccountId());
	 
	 return "Account with ID " + accountId + " has been successfully deleted...";
	}

	@Override
	public double accountBalanace(int accountId) throws AccountNotFoundException{
		Account accountDetails = accountRepository.findById(accountId).
		orElseThrow(()->new AccountNotFoundException("Account with Id :"+accountId+" is Not Found..."));
		
		return accountDetails.getBalance();
	}

	@Override
	public Account depositAmount(int accountId, double amount) throws AccountNotFoundException {
		if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
		Account deposit = accountRepository.findById(accountId).
		orElseThrow(()->new AccountNotFoundException("Account with Id :"+accountId+" is Not Found..."));
		
		deposit.setBalance(deposit.getBalance()+amount);
		return accountRepository.save(deposit);
	}

	@Override
	public Account withdrawAmount(int accountId, double amount) throws AccountNotFoundException, InsufficentBalanceException {
		if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
		
		Account withDraw = accountRepository.findById(accountId).
		orElseThrow(()->new AccountNotFoundException("Account with Id :"+accountId+" is Not Found..."));
		
		
		if(amount>withDraw.getBalance()) {
			throw new InsufficentBalanceException("Insufficient Balance.... Available Balance is :"+withDraw.getBalance());
		}
		withDraw.setBalance(withDraw.getBalance()-amount);
		return accountRepository.save(withDraw);
	}

}
