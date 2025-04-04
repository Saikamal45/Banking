package com.banking.transactionService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.banking.transactionService.entity.Transaction;
import com.banking.transactionService.exception.AccountNotFoundException;
import com.banking.transactionService.exception.InsufficientBalanceException;
import com.banking.transactionService.exception.TransactionNotFoundException;
import com.banking.transactionService.service.TransactionService;

import jakarta.ws.rs.GET;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@PutMapping("/deposit")
	public ResponseEntity<Transaction> deposit(@RequestParam int accountId,int amount)throws AccountNotFoundException, InsufficientBalanceException{
		Transaction deposit = transactionService.deposit(accountId, amount);
		return new ResponseEntity<Transaction>(deposit,HttpStatus.OK);
	}
	
	@PostMapping("/withdraw")
	public ResponseEntity<Transaction> withdraw(@RequestParam int accountId,@RequestParam double amount)throws InsufficientBalanceException{
		Transaction withdrawal = transactionService.withdrawal(accountId, amount);
		return new ResponseEntity<Transaction>(withdrawal,HttpStatus.OK);
	}
	
	@PostMapping("/transfer")
	public ResponseEntity<Transaction> transfer(@RequestParam int fromAccountId,@RequestParam int toAccountId,@RequestParam double amount)throws AccountNotFoundException,InsufficientBalanceException{
		Transaction transfer = transactionService.transfer(fromAccountId, toAccountId, amount);
		return new ResponseEntity<Transaction>(transfer,HttpStatus.OK);
	}
	
	@GetMapping("/getTransactions")
	public ResponseEntity<List<Transaction>> getTransactionByAccountId(@RequestParam int accountId)throws AccountNotFoundException{
		List<Transaction> transactionsByAccountId = transactionService.getTransactionsByAccountId(accountId);
		return new ResponseEntity<List<Transaction>>(transactionsByAccountId,HttpStatus.OK);
		
	}
	
	@GetMapping("/getTransactionsByDate")
	public ResponseEntity<List<Transaction>> getTransactionsCreatedBetween(@RequestParam int accountId,
			@RequestParam String startDate,@RequestParam String endDate)throws AccountNotFoundException{
		List<Transaction> transactionsByDateRange = transactionService.getTransactionsByDateRange(accountId, startDate, endDate);
		return new ResponseEntity<List<Transaction>>(transactionsByDateRange,HttpStatus.OK);
	}
	
	@GetMapping("/getTransactionById")
	public ResponseEntity<Transaction> getTransactionById(@RequestParam int transactionId)throws TransactionNotFoundException{
		Transaction transactionById = transactionService.getTransactionById(transactionId);
		return new ResponseEntity<Transaction>(transactionById,HttpStatus.OK);
	}
	
	@GetMapping("/getLastNTransactions")
	public ResponseEntity<List< Transaction>> getLastNTransactions(@RequestParam int accountId,@RequestParam int count) throws AccountNotFoundException{
		List<Transaction> lastNTransactions = transactionService.getLastNTransactions(accountId, count);
		return new ResponseEntity<List<Transaction>>(lastNTransactions,HttpStatus.OK);
	}
}
