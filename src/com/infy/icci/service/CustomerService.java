package com.infy.icci.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.infy.icci.entity.CustomerEntity;
import com.infy.icci.to.CustomerTO;

public class CustomerService 
{
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ICCI_Bank");
	private EntityManager em = null;
	
	/**
	 * 
	 * Description: This method validate a customer depending of customerId
	 * Modified by: Simon Escobar Benitez
	 * @param customerTo
	 * @return
	 * @throws Exception 
	 */
	public CustomerTO getCustomerDetails(CustomerTO customerTo) throws Exception
	{
		em = emf.createEntityManager();
		CustomerEntity ce = em.find(CustomerEntity.class, customerTo.getCustomerId());
		if(ce == null)
		{
			throw new Exception("There is no customer with this id");
		}
		if(ce.getUserName().getUserName().equals(customerTo.getUserName()))
		{
			customerTo.setCustomerId(ce.getCustomerId());
			customerTo.setName(ce.getName());
			return customerTo;
		}
		else
		{
			throw new Exception("Invalid Customer Id, Please Enter a Valid Customer Id");
		}
		
	}
	
}
