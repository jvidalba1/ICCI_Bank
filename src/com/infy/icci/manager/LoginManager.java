package com.infy.icci.manager;

import com.infy.icci.service.LoginService;
import com.infy.icci.to.LoginTO;

public class LoginManager 
{	
	/**
	 * 
	 * Description: received a LoginTO object,then create a loginService object
	 * 				and call validateLogin method of loginService class
	 * Modified by: Luis Miguel Marulanda Jaramillo
	 * @param loginTo
	 * @return
	 */

	public LoginTO validateLogin(LoginTO loginTo) throws Exception
	{
		return new LoginService().validateLogin(loginTo);	
	}
}
