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
@XmlRootElement(name = "ManageBookmarkResponse")
@XmlAccessorType(XmlAccessType.NONE)
public class ManageBookmarkResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement(nillable=true)
	private Set<ErrorList> errorList = new HashSet<ErrorList>();	

	/**
	 * 
	 */
	public ManageBookmarkResponse() {
		super();
	}

	/**
	 * @return the errorList
	 */
	public Set<ErrorList> getErrorList() {
		return errorList;
	}

	/**
	 * @param errorList the errorList to set
	 */
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
		return "ManageBookmarkResponse [errorList=" + errorList + "]";
	}
}