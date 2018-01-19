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
@XmlRootElement(name = "ContentId")
@XmlAccessorType(XmlAccessType.NONE)
public class ContentId implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement
	private String contentId;
	

	/**
	 * Constructor
	 */
	public ContentId() {
		super();
	}

	/**
	 * @return the contentId
	 */
	public String getContentId() {
		return contentId;
	}

	/**
	 * @param contentId the contentId to set
	 */
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ContentId [contentId=" + contentId + "]";
	}
}