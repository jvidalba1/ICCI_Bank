package com.infy.icci.managedBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.infy.icci.to.ApprovedCardTO;
import com.infy.icci.to.CardApplicationTO;
import com.infy.icci.wrapper.InfyCreditCardWrapper;

public class CardStatusMB 
{
	private Long cardNo;
	private Date fromDate;
	private Date toDate;
	

	//Supresor Advertencia falta de <Type>
	private List <Long> cardNoList;
	private Double rejectedPercent;
	private Double approvedPercent;
	private List<CardApplicationTO> cardRejectedList;
	private List<ApprovedCardTO> cardApprovedList;
	private String message;
	
	// It is used to the the cardNo from UIComponent
	private UIData cardData;
	private boolean flag;
	// To show the when the GetDetails Buttom is press
	private boolean grup2 = false;
	// To show the links of the dataTable or cardNo
	private boolean grup3=false;
	private HttpSession session =
		(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	
	public CardStatusMB() {
		if(session.getAttribute("userName")== null)
		{
			setMessage("You Are Not Allow to enter here, please Login");
		}
	}
	
	/**
	 * 
	 * Description: This method brings and calculates the percentage of both
	 * 				the accepted and rejected/pending cards.
	 * Modified by: YANNY ANDRES CASTRILLON 
	 * @return
	 */
	public String statusDetails()
	{
		this.grup2=true;
		// Instantiates the wrapper class
		InfyCreditCardWrapper wrapper = new InfyCreditCardWrapper();
		// sends from the binding of the Jsp and the bean Attributes the fromDate and ToDate
		Map<String, Integer> map = new HashMap<String, Integer>(); 
		try{

			map = wrapper.statusDetails(this.getFromDate(), this.getToDate());
			
			// Initialize two Iterators to get the keys and values of the map
			Iterator<String>strinIt = map.keySet().iterator();
			Iterator<Integer>intIt = map.values().iterator();
			
			List <String> listStr = new ArrayList<String>();
			List <Integer> listInt= new ArrayList<Integer>();
			
			//Iterates throw the map so it can get the string (key) and its proper value (Integer) into different lists
			while(strinIt.hasNext() && intIt.hasNext()){
				
				String stringKey = (String)strinIt.next();
				Integer integerValue = (Integer)intIt.next();
				// add to the listStr all the keyString of the map with an iterator
				listStr.add(stringKey);
				// add to the listInt all the valueInteger of the map with an iterator
				listInt.add (integerValue);
			}
			// listInt in the first position will have always the number of approve cards
			this.approvedPercent = Double.parseDouble(listInt.get(0).toString());
			
			// listInt int the second position will have always the number of rejected cards
			this.rejectedPercent = Double.parseDouble(listInt.get(1).toString());
			
			return "success";
		}
		catch (Exception e) {
			this.message=e.getMessage();
			return "fail";
		}
	}
	
	/**
	 * 
	 * Description: This method brings the all the accepted cards from the DB
	 * 				which are inside the range of the dates.
	 * Modified by: YANNY ANDRES CASTRILLON 
	 * @return
	 */
	public String getCardNumbers()
	{
		this.grup3=true;
		try{
			// Initializes the class attribute list
			this.cardNoList= new ArrayList<Long>();
			InfyCreditCardWrapper wrapper = new InfyCreditCardWrapper();
			List<Long>listCardAccepted = wrapper.getCardNumbers(fromDate, toDate);
			
			/* if it brings cards from the service method (DB) then, it will assign it to 
			 * data member (cardNoList) which is the one who is bind in the JSP
			 */
			if (listCardAccepted.size()!=0){
				
				// assigns the returned list to the class attribute list for the binding
				this.cardNoList=listCardAccepted;
				
				return "success";
			}
			else
				return "fail";
						
		}
		catch (Exception e) {
			this.message = e.getMessage();
			return "fail";
		}
		
	}
	
	/**
	 * 
	 * Description: This method goes and brings from DB the list of cards 
	 * 				that are Pending/Rejected inside of the range of the dates.
	 * Modified by: YANNY ANDRES CASTRILLON 
	 * @return
	 */
	public String rejectedReport()
	{
		try{
			InfyCreditCardWrapper wrapper = new InfyCreditCardWrapper();
			this.cardRejectedList=wrapper.cardRejectedDetails(fromDate, toDate);
			return "successReject";
		}
		catch (Exception e) {
			this.message=e.getMessage();
			return "failReject";
		}
	}
	

	
	/**
	 * 
	 * Description: Creates a HasMap where it will get the details of the cards that are approved and rejected
	 * 				as a percentage to show it in the JSP as a hiperLink. 
	 * Modified by: YANNY ANDRES CASTRILLON 
	 * @return
	 */
	public String acceptedReport()
	{
		try{
			// It is used to get the cardNo from the UIcomponent and then set it on the class attribute
			this.setCardNo(Long.parseLong(this.cardData.getRowData().toString()));
						
			// List that is going to show on the dataTable
			List<ApprovedCardTO> tempList = new ArrayList<ApprovedCardTO>();
			InfyCreditCardWrapper wrapper = new InfyCreditCardWrapper();
			
			this.cardApprovedList = wrapper.cardApprovedDetails(fromDate, toDate);
			
			
			// This 'for' is used to get ONLY the selected cardNo from the rest of the CardNo
			for (int i = 0; i < cardApprovedList.size(); i++) {
				if (cardApprovedList.get(i).getPaymentType().equals("-")){
					cardApprovedList.get(i).setPaymentType("N/A");
				}
				
				
				if (this.cardNo==cardApprovedList.get(i).getCardNo()){
					tempList.add(cardApprovedList.get(i));
					
					// To see if the Date of Bloc is null then don't show that field on the JSP Page.
					if (cardApprovedList.get(i).getDateOfBlock()== null){
						flag=false;

					}
					else
						flag=true;
					
					// clears the whole list
					cardApprovedList.clear();
					// assign the showList to the cardApprovedList which is the class attribute.
					cardApprovedList=tempList;
					break;
				}
									
			}
			return "successfullApprove";
		}
		catch (Exception e) {
			this.message=e.getMessage();
			return "failApprove";
		}
	}

	
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}
	
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	public Long getCardNo() {
		return cardNo;
	}
	
