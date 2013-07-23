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
@Table(name="ICCI_CARD_APPLICATION_DETAILS")
public class CardApplicationEntity 
{
	@Id
	@SequenceGenerator(name="seq_applicationid", sequenceName="seq_applicationid",
			initialValue=40006, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_applicationid")
	private int applicationId;
	private String name;
	private String address;
	private String email;
	private String phone;
	@Temporal(TemporalType.DATE)
	private Date dateOfApplication;

	/**
	 * Method getApplicationId
	 * @return the applicationId
	 */
	public int getApplicationId() {
		return applicationId;
	}
	/**
	 * Method setApplicationId
	 * @param applicationId the applicationId to set
	 */
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	/**
	 * Method getName
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Method setName
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Method getEmail
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Method setEmail
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Method getAddress
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * Method setAddress
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * Method getPhone
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * Method setPhone
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * Method getDateOfApplication
	 * @return the dateOfApplication
	 */
	public Date getDateOfApplication() {
		return dateOfApplication;
	}
	/**
	 * Method setDateOfApplication
	 * @param dateOfApplication the dateOfApplication to set
	 */
	public void setDateOfApplication(Date dateOfApplication) {
		this.dateOfApplication = dateOfApplication;
	}
}
