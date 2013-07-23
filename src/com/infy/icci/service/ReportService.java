package com.infy.icci.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.infy.icci.entity.BlockedCardEntity;
import com.infy.icci.entity.CardApplicationEntity;
import com.infy.icci.entity.CardEntity;
import com.infy.icci.entity.CustomerEntity;
import com.infy.icci.entity.TransactionEntity;
import com.infy.icci.exception.FromDateGreaterToDate;
import com.infy.icci.exception.NoApprovedCardFound;
import com.infy.icci.exception.NoCardsBlockedForReason;
import com.infy.icci.exception.NoDataFoundException;
import com.infy.icci.exception.NoRejectedCardFound;
import com.infy.icci.exception.SchemeNotChosenException;
import com.infy.icci.to.ApprovedCardTO;
import com.infy.icci.to.BlockedCardTO;
import com.infy.icci.to.CardApplicationTO;
import com.infy.icci.to.CardTO;
import com.infy.icci.to.CustomerTO;
import com.infy.icci.to.SchemeTO;
import com.infy.icci.to.TransactionTO;

public class ReportService 
{
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ICCI_Bank");
	private EntityManager em = null;
	/**
	 * 
	 * Description: The card number and count of transactions 
	 * carried out using the card during a specified duration are populated
	 * in a map (Key is card number and value is count of transactions)
	 * and returned
	 * Modified by: Edgardo
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map transactionDetails(Date fromDate , Date toDate)throws Exception
	{	
		em = emf.createEntityManager();
		try{
			/*Get the customers and number of transactions */
			Query query=em.createQuery("select j.customer, Count(i.transactionId) from TransactionEntity i,CardEntity j where (i.dateOfTransaction between ?1 and ?2) and (i.cardNo = j.cardNo) group by j.customer");
			query.setParameter(1, fromDate);
			query.setParameter(2, toDate);
			List temp = query.getResultList();
			if(temp == null)
				throw new NoDataFoundException("No Transactions Between Those Dates");
			HashMap<CustomerTO ,Long> map = new HashMap<CustomerTO, Long>();
			/*Set the result of the query into a map*/
			for (int i = 0; i < temp.size(); i++) 
			{
				Object []objet = (Object[])temp.get(i); 
				CustomerEntity entity = (CustomerEntity)objet[0];
				CustomerTO cust = new CustomerTO();
				cust.setCustomerId(entity.getCustomerId());
				cust.setName(entity.getName());
				cust.setPhone(entity.getPhone());
				cust.setEMail(entity.getEMail());
				cust.setAddress(entity.getAddress());
				cust.setUserName(entity.getUserName().getUserName());
				map.put(cust,Long.parseLong(objet[1].toString()));
			}
			return map;
		}finally{
			if(em!=null){
				em.close();
			}
		}
	}
	
	/**
	 * 
	 * Description: Retrieve and return a list of the transactions 
	 * carried out by specified card number during the	specified duration.
	 * Modified by: Edgardo
	 * @param cardNo
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TransactionTO> getTransactions(long cardNo, Date fromDate , Date toDate)throws Exception
	{
		em = emf.createEntityManager();
		try{
			/*Get the details of all transactions between two dates*/
			Query query=em.createQuery("select i,j.balanceAmount from TransactionEntity i,CardEntity j  where (i.dateOfTransaction between ?1 and ?2) and (i.cardNo =?3) and (j.cardNo = i.cardNo)");
			query.setParameter(1, fromDate);
			query.setParameter(2, toDate);
			query.setParameter(3, cardNo);
			List<Object> temp = (List<Object>)query.getResultList();
			if(temp == null)
				throw new NoDataFoundException("No Transactions Between Those Dates");
			List<TransactionTO> transactions = new ArrayList<TransactionTO>();
			/*Set those details into a list of transactionTo*/
			for (int i = 0; i < temp.size(); i++) 
			{
				Object obj[] = (Object[])temp.get(i);
				TransactionEntity trTemp = (TransactionEntity)obj[0];
				TransactionTO trans = new TransactionTO();
				trans.setTransactionId(trTemp.getTransactionId());
				trans.setDescription(trTemp.getDescription());
				trans.setDateOfTransaction(trTemp.getDateOfTransaction());
				trans.setCardNo(trTemp.getCardNo());
				trans.setBalanceAmount(Double.parseDouble(obj[1].toString()));
				trans.setAmount(trTemp.getAmount());
				transactions.add(trans);
			}
			return transactions;
		}finally{
			if(em!=null){
				em.close();
			}
		}
	}
	
	/**
	 * 
	 * Description:Retrieve and return a list of all cards blocked
	 * because of the specified reasons
	 * Modified by: Sara Giraldo
	 * @param reasons
	 * @return
	 * @throws NoCardsBlockedForReason 
	 */
	@SuppressWarnings("unchecked")
	public List<BlockedCardTO> getBlockedDetails(String[] reasons) throws NoCardsBlockedForReason 
	{
		try {
			
			em = emf.createEntityManager();
			List<BlockedCardTO> aux = new ArrayList<BlockedCardTO>();
			
			for (int i = 0; i < reasons.length; i++) {
				Query query;
				if(reasons[i].equals("Others")){
					query = em.createQuery("select k from BlockedCardEntity k where k.status='B' and k.description not in ('Theft','Payment not done')");
					
				}else{
				
				//Query for retrieve all blocked cards for  specified reason
				query = em.createQuery("select k from BlockedCardEntity k where k.status='B' and k.description=:reason");
				query.setParameter("reason", reasons[i]);
				}
			   List <BlockedCardEntity>results =(List <BlockedCardEntity>)query.getResultList();
			 
				//add to the list of BlockedCardTo , the cardTo object with the setting attributes
				for (BlockedCardEntity card : results) {
					BlockedCardTO carto=new BlockedCardTO();
					carto.setBlockId(card.getBlockId());
					carto.setCardNo(card.getCard().getCardNo());
					carto.setDateOfBlock(card.getDateOfBlock());
					carto.setDescription(card.getDescription());
					carto.setStatus(card.getStatus());
					aux.add(carto);
				}
				
			}
			System.out.println("aux length "+aux.size());
			return aux;
				
		} finally
		{
			if(em != null)
			{
				em.close();
			}

		}
	
	}
	
	/**
	 * 
	 * Description: Gets the finance details of the company. Create a Object
	 * of InfyCreditCardWrapper and invoke the getRevenueDetails() by passing 
	 * the fromDate and toDate as parameters. the method invocation returns a 
	 * List of financial details like profit, expenditure, revenue, etc. Display
	 * the details using a PanelGrid
	 * Modified by: Simon Escobar Benitez
	 * @param fromDate
	 * @param toDate
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public List<Double> getRevenueDeatils(Date fromDate , Date toDate) throws Exception
	{
		em =emf.createEntityManager();
		
		Query query = em.createQuery("select pay.amountPaid, pay.card from PaymentEntity pay where pay.dateOfPayment between ?1 and ?2");
		query.setParameter(1, fromDate);
		query.setParameter(2, toDate);
		List pays = query.getResultList();
		/*if the list of pays is empty is because there are no pay between those days*/
		if(pays.size() == 0)
		{
			throw new Exception("There are no payments in those days");
		}
		double revenue = 0;
		/*create a cardEntity list to get all the card details from the pays list included the pay details
		 * and calculate the revenue
		 * */
		List<CardEntity> cards = new ArrayList<CardEntity>();
		for(int i=0; i<pays.size(); ++i)
		{
			Object[] obj = (Object[])pays.get(i);
			revenue += Double.parseDouble(obj[0].toString());
			cards.add((CardEntity)obj[1]);
		}
		/*calculate the expenditure for each car entity who is in the list*/
		double expenditure = 0;
		for(CardEntity card : cards)
		{
			expenditure += card.getScheme().getSchemeAmount();
		}
		/* calculate the profit*/
		double profit = revenue - expenditure;
		List<Double> values = new ArrayList<Double>();
		values.add(revenue);
		values.add(expenditure);
		values.add(profit);
		return values;
	}
	
	/**
	 * Description: This method is in charge of returning a list with the details of all the 
	 * 				cards that are rejected or pending
	 * Modified by: YANNY ANDRES CASTRILLON
	 * @param fromDate
	 * @param toDate
	 * @return List<CardApplicationTO>
	 */
	@SuppressWarnings("unchecked")
	public List<CardApplicationTO> cardRejectedDetails(Date fromDate , Date toDate)throws Exception
	{
		// Declares and Initialize a list of type CardApplicationTO 
		List <CardApplicationTO> listRejectedCardTO = new ArrayList<CardApplicationTO>();
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try{
			// Brings all the pending/rejected cards that are between the FromDate and ToDate
			Query query1 = em.createQuery("select c from CardApplicationEntity c where c.dateOfApplication>=?1 and c.dateOfApplication<=?2");
			// sets the parameters
			query1.setParameter(1, fromDate);
			query1.setParameter(2, toDate);
			
			List <CardApplicationEntity> listCardRejectedEntity= query1.getResultList();
			
			int size = listCardRejectedEntity.size();
			if (size!=0)
			{
				for (int i = 0; i < size; i++) {
					/* Declares and Initialize one instance for every rejected cardEntity found in the DB 
					 * between the specific dates
					 */ 
					CardApplicationTO to = new CardApplicationTO();
					
					// sets the respective parameters of each instance
					to.setApplicationId(listCardRejectedEntity.get(i).getApplicationId());
					to.setName(listCardRejectedEntity.get(i).getName());
					to.setEmail(listCardRejectedEntity.get(i).getEmail());
					to.setPhone(listCardRejectedEntity.get(i).getPhone());
					
					// add that instance to the list 
					listRejectedCardTO.add(to);
				}
				// returns the rejected list cards
				return listRejectedCardTO;
			}
			else
				throw new NoRejectedCardFound();
		}
		// entityManager connection is close
		finally{
			if (em!=null){
				em.close();
			}
		}
		
	}

	
	/**
	 * 
	 * Description: This method is in charge of returning the approveDetails of the cards
	 * 				that are in the range of the given dates.
	 * Modified by: YANNY ANDRES CASTRILLON
	 * @param fromDate
	 * @param toDate
	 * @return List<ApprovedCardTO>
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public List<ApprovedCardTO> cardApprovedDetails(Date fromDate , Date toDate) throws Exception
	{
		//  Declares and Initialize a list of type ApproveCardTO
		List <ApprovedCardTO> listApprovesCardTo = new ArrayList<ApprovedCardTO>();
		
		Date dateOfBlock = new Date();
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try{
			// Bring the CardEntity that are between the range of date given.
			Query query1 = em.createQuery("select c from CardEntity c where c.dateOfRegistration>=?1 and c.dateOfRegistration<=?2");
			query1.setParameter(1, fromDate);
			query1.setParameter(2, toDate);
			
			// Gets the CardEntity
			List <CardEntity> cardEntity = query1.getResultList();
			System.out.println(cardEntity);

			int size = cardEntity.size();
			for (int i = 0; i < size; i++) 
			{
				ApprovedCardTO to= new ApprovedCardTO();
				
				/* Inner Query: Brings the latest dateOfBlock of each of the CardEntities
				 * Outer Query: Brings the dateOfBlock for each of the CardEntities that are on the range 
				 */
				Query query2 = em.createQuery( "select bc.dateOfBlock from BlockedCardEntity bc  " +
											   "where bc.card=?3 and bc.dateOfBlock =            " +
											   "(select max(b.dateOfBlock) from BlockedCardEntity b where b.card=?4)"
											 );
				// sets of the parameters
				query2.setParameter(3, cardEntity.get(i));
				System.out.println(cardEntity.get(i).getCustomer().getName());
				query2.setParameter(4, cardEntity.get(i));
				
				try{
					// Brings the Date of block
					dateOfBlock = (Date)query2.getSingleResult();
				}
				catch (Exception e) {
					dateOfBlock=null;
					
				}
				// Brings the paymentType of the last payment made by that particular card.
				Query query3 = em.createQuery( "select pa.paymentType from PaymentEntity pa " +
											   "where pa.card=?5 and pa.dateOfPayment =     " +
	   					                       "(select max(p.dateOfPayment) from PaymentEntity p where p.card=?6 )"
	 					 					 );
				// sets of the parameters
				query3.setParameter(5, cardEntity.get(i));
				query3.setParameter(6, cardEntity.get(i));
				char paymentType;
				
				try{
					// Brings paymentEntity of those customer's that have a paymentType
					paymentType = (Character)query3.getSingleResult();
				}
				catch (Exception e) {
					paymentType='-';
				}
				// set the respective attributes to the ApprovedCardTO instance
				to.setDateOfBlock(dateOfBlock);
				to.setPaymentType(String.valueOf(paymentType));
				to.setCustomerName(cardEntity.get(i).getCustomer().getName());
				
				//checks if the card has a scheme
				if (cardEntity.get(i).getScheme()!=null){
					
					to.setSchemeAmt(cardEntity.get(i).getScheme().getSchemeAmount());
					System.out.println(cardEntity.get(i).getScheme().getSchemeAmount());
					
				}
				else
				{
					// If there is not scheme assign to that card then it sets it's value to 0.0
					to.setSchemeAmt(0.0);
				}
				//macheteee...!!
				to.setCardNo(cardEntity.get(i).getCardNo());
				
				// add that instance to the list
				listApprovesCardTo.add(to);
			}
			// returns a list of type ApproveCardTO
			return listApprovesCardTo;
		}
		// entityManager close connection
		finally{
			if (em!=null){
				em.close();
			}
		}
		
	}
	
	/**
	 * 
	 * Description: This method is in charge of bringing the quantity of Approved cards and Rejected cards
	 * Returns:	It returns a HashMap with the respective card's value (Accepted and Rejected) 
	 * Modified by: YANNY ANDRES CASTRILLON
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public Map<String, Integer> statusDetails(Date fromDate , Date toDate)throws Exception
	{
		// Declares and Initialize a hasmap of String and Integer
		Map <String, Integer> map = new HashMap<String, Integer>();
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		
		try{
			// validates if the fromDate > toDate
			if (fromDate.after(toDate)){
				
				throw new FromDateGreaterToDate();
				
			}
			else{
				// Query that returns the number of cards that are approve between FromDate and ToDate
				Query query1 = em.createQuery( "select count(c.cardNo) from CardEntity c " +
											   "where c.dateOfRegistration >=?1 and c.dateOfRegistration <=?2 "
											  );
				query1.setParameter(1, fromDate);
				query1.setParameter(2, toDate);
				
				// portion of accepted cards that are in the range of the dates
				long cardsAccepted = (Long)(query1.getSingleResult());
				
							
				// query to select all the accepted cards that are in the dataBase
				query1 = em.createQuery( "select count(c.cardNo) " +
										 "from CardEntity c"
										);
				long totalAccepted = (Long)(query1.getSingleResult());
				
				// percentage of the cards that are inside of the given range OFF the whole total cards that are Accepted in the DataBase
				long percentAcceptCards = (long)(((double)cardsAccepted/totalAccepted)*100);
				
				
				// checks if there are any cards accepted or not
				if (cardsAccepted==0){
					map.put("accepted", (int)percentAcceptCards);
					throw new NoApprovedCardFound();
				}
				else{
					// add to the Hash-map the string associated with its respective percentage (approve)
					map.put("accepted", (int)percentAcceptCards);
				}
				
				// Query that returns the number of applications that are pending between FromDate and ToDate
				Query query2 = em.createQuery("select count(c.applicationId) " +
											  "from CardApplicationEntity c  " +
											  "where c.dateOfApplication>=?1 " +
											  "and c.dateOfApplication<=?2"
											  );
				query2.setParameter(1, fromDate);
				query2.setParameter(2, toDate);
				
				// portion of rejected/pending cards that are in the range of the dates
				long cardsRejected = (Long)query2.getSingleResult();
				
								
				// query to select all the pending/rejected cards that are in the dataBase
				query2 = em.createQuery("select count(c.applicationId) " +
										"from CardApplicationEntity c"
									   );
				long totalRejected = (Long)(query2.getSingleResult());
				
				// percentage of the cards that are inside of the given range OFF the total cards that are Rejected/Pending in the DataBase
				long percentrejectCards = (long)(((double)cardsRejected/totalRejected)*100);
				
				if (cardsRejected==0){
					map.put("rejected", (int)percentrejectCards);
				}
				else{
					// add to the Hash-map the string associated with its respective percentage (rejected)
					map.put("rejected", (int)percentrejectCards);
				}
								
				/* it returns a map with: [ "accepted", count(approveCards),
				 *							"rejetced", count(rejectedCards)]
				 */
				return map;
			}
		}
		// close entityManager connection
		finally{
			if (em!=null){
				em.close();
			}
		}
	}
	
	/**
	 * 
	 * Description: This method is in charge of getting the cardNumbers
	 * Returns:	It returns a list of  cardNo (Long)  
	 * Modified by: YANNY ANDRES CASTRILLON
	 * @param fromDate
	 * @param toDate
	 */
	@SuppressWarnings("unchecked")
	public List getCardNumbers(Date fromDate , Date toDate)throws Exception
	{
		// Entity Manager Management
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try{
			// brings all the cards that are between the fromDate and toDate
			Query query = em.createQuery( "select c from CardEntity c " +
										  "where c.dateOfRegistration >=?1 and c.dateOfRegistration <=?2 "
										 );
			query.setParameter(1, fromDate);
			query.setParameter(2, toDate);
			
			// brings the  result list
			List rs = query.getResultList();
			int size = rs.size();
			
			// if there are not results from the query
			if (size ==0){
				throw new NoApprovedCardFound();
			}
			else{
				// List that contains all the accepted number cards
				List <Long> listCardAccepted = new ArrayList<Long>();
				
				for (int i = 0; i < size; i++) {
					CardEntity acceptedCard = (CardEntity)rs.get(i);
					listCardAccepted.add(acceptedCard.getCardNo());
				}
				
				// returns card numbers
				return listCardAccepted;	
			}
		}
		// close the connection with entityManager
		finally {
			if (em!=null){
				em.close();
			}
		}
	}
	
	/**
	 * 
	 * Description:This method gets the card details for the particular scheme.
	 *  If the scheme is not chosen , then it throws SchemeNotChosen Exception.
	 * Modified by: Sara Giraldo
	 * @param schemeTo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CardTO> getCustomersForScheme(SchemeTO schemeTo)throws SchemeNotChosenException
	{
		System.out.println("dentro del service del metodo");
		try
		{
			em = emf.createEntityManager();
			//Select all card details with the specified schemeId 
			Query query = em.createQuery("select k from CardEntity k where k.scheme.schemeId=:schameId");
			query.setParameter("schameId",schemeTo.getSchemeId());
			
			List  rs = query.getResultList();
			
			//if is not card details available for the specified schemeId
			if(rs.size() == 0){
				throw new SchemeNotChosenException();
			}
			
						
			List<CardTO> cardList = new ArrayList<CardTO>();
			//
			for (Object object : rs) {
				
				CardEntity cardE = (CardEntity) object;
				//setting the attributes of the card entity to the cardTo object
				CardTO carto = new CardTO(cardE.getCardNo(), cardE.getPin(),
						cardE.getScheme().getSchemeId(),
						cardE.getBalanceAmount(), cardE.getCardAmount(),
						cardE.getDateOfRegistration(),
						cardE.getCustomer().getCustomerId());
				//adding to cardList the cardto object with the setting attibutes
				cardList.add(carto);
			}

			return cardList ;

		}
		finally
		{
			if(em != null)
			{
				em.close();
			}
		}
	}

	
	/**
	 * 
	 * Description:This method gets the details of the particular customer. 
	 * Modified by: Sara Giraldo
	 * @param custTo
	 * @return
	 */
	public CustomerTO getCustomerDetails(CustomerTO custTo)
	{
		try {
			em = emf.createEntityManager();			
			//Retrieve the customer details for the specified customerId
			CustomerEntity cust = em.find(CustomerEntity.class, custTo.getCustomerId());
			CustomerTO cus = new CustomerTO();
			cus.setAddress(cust.getAddress());
			cus.setName(cust.getName());
			cus.setCustomerId(cust.getCustomerId());
			return cus;

		}finally
		{
			if(em != null)
			{
				em.close();
			}
		}
	}
	
	/**
	 * 
	 * Description: return the cardNo of a customerId
	 * Modified by: Edgardo
	 * @param customerId
	 * @return
	 */
	public CardTO getCardNo(int customerId)throws Exception
	{
		em = emf.createEntityManager();
		try{
			Query query=em.createQuery("select i.cardNo from CardEntity i where i.customer.customerId =?1");
			query.setParameter(1, customerId);
			Long cardNo = (Long)query.getSingleResult();
			CardTO card = new CardTO();
			card.setCardNo(cardNo);
			return card;
		}finally{
			if(em!=null){
				em.close();
			}
		}
	}
}
