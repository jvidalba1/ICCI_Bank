package com.infy.icci.exception;

public class CardService_CardNotFound extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public CardService_CardNotFound() {
		super("No card number found");
	}
}
