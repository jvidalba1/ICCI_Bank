package com.infy.icci.managedBean;

import java.util.Random;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.infy.icci.to.CustomerTO;
import com.infy.icci.wrapper.InfyCreditCardWrapper;

public class CustomerMB 
{
	private Integer customerId;
	private String name;
	private String address;
	private String phone;
	private String email;
	private String userName;
	private String randomCode;
	private String message;
	private String enteredCode;
	private HttpSession session =
		(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	private InfyCreditCardWrapper iccw; 
	
	/**
	 * 
	 * Constructor
	 */
	public CustomerMB()
	{
		if(session.getAttribute("userName")==null)
		{
			setMessage("You Are Not Allow to enter here, please Login");
		}
		else
		{
			iccw = new InfyCreditCardWrapper();
			this.setRandomCode(generateRandomeChar());
		}
	}

	/**
	 * Description: Create a random characters generator 
	 * 				for use as captcha 
	 * Modified by: Luis Miguel Marulanda Jaramillo
	 */
	public String generateRandomeChar()
	{
		String sCharacters = "";
		Random random = new Random();
		while(sCharacters.length() <= 5){
			char cChar = (char)random.nextInt(123);
			if((cChar >= 48 && cChar <= 57) ||
					(cChar >= 97 && cChar <= 122)){
				sCharacters += cChar;
			}
		}	

		return sCharacters;
	}


	/**
	 * 
	 * Description: Validate the entered code and the random code
	 * 				are equal.
	 * Modified by: Luis Miguel Marulanda Jaramillo
	 * @return
	 */
	public String validateCustomer()
	{
		CustomerTO cust = new CustomerTO();
		cust.setCustomerId(customerId);
		try {
			cust.setUserName(session.getAttribute("userName").toString());
			cust = iccw.getCustomerDetailsForLogin(cust);
			session.setAttribute("customerID", cust.getCustomerId());
			
			if(this.randomCode.equals(this.enteredCode)){
				return "success";
			}else{
				return "failure";
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
			e.printStackTrace();
			return "failure";
		}
	}

	/**
	 * Method getCustomerId
	 * @return the customerId
	 */
	public Integer getCustomerId() {
		return customerId;
	}

	/**
	 * Method setCustomerId
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(Integer customerId) {
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

	/**
	 * Method getRandomCode
	 * @return the randomCode
	 */
	public String getRandomCode() {
		return randomCode;
	}

	/**
	 * Method setRandomCode
	 * @param randomCode the randomCode to set
	 */
	public void setRandomCode(String randomCode) {
		this.randomCode = randomCode;
	}

	/**
	 * Method getMessage
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Method setMessage
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Method getEnteredCode
	 * @return the enteredCode
	 */
	public String getEnteredCode() {
		return enteredCode;
	}

	/**
	 * Method setEnteredCode
	 * @param enteredCode the enteredCode to set
	 */
	public void setEnteredCode(String enteredCode) {
		this.enteredCode = enteredCode;
	}
}
