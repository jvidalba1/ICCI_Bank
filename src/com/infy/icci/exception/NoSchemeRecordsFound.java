package com.infy.icci.exception;


public class NoSchemeRecordsFound extends Exception{
	
	private static final long serialVersionUID = 1L;

	public NoSchemeRecordsFound() throws Exception
	{
		super("No scheme records found");
	}

}
