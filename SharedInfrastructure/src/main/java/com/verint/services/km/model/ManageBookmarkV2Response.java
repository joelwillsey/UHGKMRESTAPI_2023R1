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

	//@XmlElement(nillable=true)
	//private Set<ErrorList> errorList = new HashSet<ErrorList>();	
	@XmlElement
	private boolean toReturn;
	/**
	 * 
	 */
	public ManageBookmarkV2Response() {
		super();
	}
public boolean getToReturn(){
	return this.toReturn;
	
}
public void setToReturn(boolean t){
	this.toReturn = t;
}
	/**
	 * @return the errorList
	 */
/*
public Set<ErrorList> getErrorList() {
		return errorList;

	public void setErrorList(Set<ErrorList> errorList) {
		this.errorList = errorList;
	}
	*/
	

	/**
	 * @param errorList the errorList to set
	 */
	//public void addErrorList(ErrorList error) {
	//	errorList.add(error);
	//}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ManageBookmarkV2Response [toReturn=" + toReturn + "]";
	}
}