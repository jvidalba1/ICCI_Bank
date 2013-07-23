package com.infy.icci.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ICCI_CUSTOMER")
public class CustomerEntity 
{
	@Id
	@SequenceGenerator(name="seq_customerid", sequenceName="seq_customerid",
			initialValue=20006, allocationSize=1)
	@GeneratedValue(generator="seq_customerid",
			strategy=GenerationType.SEQUENCE)
	private int customerId;
	private String name;
	private String address;
	private String phone;
	private String eMail;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="userName")
	private LoginEntity userName;
	
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
	 * Method getEMail
	 * @return the eMail
	 */
	public String getEMail() {
		return eMail;
	}
	/**
	 * Method setEMail
	 * @param mail the eMail to set
	 */
	public void setEMail(String mail) {
		eMail = mail;
	}
	/**
	 * Method getUserName
	 * @return the userName
	 */
	public LoginEntity getUserName() {
		return userName;
	}
	/**
	 * Method setUserName
	 * @param userName the userName to set
	 */
	public void setUserName(LoginEntity userName) {
		this.userName = userName;
	}
	
}
