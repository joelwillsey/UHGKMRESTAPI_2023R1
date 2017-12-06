/**
 * 
 */
package com.verint.services.km.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author jmiller
 *
 */
@XmlRootElement(name = "ManageBookmarkV2Response")
@XmlAccessorType(XmlAccessType.NONE)
public class ManageBookmarkV2Response implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@XmlElement(nillable=true)
    private ContentBookmarksV2 response;		
	
	/**
	 * Constructor
	 */
	public ManageBookmarkV2Response() {
		super();
	}
	
	/**
	 * @return the contentBookmarksV2
	 */
	public ContentBookmarksV2 getResponse() {
		return response;
	}

	/**
	 * @param contentBookmarksV2 the contentBookmarksV2 to set
	 */
	public void setResponse(ContentBookmarksV2 contentBookmarksV2) {
		this.response = contentBookmarksV2;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ListAllBookmarksV2Response [" +  response.toString() + "]";
	}
}