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
@XmlRootElement(name = "LoginRequest")
@XmlAccessorType(XmlAccessType.NONE)
public class LoginV2Request implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement(nillable=true)
	private String username;

	@XmlElement(nillable=true)
	private String password;
	
	@XmlElement(nillable=true)
	private String firstName;
	
	@XmlElement(nillable=true)
	private String lastName;
	
	@XmlElement(nillable=true)
	private String vemGroups;

	/**
	 * Constructor
	 */
	public LoginV2Request() {
		super();
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
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getVemGroups() {
		return vemGroups;
	}

	public void setVemGroups(String vemGroups) {
		this.vemGroups = vemGroups;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LoginRequest [username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + ", vemGroups=" + vemGroups + "]";
	}
}