package com.infy.icci.wrapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.infy.icci.exception.NoCardsBlockedForReason;
import com.infy.icci.exception.SchemeNotChosenException;
import com.infy.icci.manager.CardManager;
import com.infy.icci.manager.LoginManager;
import com.infy.icci.manager.PaymentManager;
import com.infy.icci.manager.ReportManager;
import com.infy.icci.manager.SchemeManager;
import com.infy.icci.manager.TransactionManager;
import com.infy.icci.manager.CustomerManager;
import com.infy.icci.to.ApprovedCardTO;
import com.infy.icci.to.BlockedCardTO;
import com.infy.icci.to.CardApplicationTO;
import com.infy.icci.to.CardTO;
import com.infy.icci.to.CustomerTO;
import com.infy.icci.to.LoginTO;
import com.infy.icci.to.PaymentTO;
import com.infy.icci.to.SchemeTO;
import com.infy.icci.to.TransactionTO;

public class InfyCreditCardWrapper 
{
	
	/**
	 * 
	 * Description: This method calls the getMonthList in
	 * the TransactionManager class
	 * Modified by: Mateo Vidal
	 * @param cardNo
	 * @param fromDate
	 * @param toDate
	 * @return list
	 */
	public List<String> getMonthList() throws Exception
	{
		return new TransactionManager().getMonthList();
	}

	
	/**
	 * 
	 * Description: Call getTransactions method from ReportManager
	 * Modified by: Edgardo
	 * @param cardNo
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public List<TransactionTO> getTransactions(long cardNo, Date fromDate , Date toDate)
													throws Exception
	{
		return new ReportManager().getTransactions(cardNo, fromDate, toDate);
	}

	
	/**
	 * 
	 * Description: Invoke the method getBlockedDetails
	 * in the ReportManager and return a list of blockedCards
	 * Modified by: Sara Giraldo
	 * @param reasons
	 * @return
	 */
	public List<BlockedCardTO> getBlockedDetails(String[] reasons)
										throws NoCardsBlockedForReason
	{
		return new ReportManager().getBlockedDetails(reasons);
	}

	
	/**
	 * 
	 * Description: Pass Two dates to transactionDetails of ReportManager Class
	 * Modified by: Edgardo
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map transactionDetails(Date fromDate, Date toDate)
													throws Exception
	{
		return new ReportManager().transactionDetails(fromDate, toDate);
	}
	
	
	/**
	 * 
	 * Description: call the method getRevenueDetails from
	 * Report Manager class
	 * Modified by: Simon Escobar Benitez
	 * @param fromDate
	 * @param toDate
	 * @return
	 * @throws Exception 
	 */
	public List<Double> getRevenueDetails(Date fromDate , Date toDate) throws Exception
	{
		return new ReportManager().getRevenueDetails(fromDate, toDate);
	}
	
	public List<CardApplicationTO> cardRejectedDetails(Date fromDate , Date toDate)throws Exception
	{
		List<CardApplicationTO> listRejectedCardTO = new ArrayList<CardApplicationTO>();
		ReportManager manager = new ReportManager();
		
		listRejectedCardTO = manager.cardRejectedDetails(fromDate, toDate);
		return listRejectedCardTO;
	}

	
	/**
	 * 
	 * Description: call the method cardApprovedDetails from
	 * 				Report Manager class
	 * Modified by: YANNY ANDRES CASTRILLON
	 * @param fromDate
	 * @param toDate
	 * @return List<ApprovedCardTO>
	 * @throws Exception 
	 */
	public List<ApprovedCardTO> cardApprovedDetails(Date fromDate , Date toDate)throws Exception
	{
		ReportManager manager = new ReportManager();
		List<ApprovedCardTO> listApproveTo= manager.cardApprovedDetails(fromDate, toDate);
		return listApproveTo;
	}

	
	/**
	 * 
	 * Description: call the method statusDetails from
	 * 				Report Manager class
	 * Modified by: YANNY ANDRES CASTRILLON
	 * @param fromDate
	 * @param toDate
	 * @return HashMap<String, Integer>
	 * @throws Exception 
	 */
	public Map<String, Integer> statusDetails(Date fromDate , Date toDate)throws Exception
	{
		ReportManager manager = new ReportManager();
		Map<String, Integer> map = new HashMap<String, Integer>(); 
		map = manager.statusDetails(fromDate, toDate);
		return map;
	}

	
	/**
	 * 
	 * Description: Invoke the method getCustomerForScheme
	 * and return a list of cardTo
	 * Modified by: Sara Giraldo
	 * @param schemeTo
	 * @return
	 */
	public List<CardTO> getCustomersForScheme(SchemeTO schemeTo)
								throws SchemeNotChosenException
	{
		return new ReportManager().getCustomersForScheme(schemeTo);
	}

