package com.infy.icci.managedBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import com.infy.icci.to.BlockedCardTO;
import com.infy.icci.wrapper.InfyCreditCardWrapper;


public class BlockCardMB 
{
	private String[] cardNo;
	private String cause;
	private boolean disable ;
	private List<SelectItem> causes;
	private String message;
	private Long cardPersisted;
	private int blockId;
	private HttpSession session =
		(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	
	/**
	 *
	 * Constructor
	 */
	public BlockCardMB()
	{
		disable = false;
		if(session.getAttribute("userName") == null)
		{
			setMessage("You Are Not Allow to enter here, please Login");
		}
		else
		{
			causes = new ArrayList<SelectItem>();
			//add to the causes list the causes of block
			causes.add(new SelectItem("Theft","Theft"));
			causes.add(new SelectItem("Payment not done","Payment not done"));
			causes.add(new SelectItem("Other reasons","Other reasons"));
			cardNo = new String [4];
		}
	}

	
	/**
	 * 
	 * Description: This method checks which cause is choose, and set the cause 
	 * 				variable with this, if the cause of block equals to
	 * 				other reasons textArea is disable.
	 * Modified by: Luis Miguel Marulanda Jaramillo
	 * @param event
	 */
	public void checkCause(ValueChangeEvent event)
	{
		//set the value of cause with the value choose by the user
		setCause(event.getNewValue().toString());
		
		//check when the value choose is other reason and enable the text area
		if(event.getNewValue().toString().equals("Other reasons")){
			this.disable = false;
		}else{
			this.disable = true;
		}
	}

	
	/**
	 * 
	 * Description: This method block the card
	 * Modified by: Luis Miguel Marulanda Jaramillo
	 * @return success or fail
	 * @throws Exception 
	 */
	public String blockCard() throws Exception
	{
		try{
			String card = "";	
			//this method concatenate the 4 parts of the card in one
			for (int i = 0; i < cardNo.length; i++) {
				card = card + cardNo[i];
			}
			
			//create and object of type BlockedCardTO
			BlockedCardTO to = new BlockedCardTO();
			//set the different values to the to object 
			to.setCardNo(Long.parseLong(card));
			to.setDateOfBlock(new Date());
			to.setDescription(this.cause);
			InfyCreditCardWrapper wrapper = new InfyCreditCardWrapper();
			//call the method checkCardBlocked of the wrapper object and pass to 
			//object
			to = wrapper.checkCardBlocked(to);
			//after received returned value, set the cardPersisted and blockId 
			//getting the values from  the object
			setCardPersisted(to.getCard());
			setBlockId(to.getBlockId());
			
			return "success";
		}catch (Exception e) {
			this.setMessage(e.getMessage());
			return "fail";
		}
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
	 * Method getCause
	 * @return the cause
	 */
	public String getCause() {
		return cause;
	}

	/**
	 * Method setCause
	 * @param cause the cause to set
	 */
	public void setCause(String cause) {
		this.cause = cause;
	}

	/**
	 * Method isDisable
	 * @return the disable
	 */
	public boolean isDisable() {
		return disable;
	}

	/**
	 * Method setDisable
	 * @param disable the disable to set
	 */
	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	/**
	 * Method getCauses
	 * @return the causes
	 */
	public List<SelectItem> getCauses() {
		return causes;
	}

	/**
	 * Method setCauses
	 * @param causes the causes to set
	 */
	public void setCauses(List<SelectItem> causes) {
		this.causes = causes;
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
	 * @param cardPersisted the cardPersisted to set
	 */
	public void setCardPersisted(Long cardPersisted) {
		this.cardPersisted = cardPersisted;
	}

	/**
	 * @return the cardPersisted
	 */
	public Long getCardPersisted() {
		return cardPersisted;
	}

	/**
	 * @param blockId the blockId to set
	 */
	public void setBlockId(int blockId) {
		this.blockId = blockId;
	}

	/**
	 * @return the blockId
	 */
	public int getBlockId() {
		return blockId;
	}
}
