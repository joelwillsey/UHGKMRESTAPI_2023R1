package com.verint.services.km.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author PSeifert
 *
 */
@XmlRootElement(name = "HoverTextResponse")
@XmlAccessorType(XmlAccessType.NONE)
public class HoverTextResponse {
private static final long serialVersionUID = 1L;
		
	@XmlElement(nillable=true)
	private String errorTextString = "";
	
	@XmlElement(nillable=true)
	private String hoverTextString = "";
	
	/**
	 * 
	 */
	public HoverTextResponse() {
		super();
	}
	
	/**
	 * @return
	 */
	public String getErrorString(){
		return errorTextString;
	}
	
	/**
	 * @param Error String to set
	 */
	public void setErrorString(String errorTextString) {
		this.errorTextString = errorTextString;
	}
		
	
	/**
	 * @return
	 */
	public String getHoverTextString(){
		return hoverTextString;
	}
	
	/**
	 * @param property string 
	 */
	public void setHoverTextString(String hoverTextString){
		this.hoverTextString = hoverTextString;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return "HoverTextResponse [hoverTextString=" + hoverTextString + " errorString=" + errorTextString + "]";
	}
}

