package com.banking.accountService.exception;

public class InsufficentBalanceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsufficentBalanceException(String msg) {
		super(msg);
	}
}
