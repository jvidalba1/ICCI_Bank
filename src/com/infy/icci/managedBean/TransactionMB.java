package com.infy.icci.managedBean;

import java.util.Date;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.infy.icci.to.TransactionTO;
import com.infy.icci.wrapper.InfyCreditCardWrapper;

public class TransactionMB 
{
	private Integer transactionId;
	private Date transactionDate;
	private String description;
	private Double amount;
	private String errorMessage;
	private Double balanceAmount;
	private HttpSession session =
		(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	
	public TransactionMB() {
		if(session.getAttribute("userName") == null)
		{
			setErrorMessage("You Are Not Allow to enter here, please Login");
		}
	}
	
	/**
	 * 
	 * Description: Insert A new Transaction
	 * Modified by: Edgardo
	 * @return
	 */
	public String saveTransaction()
	{
		try{
			/*create a new instance of transaction to and set the values*/
			TransactionTO transaction = new TransactionTO();
			transaction.setAmount(this.getAmount());
			transaction.setDescription(this.getDescription());
			transaction.setDateOfTransaction(new Date());
			/*Get the card Number from the session atribute*/
			HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			transaction.setCardNo(Long.parseLong(session.getAttribute("cardNo").toString()));
			transaction = new InfyCreditCardWrapper().saveTransaction(transaction);
			this.setBalanceAmount(transaction.getBalanceAmount());
			this.setTransactionId(transaction.getTransactionId());
			this.setTransactionDate(transaction.getDateOfTransaction());
			return "success";
		}catch (Exception e) {
			this.setErrorMessage(e.getMessage());
			return "failure";
		}
	}

	/**
	 * Method getTransactionId
	 * @return the transactionId
	 */
	public Integer getTransactionId() {
		return transactionId;
	}

	/**
	 * Method setTransactionId
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * Method getTransactionDate
	 * @return the transactionDate
	 */
	public Date getTransactionDate() {
		return transactionDate;
	}

	/**
	 * Method setTransactionDate
	 * @param transactionDate the transactionDate to set
	 */
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	/**
	 * Method getDescription
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Method setDescription
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Method getAmount
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * Method setAmount
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * Method getErrorMessage
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Method setErrorMessage
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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
}
