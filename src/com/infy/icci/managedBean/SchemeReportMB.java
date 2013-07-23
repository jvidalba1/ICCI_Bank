package com.infy.icci.managedBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import com.infy.icci.to.CardTO;
import com.infy.icci.to.CustomerTO;
import com.infy.icci.to.SchemeTO;
import com.infy.icci.wrapper.InfyCreditCardWrapper;

public class SchemeReportMB 
{
	private List<SelectItem> customerIdList;
	private String message;
	private List<SelectItem> cardNoList;
	private CustomerTO customerTo;
	private CardTO cardTo;
	private HttpSession session =
		(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	private boolean render;
	
	public SchemeReportMB() {
		setRender(true);
		if(session.getAttribute("userName") == null)
		{
			setMessage("You Are Not Allow to enter here, please Login");
			setRender(false);
		}
	}
	/**
	 * 
	 * Description: Gets the customerIds and CardNos for a particular scheme.
	 *  The event’s value would be schemeId.
	 * Modified by: Sara Giraldo
	 * @param event
	 */
	public void getCustomersForScheme(ValueChangeEvent event)
	{
		System.out.println("entro al metodo");
		this.customerTo = null;
		this.cardTo = null;
		this.setCardNoList(new ArrayList<SelectItem>());
		this.setCustomerIdList(new ArrayList<SelectItem>());
		//Capture the event(schemeId)
		char schemeId = event.getNewValue().toString().charAt(0);
			
		try {
	
			InfyCreditCardWrapper wrap = new InfyCreditCardWrapper();
			SchemeTO schemeto= new SchemeTO();
			schemeto.setSchemeId(schemeId);
	
			/*Invoke the getCustomerForScheme method of the wrapper,
			 * which return a list of cardTo */
			List<CardTO>  aux = wrap.getCustomersForScheme(schemeto);

			System.out.println("tam " + aux.size());
	
			/* While the list of cardTo have element , the cardNoList 
			 * and customerIdList is getting fill*/
			Iterator<CardTO> card = aux.iterator();
			while(card.hasNext()){
	
				CardTO cardto = card.next();
	
				SelectItem cardno = new SelectItem(cardto.getCardNo());
				SelectItem custid = new SelectItem(cardto.getCustomerId());
	
				this.cardNoList.add(cardno);
				this.customerIdList.add(custid);
			}
			System.out.println("cardListsize: "+ this.getCardNoList().size());
			System.out.println("custIdLis: "+ this.getCustomerIdList().size());
			this.message= null;
		} catch (Exception e) {
			this.setMessage(e.getMessage());
		}
	
	}
	
	/**
	 * 
	 * Description: Gets the customer details and card details
	 *  for the customerId and cardNo passed respectively.
	 * Modified by: Sara Giraldo
	 * @param event
	 */
	public void getCustomerAndCardDetails(ValueChangeEvent event)
	{
		String aux = (String)event.getNewValue();
		int customerId = 0;
		long cardNo = 0;
		try{
			InfyCreditCardWrapper wrp = new InfyCreditCardWrapper();
			//if the event is the cardNo selected
			if(aux.length() == 16) {
				cardNo = Long.parseLong(aux);
				this.cardTo = new CardTO();
				this.cardTo.setCardNo(cardNo);
				/*invoke the method getCardDetails in the wrapper
				 *  passing the cardNo selected*/
				this.cardTo = wrp.getCardDetails(cardTo);
				this.customerTo = null;
			}else{
				//is the event is the customerId
				customerId=Integer.parseInt(aux);
				this.customerTo= new CustomerTO();
				this.customerTo.setCustomerId(customerId);
				/*invoke the method getCardDetails in the wrapper 
				 * passing the customerId selected*/
				this.customerTo=wrp.getCustomerDetails(customerTo);
				this.cardTo = null;
			}
		}catch(Exception e){
			this.setMessage(e.getMessage());
		}
	}

	/**
	 * Method getCustomerIdList
	 * @return the customerIdList
	 */
	public List<SelectItem> getCustomerIdList() {
		return customerIdList;
	}

	/**
	 * Method setCustomerIdList
	 * @param customerIdList the customerIdList to set
	 */
	public void setCustomerIdList(List<SelectItem> customerIdList) {
		this.customerIdList = customerIdList;
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
	 * Method getCardNoList
	 * @return the cardNoList
	 */
	public List<SelectItem> getCardNoList() {
		return cardNoList;
	}

	/**
	 * Method setCardNoList
	 * @param cardNoList the cardNoList to set
	 */
	public void setCardNoList(List<SelectItem> cardNoList) {
		this.cardNoList = cardNoList;
	}

	/**
	 * Method getCustomerTo
	 * @return the customerTo
	 */
	public CustomerTO getCustomerTo() {
		return customerTo;
	}

	/**
	 * Method setCustomerTo
	 * @param customerTo the customerTo to set
	 */
	public void setCustomerTo(CustomerTO customerTo) {
		this.customerTo = customerTo;
	}

	/**
	 * Method getCardTo
	 * @return the cardTo
	 */
	public CardTO getCardTo() {
		return cardTo;
	}

	/**
	 * Method setCardTo
	 * @param cardTo the cardTo to set
	 */
	public void setCardTo(CardTO cardTo) {
		this.cardTo = cardTo;
	}
	
	/**
	 * @return the render
	 */
	public boolean isRender() {
		return render;
	}
	/**
	 * @param render the render to set
	 */
	public void setRender(boolean render) {
		this.render = render;
	}
}
