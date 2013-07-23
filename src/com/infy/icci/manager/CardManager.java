package com.infy.icci.manager;

import java.util.List;

import com.infy.icci.service.CardService;
import com.infy.icci.to.BlockedCardTO;
import com.infy.icci.to.CardApplicationTO;
import com.infy.icci.to.CardTO;

public class CardManager 
{
	/**
	 * 
	 * Description: This method call getCardDetails from CardService
	 * Modified by: Simon Escobar Benitez
	 * @param cardTo
	 * @return
	 * @throws Exception 
	 */
	public CardTO getCardDetails(CardTO cardTo) throws Exception
	{
		return new CardService().getCardDetails(cardTo);
	}
	
	/**
	 * 
	 * Description: Call the applycard method of Card Service Class
	 * Modified by: Santiago Moreno Palacio
	 * @param cardApplicationTo
	 * @return
	 */
	public CardApplicationTO applyCard(CardApplicationTO cardApplicationTo)
											throws Exception
	{
		return new CardService().applyCard(cardApplicationTo);
	}

	
	/**
	 * 
	 * Description: Call the retrieveAppliedCardDetails method of
	 * Card Service Class
	 * Modified by: Santiago Moreno Palacio
	 * @return List<CardApplicationTO>
	 */
	public List<CardApplicationTO> retrieveAppliedCardDetails()
											throws Exception
	{
		return new CardService().retrieveAppliedCardDetails();
	}

	
	/**
	 * 
	 * Description: Call the approveCard method of Card Service Class
	 * Modified by: Santiago Moreno Palacio
	 * @param applicationId
	 */
	public void approveCard(int applicationId) throws Exception
	{
		new CardService().approveCard(applicationId);
	}

	
	/**
	 * 
	 * Description: Received a BlockedCardTO object and pass it as parameter of checkCardBlocked
	 * 				of CardService class, if card is not blocked, then the object is passed to 
	 * 				blockCard method of cardService.
	 * Modified by: Luis Miguel Marulanda Jaramillo
	 * @param cardNo
	 * @return
	 * @throws Exception 
	 */
	public BlockedCardTO checkCardBlocked(BlockedCardTO blockedCardTo) throws Exception
	{
		BlockedCardTO to = new BlockedCardTO();
		to = new CardService().checkCardBlocked(blockedCardTo);
	
		return new CardService().blockCard(to);

	}
	
	/**
	 * 
	 * Description: call the method getCardDetails of CardService class and
	 * 				pass cardTo object as parameter
	 * Modified by: Simon Escobar Benitez
	 * @param cardTo
	 * @return
	 * @throws Exception 
	 */

	public CardTO getCardDetailsForLogin(CardTO cardTo) throws Exception
	{
		return new CardService().getCardDetails(cardTo);
	}
	
	/**
	 * 
	 * Description: This method call unblockCard from CardService
	 * Modified by: Yanny
	 * @param blockCardTo
	 * @return
	 */
	public BlockedCardTO unblockCard(BlockedCardTO blockCardTo)throws Exception
	{
		
		CardService service = new CardService();
		blockCardTo = service.checkCardBlocked(blockCardTo.getCard());
		//checks if there are any block card
		if (blockCardTo!=null){
			// if (blockedCardTo != null) found a blocked Card
			blockCardTo = service.unblockCard(blockCardTo);
			return blockCardTo;
		}
		return null;
		
	}

}
