/**
 * 
 */
package com.verint.services.km.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author jmiller
 *
 */
@XmlRootElement(name = "Tag")
@XmlAccessorType(XmlAccessType.NONE)
public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@XmlElement(nillable=true)
	private String parentTagName = "";
	
	@XmlElement(nillable=true)
	private String systemTagSetName = "";

	@XmlElement(nillable=true)
	private String systemTagSetDisplayName = "";

	@XmlElement(nillable=true)
	private String systemTagName = "";

	@XmlElement(nillable=true)
	private String systemTagDisplayName = "";

	@XmlElement(nillable=true)
	private String coverage = "";

	@XmlElement(nillable=true)
	private Boolean preselected = new Boolean(false);

	/**
	 * Constructor 
	 */
	public Tag() {
		super();
	}

	/**
	 * @return the parentTagName
	 */
	public String getParentTagName() {
		return parentTagName;
	}

	/**
	 * @param parentTagName the parentTagName to set
	 */
	public void setParentTagName(String parentTagName) {
		this.parentTagName = parentTagName;
	}

	/**
	 * @return the systemTagSetName
	 */
	public String getSystemTagSetName() {
		return systemTagSetName;
	}

	/**
	 * @param systemTagSetName the systemTagSetName to set
	 */
	public void setSystemTagSetName(String systemTagSetName) {
		this.systemTagSetName = systemTagSetName;
	}

	/**
	 * @return the systemTagSetDisplayName
	 */
	public String getSystemTagSetDisplayName() {
		return systemTagSetDisplayName;
	}

	/**
	 * @param systemTagSetDisplayName the systemTagSetDisplayName to set
	 */
	public void setSystemTagSetDisplayName(String systemTagSetDisplayName) {
		this.systemTagSetDisplayName = systemTagSetDisplayName;
	}

	/**
	 * @return the systemTagName
	 */
	public String getSystemTagName() {
		return systemTagName;
	}

	/**
	 * @param systemTagName the systemTagName to set
	 */
	public void setSystemTagName(String systemTagName) {
		this.systemTagName = systemTagName;
	}

	/**
	 * @return the systemTagDisplayName
	 */
	public String getSystemTagDisplayName() {
		return systemTagDisplayName;
	}

	/**
	 * @param systemTagDisplayName the systemTagDisplayName to set
	 */
	public void setSystemTagDisplayName(String systemTagDisplayName) {
		this.systemTagDisplayName = systemTagDisplayName;
	}

	public String getCoverage() {
		return coverage;
	}

	public void setCoverage(String coverage) {
		this.coverage = coverage;
	}

	/**
	 * @return the preselected
	 */
	public Boolean getPreselected() {
		return preselected;
	}

	/**
	 * @param preselected the preselected to set
	 */
	public void setPreselected(Boolean preselected) {
		this.preselected = preselected;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Tag [parentTagName=" + parentTagName + ", systemTagSetName=" + systemTagSetName
				+ ", systemTagSetDisplayName=" + systemTagSetDisplayName + ", systemTagName=" + systemTagName
				+ ", systemTagDisplayName=" + systemTagDisplayName + ", preselected=" + preselected + "]\n";
	}
}
