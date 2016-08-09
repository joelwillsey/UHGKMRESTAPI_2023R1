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
@XmlRootElement(name = "SearchRequest")
@XmlAccessorType(XmlAccessType.NONE)
public class TagSetRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@XmlElement(nillable=true)
	private String username = "";

	/**
	 * Constructor
	 */
	public TagSetRequest() {
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TagSetRequest [username=" + username + "]";
	}
}