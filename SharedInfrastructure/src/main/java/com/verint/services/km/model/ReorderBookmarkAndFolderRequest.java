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
@XmlRootElement(name = "ReorderBookmarkAndFolderRequest")
@XmlAccessorType(XmlAccessType.NONE)
public class ReorderBookmarkAndFolderRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement(nillable=true)
	private String contentID="";
	
	@XmlElement(nillable=true)
	private String userAction = "";

	@XmlElement(nillable=true)
	private String userName = "";

	@XmlElement(nillable=true)
	private String password = "";

	@XmlElement(nillable=true)
	private String destinationFolderID="";
	
	@XmlElement(nillable=true)
	private String folderID="";
	
	@XmlElement
	private String numMoved="";
	
	@XmlElement(nillable=true)
	private String direction = "";
	
	@XmlElement(nillable=true)
	private String localeName="";
	
	@XmlElement
	private String applicationID="";
	
	@XmlElement(nillable=true)
	private String folderName="";
	/**
	 * Constructor
	 */
	public ReorderBookmarkAndFolderRequest() {
		super();
	}

	/**
	 * @return the contentId
	 */
	public String getContentID() {
		return contentID;
	}
	
	
	/**
	 * @param contentId the contentId to set
	 */
	public void setContentID(String contentId) {
		this.contentID = contentId;
	}
	
	//return the folder ID
	public String getFolderID() {
		return folderID;
		
	}
	
	public void setFolderID(String fol){
		this.folderID = fol;
	}

	public String getDestinationFolderID() {
		return destinationFolderID;
		
	}
	
	public void setDestinationFolderID(String fol){
		this.destinationFolderID = fol;
	}

	
	public String getLocaleName(){
		return this.localeName;
	}
	
	public void setLocaleName(String ln){
		this.localeName=ln;
	}
	
	public String getApplicationID(){
		return applicationID;
	}
	
	public void setApplicationID(String app){
		this.applicationID = app;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getUserAction() {
		return userAction;
	}

	public String getDirection() {
		return direction;
	}
	
	public void setDirection(String dir){
		this.direction = dir;
	}
	
	public String getNumMoved(){
		return numMoved;
	}
	public void setNumMoved(String num){
		this.numMoved = num;
	}
	
	
	
	/**
	 * 
	 * @param userAction
	 */
	public void setUserAction(String userA) {
		this.userAction = userA;
	}

	/**
	 * @return the username
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param username the username to set
	 */
	public void setUserName(String username) {
		this.userName = username;
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
	public void setPassword(String pw) {
		this.password = pw;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ReorderBookmarkAndFolderRequest [contentId=" + contentID + ", userAction=" + userAction + ", username=" + userName + ", password=" + password +", folderId=" + folderID
				+ ", destinationFolderID=" + destinationFolderID + ", direction="+ direction + ", numMoved=" + numMoved + ", localeName="+localeName+"]";
	}
}