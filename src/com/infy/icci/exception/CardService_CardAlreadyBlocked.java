package com.infy.icci.exception;


public class CardService_CardAlreadyBlocked extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public CardService_CardAlreadyBlocked() {
		super("Card already blocked");
	}
}
