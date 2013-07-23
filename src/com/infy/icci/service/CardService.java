package com.infy.icci.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.infy.icci.entity.BlockedCardEntity;
import com.infy.icci.entity.CardApplicationEntity;
import com.infy.icci.entity.CardEntity;
import com.infy.icci.entity.CustomerEntity;
import com.infy.icci.entity.LoginEntity;
import com.infy.icci.exception.CardService_CardAlreadyBlocked;
import com.infy.icci.exception.CardService_CardNotFound;
import com.infy.icci.exception.CardService_IncorrectCard;
import com.infy.icci.exception.CardService_OtherException;
import com.infy.icci.exception.InvalidCardNo;
import com.infy.icci.exception.NoDataFoundException;
import com.infy.icci.exception.NotPaymentDone;
import com.infy.icci.to.BlockedCardTO;
import com.infy.icci.to.CardApplicationTO;
import com.infy.icci.to.CardTO;

public class CardService 
{
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ICCI_Bank");
	private EntityManager em = null;
	
	/**
	 * 
	 * Description: Retrieve and return the details of the card whose
	 * CardNo is specified as part of CardTO.
	 * Modified by: Simon Escobar Benitez
	 * @param cardTo
	 * @return
	 * @throws Exception 
	 */
	public CardTO getCardDetails(CardTO cardTo) throws Exception
	{
		try
		{
			em = emf.createEntityManager();
			Query query;
			Object obj;
			boolean islogin = false;
			if(cardTo.getPin()==0)
			{
				/*select all the card details for report propose*/
				query = em.createQuery("select card from CardEntity card where card.cardNo = ?1");
				query.setParameter(1, cardTo.getCardNo());
			}
			else
			{
				/*select all the card details if the card number and pin entered by the user are correct*/
				query = em.createQuery("select card from CardEntity card, CustomerEntity cust " +
										"where card.cardNo = ?1 and " +
										"card.pin = ?2 and " +
										"card.customer.customerId = cust.customerId and "+
										"cust.customerId = ?3");
				/*set the parameters to the query*/
				query.setParameter(1, cardTo.getCardNo());
				query.setParameter(2, cardTo.getPin());
				query.setParameter(3, cardTo.getCustomerId());
				islogin = true;
			}
			obj = query.getSingleResult();
			/*if the object is null is because there is no card with the card number entered*/
			if(obj == null)
			{
				if(islogin)
					throw new InvalidCardNo("Invalid Card Number or Pin Enter a" +
							" valid Card Number and Pin");
				throw new InvalidCardNo();
			}
			/*get the card entity from the object*/
			CardEntity ce = (CardEntity)obj;
			/*set to the Transfer object CardTo the values of the data base*/
			cardTo.setBalanceAmount(ce.getBalanceAmount());
			cardTo.setCardAmount(ce.getCardAmount());
			cardTo.setCardNo(ce.getCardNo());
			/*if the card's scheme id is null is because is a new customer*/
			if(ce.getScheme() == null)
			{
				cardTo.setSchemeId(' ');
			}else{
				cardTo.setSchemeId(ce.getScheme().getSchemeId());
			}
			cardTo.setCustomerId(ce.getCustomer().getCustomerId());
			cardTo.setDateOfRegistration(ce.getDateOfRegistration());
			/*return cardTo*/
			return cardTo;
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
	 * Description: The method is used persist the card application
	 * details in the database
	 * Modified by: Santiago Moreno Palacio
	 * @param cardApplicationTo
	 * @return cardApplicationTo
	 */
	public CardApplicationTO applyCard(CardApplicationTO cardApplicationTo)
											throws Exception
	{	
		try{			
			em = emf.createEntityManager();
			
			CardApplicationEntity entity = new CardApplicationEntity();
			entity.setName(cardApplicationTo.getName());
			entity.setAddress(cardApplicationTo.getAddress());
			entity.setEmail(cardApplicationTo.getEmail());
			entity.setPhone(cardApplicationTo.getPhone());
			entity.setDateOfApplication(new Date());
			
			EntityTransaction et = em.getTransaction();
			et.begin();
			em.persist(entity);
			et.commit();
			cardApplicationTo.setApplicationId(entity.getApplicationId());
			return cardApplicationTo;
		}finally {
			if(em != null){
				em.close();
			}
		}
	}

	
	/**
	 * 
	 * Description: The method is used to retrieve the applied card details
	 * Modified by: Santiago Moreno Palacio
	 * @return List<CardApplicationTO>
	 */
	@SuppressWarnings("unchecked")
	public List<CardApplicationTO> retrieveAppliedCardDetails()
											throws Exception
	{
		try{			
			em = emf.createEntityManager();
			/* Create a new query */
			String strQuery = "select c from CardApplicationEntity c" +
					" order by c.applicationId";
			Query query = em.createQuery(strQuery);
			
			/* Check the result list */
			List<CardApplicationEntity> list = query.getResultList();
			if(list.size() == 0){
				throw new NoDataFoundException("No Card Application Found");
			}
			
			/* Populate all entities into list of transfer objects (listTO) */
			List<CardApplicationTO> listTO =
				new ArrayList<CardApplicationTO>();
			for (CardApplicationEntity cardApp : list) {
				CardApplicationTO card = new CardApplicationTO();
				card.setApplicationId(cardApp.getApplicationId());
				card.setName(cardApp.getName());
				card.setAddress(cardApp.getAddress());
				card.setPhone(cardApp.getPhone());
				card.setEmail(cardApp.getEmail());
				listTO.add(card);
			}
			
			/* Return the populate list */
			return listTO;
		}finally{
			if(em != null){
				em.close();
			}
		}
	}

	
	/**
	 * 
	 * Description: The method is used to retrieve the applied card
	 * details.
	 * Modified by: Santiago Moreno Palacio
	 * @param applicationId
	 */
	public void approveCard(int applicationId) throws Exception
	{
		try {
			em = emf.createEntityManager();
			/* Find card application entity with the application id */
			CardApplicationEntity cardAppEntity =
				em.find(CardApplicationEntity.class, applicationId);
			if(cardAppEntity == null){
				throw new Exception("No Card application Details Found "+
						"for Id:+" + applicationId);
			}
			
			/* Calculate the name from the new user*/
			String sNameFromUser =
				cardAppEntity.getName().substring(0, 3).toLowerCase();
			/* Generate 3 Integer random */
			String sRandomNameNumbers = generateRandom(false, 3);			
			/* Generate UserName with sNameFromUser and sRandomNameNumbers */
			String sUserName = sNameFromUser + sRandomNameNumbers;
			/* Generate a new Password for the UserName*/
			String sPassword = generateRandom(true, 8);			
			/* Generate a new Card Number */
			Long iCardNo = Long.parseLong(generateRandom(false, 16));			
			/* Generate a new Pin */
			Integer iPin = Integer.parseInt(generateRandom(false, 4));						
			
			/* Verify UserName does no exist in data base */
			LoginEntity loginTemp = em.find(LoginEntity.class, sUserName);
			while(loginTemp != null){
				sRandomNameNumbers = generateRandom(false, 3); 
				sUserName = sNameFromUser + sRandomNameNumbers;
				loginTemp = em.find(LoginEntity.class, sUserName);
			}
			
			/* Instantiate and populate a new Login Entity */
			LoginEntity loginEntity = new LoginEntity();
			loginEntity.setUserName(sUserName);
			loginEntity.setPassword(sPassword);
			
			/*  Instantiate and populate a new Customer Entity */
			CustomerEntity customerEntity = new CustomerEntity();
			customerEntity.setName(cardAppEntity.getName());
			customerEntity.setAddress(cardAppEntity.getAddress());
			customerEntity.setPhone(cardAppEntity.getPhone());
			customerEntity.setEMail(cardAppEntity.getEmail());
			customerEntity.setUserName(loginEntity);
			
			/* Verify CardNo does no exist in data base */
			CardEntity cardTemp = em.find(CardEntity.class, iCardNo);
			while(cardTemp != null){
				iCardNo = Long.parseLong(generateRandom(false, 16));
				cardTemp = em.find(CardEntity.class, iCardNo);
			}
			
			/*  Instantiate and populate a new Card Entity */
			CardEntity cardEntity = new CardEntity();;
			cardEntity.setCardNo(iCardNo);
			cardEntity.setPin(iPin);
			cardEntity.setScheme(null);
			cardEntity.setBalanceAmount(0);
			cardEntity.setCardAmount(0);
			cardEntity.setDateOfRegistration(new Date());
			cardEntity.setCustomer(customerEntity);
			
			/* Persist the different entities into the database */
			EntityTransaction et = em.getTransaction();
			et.begin();
			em.persist(loginEntity);
			em.persist(customerEntity);
			em.persist(cardEntity);
			em.remove(cardAppEntity);
			
			et.commit();
			/* Print the information generate */
			System.out.println("Customer Name: "+customerEntity.getName());
			System.out.println("User Name: "+sUserName);
			System.out.println("Password: "+sPassword);
			System.out.println("Card No: "+iCardNo);
			System.out.println("Pin: "+iPin);
		}finally{
			if(em != null){
				em.close();
			}
		}
	}
	
	/**
	 * 
	 * Description: Method created for generating random strings
	 * Modified by: Santiago Moreno Palacio
	 * @param botNumChars
	 * @return randomString
	 */
	public String generateRandom(boolean botNumChars, int limit){
		Random random = new Random();
		String randomString = "";
		int iCount = 0;
		boolean firstNumber = true;
		if(botNumChars){
			/* Generate chars and numbers */
			do{
				char cChar = (char)random.nextInt(123);
				if((cChar >= 48 && cChar <= 57) ||
						(cChar >= 65 && cChar <= 90) ||
						(cChar >= 97 && cChar <= 122)){
					randomString += cChar;
					iCount++;
				}
			}while(iCount != limit);
		}else{
			/* Generate only numbers */
			iCount = 0;
			do{
				char cChar = (char)random.nextInt(58);
				if(cChar == 48 && firstNumber){
					continue;
				}
				if(cChar >= 48 && cChar <= 57){
					firstNumber = false;
					randomString += cChar;
					iCount++;					
				}
			}while(iCount != limit);
		}
		return randomString.toLowerCase();
	}

	
	/**
	 * 
	 * Description: Check if the card is blocked, creating a native query for execute the 
	 * 				stored function
	 * Modified by: Luis Miguel Marulanda Jaramillo
	 * @param cardNo
	 * @return BlockedCardTO
	 */
	@SuppressWarnings("unchecked")
	public BlockedCardTO checkCardBlocked(BlockedCardTO to) throws Exception
	{
		em = emf.createEntityManager();
		try{

			Query query = em.createNativeQuery("select sf_checkcardstatus(?1) from dual");
			query.setParameter(1, to.getCard());

			Vector vec = (Vector) query.getSingleResult();

			String state = vec.toString().substring(1,2);
			//depending of the value returned a decision is choose
			if(state.equals("0")){
				throw new CardService_OtherException();
			}else if(state.equals("1")){
				throw new CardService_IncorrectCard();
			}else if(state.equals("3")){
				throw new CardService_CardAlreadyBlocked();
			}else{
				//if the returned value is 2 then the object is returned
				return to;
			}
		}
		finally
		{
			if(em != null){
				em.close();
			}
		}

	}

	/**
	 * Description: Check if the card is blocked, creating a native query for execute the 
	 * 				stored function
	 * Modified by: Franco
	 * @param cardNo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public BlockedCardTO checkCardBlocked(long cardNo) throws Exception
	{
		em = emf.createEntityManager();
		try{

			Query query = em.createQuery("select be " +
					"from BlockedCardEntity be " +
					"where be.card.cardNo =?1 order by be.dateOfBlock desc");
			query.setParameter(1, cardNo);
	
			List<BlockedCardEntity> rs = query.getResultList();
			BlockedCardTO blockedCardTO = new BlockedCardTO();
			if (rs.size() == 0) {
				blockedCardTO.setStatus('N');
			} else {
				blockedCardTO.setStatus(rs.get(0).getStatus());
				blockedCardTO.setDescription(rs.get(0).getDescription());
				blockedCardTO.setCardNo(rs.get(0).getCard().getCardNo());
				blockedCardTO.setBlockId(rs.get(0).getBlockId());
				blockedCardTO.setDateOfBlock(rs.get(0).getDateOfBlock());
			}
			return blockedCardTO;
		}
		finally
		{
			if(em != null){
				em.close();
			}
		}

	}

	/**
	 * Description: Block the card after this was checked
	 * Modified by: Luis Miguel Marulanda Jaramillo
	 * @param blockCardTo
	 * @return
	 */
	public BlockedCardTO blockCard(BlockedCardTO blockCardTo) throws Exception
	{
		try{
			em = emf.createEntityManager();
			em.getTransaction().begin();
			//look if the card exist creating an object of type CardEntity and finding it
			CardEntity card = em.find(CardEntity.class, blockCardTo.getCard());
			if(card == null){
				throw new CardService_CardNotFound();
			}
			//set the values for the blocked card 
			BlockedCardEntity blockcard = new BlockedCardEntity();
			blockcard.setCard(card);
			blockcard.setDateOfBlock(blockCardTo.getDateOfBlock());

			//fetch amount paid from paymentEntity
			Double amount = card.getBalanceAmount();
			Double scheme = card.getScheme().getSchemeAmount();

			//if the amount to be paid > 2*scheme the description is set as Payment not done
			if(amount > 2 * scheme){
				blockcard.setDescription("Payment is not done");	
			}else{
				blockcard.setDescription(blockCardTo.getDescription());
			}

			blockcard.setStatus('B');
			em.persist(blockcard);

			em.getTransaction().commit();
			//set the blockId of the blocked card after do commit (generated by sequence)
			blockCardTo.setBlockId(blockcard.getBlockId());
			return blockCardTo;
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
	 * Description: This method unblocked a card depending of blockCardTo
	 * which reason is Payment Not Done
	 * Modified by: Yanny Andres Castrillon
	 * @param blockCardTo
	 * @return
	 */
	public BlockedCardTO unblockCard(BlockedCardTO blockCardTo)throws Exception
	{
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try{
			
			et.begin();
	
			// Bring Card Entity so i can get the Balance
			Query query = em.createQuery("select c from CardEntity c where c.cardNo=?1");
			query.setParameter(1, blockCardTo.getCard());
			
			CardEntity cardEntity = (CardEntity)query.getSingleResult();
			// if there is an entity
			if (cardEntity!=null){
				// First to check if the description is Payment Not Done
				if (blockCardTo.getDescription().equalsIgnoreCase("Payment Not Done")){
						// set the status to N (non block)
					BlockedCardEntity blockCardEntity = new BlockedCardEntity();
					
					blockCardEntity.setBlockId(blockCardTo.getBlockId());
					blockCardEntity.setCard(cardEntity);
					blockCardEntity.setDescription(blockCardTo.getDescription());
					blockCardEntity.setDateOfBlock(blockCardTo.getDateOfBlock());
					
					blockCardEntity.setStatus('N');
					em.merge(blockCardEntity);
					et.commit();
	
				}
				else
					throw new NotPaymentDone();
				
			}
			return blockCardTo;
		}
		finally{
			if (em!=null){
				em.close();
			}
		}

	}
}