	/**
	 * 
	 * Description: Invoke the getCustomerDetails()method and return a 
	 * 				CustomerTO
	 * Modified by: Simon Escobar Benitez
	 * @param custTo
	 * @return CustomerTO
	 * @throws Exception 
	 */
	public CustomerTO getCustomerDetailsForLogin(CustomerTO custTo) throws Exception
	{
		return new CustomerManager().getCustomerDetails(custTo);
	}
	
	
	/**
	 * 
	 * Description: Invoke the getCustomerDetails()method and return a 
	 * 				CustomerTO
	 * Modified by: Sara Giraldo
	 * @param custTo
	 * @return CustomerTO
	 */
	public CustomerTO getCustomerDetails(CustomerTO custTo)
	{
		return new ReportManager().getCustomerDetails(custTo);
	}
	
	/**
	 * 
	 * Description: Send a transactionTo to TransactionManager
	 * Modified by: Edgardo
	 * @param transactionTo
	 * @return
	 */
	public TransactionTO saveTransaction(TransactionTO transactionTo)
													throws Exception
	{
		return new TransactionManager().saveTransaction(transactionTo);
	}

	
	/**
	 * 
	 * Description: This method calls to cardUsage method in
	 * 				the Manager class
	 * Modified by: Mateo Vidal
	 * @param month
	 * @param option
	 * @return list
	 * @throws Exception 
	 */
	public List<TransactionTO> cardUsage(String month, String option)
													throws Exception
	{
		List<TransactionTO> list = new  ArrayList<TransactionTO>();
		TransactionManager manager = new TransactionManager();
		
		list = manager.cardUsage(month, option);
		return list;
	}

	
	/**
	 * 
	 * Description: Call the applycard method of Card Manager Class
	 * Modified by: Santiago Moreno Palacio
	 * @param cardApplicationTo
	 * @return cardApplicationTo
	 */
	public CardApplicationTO applyCard(CardApplicationTO cardApplicationTo)
													throws Exception
	{		
		return new CardManager().applyCard(cardApplicationTo);
	}

	
	/**
	 * 
	 * Description: Call the retrieveAppliedDetails method of Card 
	 * 				Manager Class
	 * Modified by: Santiago Moreno Palacio
	 * @return List<CardApplicationTO>
	 */
	public List<CardApplicationTO> retrieveAppliedCardDetails()
													throws Exception
	{
		return new CardManager().retrieveAppliedCardDetails();
	}

	
	/**
	 * 
	 * Description: Call the approveCard method of Card Manager Class
	 * Modified by: Santiago Moreno Palacio
	 * @param applicationId
	 */
	public void approveCard(int applicationId) throws Exception
	{
		new CardManager().approveCard(applicationId);
	}

	
	/**
	 * 
	 * Description: call the method getPaymentDetails from
	 * 				Payment Manager class
	 * Modified by: Franco
	 * @param cardTo
	 * @return
	 * @throws Exception 
	 */
	public PaymentTO getPaymentDetails(CardTO cardTo) throws Exception
	{
		return new PaymentManager().getPaymentDetails(cardTo);
	}
	
