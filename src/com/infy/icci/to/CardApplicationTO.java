/**
 * 
 */
package com.infy.icci.to;

/**
 * @author edgardo_406751
 *
 */
public class CardApplicationTO 
{
	private String name;
	private String email;
	private String address;
	private String phone;
	private Integer applicationId;
	private boolean applicationSelected;
	
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
	 * Method getApplicationId
	 * @return the applicationId
	 */
	public Integer getApplicationId() {
		return applicationId;
	}
	/**
	 * Method setApplicationId
	 * @param applicationId the applicationId to set
	 */
	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}
	/**
	 * Method isApplicationSelected
	 * @return the applicationSelected
	 */
	public boolean isApplicationSelected() {
		return applicationSelected;
	}
	/**
	 * Method setApplicationSelected
	 * @param applicationSelected the applicationSelected to set
	 */
	public void setApplicationSelected(boolean applicationSelected) {
		this.applicationSelected = applicationSelected;
	}
}
