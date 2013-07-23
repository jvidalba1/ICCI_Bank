package com.infy.icci.managedBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.infy.icci.to.CardTO;
import com.infy.icci.to.CustomerTO;
import com.infy.icci.to.TransactionTO;
import com.infy.icci.wrapper.InfyCreditCardWrapper;

public class TransactionStatisticsMB 
{
	private Integer custId;
	private Long cardNo;
	private Date fromDate;
	private Date toDate;
	private CustomerTO customerDetails;
	private List<CustomerTO> customers;
	private List<Integer> count;
	private String message;
	private List<TransactionTO> transaction;
	private String customerName;
	private HtmlDataTable names;
	private HtmlDataTable trans;
	private HttpSession session =
		(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	
	public TransactionStatisticsMB() {
		if(session.getAttribute("userName") == null)
		{
			setMessage("You Are Not Allow to enter here, please Login");
		}
	}
	
	/**
	 * 
	 * Description: 
	 * Modified by: 
	 * @return
	 */
	public String getCustomer()
	{
		try{
			this.setMessage(null);
			this.setCardNo(null);
			this.setTransaction(null);
			/*get the index of the link pressed*/
			Integer index = names.getRowIndex();
			this.setCustomerDetails(this.getCustomers().get(index));
			return "success";
		}catch (Exception e){
			this.setMessage(e.getMessage());
			return "failure";
		}
	}
	
	/**
	 * 
	 * Description: Set the count and Customers list
	 * Modified by: Edgardo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getStatistics()
	{
		try{
			this.setMessage(null);
			this.setCount(null);
			this.setCustomers(null);
			if(fromDate.after(new Date()) || toDate.after(new Date())){
				this.setMessage("From Date and To Date Can't be Greater than Today's Date.");
				return "failure";
			}
			if(fromDate.after(toDate)){
				this.setMessage("From Date Should Be Before The To Date.");
				return "failure";
			}
			ArrayList<CustomerTO> cust = new ArrayList<CustomerTO>();
			ArrayList<Integer> No = new ArrayList<Integer>();
			Map<CustomerTO,Long> map = new InfyCreditCardWrapper().transactionDetails(this.getFromDate(),this.getToDate());
			Iterator<Long> iter = map.values().iterator();
			Iterator<CustomerTO> keys = map.keySet().iterator();
			/*set the names and number of transactions into customers and count lists*/
			while(iter.hasNext()&& keys.hasNext())
			{ 
				Integer val = Integer.parseInt(iter.next().toString());
				cust.add(keys.next());
				No.add(val);
			}
			this.setCount(No);
			this.setCustomers(cust);
			return "success";
		}catch (Exception e){
			this.setMessage(e.getMessage());
			return "failure";
		}
	}
	
	/**
	 * 
	 * Description: 
	 * Modified by: 
	 * @return success or fail
	 */
	public String getTransactions()
	{
		try{
			this.setMessage(null);
			this.setCustomerDetails(null);
			/*get the index of the link pressed*/
			Integer index = trans.getRowIndex();
			CardTO card = new InfyCreditCardWrapper().getCardNo(this.getCustomers().get(index).getCustomerId());
			this.setCardNo(card.getCardNo());
			this.setTransaction(new InfyCreditCardWrapper().getTransactions(card.getCardNo(), this.getFromDate(), this.getToDate()));
			return "success";
		}catch (Exception e){
			this.setMessage(e.getMessage());
			return "failure";
		}
	}

	/**
	 * Method getCustId
	 * @return the custId
	 */
	public Integer getCustId() {
		return custId;
	}

	/**
	 * Method setCustId
	 * @param custId the custId to set
	 */
	public void setCustId(Integer custId) {
		this.custId = custId;
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
	 * Method getFromDate
	 * @return the fromDate
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * Method setFromDate
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * Method getToDate
	 * @return the toDate
	 */
	public Date getToDate() {
		return toDate;
	}

	/**
	 * Method setToDate
	 * @param toDate the toDate to set
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	/**
	 * Method getCustomerDetails
	 * @return the customerDetails
	 */
	public CustomerTO getCustomerDetails() {
		return customerDetails;
	}

	/**
	 * Method setCustomerDetails
	 * @param customerDetails the customerDetails to set
	 */
	public void setCustomerDetails(CustomerTO customerDetails) {
		this.customerDetails = customerDetails;
	}

	/**
	 * Method getCustomers
	 * @return the customers
	 */
	public List<CustomerTO> getCustomers() {
		return customers;
	}

	/**
	 * Method setCustomers
	 * @param customers the customers to set
	 */
	public void setCustomers(List<CustomerTO> customers) {
		this.customers = customers;
	}

	/**
	 * Method getCount
	 * @return the count
	 */
	public List<Integer> getCount() {
		return count;
	}

	/**
	 * Method setCount
	 * @param count the count to set
	 */
	public void setCount(List<Integer> count) {
		this.count = count;
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
	 * Method getTransaction
	 * @return the transaction
	 */
	public List<TransactionTO> getTransaction() {
		return transaction;
	}

	/**
	 * Method setTransaction
	 * @param transaction the transaction to set
	 */
	public void setTransaction(List<TransactionTO> transaction) {
		this.transaction = transaction;
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
	 * Method setTrans
	 * @param trans the trans to set
	 */
	public void setTrans(HtmlDataTable trans) {
		this.trans = trans;
	}

	/**
	 * Method getTrans
	 * @return the trans
	 */
	public HtmlDataTable getTrans() {
		return trans;
	}
	
	/**
	 * Method getNames
	 * @return the names
	 */
	public HtmlDataTable getNames() {
		return names;
	}

	/**
	 * Method setNames
	 * @param names the names to set
	 */
	public void setNames(HtmlDataTable names) {
		this.names = names;
	}
}