	/**
	 * 
	 * Description: call the method makePayment from
	 * 				Payment Manager class
	 * Modified by: Franco
	 * @param paymentTo
	 * @return
	 * @throws Exception 
	 */
	public PaymentTO makePayment(PaymentTO paymentTo) throws Exception
	{
		return new PaymentManager().makePayment(paymentTo);
	}

	
	/**
	 * Description: Received a BlockedCardTO object and pass it
	 * 				as parameter of checkCardBlocked of CardManager class
	 * Modified by: Luis Miguel Marulanda Jaramillo
	 * @param blockedCardTo
	 * @return
	 * @throws Exception 
	 */
	public BlockedCardTO checkCardBlocked(BlockedCardTO blockedCardTo) throws Exception
	{
		return new CardManager().checkCardBlocked(blockedCardTo);
	}
	
	/**
	 * 
	 * Description: call the method getCardDetails from
	 * 				Card Manager class
	 * Modified by: Simon Escobar Benitez
	 * @param cardTo
	 * @return
	 * @throws Exception 
	 */
	public CardTO getCardDetails(CardTO cardTo) throws Exception
	{
		return new CardManager().getCardDetails(cardTo);
	}
	
	/**
	 * 
	 * Description: call the method getCardDetailsForLogin of CardManager
	 * 				class and pass cardTo object as parameter
	 * Modified by: Simon Escobar Benitez
	 * @param cardTo
	 * @return
	 * @throws Exception 
	 */
	public CardTO getCardDetailsForLogin(CardTO cardTo) throws Exception
	{
		return new CardManager().getCardDetailsForLogin(cardTo);
	}
	
	/**
	 * 
	 * Description: call the method getSchemeDetails from
	 * 				Scheme Manager class
	 * Modified by: Franco
	 * @param schemeTo
	 * @return
	 * @throws Exception 
	 */
	public SchemeTO getSchemeDetails(SchemeTO schemeTo) throws Exception
	{
		return new SchemeManager().getSchemeDetails(schemeTo);
	}
	
	/**
	 * 
	 * Description: Call the unblockCard method from the Manager class
	 * Modified by: YANNY ANDRES CASTRILLON
	 * @param blockCardTo
	 * @return BlockedCardTO
	 */
	public BlockedCardTO unblockCard(BlockedCardTO blockCardTo)throws Exception
	{
		
		CardManager manager = new CardManager();
		blockCardTo =manager.unblockCard(blockCardTo);
		return blockCardTo;
	}
	
	/**
	 * 
	 * Description: received a LoginTO object,then create a loginManager object
	 * 				and call validateLogin method of loginaManager class
	 * Modified by: Luis Miguel Marulanda
	 * @param loginTo
	 * @return
	 */
	public LoginTO validateLogin(LoginTO loginTo) throws Exception
	{
		return new LoginManager().validateLogin(loginTo); 
	}
	
	/**
	 * 
	 * Description: This method calls to updateSchemeDetails method
	 * 				in the SchemeManager class
	 * Modified by: Mateo Vidal
	 * @param to
	 */
	public void updateSchemeDetails(CardTO to)
	{
		new SchemeManager().updateSchemeDetails(to);
	}

	/**
	 * Description: Call getCardNumbers from Report Manager Classs
	 * Modified By: Yanny
	 * @param fromDate
	 * @param toDate
	 * @return
	 * @throws Exception
	 */
	public List<Long> getCardNumbers(Date fromDate , Date toDate)throws Exception
	{
		return new ReportManager().getCardNumbers(fromDate, toDate);
	}

	
	/**
	 * 
	 * Description: Call getCardNo from ReportManager
	 * Modified by: Edgardo
	 * @param customerId
	 * @return
	 */
	public CardTO getCardNo(int customerId)throws Exception
	{
		return new ReportManager().getCardNo(customerId);
	}

}
