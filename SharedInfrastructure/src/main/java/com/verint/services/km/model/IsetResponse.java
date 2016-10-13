package com.verint.services.km.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IsetResponse")
@XmlAccessorType(XmlAccessType.NONE)
public class IsetResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@XmlElement
	private Set<MigratableReferenceId> migratableReferenceId = new HashSet<MigratableReferenceId>();
	
	/**
	 * Constructor
	 */
	public IsetResponse(){
		super();
	}
	
	/*
	 * @return the migratable reference id
	 */
	public Set<MigratableReferenceId> getMigratableReferenceId() {
		return migratableReferenceId;
	}
	
	/*
	 * @param migratable reference id to set the migratable reference id
	 */
	public void setMigratableReferenceId(Set<MigratableReferenceId> migratableReferenceId) {
		this.migratableReferenceId = migratableReferenceId;
	}
	
	/*
	 * @param migratable reference id
	 */
	public void addMigratableReferenceId(MigratableReferenceId migratableReferenceId){
		this.migratableReferenceId.add(migratableReferenceId);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MigratableReferenceId [migratableReferenceId=" + migratableReferenceId + "]";
	}
}
