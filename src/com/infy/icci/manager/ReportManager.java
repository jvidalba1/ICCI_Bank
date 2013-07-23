package com.infy.icci.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.infy.icci.exception.NoCardsBlockedForReason;
import com.infy.icci.exception.SchemeNotChosenException;
import com.infy.icci.service.ReportService;
import com.infy.icci.to.ApprovedCardTO;
import com.infy.icci.to.BlockedCardTO;
import com.infy.icci.to.CardApplicationTO;
import com.infy.icci.to.CardTO;
import com.infy.icci.to.CustomerTO;
import com.infy.icci.to.SchemeTO;
import com.infy.icci.to.TransactionTO;

public class ReportManager 
{
	/**
	 * 
	 * Description: Call transactionDetails method from ReportService
	 * Modified by: Edgardo 
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map transactionDetails(Date fromDate , Date toDate)throws Exception
	{
		return new ReportService().transactionDetails(fromDate, toDate);
	}
	
	/**
	 * 
	 * Description: Call getTransactions method from ReportService
	 * Modified by: Edgardo
	 * @param cardNo
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public List<TransactionTO> getTransactions(long cardNo, Date fromDate , Date toDate)throws Exception
	{
		return new ReportService().getTransactions(cardNo, fromDate, toDate);
	}

	
	/**
	 * 
	 * Description: Creates an Object of ReportService and invokes 
	 * the getBlockedDetails ()method by passing the parameters.
	 * Modified by: Sara Giraldo
	 * @param reasons
	 * @return
	 */
	public List<BlockedCardTO> getBlockedDetails(String[] reasons)
									throws NoCardsBlockedForReason
	{
		ReportService report= new ReportService();
		return report.getBlockedDetails(reasons) ;
	}

	
	/**
	 * 
	 * Description: Call the method getRevenueDetails from ReportService
	 * Modified by: Simon Escobar Benitez
	 * @param fromDate
	 * @param toDate
	 * @return
	 * @throws Exception 
	 */
	public List<Double> getRevenueDetails(Date fromDate , Date toDate) throws Exception
	{
		return new ReportService().getRevenueDeatils(fromDate, toDate);
	}
	
	/**
	 * 
	 * Description: Creates an Object of ReportService and invokes 
	 * 				the cardRejectedDetails method.
	 * Modified by: YANNY ANDRES CASTRILLON
	 * @param reasons
	 * @return List<CardApplicationTO>
	 */
	public List<CardApplicationTO> cardRejectedDetails(Date fromDate , Date toDate)throws Exception
	{
		List<CardApplicationTO> listRejectedCardTO = new ArrayList<CardApplicationTO>();
		ReportService service = new ReportService();
		listRejectedCardTO= service.cardRejectedDetails(fromDate, toDate);
		return listRejectedCardTO;
	}

	
	/**
	 * 
	 * Description: Creates an Object of ReportService and invokes 
	 * 				the cardApprovedDetails method.
	 * Modified by: YANNY ANDRES CASTRILLON
	 * @param reasons
	 * @return List<ApprovedCardTO>
	 */
	public List<ApprovedCardTO> cardApprovedDetails(Date fromDate , Date toDate)throws Exception
	{
		ReportService service = new ReportService();
		List<ApprovedCardTO> listApproveTo = service.cardApprovedDetails(fromDate, toDate);
		return listApproveTo;
	}

	
	/**
	 * 
	 * Description: Creates an Object of ReportService and invokes 
	 * 				the statusDetails method.
	 * Modified by: YANNY ANDRES CASTRILLON
	 * @param 
	 * @return HashMap<String, Integer>
	 */
	public Map<String, Integer> statusDetails(Date fromDate , Date toDate)throws Exception
	{
		ReportService service = new ReportService();
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		map = service.statusDetails(fromDate, toDate);
		return map;
	}

	
	/**
	 * 
	 * Description: Creates an Object of ReportService and invokes 
	 * 				the getCardNumbers method.
	 * Modified by: YANNY ANDRES CASTRILLON
	 * @param 
	 * @return List<Long>
	 */
	@SuppressWarnings("unchecked")
	public List<Long> getCardNumbers(Date fromDate , Date toDate)throws Exception
	{
		ReportService service = new ReportService();
		List <Long> listCardAccepted= service.getCardNumbers(fromDate, toDate);
		return listCardAccepted;
	}

	
	/**
	 * 
	 * Description: This method gets the card details for the particular scheme
	 *  by invoking the corresponding service method.
	 *  If the scheme is not chosen , then it throws SchemeNotChosen Exception.
	 * Modified by: Sara Giraldo
	 * @param schemeTo
	 * @return
	 */
	public List<CardTO> getCustomersForScheme(SchemeTO schemeTo)throws SchemeNotChosenException
	{
		ReportService report= new ReportService();
		List<CardTO> aux = new ArrayList<CardTO>();
		aux = report.getCustomersForScheme(schemeTo);
		return aux;
	}

	
	/**
	 * 
	 * Description: This method gets the details of the particular customer
	 *  by invoking the corresponding service method. 
	 *  Invokes the getCustomerDetails and getCardDetailsForUpdate
	 *  methods of the wrapper.
	 * Modified by: Sara Giraldo
	 * @param custTo
	 * @return
	 */
	public CustomerTO getCustomerDetails(CustomerTO custTo)
	{
		ReportService repor= new ReportService();
		return repor.getCustomerDetails(custTo);
	}
	
	/**
	 * 
	 * Description: Invoke getCardNo method of ReportService
	 * Modified by: Edgardo
	 * @param customerId
	 * @return
	 */
	public CardTO getCardNo(int customerId)throws Exception
	{
		return new ReportService().getCardNo(customerId);
	}


}
