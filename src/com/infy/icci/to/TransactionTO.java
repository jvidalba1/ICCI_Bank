/**
 * 
 */
package com.infy.icci.to;

import java.util.Date;


/**
 * @author edgardo_406751
 *
 */
public class TransactionTO 
{
	private int transactionId;
	private Date dateOfTransaction;
	private String description;
	private double amount;
	private long cardNo;
	private double balanceAmount;

	/**
	 * Method getTransactionId
	 * @return the transactionId
	 */
	public int getTransactionId() {
		return transactionId;
	}
	/**
	 * Method setTransactionId
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	/**
	 * Method getDateOfTransaction
	 * @return the dateOfTransaction
	 */
	public Date getDateOfTransaction() {
		return dateOfTransaction;
	}
	/**
	 * Method setDateOfTransaction
	 * @param dateOfTransaction the dateOfTransaction to set
	 */
	public void setDateOfTransaction(Date dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}
	/**
	 * Method getDescription
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Method setDescription
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * Method getAmount
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * Method setAmount
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
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
}
