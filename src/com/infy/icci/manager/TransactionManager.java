package com.infy.icci.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.infy.icci.exception.LowBalanceException;
import com.infy.icci.service.TransactionService;
import com.infy.icci.to.TransactionTO;

public class TransactionManager 
{
	private HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	/**
	 * 
	 * Description: Based on session handling retrieve the cardNo used for logging in.
	 *				Pass the card no to getBalance() method of TransactionService
	 *				If the balance returned is less than the transaction Amount raise an exception.
	 *				If balance is greater than transaction amount, call the updatecardAmount() method of TransactionService
	 *				After that call the saveTransaction() method of TransactionService.
	 * Modified by: 	Edgardo
	 * @param transactionTo
	 * @return
	 */
	public TransactionTO saveTransaction(TransactionTO transactionTo)throws Exception
	{
		TransactionService transService = new TransactionService();
		double balance = transService.getBalance(transactionTo.getCardNo());
		if((balance - transactionTo.getAmount()) < 0){
			throw new LowBalanceException("Insuficient Card Amount");
		}
		transactionTo.setBalanceAmount(balance - transactionTo.getAmount());
		transService.updateCardAmount(transactionTo.getCardNo(), transactionTo.getBalanceAmount());
		return transService.saveTransaction(transactionTo);
	}
	
	/**
	 * 
	 * Description: Call Card usae Method of Transaction Service Class 
	 * Modified by: Mateo Vidal
	 * @param month
	 * @param position
	 * @return List<TransactionTO>
	 * @throws Exception 
	 */
	public List<TransactionTO> cardUsage(String month, String option) throws Exception
	{
		return new TransactionService().cardUsage(month, option,
				Long.parseLong(session.getAttribute("cardNo").toString()));
	}
	
	/**
	 * 
	 * Description: Algorithm to create lists with respective months
	 * Modified by: Mateo Vidal
	 * @return List<String>
	 */
	@SuppressWarnings({ "deprecation" })
	public List<String> getMonthList() throws Exception
	{
		String months[] = {"January",
							"February",
							"March",
							"April",
							"May",
							"June",
							"July",
							"August",
							"September",
							"October",
							"November",
							"December"};
		
		// Lists Creation
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		List<String> finalList = new ArrayList<String>();
		Date today = new Date();
		int mes = today.getMonth();

		// Check June Month
		if(mes >= 5)
		{
			int cont = 1;
			for (int i = mes; i >= 0 ; i--) {
				
				if(cont==7)
				{
					break;
				}
				list1.add(months[i]);
				cont++;
			}
				
		}else if(mes < 5){
			int cont1 = 0;
			int i = mes;
			while ( i >= 0) {
				list1.add(months[i]);
				i--;
				cont1++;
			}
			for (int j = 0; j < 6-cont1; j++) {
				list2.add(months[i+12]);
				i--;
			}

		}
		
		// Calculate Final Month List
		// Add list for this year or previous year
		int totalSize = list1.size()+list2.size();
		int j = 0;
		for (int i = 0; i < totalSize; i++) {
			
			if(i>=list1.size())
			{
				finalList.add(list2.get(j));
				j++;
			}else
			{
				finalList.add(list1.get(i));
			}
		}
		
		return finalList;
	}
}
