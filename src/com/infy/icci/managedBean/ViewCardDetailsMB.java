package com.infy.icci.managedBean;

import java.util.Date;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.infy.icci.to.CardTO;
import com.infy.icci.to.CustomerTO;
import com.infy.icci.to.PaymentTO;
import com.infy.icci.to.SchemeTO;
import com.infy.icci.wrapper.InfyCreditCardWrapper;

public class ViewCardDetailsMB 
{
	private String customerName;
	private Long cardNo;
	private Double balanceAmount;
	private Character schemeId;
	private Double schemeAmount;
	private Double cardAmount;
	private Double rateOfInterest;
	private Date lastPaymentDate;
	private String cardStatus;
	private String message;
	private InfyCreditCardWrapper iccw;
	private CardTO cardTo;
	private SchemeTO schemeTo;
	private PaymentTO paymentTo;
	private CustomerTO custTo;
	private HttpSession session =
		(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	
	/**
	 * 
	 * Description: Gets the card number attribute from the session variable, 
	 * and gets some attributes whit it to display
	 * in a table some user,card,scheme and payment's details
	 * Modified by: Simon Escobar Benitez
	 * Constructor
	 */
	public ViewCardDetailsMB() {
		/* if someone enter to this page and is not logged, can't see anything*/
		if(session.getAttribute("cardNo") == null)
		{
			setMessage("You Are Not Allow to enter here, please Login");
		}
		else
		{
			/*initialize the objects to get all the details to display*/
			iccw = new InfyCreditCardWrapper();
			cardTo = new CardTO();
			schemeTo = new SchemeTO();
			custTo = new CustomerTO();
			//blocketCard = new BlockedCardTO();
			try {
				/*get the card number from session variable*/
				cardTo.setCardNo(Long.parseLong(session.getAttribute("cardNo").toString()));
				/*get card details*/
				cardTo = iccw.getCardDetails(cardTo);
				cardNo = cardTo.getCardNo();
				balanceAmount = cardTo.getBalanceAmount();
				cardAmount = cardTo.getCardAmount();
				/*get the scheme details using de card transfer object, got it before*/
				schemeTo.setSchemeId(cardTo.getSchemeId());
				schemeTo = 	iccw.getSchemeDetails(schemeTo);
				schemeAmount = schemeTo.getSchemeAmount();
				schemeId = schemeTo.getSchemeId();
				rateOfInterest = schemeTo.getInterestRate();
				/*get the last payment date using the card transfer object, got it before*/
				paymentTo =	iccw.getPaymentDetails(cardTo);
				lastPaymentDate = paymentTo.getDateOfPayment();
				cardStatus = paymentTo.getCardStatus();
				/*get the customer details  using the card transfer object, got it before*/
				custTo.setCustomerId(cardTo.getCustomerId());
				custTo = iccw.getCustomerDetails(custTo);
				customerName = custTo.getName();
				setMessage(null);
			} catch (Exception e) {
				setMessage(e.getMessage());
				e.printStackTrace();
			}
		}
	}

	/**
	 * Method getCustomerName
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * Method setCustomerName
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * Method getCardNo
	 * @return the cardNo
	 * 
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
	 * Method getRateOfInterest
	 * @return the rateOfInterest
	 */
	public Double getRateOfInterest() {
		return rateOfInterest;
	}

	/**
	 * Method setRateOfInterest
	 * @param rateOfInterest the rateOfInterest to set
	 */
	public void setRateOfInterest(Double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}

	/**
	 * Method getLastPaymentDate
	 * @return the lastPaymentDate
	 */
	public Date getLastPaymentDate() {
		return lastPaymentDate;
	}

	/**
	 * Method setLastPaymentDate
	 * @param lastPaymentDate the lastPaymentDate to set
	 */
	public void setLastPaymentDate(Date lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
	}

	/**
	 * Method getCardStatus
	 * @return the cardStatus
	 */
	public String getCardStatus() {
		return cardStatus;
	}

	/**
	 * Method setCardStatus
	 * @param cardStatus the cardStatus to set
	 */
	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
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
