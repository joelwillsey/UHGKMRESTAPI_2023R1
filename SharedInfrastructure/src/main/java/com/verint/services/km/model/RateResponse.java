/**
 * 
 */
package com.verint.services.km.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author jmiller
 *
 */
@XmlRootElement(name = "RateResponse")
@XmlAccessorType(XmlAccessType.NONE)
public class RateResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement
	private String ratingUUID = "";	

	/**
	 * 
	 */
	public RateResponse() {
		super();
	}

	/**
	 * @return the ratingUUID
	 */
	public String getRatingUUID() {
		return ratingUUID;
	}

	/**
	 * @param ratingUUID the ratingUUID to set
	 */
	public void setRatingUUID(String ratingUUID) {
		this.ratingUUID = ratingUUID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RateResponse [ratingUUID=" + ratingUUID + "]";
	}
}