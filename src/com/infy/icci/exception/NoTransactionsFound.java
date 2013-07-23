package com.infy.icci.exception;

public class NoTransactionsFound extends Exception{


	private static final long serialVersionUID = 1L;

	public NoTransactionsFound() throws Exception
	{
		super("There are no transactions");
	}
}
