package com.verint.services.km.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author ERaygorodetskiy
 *
 */
public class ManifestResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@XmlElement(nillable=true)
	private String versionNumber = "";

	/**
	 * 
	 */
	public ManifestResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return
	 */
	public String getVersionNumber() {
		return versionNumber;
	}

	/**
	 * 
	 * @param versionNumber
	 */
	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "ManifestResponse [versionNumber=" + versionNumber + "]";
	}
}
