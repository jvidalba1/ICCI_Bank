package com.infy.icci.exception;

public class LoginService_PasswordIncorrect extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public LoginService_PasswordIncorrect() {
		super("Password incorrect");
	}
}
