package com.infy.icci.managedBean;

import java.util.List;
import java.util.Random;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.infy.icci.to.CardApplicationTO;
import com.infy.icci.wrapper.InfyCreditCardWrapper;

public class CardApplicationMB 
{
	private String name;
	private String email;
	private String address;
	private String phone;
	private Integer applicationId;
	private String message;
	private static String randomCharacter;
	private String enteredCharacter;
	private List<CardApplicationTO> cardTOList;
	private HttpSession session =
		(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	private boolean renderApply;

	/**
	 * Constructor
	 */
	public CardApplicationMB(){
		this.setRenderApply(true);
	}
		
	/**
	 * 
	 * Description: This method is used as a value change event handler
	 * for the Apply button
	 * Modified by: Santiago Moreno Palacio
	 * @return success or fail
	 */
	public String applyCard()
	{
		this.setMessage(null);
		if(this.getEnteredCharacter().equals(randomCharacter)){
			try{
				CardApplicationTO cardApplication = new CardApplicationTO();
				cardApplication.setName(this.getName());
				cardApplication.setAddress(this.getAddress());
				cardApplication.setEmail(this.getEmail());
				cardApplication.setPhone(this.getPhone());
				cardApplication = new InfyCreditCardWrapper().applyCard(cardApplication);				
				
				this.setMessage("Applied for card successully. Id: "
						+ cardApplication.getApplicationId());
				
				this.setName(null);
				this.setAddress(null);
				this.setEmail(null);
				this.setPhone(null);
				this.setEnteredCharacter(null);
				randomCharacter = null;
				this.getRandomCharacter();
				return "success";
			}catch (Exception exception) {
				this.setMessage(exception.getMessage());
				return "fail";
			}
		}else{
			this.setMessage("Entered Chars is different to Random Chars");
			return "fail";
		}
	}
	
	/**
	 * 
	 * Description: This method is used as a value change event handler
	 * for the Approve button
	 * Modified by: Santiago Moreno Palacio
	 * @return success or fail
	 */
	public String approveCard()
	{
		this.setRenderApply(true);
		try {
			int iSizeList = this.cardTOList.size();
			int iCountNoSelected = 0;
			for (int iter = 0; iter < iSizeList; iter++) {
				CardApplicationTO card = this.cardTOList.get(iter);				
				if(card.isApplicationSelected()){
					new InfyCreditCardWrapper().approveCard(card.getApplicationId());
				}else{
					iCountNoSelected++;
				}
			}
			if(iSizeList == iCountNoSelected){
				this.setMessage("No Application Cards Selected");
				return "fail";
			}else{
				this.setMessage("All the selected Application Card have been" +
				" approved");
			}
			return "success";
		} catch (Exception exception) {
			this.setMessage(exception.getMessage());
			return "fail";
		}
	}

	/**
	 * Method getName
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method setName
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method getEmail
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Method setEmail
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Method getAddress
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Method setAddress
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Method getPhone
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Method setPhone
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Method getApplicationId
	 * @return the applicationId
	 */
	public Integer getApplicationId() {
		return applicationId;
	}

	/**
	 * Method setApplicationId
	 * @param applicationId the applicationId to set
	 */
	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}

	/**
	 * Method getMessage
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Method setMessage
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Method getRandomCharacter
	 * Description: This method is used for generating random characters.
	 * Modified by: Santiago_406749
	 * @return the random characters generated
	 */
	public String getRandomCharacter() {
		if(randomCharacter == null){
			int iCount = 0;
			String sCharacters = "";
			Random random = new Random();
			do{
				char cChar = (char)random.nextInt(123);
				if((cChar >= 48 && cChar <= 57) ||
						(cChar >= 65 && cChar <= 90) ||
						(cChar >= 97 && cChar <= 122)){
					sCharacters += cChar;
					iCount++;
				}
			}while(iCount != 8);
			this.setRandomCharacter(sCharacters.toLowerCase());
		}
		return randomCharacter;
	}

	/**
	 * Method setRandomCharacter
	 * @param randomCharacter the randomCharacter to set
	 */
	public void setRandomCharacter(String randomCharacters) {
		randomCharacter = randomCharacters;
	}

	/**
	 * Method getEnteredCharacter
	 * @return the enteredCharacter
	 */
	public String getEnteredCharacter() {
		return enteredCharacter;
	}

	/**
	 * Method setEnteredCharacter
	 * @param enteredCharacter the enteredCharacter to set
	 */
	public void setEnteredCharacter(String enteredCharacter) {
		this.enteredCharacter = enteredCharacter;
	}

	/**
	 * Method getCardTOList
	 * @return the cardTOList
	 */
	public List<CardApplicationTO> getCardTOList() throws Exception{		
		if(session.getAttribute("userName") != null){			
			try{
				List<CardApplicationTO> list =
					new InfyCreditCardWrapper().retrieveAppliedCardDetails();
				this.setCardTOList(list);
			}catch (Exception exception) {
				this.setMessage(exception.getMessage());
			}
		}else{
			this.setMessage("You Are Not Allow to enter here, please Login");
			this.setRenderApply(false);
		}
		return cardTOList;
	}

	/**
	 * Method setCardTOList
	 * @param cardTOList the cardTOList to set
	 */
	public void setCardTOList(List<CardApplicationTO> cardTOList) {
		this.cardTOList = cardTOList;
	}
	/**
	 * isRenderApply Method
	 * @return the renderApply
	 */
	public boolean isRenderApply() {
		return renderApply;
	}

	/**
	 * setRenderApply Method
	 * @param the renderApply to set
	 */
	public void setRenderApply(boolean renderApply) {
		this.renderApply = renderApply;
	}
}
