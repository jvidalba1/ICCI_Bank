/**
 * 
 */
package com.infy.icci.exception;

/**
 * @author edgardo_406751
 *
 */

public class LowBalanceException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 */
	public LowBalanceException(String exp) {
		super(exp);
	}
}