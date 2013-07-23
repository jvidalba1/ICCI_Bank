/**
 *
 */
package com.infy.icci.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class ValidateEmail implements Validator{
	
	/**
	 * Method validate
	 * Description: This method is used for validate the input email from
	 * the user. Throw a Exception if the email is invalid.
	 * Modified by: Santiago Moreno Palacio
	 * @throws Validator Exception
	 */
	public void validate(FacesContext facesContext, UIComponent component,
			Object object) throws ValidatorException
	{
		if(facesContext == null || component == null){
			throw new NullPointerException();
		}
		if(!(component instanceof UIInput)){
			return;
		}
		String sExp = "[A-z][A-z0-9]*@[A-z]+\\.[A-z]{2,4}";
		if(!(object.toString().trim().matches(sExp))){
			FacesMessage message = new FacesMessage();
			message.setSummary("Invalid Email");
			message.setDetail("Invalid Email(Format: user@mail.com)");
			throw new ValidatorException(message);
		}
	}

}
