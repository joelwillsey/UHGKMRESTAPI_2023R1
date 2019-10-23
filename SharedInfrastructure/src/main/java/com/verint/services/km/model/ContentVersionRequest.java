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
@XmlRootElement(name = "ContentVersionRequest")
@XmlAccessorType(XmlAccessType.NONE)
public class ContentVersionRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement
	private String contentId;

	@XmlElement(nillable=true)
	private String username = "";

	@XmlElement(nillable=true)
	private String password = "";
	
	@XmlElement
	private int startRow;
	
	@XmlElement
	private int pageSize;
	
	@XmlElement
	private boolean sortAscending = true;
	
	@XmlElement
	private boolean showMinorVersions = true;
	

	/**
	 * Constructor
	 */
	public ContentVersionRequest() {
		super();
	}

	/**
	 * @return the contentId
	 */
	public int getStartRow(){
		return startRow;
	}
	
	public void setStartRow(int startRow){
		this.startRow = startRow;
	}

	public int getPageSize(){
		return pageSize;
	}
	
	public void setPageSize(int pageSize){
		this.pageSize = pageSize;
	}
	
	public String getContentId() {
		return contentId;
	}

	public void setSortAscending(boolean sortAscending){
		this.sortAscending = sortAscending;
	}
	
	public boolean getSortAscending() {
		return sortAscending;
	}	
	
	public void setShowMinorVersions(boolean showMinorVersions){
		this.showMinorVersions = showMinorVersions;
	}
	
	public boolean getShowMinorVersions() {
		return showMinorVersions;
	}	
	
	/**
	 * @param contentId the contentId to set
	 */
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return the password
	 */
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ContentVersionRequest [contentId=" + contentId + ", username=" + username + ", password=" + password +  "]";
	}
}