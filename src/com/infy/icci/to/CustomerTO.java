/**
 * 
 */
package com.infy.icci.to;

/**
 * @author edgardo_406751
 *
 */
public class CustomerTO 
{
	private int customerId;
	private String name;
	private String address;
	private String phone;
	private String eMail;
	private String userName;
	
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
	public String getUserName() {
		return userName;
	}
	/**
	 * Method setUserName
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
