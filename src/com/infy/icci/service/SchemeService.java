package com.infy.icci.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.infy.icci.entity.SchemeEntity;
import com.infy.icci.exception.NoSchemeRecordsFound;
import com.infy.icci.to.CardTO;
import com.infy.icci.to.SchemeTO;

public class SchemeService 
{
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ICCI_Bank");
	private EntityManager em = null;
	
	/**
	 * 
	 * Description: This method retrieves the data of a respectively schemeId
	 * Modified by: Mateo Vidal
	 * @param schemeTo
	 * @return schemeTo
	 * @throws Exception 
	 */
	public SchemeTO getSchemeDetails(SchemeTO schemeTo) throws Exception
	{
		try{
			em = emf.createEntityManager();
			
			/*
			 * Find the data for the schemeId selected by the user
			 */
			SchemeEntity scheme = em.find(SchemeEntity.class, schemeTo.getSchemeId());
			
			/*
			 * Ask if the Find result is null, I mean, if there are no records 
			 * in the database, set the values to schemeTo.
			 */
			if(scheme == null)
			{
				throw new NoSchemeRecordsFound();
			}else
			{
				/*
				 * Set the values into the schemeTo obj with the values of
				 * scheme obj (SchemeEntity)
				 */
				schemeTo.setInterestRate(scheme.getInterestRate());
				schemeTo.setMinimumAmount(scheme.getMinimumAmount());
				schemeTo.setSchemeAmount(scheme.getSchemeAmount());
				schemeTo.setSchemeId(scheme.getSchemeId());
			}
			return schemeTo;
		}finally{
			if(em != null)
				em.close();
		}
		
	}
	
	/**
	 * 
	 * Description: This method updates the data the user card
	 * 				with the respectively data in the scheme table 
	 * Modified by: Mateo Vidal
	 * @param to
	 */
	public void updateSchemeDetails(CardTO to)
	{
		try{
			em=emf.createEntityManager();	
			em.getTransaction().begin();
			
			char schemeId = to.getSchemeId();
			
			SchemeEntity schemeEntity  = new SchemeEntity();
			SchemeEntity schemeEntity1 = new SchemeEntity();
			/*
			 * This query retrieve the data of a respectively schemeId
			 * It's into the to obj (CardTO)
			 */
			schemeEntity.setSchemeId(schemeId);
			Query query1 = em.createQuery("select s" +
										 " from SchemeEntity s" +
										 " where s.schemeId=?1");
			query1.setParameter(1,schemeId);
			
			schemeEntity1 = (SchemeEntity) query1.getSingleResult();
			 
			long cardNum = to.getCardNo();
			
			//Update the schemeId
			Query query = em.createQuery("update CardEntity k" +
										" set k.scheme=?1" +
										" where k.cardNo=?2");
			query.setParameter(1, schemeEntity);
			query.setParameter(2, cardNum);
			query.executeUpdate();
			
			//update the balanceAmount
			Query query2 = em.createQuery("update CardEntity c" +
										 " set c.balanceAmount=?1" +
										 " where c.cardNo=?2");
			query2.setParameter(1, schemeEntity1.getMinimumAmount());
			query2.setParameter(2, cardNum);
			query2.executeUpdate();
			
			//update the cardAmount
			
			Query query3 = em.createQuery("update CardEntity a" +
										 " set a.cardAmount=:cardAmount" +
										 " where a.cardNo=:cardNo");
			query3.setParameter("cardAmount", schemeEntity1.getSchemeAmount());
			query3.setParameter("cardNo", cardNum);
			query3.executeUpdate();
			
			em.getTransaction().commit();
			
		}finally{
			if(em!=null){
				em.close();
			}
		}
	}

}
