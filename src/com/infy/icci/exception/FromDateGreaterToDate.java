package com.infy.icci.exception;

public class FromDateGreaterToDate extends Exception{

	private static final long serialVersionUID = 1L;

	public FromDateGreaterToDate(){
		super("Date 'FromDate' is greater than 'ToDate'" );
	}
}
