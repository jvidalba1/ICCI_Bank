/**
 * 
 */
package com.infy.icci.to;

import java.util.Date;

/**
 * @author edgardo_406751
 *
 */
public class ApprovedCardTO 
{
	private String customerName;
	private double schemeAmt;
	private String paymentType;
	private Date dateOfBlock;
	private long cardNo;
	/**
	 * @return the cardNo
	 */
	public long getCardNo() {
		return cardNo;
	}
	/**
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(long cardNo) {
		this.cardNo = cardNo;
	}
	/**
	 * Method getCustomerName
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * Method setCustomerName
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * Method getSchemeAmt
	 * @return the schemeAmt
	 */
	public double getSchemeAmt() {
		return schemeAmt;
	}
	/**
	 * Method setSchemeAmt
	 * @param schemeAmt the schemeAmt to set
	 */
	public void setSchemeAmt(double schemeAmt) {
		this.schemeAmt = schemeAmt;
	}
	/**
	 * Method getPaymentType
	 * @return the paymentType
	 */
	public String getPaymentType() {
		return paymentType;
	}
	/**
	 * Method setPaymentType
	 * @param paymentType the paymentType to set
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	/**
	 * Method getDateOfBlock
	 * @return the dateOfBlock
	 */
	public Date getDateOfBlock() {
		return dateOfBlock;
	}
	/**
	 * Method setDateOfBlock
	 * @param dateOfBlock the dateOfBlock to set
	 */
	public void setDateOfBlock(Date dateOfBlock) {
		this.dateOfBlock = dateOfBlock;
	}
}
