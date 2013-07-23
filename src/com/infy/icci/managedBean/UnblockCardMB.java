package com.infy.icci.managedBean;



import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.infy.icci.to.BlockedCardTO;
import com.infy.icci.wrapper.InfyCreditCardWrapper;

public class UnblockCardMB 
{
	private Long cardNo;
	
	// card part1 number as string
	private Long cardPartNo1;
	private Long cardPartNo2;
	private Long cardPartNo3;
	private Long cardPartNo4;
	private String message;
	private HttpSession session =
		(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	
	public UnblockCardMB() {
		if(session.getAttribute("userName")== null)
		{
			setMessage("You Are Not Allow to enter here, please Login");
		}
	}
	
	/**
	 * 
	 * Description: This module is in charge of unBlocking the cardNo 
	 * 				given by the user. It also concatenates every part 
	 * 				to make it one.
	 * Modified by: YANNY ANDRES CASTRILLON
	 * @return
	 */
	public String unblockCard()
	{
		try{
				// This concatenates all 4 parts of the CardNo into one whole number
				cardNo = Long.parseLong((cardPartNo1+""+cardPartNo2+""+cardPartNo3+""+cardPartNo4).trim());
				// Sets the blockCard from the user
				BlockedCardTO blockCardTo = new BlockedCardTO();
				blockCardTo.setCardNo(this.getCardNo());
				
				// Sends the blockCardTo the Wrapper
				InfyCreditCardWrapper wrapper = new InfyCreditCardWrapper();
				blockCardTo = wrapper.unblockCard(blockCardTo);
				this.message = "This card with cardNo: "+blockCardTo.getCard()+" unblocked successfully ";
				
				return "success";
			}catch (Exception e) {
				this.message = e.getMessage();
				System.out.println(this.message);
				return "fail";
			}
	}
	
	public Long getCardPartNo1() {
		return cardPartNo1;
	}

	public void setCardPartNo1(Long cardPartNo1) {
		this.cardPartNo1 = cardPartNo1;
	}

	public Long getCardPartNo2() {
		return cardPartNo2;
	}

	public void setCardPartNo2(Long cardPartNo2) {
		this.cardPartNo2 = cardPartNo2;
	}

	public Long getCardPartNo3() {
		return cardPartNo3;
	}

	public void setCardPartNo3(Long cardPartNo3) {
		this.cardPartNo3 = cardPartNo3;
	}

	public Long getCardPartNo4() {
		return cardPartNo4;
	}

	public void setCardPartNo4(Long cardPartNo4) {
		this.cardPartNo4 = cardPartNo4;
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