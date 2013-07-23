package com.infy.icci.managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;

import com.infy.icci.to.BlockedCardTO;
import com.infy.icci.to.CardTO;
import com.infy.icci.wrapper.InfyCreditCardWrapper;

public class BlockStatisticsMB 
{
	private String[] reasons= new String[3];
	private String message;
	private List<BlockedCardTO> blockedCards;
	private Long cardNo;
	private CardTO cardTo;
	private HtmlDataTable cards;
	private HttpSession session =
		(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	private boolean render;
	

	/**
	 * Constructor
	 */
	public BlockStatisticsMB() {
		setMessage(null);
		setRender(true);
		if(session.getAttribute("userName")==null)
		{
			setMessage("You Are Not Allow to enter here, please Login");
			setRender(false);
		}
	}
	
	/**
	 * 
	 * Description: This value change listener is invoked
	 * when a reason for blockage of card is selected by the user
	 * Modified by: Sara Giraldo
	 * @param event
	 */
	public void getBlockedDetails(ValueChangeEvent event)
	{
		this.setMessage(null);
		//set into the reasons array the choosen reason through the event 
		reasons =(String[])event.getNewValue();
		
		for (int i = 0; i < reasons.length; i++) {
			System.out.println("dentro del array hay");
			System.out.println(reasons[i]);
		}
		
		System.out.println("size es: " + reasons.length);
		//if it's not chosen reason
		if(reasons.length == 0){
			this.setBlockedCards(null);			
			return;
		}
		//initialize the blockcards list
		this.setBlockedCards(new ArrayList<BlockedCardTO>());
		InfyCreditCardWrapper wrp= new InfyCreditCardWrapper();
		try {
			/*Invoke the method getBlockedDetails in the wraper and save the
			return in the blockcard list*/ 
			this.setBlockedCards(wrp.getBlockedDetails(this.getReasons()));
			//if it's not blocked cards
			if(blockedCards.size()==0){
				this.setMessage("There are not blocked card by the selected reason ");
			}
			this.setCardTo(null);
			System.out.println("Lista de las bloqueadas "+blockedCards.size());
		} catch (Exception e) {
				
			this.setMessage(e.getMessage());
				
			
		}
		
		
	}
	
	/**
	 * 
	 * Description: Retrieve card details of selected card number
	 * Modified by: Sara Giraldo
	 * @return
	 */
	public String getCardDetails()
	{
		//get the index in the blockcards list of the cardno clicked
		int i = cards.getRowIndex();
		CardTO card = new CardTO();
		card.setCardNo(blockedCards.get(i).getCard());
		InfyCreditCardWrapper wr = new InfyCreditCardWrapper();
		try {
			//Invoke the getCardDetails method , if is fine return success
			this.cardTo= wr.getCardDetails(card);
			return"success";
			
		} catch (Exception e) {
			this.setMessage(e.getMessage());
			e.printStackTrace();
			return "fail";
		}
		
		
	}

	/**
	 * Method getReasons
	 * @return the reasons
	 */
	public String[] getReasons() {
		return reasons;
	}

	/**
	 * Method setReasons
	 * @param reasons the reasons to set
	 */
	public void setReasons(String[] reasons) {
		this.reasons = reasons;
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
	 * Method getBlockedCards
	 * @return the blockedCards
	 */
	public List<BlockedCardTO> getBlockedCards() {
		return blockedCards;
	}

	/**
	 * Method setBlockedCards
	 * @param blockedCards the blockedCards to set
	 */
	public void setBlockedCards(List<BlockedCardTO> blockedCards) {
		this.blockedCards = blockedCards;
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
	 * Method getCards
	 * @return cards
	 */
	public HtmlDataTable getCards() {
		return cards;
	}

	/**
	 * serCards
	 * @param cards
	 */
	public void setCards(HtmlDataTable cards) {
		this.cards = cards;
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
