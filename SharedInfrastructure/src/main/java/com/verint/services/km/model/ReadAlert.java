/**
 * 
 */
package com.verint.services.km.model;

import java.io.Serializable;
import java.math.BigInteger;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author jmiller
 *
 */
@XmlRootElement(name = "ReadAlert")
@XmlAccessorType(XmlAccessType.NONE)
public class ReadAlert implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@XmlElement
	private String contentID = "";
	
	@XmlElement
	private String migRefID = "";
 
	@XmlElement
	private String accessDate = "";


	
	/**
	 * Constructor 
	 */
	public ReadAlert() {
		super();
	}

	/**
	 * @return the contentid
	 */
	public String getContentID() {
		return contentID;
	}

	/**
	 * @param id the contentid to set
	 */
	public void setContentID(String id) {
		this.contentID = id;
	}

	/**
	 * @return the migRefID
	 */
	public String getMigRefID() {
		return migRefID;
	}

	/**
	 * @param set migRefID
	 */
	public void setMigRefID(String mig) {
		this.migRefID = mig;
	}

	/**
	 * @return the accessdate
	 */
	public String getAccessDate() {
		return accessDate;
	}

	/**
	 * @param set the access date
	 */
	public void setAccessDate(String aDate) {
		this.accessDate = aDate;
	}

	@Override
	public String toString() {
		return "ReadAlert [contentID=" + contentID + ", migRefID=" + migRefID + ", accessDate=" + accessDate + "]";
	}
}