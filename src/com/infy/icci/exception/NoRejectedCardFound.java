package com.infy.icci.exception;

public class NoRejectedCardFound extends Exception{
	
	private static final long serialVersionUID = 1L;

	public NoRejectedCardFound(){
		super("No data found for RejectedCards");
	}
}
