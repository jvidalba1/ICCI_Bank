package com.infy.icci.managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import com.infy.icci.to.TransactionTO;
import com.infy.icci.wrapper.InfyCreditCardWrapper;

public class CardUsageMB 
{
	private List<TransactionTO> transactionList = new ArrayList<TransactionTO>();
	private String monthSelectedValue;
	private List<SelectItem> displayList = new ArrayList<SelectItem>();
	private String option;
	private String message;
	private boolean permission;

	private HttpSession session =
		(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);

	/**
	 * Constructor 
	 */
	public CardUsageMB(){
		if(session.getAttribute("userName") == null){
			this.setMessage("You Are Not Allow to enter here, please Login");
			this.setPermission(false);
		}else{
			this.setPermission(true);
		}
	}
	
	/**
	 * 
	 * Description: This receives the list with the months to populate
	 *  into the select one menu 
	 * Modified by: Mateo Vidal
	 * @param event
	 */
	public void populateMonths(ValueChangeEvent event)
	{
		/*
		 * List displayList 1 and 2, the first goes to current year and the another one
		 * goes to the previous year option
		 */
		List<SelectItem> displayList1 = new ArrayList<SelectItem>();
		List<SelectItem> displayList2 = new ArrayList<SelectItem>();
		
		displayList = new ArrayList<SelectItem>();
		this.setOption(event.getNewValue().toString());
		
		/*
		 * Create the obj wrapper to go to getMonthList method
		 */
		InfyCreditCardWrapper wrapper = new InfyCreditCardWrapper();
		List<String> list = new ArrayList<String>();
		try {
			list = wrapper.getMonthList();
		} catch (Exception exception) {
			this.setMessage(exception.getMessage());
		}
		
		
		/*
		 * Goes through the list received until of the last position
		 * and the another if statement asks if the next position is
		 * "December", if its "December" add to the displayList1 
		 * the last position because the rest of the list received
		 * is for the second list (displayList2).		 
		 */
		
		int cont = 0;
		for (int i = 0; i < list.size(); i++) 
		{
			if(i==list.size()-1)
			{
				displayList1.add(new SelectItem(list.get(i),
						list.get(i).toString()));
				break;
			}else{
				if(list.get(i+1).equals("December"))
				{
					displayList1.add(new SelectItem(list.get(i),
							list.get(i).toString()));
					break;
				}else
				{
					displayList1.add(new SelectItem(list.get(i),
							list.get(i).toString()));
				}
			}
			cont++;
		}
		/*
		 * Delete the elements of the list received and 
		 * added into the displayList1
		 * */
		for(int i = cont; i >= 0; i--)
		{
			System.out.println("5. "+i);
			list.remove(i);
		}
		/*
		 * Add the rest of the list into the displayList2
		 * */
		for (int i = 0; i < list.size(); i++) 
		{
			displayList2.add(new SelectItem(list.get(i),
					list.get(i).toString()));
		}
		/*
		 * If the option is current, I mean, the user selected
		 * the first radio button (this year), so equals the 
		 * displayList1 to displayList, it's the list that will
		 * show to the user in the select one menu. 
		 * Otherwise equals the displayList2 to displayList, in
		 * this case the user selected the second option
		 * (previous year)
		 * */
		if(option.equals("current"))
		{
			System.out.println("dentro de current");
			displayList = displayList1;
		}else if(option.equals("previous"))
		{
			System.out.println("dentro de previous");
			displayList = displayList2;
		}
	}
	
	/**
	 * 
	 * Description: This method call the cardUsage method of the wrapper class
	 * Modified by: Mateo Vidal
	 * @return success or fail
	 */
	public String populateTable()
	{
		try{			
			transactionList = new ArrayList<TransactionTO>();			
			// Create the obj wrapper to go to cardUsage method			 
			InfyCreditCardWrapper wrapper = new InfyCreditCardWrapper();
			transactionList = wrapper.cardUsage(this.getMonthSelectedValue(), this.getOption());			
			return "success";			
		}catch (Exception exception) {
			this.setMessage(exception.getMessage());
			return "fail";
		}
		
	}

	/**
	 * Method getTransactionList
	 * @return the transactionList
	 */
	public List<TransactionTO> getTransactionList() {
		return transactionList;
	}

	/**
	 * Method setTransactionList
	 * @param transactionList the transactionList to set
	 */
	public void setTransactionList(List<TransactionTO> transactionList) {
		this.transactionList = transactionList;
	}

	/**
	 * Method getMonthSelectedValue
	 * @return the monthSelectedValue
	 */
	public String getMonthSelectedValue() {
		return monthSelectedValue;
	}

	/**
	 * Method setMonthSelectedValue
	 * @param monthSelectedValue the monthSelectedValue to set
	 */
	public void setMonthSelectedValue(String monthSelectedValue) {
		this.monthSelectedValue = monthSelectedValue;
	}

	/**
	 * Method getDisplayList
	 * @return the displayList
	 */
	public List<SelectItem> getDisplayList() {
		return displayList;
	}

	/**
	 * Method setDisplayList
	 * @param displayList the displayList to set
	 */
	public void setDisplayList(List<SelectItem> displayList) {
		this.displayList = displayList;
	}

	/**
	 * Method getOption
	 * @return the option
	 */
	public String getOption() {
		return option;
	}

	/**
	 * Method setOption
	 * @param option the option to set
	 */
	public void setOption(String option) {
		this.option = option;
	}

	/**
	 * Method setMessage
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Method getMessage
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Method isPermission
	 * @return permission
	 */
	public boolean isPermission() {
		return permission;
	}

	/**
	 * Method setPermission
	 * @param permission
	 */
	public void setPermission(boolean permission) {
		this.permission = permission;
	}
	
}
