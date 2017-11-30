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

	
	
	
	
	@Override
	public String toString() {
		return "BookmarkedContentV2 [contentIds=" + contentId + ", title=" + title + ", synopsis=" + synopsis
				+ ", localeName=" + localeName + ", createdDates=" + createdDate.toString()  
				+ ", isFeatured=" + isFeatured + ", isNewOrChanged=" + isNewOrChanged + ", sequenceNumber=" + sequenceNumber + ", parentFolderId=" + parentFolderId +"]";
	}
}
