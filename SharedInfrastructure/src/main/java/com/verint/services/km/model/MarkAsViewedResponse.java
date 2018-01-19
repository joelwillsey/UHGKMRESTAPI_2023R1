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
 * @author pseifert
 *
 */
@XmlRootElement(name = "MarkAsViewedResponse")
@XmlAccessorType(XmlAccessType.NONE)
public class MarkAsViewedResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement
	private String viewUUID = "";	

	/**
	 * 
	 */
	public MarkAsViewedResponse() {
		super();
	}

	/**
	 * @return the ratingUUID
	 */
	public String getViewUUID() {
		return viewUUID;
	}

	/**
	 * @param ratingUUID the ratingUUID to set
	 */
	public void setViewUUID(String viewUUID) {
		this.viewUUID = viewUUID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MarkAsViewedResponse [viewUUID=" + viewUUID + "]";
	}
}