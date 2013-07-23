package com.infy.icci.managedBean;

import java.util.Date;

import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

import com.infy.icci.to.CardTO;
import com.infy.icci.wrapper.InfyCreditCardWrapper;

public class CardMB 
{
	private Long cardNo;
	//used to assign the value of each field of the CardDetails.jsp
	private String cardNo1;
	private String cardNo2;
	private String cardNo3;
	private String cardNo4;
	private String message;
	private Integer pin;
	private Double balanceAmount;
	private Double cardAmount;
	private Date dateOfRegistration;
	private InfyCreditCardWrapper iccw;
	private CardTO cardTo;
	private HttpSession session =
		(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	
	/**
	 * 
	 * Description: Used to check the cardNo and the pin entered by the user.
	 * If valid,sets the cardNo in session scope.It then checks if the customer
	 * has chosen a scheme by invoking schemechosen method. If the customer has 
	 * not yet chosen a scheme, directs him to choose the scheme else directs to the customer home page.
	 * Modified by: Simon Escobar Benitez
	 * @return
	 */
	public String checkCardDetails()
	{
		/*Concat all the fields to form the complete card number*/
		cardNo = Long.parseLong(cardNo1+cardNo2+cardNo3+cardNo4);
		cardTo.setCardNo(cardNo);
		cardTo.setPin(pin);
		try {
			cardTo.setCustomerId(Integer.parseInt(session.getAttribute("customerID").toString()));
			/* call the wrapper method get the card details if the card number and pin entered are correct*/
			 cardTo = iccw.getCardDetailsForLogin(cardTo);
			 /*set all the attributes to the bean*/
			 balanceAmount = cardTo.getBalanceAmount();
			 cardAmount = cardTo.getCardAmount();
			 /*set the attribute cardNo at the session scope to get it in other modules*/
			 session.setAttribute("cardNo", cardTo.getCardNo());
			 setMessage(null);
			 if(cardTo.getSchemeId()==' ')
			 {
				 return "noscheme";
			 }
			 return "success";
		}
		catch(NoResultException e)
		{
			setMessage(e.getMessage());
			e.printStackTrace();
			return "failure";
		}
		catch (Exception e) {
			setMessage(e.getMessage());
			e.printStackTrace();
			return "failure";
		}
	}

	/**
	 * Constructor
	 * Modified by: Simon Escobar Benitez
	 */
	public CardMB() {
		/* if someone enter to this page and is not logged, can't see anything*/
		if(session.getAttribute("userName") == null)
		{
			setMessage("You Are Not Allow to enter here, please Login");
		}
		else
		{
			cardTo= new CardTO();
			iccw = new InfyCreditCardWrapper();
		}
	}
	
	/**
	 * Method getCardNo
	 * @return the cardNo
	 */
	public Long getCardNo() {
		return cardNo;
	}

	/**
	 * Method setCardNo
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(Long cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * Method getCardNo1
	 * @return the cardNo1
	 */
	public String getCardNo1() {
		return cardNo1;
	}

	/**
	 * Method setCardNo1
	 * @param cardNo1 the cardNo1 to set
	 */
	public void setCardNo1(String cardNo1) {
		this.cardNo1 = cardNo1;
	}

	/**
	 * Method getCardNo2
	 * @return the cardNo2
	 */
	public String getCardNo2() {
		return cardNo2;
	}

	/**
	 * Method setCardNo2
	 * @param cardNo2 the cardNo2 to set
	 */
	public void setCardNo2(String cardNo2) {
		this.cardNo2 = cardNo2;
	}

	/**
	 * Method getCardNo3
	 * @return the cardNo3
	 */
	public String getCardNo3() {
		return cardNo3;
	}

	/**
	 * Method setCardNo3
	 * @param cardNo3 the cardNo3 to set
	 */
	public void setCardNo3(String cardNo3) {
		this.cardNo3 = cardNo3;
	}

	/**
	 * Method getCardNo4
	 * @return the cardNo4
	 */
	public String getCardNo4() {
		return cardNo4;
	}

	/**
	 * Method setCardNo4
	 * @param cardNo4 the cardNo4 to set
	 */
	public void setCardNo4(String cardNo4) {
		this.cardNo4 = cardNo4;
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
	 * Method getPin
	 * @return the pin
	 */
	public Integer getPin() {
		return pin;
	}

	/**
	 * Method setPin
	 * @param pin the pin to set
	 */
	public void setPin(Integer pin) {
		this.pin = pin;
	}

	/**
	 * Method getBalanceAmount
	 * @return the balanceAmount
	 */
	public Double getBalanceAmount() {
		return balanceAmount;
	}

	/**
	 * Method setBalanceAmount
	 * @param balanceAmount the balanceAmount to set
	 */
	public void setBalanceAmount(Double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	/**
	 * Method getCardAmount
	 * @return the cardAmount
	 */
	public Double getCardAmount() {
		return cardAmount;
	}

	/**
	 * Method setCardAmount
	 * @param cardAmount the cardAmount to set
	 */
	public void setCardAmount(Double cardAmount) {
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
}
