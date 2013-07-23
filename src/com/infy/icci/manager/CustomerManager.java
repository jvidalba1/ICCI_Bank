package com.infy.icci.manager;

import com.infy.icci.service.CustomerService;
import com.infy.icci.to.CustomerTO;

public class CustomerManager 
{
	/**
	 * 
	 * Description: Call the method getCustomerDetails from CustomerService
	 * Modified by: Simon Escobar Benitez
	 * @param customerTo
	 * @return
	 * @throws Exception 
	 */
	public CustomerTO getCustomerDetails(CustomerTO customerTo) throws Exception
	{
		return new CustomerService().getCustomerDetails(customerTo);
	}
}
