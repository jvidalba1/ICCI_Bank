package com.infy.icci.managedBean;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;

import com.infy.icci.to.CardTO;
import com.infy.icci.to.CustomerTO;
import com.infy.icci.to.SchemeTO;
import com.infy.icci.wrapper.InfyCreditCardWrapper;

public class SchemeMB 
{
	private Character schemeId;
	private Double schemeAmount;
	private Double minimumAmount;
	private Double interestRate;
	private String message;
	private HttpSession session =
		(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	private boolean permission;
	
	/**
	 * Constructor
	 */
	public SchemeMB(){
		if(session.getAttribute("userName") == null){
			this.setMessage("You Are Not Allow to enter here, please Login");
			this.setPermission(false);
		}else{
			this.setPermission(true);
		}
	}
	
	/**
	 * 
	 * Description: This method calls the getSchemeDetails in the wrapper class
	 * 				and sets the values into the schemeTO class
	 * Modified by: Mateo Vidal
	 * @param event
	 */
	public void display(ValueChangeEvent event)
	{
		InfyCreditCardWrapper wrapper = new InfyCreditCardWrapper();
		SchemeTO schemeTO = new SchemeTO();
		char sel = (Character)event.getNewValue();
		
		try{
			/*
			 * Create the session and set the sel value into it with 
			 * the setAttribute method 
			 */
			HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("schemeId", sel);
			schemeTO.setSchemeId(sel);
			
			schemeTO = wrapper.getSchemeDetails(schemeTO);
			this.setInterestRate(schemeTO.getInterestRate());
			this.setMinimumAmount(schemeTO.getMinimumAmount());
			this.setSchemeAmount(schemeTO.getSchemeAmount());
			this.setSchemeId(schemeTO.getSchemeId());
		}catch(Exception e)
		{
			this.setMessage(e.getMessage());
		}		
	}
	
	/**
	 * 
	 * Description: This method calls the updateSchemeDetails
	 * in the wrapper class
	 * Modified by: Mateo Vidal
	 * @return success or fail, depend if the process is correct
	 */
	public String update()
	{
		try{
			InfyCreditCardWrapper wrapper = new InfyCreditCardWrapper();

			/*
			 * Retrieve the schemeId value into the session created
			 * in the display method
			 * */			
			this.setSchemeId((Character) session.getAttribute("schemeId"));

			CustomerTO  custToObj = new CustomerTO();
			custToObj.setCustomerId(Integer.parseInt(session.getAttribute("customerID").toString()));

			CardTO cardToObj = new CardTO();
			cardToObj.setCardNo(Long.parseLong(session.getAttribute("cardNo").toString()));
			cardToObj.setSchemeId(this.getSchemeId());
			
			wrapper.updateSchemeDetails(cardToObj);
			return "success";
		}catch(Exception exception){
			this.setMessage(exception.getMessage());
			return "fail";
		}
	}

	/**
	 * Method getSchemeId
	 * @return the schemeId
	 */
	public Character getSchemeId() {
		return schemeId;
	}

	/**
	 * Method setSchemeId
	 * @param schemeId the schemeId to set
	 */
	public void setSchemeId(Character schemeId) {
		this.schemeId = schemeId;
	}

	/**
	 * Method getSchemeAmount
	 * @return the schemeAmount
	 */
	public Double getSchemeAmount() {
		return schemeAmount;
	}

	/**
	 * Method setSchemeAmount
	 * @param schemeAmount the schemeAmount to set
	 */
	public void setSchemeAmount(Double schemeAmount) {
		this.schemeAmount = schemeAmount;
	}

	/**
	 * Method getMinimumAmount
	 * @return the minimumAmount
	 */
	public Double getMinimumAmount() {
		return minimumAmount;
	}

	/**
	 * Method setMinimumAmount
	 * @param minimumAmount the minimumAmount to set
	 */
	public void setMinimumAmount(Double minimumAmount) {
		this.minimumAmount = minimumAmount;
	}

	/**
	 * Method getInterestRate
	 * @return the interestRate
	 */
	public Double getInterestRate() {
		return interestRate;
	}

	/**
	 * Method setInterestRate
	 * @param interestRate the interestRate to set
	 */
	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
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
	 * Method isPermission
	 * @return the permission
	 */
	public boolean isPermission() {
		return permission;
	}

	/**
	 * Method setPermission
	 * @param permission the permission to set
	 */
	public void setPermission(boolean permission) {
		this.permission = permission;
	}

}
