package com.infy.icci.entity;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ICCI_CARD")
public class CardEntity 
{
	@Id
	private long cardNo;
	private int pin;
	@ManyToOne(targetEntity=SchemeEntity.class, cascade=CascadeType.ALL)
	@JoinColumn(name="schemeId")
	private SchemeEntity scheme;
	private double balanceAmount;
	private double cardAmount;
	@Temporal(TemporalType.DATE)
	private Date dateOfRegistration;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="customerId")
	private CustomerEntity customer;
	
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
	 * Method getScheme
	 * @return the scheme
	 */
	public SchemeEntity getScheme() {
		return scheme;
	}
	/**
	 * Method setScheme
	 * @param scheme the scheme to set
	 */
	public void setScheme(SchemeEntity scheme) {
		this.scheme = scheme;
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
	 * Method getCustomer
	 * @return the customer
	 */
	public CustomerEntity getCustomer() {
		return customer;
	}
	/**
	 * Method setCustomer
	 * @param customer the customer to set
	 */
	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}
}
