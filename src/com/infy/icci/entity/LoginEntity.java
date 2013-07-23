package com.infy.icci.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ICCI_LOGIN")
public class LoginEntity 
{
	@Id
	private String userName;
	private String password;
	
	/**
	 * Method getUserName
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * Method setUserName
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * Method getPassword
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Method setPassword
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
