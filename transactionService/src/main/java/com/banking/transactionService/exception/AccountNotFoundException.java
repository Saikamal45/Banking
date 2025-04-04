package com.banking.transactionService.exception;


public class AccountNotFoundException extends Throwable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccountNotFoundException(String msg) {
		super(msg);
	}
}

