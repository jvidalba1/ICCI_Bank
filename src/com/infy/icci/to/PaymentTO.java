/**
 * 
 */
package com.infy.icci.to;

import java.util.Date;

/**
 * @author edgardo_406751
 *
 */
public class PaymentTO 
{
	private int paymentId;
	private long cardNo;
	private char paymentType;
	private double amountPaid;
	private Date dateOfPayment;
	private String cardStatus;
	
	/**
	 * Method getPaymentId
	 * @return the paymentId
	 */
	public int getPaymentId() {
		return paymentId;
	}
	/**
	 * Method setPaymentId
	 * @param paymentId the paymentId to set
	 */
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
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
	 * Method getPaymentType
	 * @return the paymentType
	 */
	public char getPaymentType() {
		return paymentType;
	}
	/**
	 * Method setPaymentType
	 * @param paymentType the paymentType to set
	 */
	public void setPaymentType(char paymentType) {
		this.paymentType = paymentType;
	}
	/**
	 * Method getAmountPaid
	 * @return the amountPaid
	 */
	public double getAmountPaid() {
		return amountPaid;
	}
	/**
	 * Method setAmountPaid
	 * @param amountPaid the amountPaid to set
	 */
	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}
	/**
	 * Method getDateOfPayment
	 * @return the dateOfPayment
	 */
	public Date getDateOfPayment() {
		return dateOfPayment;
	}
	/**
	 * Method setDateOfPayment
	 * @param dateOfPayment the dateOfPayment to set
	 */
	public void setDateOfPayment(Date dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}
	/**
	 * Method getCardStatus
	 * @return the cardStatus
	 */
	public String getCardStatus() {
		return cardStatus;
	}
	/**
	 * Method setCardStatus
	 * @param cardStatus the cardStatus to set
	 */
	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}
}
