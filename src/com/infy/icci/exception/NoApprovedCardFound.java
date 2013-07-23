package com.infy.icci.exception;

public class NoApprovedCardFound extends Exception{

	private static final long serialVersionUID = 1L;

	public NoApprovedCardFound()
	{
		super("No approved card found in the DB");
	}
}
