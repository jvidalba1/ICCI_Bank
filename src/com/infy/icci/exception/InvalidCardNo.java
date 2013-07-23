package com.infy.icci.exception;

public class InvalidCardNo extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidCardNo() {
		super("Invalid Card Number Enter a valid Card Number");
	}
	
	public InvalidCardNo(String msg)
	{
		super(msg);
	}
}
