package com.infy.icci.managedBean;



import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.infy.icci.to.LoginTO;
import com.infy.icci.wrapper.InfyCreditCardWrapper;

public class LoginMB 
{
	private String userName;
	private String password;
	private String message;
	private HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	
	/**
	 * 
	 * Description: checks the userName and password enter by the user, and determinate when a customer is CR
	 * Modified by: Luis Miguel Marulanda Jaramillo
	 * @return String
	 * 
	 */
	public String validateLogin()
	{
		try{
			//create an object of type LoginTO
			LoginTO to = new LoginTO();
			//set the value userName and password for the to object
			to.setUserName(this.userName);
			to.setPassword(this.password);
			//create an object of type InfyCreditCardWrapper
			InfyCreditCardWrapper wrap = new InfyCreditCardWrapper();
			//call the validateLogn method passing to object
			to = wrap.validateLogin(to);
			
			//setting the userName to the session
			session.setAttribute("userName", to.getUserName());
			//checks if the customer is CR
			if(to.getUserName().equals("dav222") && to.getPassword().equals("7878da")){
				return "successCr";
			}else{
				return "success";
			}
			
		}
		catch(Exception e){
			this.setMessage(e.getMessage());
			return "fail";
		}
		
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
	 * Method getPassword
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Method setPassword
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
}
