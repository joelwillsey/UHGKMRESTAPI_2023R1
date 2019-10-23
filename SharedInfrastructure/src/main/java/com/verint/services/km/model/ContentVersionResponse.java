/**
 * 
 */
package com.verint.services.km.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.kana.contactcentre.services.model.ContentV1Service_wsdl.ErrorMessage;
import com.kana.contactcentre.services.model.ContentV1Service_wsdl.ContentVersionsResultSet;

import com.verint.services.km.util.TagSetComp;

/**
 * @author jmiller
 *
 */
@XmlRootElement(name = "ContentVeresionResponse")
@XmlAccessorType(XmlAccessType.NONE)
public class ContentVersionResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@XmlElement(nillable=true)
	private com.kana.contactcentre.services.model.ContentV1Service_wsdl.ContentVersionsResultSet resultSet;
	@XmlElement(nillable=true)
	private com.kana.contactcentre.services.model.ContentV1Service_wsdl.ErrorMessage[] errorList;
	 
	public ContentVersionResponse() {
		super();
	}

	   public void setErrorList(com.kana.contactcentre.services.model.ContentV1Service_wsdl.ErrorMessage[] errorList) {
	        this.errorList = errorList;
	    }
	   
	   public com.kana.contactcentre.services.model.ContentV1Service_wsdl.ErrorMessage[] getErrorList() {
	        return this.errorList;
	    }
		
	public void setResultSet(com.kana.contactcentre.services.model.ContentV1Service_wsdl.ContentVersionsResultSet resultSet){
		this.resultSet = resultSet;
	}
	public com.kana.contactcentre.services.model.ContentV1Service_wsdl.ContentVersionsResultSet getResultSet(){
		return this.resultSet;
	}


	@Override
	public String toString() {
		return "ContentVersionResponse [TODO ]";
	}
}