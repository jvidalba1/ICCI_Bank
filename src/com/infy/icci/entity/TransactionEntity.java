package com.infy.icci.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ICCI_TRANSACTION")
public class TransactionEntity 
{
	@Id
	@SequenceGenerator(name="seq_transactionid",sequenceName="seq_transactionid",
			allocationSize=1,initialValue=9004)
	@GeneratedValue(generator="seq_transactionid",
			strategy=GenerationType.SEQUENCE)
	private int transactionId;
	@Temporal(TemporalType.DATE)
	private Date dateOfTransaction;
	private String description;
	private double amount;
	private long cardNo;
	
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
}
