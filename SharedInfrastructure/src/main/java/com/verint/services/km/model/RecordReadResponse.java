/**
 * 
 */
package com.verint.services.km.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author jmiller
 *
 */
@XmlRootElement(name = "AlertsResponse")
@XmlAccessorType(XmlAccessType.NONE)
public class RecordReadResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement
	private Boolean didComplete;
	
	public Boolean getdidComplete(){
		return didComplete;
	}
	public void setdidComplete(Boolean d){
		this.didComplete = d;
	}
	
	@Override
	public String toString() {
		return "RecordReadResponse [didComplete=" + didComplete + "]";
	}
}