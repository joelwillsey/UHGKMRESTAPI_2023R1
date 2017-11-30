package com.verint.services.km.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author pseifert
 *
 */
@XmlRootElement(name = "BookmarkedContentV2")
@XmlAccessorType(XmlAccessType.NONE)
public class BookmarkedContentV2 implements Serializable{
	private static final long serialVersionUID = 1L;

	@XmlElement(nillable = true)
	private String synopsis;

	@XmlElement(nillable = true)
	private String localeName = "";
	
	@XmlElement(nillable = true)
	private Date createdDate;
	
	@XmlElement(nillable=true)
	private int sequenceNumber;
	
	@XmlElement(nillable=true)
	private boolean isFeatured = false;
	
	@XmlElement(nillable=true)
	private boolean isNewOrChanged = false;
	
	@XmlElement(nillable=true)
	private String title;
	
	@XmlElement(nillable=true)
	private String contentId;
	
	@XmlElement(nillable=true)
	private String parentFolderId;


	/**
	 * Constructor
	 */
	public BookmarkedContentV2() {
		super();
	}

	public String getSynopsis(){
		return synopsis;
	}
	
	public void setSynopsis(String syn){
		this.synopsis = syn;
	}
	public String getLocaleName(){
		return localeName;
	}
	public void setLocaleName(String loc){
		this.localeName = loc;
	}
	public String getContentID(){
		return contentId;
	}
	public void setContentID(String con){
		this.contentId = con;
	}
	
	public Date getCreatedDate(){
		return createdDate;
	}
	
	public void setCreatedDate(Date dat){
		this.createdDate = dat;
	}
	public boolean getIsFeatured(){
		return isFeatured;
	}
	
	public void setIsFeatured(boolean isf){
		this.isFeatured = isf;
	}
	
	public boolean getIsNewOrChanged(){
		return isNewOrChanged;
	}
	
	public void setIsNewOrChanged(boolean isn){
	this.isNewOrChanged = isn;	
	
	}
	
	public int getSequenceNumber(){
		
		return sequenceNumber;
	}
	
	public void setSequenceNumber(int seq){
		this.sequenceNumber=seq;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String ti){
		this.title = ti;
	}
	
	public String getParentFolderId(){
		return parentFolderId;
	}
	public void setParentFolderId(String par){
		this.parentFolderId = par;
	}
	@Override
	public String toString() {
		return "BookmarkedContentV2 [contentIds=" + contentId + ", title=" + title + ", synopsis=" + synopsis
				+ ", localeName=" + localeName + ", createdDates=" + createdDate.toString()  
				+ ", isFeatured=" + isFeatured + ", isNewOrChanged=" + isNewOrChanged + ", sequenceNumber=" + sequenceNumber + ", parentFolderId=" + parentFolderId +"]";
	}
}
