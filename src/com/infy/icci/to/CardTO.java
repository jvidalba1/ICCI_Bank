/**
 * 
 */
package com.infy.icci.to;

import java.util.Date;


/**
 * @author edgardo_406751
 *
 */
public class CardTO 
{
	private long cardNo;
	private int pin;
	private char schemeId;
	private double balanceAmount;
	private double cardAmount;
	private Date dateOfRegistration;
	
	private int customerId;
	
	public CardTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public CardTO(long cardNo, int pin, char schemeId, double balanceAmount,
			double cardAmount, Date dateOfRegistration, int customerId) {
		super();
		this.cardNo = cardNo;
		this.pin = pin;
		this.schemeId = schemeId;
		this.balanceAmount = balanceAmount;
		this.cardAmount = cardAmount;
		this.dateOfRegistration = dateOfRegistration;
		this.customerId = customerId;
	}


	/**
	 * Method getCardNo
	 * @return the cardNo
	 */
	public long getCardNo() {
		return cardNo;
	}
	/**
	 * Method setCardNo
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(long cardNo) {
		this.cardNo = cardNo;
	}
	/**
	 * Method getPin
	 * @return the pin
	 */
	public int getPin() {
		return pin;
	}
	/**
	 * Method setPin
	 * @param pin the pin to set
	 */
	public void setPin(int pin) {
		this.pin = pin;
	}
	/**
	 * Method getSchemeId
	 * @return the schemeId
	 */
	public char getSchemeId() {
		return schemeId;
	}
	/**
	 * Method setSchemeId
	 * @param schemeId the schemeId to set
	 */
	public void setSchemeId(char schemeId) {
		this.schemeId = schemeId;
	}
	/**
	 * Method getBalanceAmount
	 * @return the balanceAmount
	 */
	public double getBalanceAmount() {
		return balanceAmount;
	}
	/**
	 * Method setBalanceAmount
	 * @param balanceAmount the balanceAmount to set
	 */
	public void setBalanceAmount(double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	/**
	 * Method getCardAmount
	 * @return the cardAmount
	 */
	public double getCardAmount() {
		return cardAmount;
	}
	/**
	 * Method setCardAmount
	 * @param cardAmount the cardAmount to set
	 */
	public void setCardAmount(double cardAmount) {
		this.cardAmount = cardAmount;
	}
	/**
	 * Method getDateOfRegistration
	 * @return the dateOfRegistration
	 */
	public Date getDateOfRegistration() {
		return dateOfRegistration;
	}
	/**
	 * Method setDateOfRegistration
	 * @param dateOfRegistration the dateOfRegistration to set
	 */
	public void setDateOfRegistration(Date dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}
	/**
	 * Method getCustomerId
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}
	/**
	 * Method setCustomerId
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
}
