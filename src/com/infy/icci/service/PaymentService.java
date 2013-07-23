
package com.infy.icci.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.infy.icci.entity.CardEntity;
import com.infy.icci.entity.PaymentEntity;
import com.infy.icci.exception.CardService_CardNotFound;
import com.infy.icci.to.CardTO;
import com.infy.icci.to.PaymentTO;

public class PaymentService 
{
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ICCI_Bank");
	private EntityManager em = null;
	
	/**
	 * 
	 * Description: This method search the last payment did 
	 * Modified by: Franco
	 * @param cardTo
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public PaymentTO getPaymentDetails(CardTO cardTo) throws Exception
	{
		em = emf.createEntityManager();
		List pe = null;
		Query query = em.createQuery("select pay.dateOfPayment from PaymentEntity pay where pay.card.cardNo = ?1");
		query.setParameter(1, cardTo.getCardNo());
		pe = query.getResultList();
		PaymentTO paymentTo = new PaymentTO();
		if(pe.size() == 0)
		{
			return paymentTo;
		}
		paymentTo.setDateOfPayment((Date)pe.get(pe.size()-1));
		return paymentTo;
	}
	
	/**
	 * 
	 * Description: Retrieve and return payment details after payment is made
	 * Modified by: Franco
	 * @param paymentTo
	 * @return
	 * @throws Exception 
	 */
	public PaymentTO makePayment(PaymentTO paymentTo) throws Exception
	{		
		try {
			EntityManager em = emf.createEntityManager();
			EntityTransaction et = em.getTransaction();
			CardEntity cardEntity = 
				em.find(CardEntity.class, paymentTo.getCardNo());
			if (cardEntity == null) {
				throw new CardService_CardNotFound();
			}
			et.begin();
			// Populate the CardEntity to set into the new PaymentEntity
			cardEntity.setBalanceAmount(
					cardEntity.getBalanceAmount() - 
						paymentTo.getAmountPaid());
			if (cardEntity.getBalanceAmount() < 0) {
				cardEntity.setBalanceAmount(0);
			}
			cardEntity.setCardAmount(
					cardEntity.getCardAmount() +
					  cardEntity.getBalanceAmount());
			
			PaymentEntity paymentEntity = new PaymentEntity();
			paymentEntity.setAmountPaid(paymentTo.getAmountPaid());
			paymentEntity.setCard(cardEntity);
			paymentEntity.setDateOfPayment(new Date());
			paymentEntity.setPaymentType(paymentTo.getPaymentType());
			em.persist(paymentEntity);
			et.commit();
			// After the persist the sequence generate a new id that its mandatory to show to the user
			paymentTo.setPaymentId(paymentEntity.getPaymentId());
			return paymentTo;
		} finally {
			if(em != null)
			{
				em.close();
			}
		}
	}

}
