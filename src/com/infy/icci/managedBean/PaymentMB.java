package com.infy.icci.managedBean;

import java.util.Date;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.infy.icci.to.CardTO;
import com.infy.icci.to.CustomerTO;
import com.infy.icci.to.PaymentTO;
import com.infy.icci.to.SchemeTO;
import com.infy.icci.wrapper.InfyCreditCardWrapper;

public class PaymentMB 
{
	private String[] cardNo;
	private Character paymentType;
	private Integer customerId;
	private String customerName;
	private Double balanceAmount;
	private Date lastPaymentDate;
	private String cardStatus;
	private Boolean disable;
	private String message;
	private Double amountPaid;
	private Double balance;
	private Boolean disable2; /* Variable to rendered property */
	private HttpSession session =
		(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	
	public PaymentMB()
	{	
		if (session.getAttribute("userName") == null) {
			this.setMessage("You Are Not Allow to enter here, please Login");
		} else {
			this.cardNo= new String[4];
			this.setMessage(null);
		}
	}
	
	/**
	 * 
	 * Description: Used to retrieve the payment details of the entered card number
	 * Modified by: FRANCO
	 * @return success or failture
	 */
	public String getPaymentInformation()
	{
		try {
			InfyCreditCardWrapper creditCardWrapper =
				new InfyCreditCardWrapper();
			CardTO cardTO = new CardTO();
			String aux = ""; //to store the cardno
			for (int i = 0; i < this.getCardNo().length; i++) {
				aux = aux + this.cardNo[i];
			}
			//set and parse the the cardNo attribute
			cardTO.setCardNo(Long.parseLong(aux));
			//To bring the Details of the last payment
			PaymentTO paymentTO = creditCardWrapper.getPaymentDetails(cardTO);
			//to bring the details of the card
			cardTO = creditCardWrapper.getCardDetails(cardTO);
			//to bring the customer Id and Customer Name
			CustomerTO customerTO = new CustomerTO();
			customerTO.setCustomerId(cardTO.getCustomerId());
			customerTO = creditCardWrapper.getCustomerDetails(customerTO);
					/*to bring the scheme details
					SchemeTO schemeTO = new SchemeTO();
					schemeTO.setSchemeId(cardTO.getSchemeId());
					creditCardWrapper.getSchemeDetails(schemeTO);*/
			//set the information that its going to display
			this.setCustomerId(customerTO.getCustomerId());
			this.setCustomerName(customerTO.getName());
			this.setBalanceAmount(cardTO.getBalanceAmount());
			this.setLastPaymentDate(paymentTO.getDateOfPayment());
			this.setCardStatus(paymentTO.getCardStatus());
			this.setDisable(true);
			return "success";
		} catch (Exception e) {
			this.setMessage(e.getMessage());
			return "failure";
		}
		
	}
	
	/**
	 * 
	 * Description: Used to calculate the balance amount after payment
	 * Modified by: 
	 * @return success or failure
	 */
	public String calculateBalance()
	{
		try{
			InfyCreditCardWrapper cardWrapper = new InfyCreditCardWrapper();
			CardTO cardTO = new CardTO();
			String aux = ""; //to store the cardno
			for (int i = 0; i < this.getCardNo().length; i++) {
				aux = aux + this.cardNo[i];
			}
			//set and parse the the cardNo attribute
			cardTO.setCardNo(Long.parseLong(aux));
			cardTO = cardWrapper.getCardDetails(cardTO);
			
			//to bring the scheme details
			SchemeTO schemeTO = new SchemeTO();
			schemeTO.setSchemeId(cardTO.getSchemeId());
			schemeTO = cardWrapper.getSchemeDetails(schemeTO);
			
			//validation if the payment its going to be minimum or maximum
			if (cardTO.getBalanceAmount() < schemeTO.getMinimumAmount()) {
				this.setPaymentType('F');
			}
			if (this.getPaymentType() == 'F') {
				this.setAmountPaid(
						cardTO.getBalanceAmount() + 
						((cardTO.getBalanceAmount()*
								schemeTO.getInterestRate())/100));
				this.setBalance(0.0);
			} else {
				this.setAmountPaid(schemeTO.getMinimumAmount());
				this.setBalance(cardTO.getBalanceAmount() - 
						this.getAmountPaid() + 
						(cardTO.getBalanceAmount()*
								(schemeTO.getInterestRate()/100)));
			}
			if (this.getBalance() <= 0) {
				this.setBalance(0.0);
			}
			this.setDisable2(true);
			return "success";
		} catch (Exception e) {
			this.setMessage(e.getMessage());
			e.printStackTrace();
			return "failure";
		}
	}
	
	/**
	 * 
	 * Description: Used to make payment
	 * Modified by: Franco
	 * @return Succesful or failure
	 * @throws Exception 
	 */
	public String makePayment() throws Exception
	{
		try {
			PaymentTO paymentTO = new PaymentTO();
			CardTO cardTO = new CardTO();
			String aux = ""; //to store the cardno
			for (int i = 0; i < this.getCardNo().length; i++) {
				aux = aux + this.cardNo[i];
			}
			//set and parse the the cardNo attribute
			cardTO.setCardNo(Long.parseLong(aux));
			paymentTO.setCardNo(cardTO.getCardNo());
			paymentTO.setPaymentType(this.getPaymentType());
			paymentTO.setAmountPaid(this.getAmountPaid());
			paymentTO.setDateOfPayment(new Date());
			InfyCreditCardWrapper cardWrapper = new InfyCreditCardWrapper();
			//send the payment object to be "inserted" if its possible 
			paymentTO = cardWrapper.makePayment(paymentTO);
			this.setMessage("Payment Succesful...Payment Id: " +
					paymentTO.getPaymentId()); //message that appears in PaymentSuccess.jsp
			return "Succesfull";
		} catch (Exception exception) {
			exception.printStackTrace();
			this.setMessage(exception.getMessage());
			return "failure";
		}
		
	}

	/**
	 * Method getDisable2
	 * @return disable2
	 */
	public Boolean getDisable2() {
		return disable2;
	}

	/**
	 * Method setDisable2
	 * @param disable2
	 */
	public void setDisable2(Boolean disable2) {
		this.disable2 = disable2;
	}
	
	/**
	 * Method getCardNo
	 * @return the cardNo
	 */
	public String[] getCardNo() {
		return cardNo;
	}

	/**
	 * Method setCardNo
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(String[] cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * Method getPaymentType
	 * @return the paymentType
	 */
	public Character getPaymentType() {
		return paymentType;
	}

	/**
	 * Method setPaymentType
	 * @param paymentType the paymentType to set
	 */
	public void setPaymentType(Character paymentType) {
		this.paymentType = paymentType;
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
	 * Method getDisable
	 * @return the disable
	 */
	public Boolean getDisable() {
		return disable;
	}

	/**
	 * Method setDisable
	 * @param disable the disable to set
	 */
	public void setDisable(Boolean disable) {
		this.disable = disable;
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
	 * Method getAmountPaid
	 * @return the amountPaid
	 */
	public Double getAmountPaid() {
		return amountPaid;
	}

	/**
	 * Method setAmountPaid
	 * @param amountPaid the amountPaid to set
	 */
	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}

	/**
	 * Method getBalance
	 * @return the balance
	 */
	public Double getBalance() {
		return balance;
	}

	/**
	 * Method setBalance
	 * @param balance the balance to set
	 */
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
}