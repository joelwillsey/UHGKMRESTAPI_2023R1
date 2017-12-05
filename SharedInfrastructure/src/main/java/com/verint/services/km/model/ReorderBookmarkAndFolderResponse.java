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
@XmlRootElement(name = "ReorderBookmarkAndFolderResponse")
@XmlAccessorType(XmlAccessType.NONE)
public class ReorderBookmarkAndFolderResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	
	
	@XmlElement(nillable=true)
    private ContentBookmarksV2 response;		
	
	@XmlElement(nillable=true)
	private Set<ErrorList> errorList = new HashSet<ErrorList>();	
	
	/**
	 * 
	 */
	public ReorderBookmarkAndFolderResponse() {
		super();
	}

	/**
	 * @return the errorList
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
	
	
public Set<ErrorList> getErrorList() {
		return errorList;
}

	public void setErrorList(Set<ErrorList> errorList) {
		this.errorList = errorList;
	}
	

	/**
	 * @param errorList the errorList to set
	 */
	public void addErrorList(ErrorList error) {
		errorList.add(error);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ManageBookmarkV2Response [response=" + response.toString() + "]";
	}
}