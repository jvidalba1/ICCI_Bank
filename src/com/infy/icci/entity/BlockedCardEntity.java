package com.infy.icci.entity;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="icci_blockedcard")
public class BlockedCardEntity 
{
	@Id
	@SequenceGenerator(name="seq_blockid", sequenceName="seq_blockid",
			allocationSize=1, initialValue=101)
	@GeneratedValue(generator="seq_blockid",
			strategy=GenerationType.SEQUENCE)
	private int blockId;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cardNo")
	private CardEntity card;
	@Temporal(TemporalType.DATE)
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
	public CardEntity getCard() {
		return card;
	}
	/**
	 * Method setCard
	 * @param card the card to set
	 */
	public void setCard(CardEntity card) {
		this.card = card;
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
