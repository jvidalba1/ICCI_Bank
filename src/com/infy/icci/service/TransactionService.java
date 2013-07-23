package com.infy.icci.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.infy.icci.entity.BlockedCardEntity;
import com.infy.icci.entity.CardEntity;
import com.infy.icci.entity.TransactionEntity;
import com.infy.icci.exception.CardService_CardNotFound;
import com.infy.icci.exception.InvalidCardNo;
import com.infy.icci.exception.NoTransactionsFound;
import com.infy.icci.to.TransactionTO;

public class TransactionService 
{
	private EntityManagerFactory emf=Persistence.createEntityManagerFactory("ICCI_Bank");
	private EntityManager em = null;
	/**
	 * 
	 * Description: return the current available cardAmount 
	 * 				for the corresponding card.
	 * Modified by: Edgardo
	 * @param cardNo
	 * @return
	 */
	public double getBalance(long cardNo)throws Exception
	{
		em = emf.createEntityManager();
		try{
			CardEntity card = em.find(CardEntity.class,cardNo);
			Query query = em.createQuery("select p from BlockedCardEntity p where p.card =?1");
			query.setParameter(1,card);
			BlockedCardEntity blocked = (BlockedCardEntity)query.getSingleResult();
			if(blocked != null && blocked.getStatus() == 'B')
				throw new Exception("Transaction Not Done. The card Is Blocked");
			return card.getCardAmount();
		}finally{
			if(em!=null){
				em.close();
			}
		}
	}
	
	/**
	 * 
	 * Description: to persist the transaction details and to return the 
	 * 				other transaction details like dateOfTransaction, auto 
	 * 				generated transactionid, etc.
	 * Modified by: Edgardo
	 * @param transactionTo
	 * @return
	 */
	public TransactionTO saveTransaction(TransactionTO transactionTo)throws Exception
	{
		em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
			/*create a new instance of TransactionEntity and set the values*/
			TransactionEntity transaction = new TransactionEntity();
			transaction.setAmount(transactionTo.getAmount());
			transaction.setDateOfTransaction(transactionTo.getDateOfTransaction());
			transaction.setCardNo(transactionTo.getCardNo());
			transaction.setDescription(transactionTo.getDescription());
			CardEntity card = em.find(CardEntity.class, transactionTo.getCardNo());
			if(card == null)
				throw new CardService_CardNotFound();
			card.setBalanceAmount(transactionTo.getAmount());
			em.persist(transaction);
			em.getTransaction().commit();
			transactionTo.setTransactionId(transaction.getTransactionId());
			return transactionTo;
		}finally{
			if(em!=null){
				em.close();
			}
		}
	}
	
	/**
	 * 
	 * Description: to update the cardAmount by the specified 
	 * 				value for the corresponding card.
	 * Modified by: Edgardo
	 * @param cardNo
	 * @param amount
	 */
	public void updateCardAmount(long cardNo, double amount)throws Exception
	{
		em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
			CardEntity card = em.find(CardEntity.class, cardNo);
			if(card == null)
				throw new InvalidCardNo();
			card.setCardAmount(amount);
			em.getTransaction().commit();
		}finally{
			if(em!=null){
				em.close();
			}
		}
	}

	
	/**
	 * 
	 * Description: This method find the transactions for a user in determinate month and year
	 * Modified by: Mateo Vidal
	 * @param month: This is the month selected by the user
	 * @param position: The user selects this year (current) and previous year (previous)
	 * @param cardNo: The card number user
	 * @return result: This is the list with all transactions made
	 * @throws Exception 
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<TransactionTO> cardUsage(String month, String position, long cardNo) throws Exception
	{
	    List<TransactionTO> result = new ArrayList<TransactionTO>();
	    List<TransactionEntity> listentity = new ArrayList<TransactionEntity>();
		try{
			em=emf.createEntityManager();	
			em.getTransaction().begin();
			
			//Query to find the card entity with the cardNo retrieved of the session
			//CardEntity card = em.find(CardEntity.class, cardNo); 
			
			//Take the month and upper it and take the first three letters
			month = month.toUpperCase().substring(0,3);		  
			
			/*
			 * Take the today's date, and retrieve the year,
			 * in the if statement, ask if the user selected this year option("current")
			 * or previous year option ("previous"), if the user selected 
			 * the last, so decrement the year by one
			 */
			Date date = new Date();
			int year = date.getYear();
			String finalYear = "";
			if(position.equals("previous"))
			{
				year -= 1;
				/*
				 * Change the type of year variable to String, and take
				 * the second and third letters, its because the getYear
				 * method retrieves a number before the year.
				 */
				finalYear = String.valueOf(year).toString().substring(1, 3);
				
			}else if(position.equals("current")){
				finalYear = String.valueOf(year).toString().substring(1, 3);
			}
			
			/*
			 * Query to find the correct dates of the transactions
			 * made by the user, in the first SUBSTRING method, take
			 * the month in the database, and in the second SUBSTRING, 
			 * take the year.
			 * */
			Query query = em.createQuery("select k" +
										" from TransactionEntity k" +
										" where (SUBSTRING(k.dateOfTransaction,4,3)=?1)" +
										" AND (SUBSTRING(k.dateOfTransaction,8,2)=?2)" +
										" AND k.cardNo=?3");
			query.setParameter(1,month);
			query.setParameter(2,finalYear);
			query.setParameter(3,cardNo);
	        listentity = query.getResultList();
             
            int size = listentity.size();
            
            /*
             * If there are no transactions with those dates, so 
             * throw a exception with the correct error, and it'll
             * be show to the user.
             */
			if(size==0){
				throw new NoTransactionsFound();
					
			}else{
				/*
				 * In the case that there are transactions, so per
				 * each transaction, set the values into the
				 * toObj object of type TransactionTO, retrieving
				 * the values of the entObj of type TransactionEntity
				 * and add the toObj instance into the result list
				 * create and initialize in the first lines of this
				 * method, this list is returned to the manager class
				 * */
				for(int count=0;count<size && count<6 ;count++)
				{
					TransactionTO toObj = new TransactionTO();	
					TransactionEntity entObj = listentity.get(count);	
					
					toObj.setAmount(entObj.getAmount());
					toObj.setDateOfTransaction(entObj.getDateOfTransaction());
					toObj.setCardNo(entObj.getCardNo());
					toObj.setDescription(entObj.getDescription());
					toObj.setTransactionId(entObj.getTransactionId());
						  
					result.add(toObj);
				}
			}
			em.getTransaction().commit();
			return result;
		}
		finally{
			if(em!=null){
				em.close();
			}
		}
	}

}
