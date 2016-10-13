package com.verint.services.km.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author eraygorodetskiy
 *
 */
@XmlRootElement(name="MigratableReferenceId")
@XmlAccessorType(XmlAccessType.NONE)
public class MigratableReferenceId implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@XmlElement(nillable=true)
	private String migratableReferenceId = "";
	
	/**
	 * Constructor 
	 */
	public MigratableReferenceId() {
		super();
	}
	
	/**
	 * @return the migratableReferenceId
	 */
	public String getMigratableReferenceId() {
		return migratableReferenceId;
	}

	/**
	 * @param migratableReferenceId the migratableReferenceId to set
	 */
	public void setMigratableReferenceId(String migratableReferenceId) {
		this.migratableReferenceId = migratableReferenceId;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Tag [migratableReferenceId=" + migratableReferenceId + "]\n";
	}

}
