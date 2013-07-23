
package com.infy.icci.to;

import java.util.Date;


/**
 * @author edgardo_406751
 *
 */
public class BlockedCardTO 
{
	private int blockId;
	private long cardNo;
	private Date dateOfBlock;
	private String description;
	private char status;
	
	/**
	 * Method getBlockId
	 * @return the blockId
	 */
	public int getBlockId() {
		return blockId;
	}
	/**
	 * Method setBlockId
	 * @param blockId the blockId to set
	 */
	public void setBlockId(int blockId) {
		this.blockId = blockId;
	}
	/**
	 * Method getCard
	 * @return the card
	 */
	public long getCard() {
		return cardNo;
	}
	/**
	 * Method setCardNo
	 * @param card the card to set
	 */
	public void setCardNo(long card) {
		this.cardNo = card;
	}
	/**
	 * Method getDateOfBlock
	 * @return the dateOfBlock
	 */
	public Date getDateOfBlock() {
		return dateOfBlock;
	}
	/**
	 * Method setDateOfBlock
	 * @param dateOfBlock the dateOfBlock to set
	 */
	public void setDateOfBlock(Date dateOfBlock) {
		this.dateOfBlock = dateOfBlock;
	}
	/**
	 * Method getDescription
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Method setDescription
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * Method getStatus
	 * @return the status
	 */
	public char getStatus() {
		return status;
	}
	/**
	 * Method setStatus
	 * @param status the status to set
	 */
	public void setStatus(char status) {
		this.status = status;
	}
}