	public void setCardNo(Long cardNo) {
		this.cardNo = cardNo;
	}
	
	@SuppressWarnings("unchecked")
	public List getCardNoList() {
		return cardNoList;
	}
	
	@SuppressWarnings("unchecked")
	public void setCardNoList(List cardNoList) {
		this.cardNoList = cardNoList;
	}
	
	public Double getRejectedPercent() {
		return rejectedPercent;
	}
	
	public void setRejectedPercent(Double rejectedPercent) {
		this.rejectedPercent = rejectedPercent;
	}
	
	public Double getApprovedPercent() {
		return approvedPercent;
	}
	
	public void setApprovedPercent(Double approvedPercent) {
		this.approvedPercent = approvedPercent;
	}
	
	public List<CardApplicationTO> getCardRejectedList() {
		return cardRejectedList;
	}

	public void setCardRejectedList(List<CardApplicationTO> cardRejectedList) {
		this.cardRejectedList = cardRejectedList;
	}

	public List<ApprovedCardTO> getCardApprovedList() {
		return cardApprovedList;
	}
	
	public void setCardApprovedList(List<ApprovedCardTO> cardApprovedList) {
		this.cardApprovedList = cardApprovedList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public UIData getCardData() {
		return cardData;
	}

	public void setCardData(UIData cardData) {
		this.cardData = cardData;
	}


	
	public boolean isFlag() {
		return flag;
	}
	


	public void setFlag(boolean flag) {
		this.flag = flag;
	}


	public boolean isGrup2() {
		return grup2;
	}


	public void setGrup2(boolean grup2) {
		this.grup2 = grup2;
	}


	public boolean isGrup3() {
		return grup3;
	}


	public void setGrup3(boolean grup3) {
		this.grup3 = grup3;
	}


	
}