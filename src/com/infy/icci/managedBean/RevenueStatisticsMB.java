package com.infy.icci.managedBean;

import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.infy.icci.wrapper.InfyCreditCardWrapper;

public class RevenueStatisticsMB 
{
	private Date fromDate;
	private Date toDate;
	private Double expediture;
	private Double revenue;
	private Double profit;
	private InfyCreditCardWrapper iccw;
	private String msg;
	private final int POS_REVENUE = 0;
	private final int POS_EXPENDITURE = 1;
	private final int POS_PROFIT = 2;
	private List<Double> values = null;
	private HttpSession session =
		(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	private String message;
	
	
	/**
	 * 
	 * Description: 
	 * Modified by: Simon Escobar Benitez
	 * Constructor
	 */
	public RevenueStatisticsMB() {
		if(session.getAttribute("userName") == null)
		{
			setMessage("You Are Not Allow to enter here, please Login");
		}
		else
		{
			iccw = new InfyCreditCardWrapper();
		}
	}
	
	/**
	 * 
	 * Description: Gets the finance details of the company. Create a Object
	 * of InfyCreditCardWrapper and invoke the getRevenueDetails() by passing
	 * the fromDate and toDate as parameters.
	 * the method invocation returns a List of financial details like profit,
	 * expenditure, revenue, etc. Display the details using a PanelGrid
	 * Modified by: Simon Escobar Benitez
	 * @return
	 */
	public String getDetails()
	{
		try {
			values = (List<Double>) iccw.getRevenueDetails(fromDate, toDate);
			revenue = values.get(POS_REVENUE);
			expediture = values.get(POS_EXPENDITURE);
			profit = values.get(POS_PROFIT);
			return("success");
		} catch (Exception e) {
			setMsg(e.getMessage());
			e.printStackTrace();
			return("failure");
		}
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
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
	 * Method getExpediture
	 * @return the expediture
	 */
	public Double getExpediture() {
		return expediture;
	}

	/**
	 * Method setExpediture
	 * @param expediture the expediture to set
	 */
	public void setExpediture(Double expediture) {
		this.expediture = expediture;
	}

	/**
	 * Method getRevenue
	 * @return the revenue
	 */
	public Double getRevenue() {
		return revenue;
	}

	/**
	 * Method setRevenue
	 * @param revenue the revenue to set
	 */
	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}

	/**
	 * Method getProfit
	 * @return the profit
	 */
	public Double getProfit() {
		return profit;
	}

	/**
	 * Method setProfit
	 * @param profit the profit to set
	 */
	public void setProfit(Double profit) {
		this.profit = profit;
	}
	
	/**
	 * Method setMsg
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	/**
	 * Method getMsg
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	
	/**
	 * Method getValues
	 * @return the msg
	 */
	public List<Double> getValues() {
		return values;
	}
	
	/**
	 * Method setValues
	 * @param values the values to set
	 */
	public void setValues(List<Double> values) {
		this.values = values;
	}
}
