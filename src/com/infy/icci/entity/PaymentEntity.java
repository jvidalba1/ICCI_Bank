package com.infy.icci.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ICCI_PAYMENT")
public class PaymentEntity 
{
	@Id
	@SequenceGenerator(name="seq_paymentid" , allocationSize=1 ,
			initialValue=6005 , sequenceName="seq_paymentid")
	@GeneratedValue(generator="seq_paymentid" ,
			strategy=GenerationType.SEQUENCE)
	private int paymentId;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cardNo")
	private CardEntity card;
	private char paymentType;
	private double amountPaid;
	@Temporal(TemporalType.DATE)
	private Date dateOfPayment;
	
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
	 * Method getCard
	 * @return the card
	 */
	public CardEntity getCard() {
		return card;
	}
	/**
	 * Method setCard
	 * @param card the card to set
	 */
	public void setCard(CardEntity card) {
		this.card = card;
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
}
