package com.banking.User_Service.exception;

public class UserNotFound extends Throwable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFound(String msg) {
		super(msg);
	}
}
