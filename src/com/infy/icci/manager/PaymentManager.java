package com.infy.icci.manager;

import com.infy.icci.service.CardService;
import com.infy.icci.service.PaymentService;
import com.infy.icci.service.SchemeService;
import com.infy.icci.to.BlockedCardTO;
import com.infy.icci.to.CardTO;
import com.infy.icci.to.PaymentTO;
import com.infy.icci.to.SchemeTO;

public class PaymentManager 
{
	/**
	 * 
	 * Description: Checks whether specified card is blocked.
	 * The details are populated and returned in PaymentTO.
	 * Modified by: FRANCO
	 * @param cardTo
	 * @return
	 * @throws Exception 
	 */
	public PaymentTO getPaymentDetails(CardTO cardTo) throws Exception
	{
		CardService cardService = new CardService();
	    BlockedCardTO blockedCardTO = 
	    	cardService.checkCardBlocked(cardTo.getCardNo());
	 
	    PaymentTO paymentTO = new PaymentService().getPaymentDetails(cardTo);
	    if(blockedCardTO.getStatus() == 'N'){
	    	paymentTO.setCardStatus("Not Blocked");
	    } else {
	    	paymentTO.setCardStatus("Blocked");
	    }
		
		return paymentTO;
		
	}
	
	/**
	 * 
	 * Description: The final balance amount to be paid is calculated.
     * If the card is blocked, it is checked if the payment made causes
     * the card to be unblocked.
	 * Modified by: FRANCO 
	 * @param paymentTo
	 * @return
	 * @throws Exception 
	 */
	public PaymentTO makePayment(PaymentTO paymentTo) throws Exception
	{
		/*
		 * The payment details are populated in 
		 * PaymentTO and passed on to
		 * appropriate service 
		 * class method for persistence.
		 */
		paymentTo = new PaymentService().makePayment(paymentTo);
		CardTO cardTO = new CardTO();
		cardTO.setCardNo(paymentTo.getCardNo());
		cardTO = new CardService().getCardDetails(cardTO);
		SchemeTO schemeTO = new SchemeTO();
		schemeTO.setSchemeId(cardTO.getSchemeId());
		schemeTO = new SchemeService().getSchemeDetails(schemeTO);
		BlockedCardTO blockcardto = new BlockedCardTO();
		blockcardto.setCardNo(cardTO.getCardNo());
		BlockedCardTO blockedCardTO = 
			new CardService().checkCardBlocked(cardTO.getCardNo());
		/* If the card its blocked check if he balance its minor than the scheme amount*/
		if (blockedCardTO.getStatus() == 'B') { 
			/* Check if the payment Description its about payment */ 
			if (blockedCardTO.getDescription() == "Payment not done") {
				if (cardTO.getBalanceAmount() < schemeTO.getSchemeAmount()) {
					blockedCardTO = new CardService().unblockCard(blockedCardTO);
				}
			}
		}else{
			/* If the balance amount is greater than 2 times the scheme amount*/
			if(cardTO.getBalanceAmount() >=
				(2 * schemeTO.getSchemeAmount() )){
				blockedCardTO = new CardService().blockCard(blockedCardTO);
			}
		}
		return paymentTo;
	}
}
