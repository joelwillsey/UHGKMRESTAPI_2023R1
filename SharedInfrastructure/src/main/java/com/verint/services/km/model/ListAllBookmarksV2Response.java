package com.verint.services.km.model;


import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author PSeifert
 *
 */
@XmlRootElement(name = "ListAllBookmarksV2Response")
@XmlAccessorType(XmlAccessType.NONE)
public class ListAllBookmarksV2Response implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@XmlElement(nillable=true)
    private ContentBookmarksV2 contentBookmarksV2;		
	
	/**
	 * Constructor
	 */
	public ListAllBookmarksV2Response() {
		super();
	}
	
	/**
	 * @return the contentBookmarksV2
	 */
	public ContentBookmarksV2 getContentBookmarksV2() {
		return contentBookmarksV2;
	}

	/**
	 * @param contentBookmarksV2 the contentBookmarksV2 to set
	 */
	public void setContentBookmarksV2(ContentBookmarksV2 contentBookmarksV2) {
		this.contentBookmarksV2 = contentBookmarksV2;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ListAllBookmarksV2Response [" +  contentBookmarksV2.toString() + "]";
	}
}
