package com.infy.icci.exception;


public class NotPaymentDone extends Exception
{
	
	private static final long serialVersionUID = 1L;

	public NotPaymentDone() throws Exception
	{
		super("This cardNo can not be unblock" +
			  " because the specific is not: 'Payment Not Done' ");
	}
}
