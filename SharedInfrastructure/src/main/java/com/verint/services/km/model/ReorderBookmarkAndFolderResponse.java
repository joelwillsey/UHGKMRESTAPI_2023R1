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
    private ContentBookmarksV2 contentBookmarksV2;		
	
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
	public ContentBookmarksV2 getContentBookmarksV2() {
		return contentBookmarksV2;
	}

	/**
	 * @param contentBookmarksV2 the contentBookmarksV2 to set
	 */
	public void setContentBookmarksV2(ContentBookmarksV2 contentBookmarksV2) {
		this.contentBookmarksV2 = contentBookmarksV2;
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
		return "ManageBookmarkV2Response [contentBookmarksV2=" + contentBookmarksV2.toString() + "]";
	}
}