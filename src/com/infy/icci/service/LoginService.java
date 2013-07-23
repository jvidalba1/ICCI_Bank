package com.infy.icci.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.infy.icci.exception.LoginService_PasswordIncorrect;
import com.infy.icci.exception.LoginService_UserNotfound;
import com.infy.icci.to.LoginTO;

public class LoginService 
{
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ICCI_Bank");
	private EntityManager em = null;
	/**
	 * 
	 * Description: this method validate the password and userName enter
	 * 				by the user, if one of this are incorrect an exception
	 * 				is throw
	 * Modified by: Luis Miguel Marulanda Jaramillo
	 * @param loginTo
	 * @return
	 */
	public LoginTO validateLogin(LoginTO loginTo)throws Exception
	{
		try{
			em = emf.createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			//get the userName and password of the loginTo object
			String username = loginTo.getUserName();
			String password = loginTo.getPassword();

			/*
			 * create a query that return the password of the userName fetched 
			 *	if this is equal to the userName in database
			 */
			Query query = em.createQuery("select k.password from LoginEntity k where k.userName=?1");			
			query.setParameter(1, username);
			Object o = null; 
			try{
				o = query.getSingleResult();
			}catch (Exception e) {
				//if the user is not found throw an Exception
				throw new LoginService_UserNotfound();
			}
			
			String password2 = (String)o;

			/*	
			 *  compare the password entered and the password fetched by the query
			 *  if the passwords are equal create a new loginTO object, set the userName
			 *	and password and the return it.
			 */
			if(password.equals(password2)){
				LoginTO to = new LoginTO();
				to.setUserName(username);
				to.setPassword(password2);
				return to;

			}else{
				throw new LoginService_PasswordIncorrect();
			}

		}
		finally{
			if(em != null){
				em.close();
			}
		}
	}


}
