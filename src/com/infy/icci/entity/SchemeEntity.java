package com.infy.icci.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ICCI_SCHEME")
public class SchemeEntity 
{
	@Id
	private char schemeId;
	private double schemeAmount;
	private double minimumAmount;
	private double interestRate;
	
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
	 * Method getSchemeAmount
	 * @return the schemeAmount
	 */
	public double getSchemeAmount() {
		return schemeAmount;
	}
	/**
	 * Method setSchemeAmount
	 * @param schemeAmount the schemeAmount to set
	 */
	public void setSchemeAmount(double schemeAmount) {
		this.schemeAmount = schemeAmount;
	}
	/**
	 * Method getMinimumAmount
	 * @return the minimumAmount
	 */
	public double getMinimumAmount() {
		return minimumAmount;
	}
	/**
	 * Method setMinimumAmount
	 * @param minimumAmount the minimumAmount to set
	 */
	public void setMinimumAmount(double minimumAmount) {
		this.minimumAmount = minimumAmount;
	}
	/**
	 * Method getInterestRate
	 * @return the interestRate
	 */
	public double getInterestRate() {
		return interestRate;
	}
	/**
	 * Method setInterestRate
	 * @param interestRate the interestRate to set
	 */
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
}
