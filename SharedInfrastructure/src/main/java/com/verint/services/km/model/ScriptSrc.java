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
 * @author pseifert
 *
 */
@XmlRootElement(name = "ScriptSrc")
@XmlAccessorType(XmlAccessType.NONE)
public class ScriptSrc implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@XmlElement(nillable=true)
	private String src = "";
	
	@XmlElement(nillable=true)
	private String async = "";
	
	@XmlElement(nillable=true)
	private String type = "";
	
	/**
	 * @return the id
	 */
	public String getSrc() {
		return src;
	}

	/**
	 * @param id the id to set
	 */
	public void setSrc(String src) {
		this.src = src;
	}

	/**
	 * @return the locale
	 */
	public String getAsync() {
		return async;
	}

	/**
	 * @param locale the locale to set
	 */
	public void setAsync(String async) {
		this.async = async;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ScriptSrc [src=" + src + ", aysnc=" + async + ", type=" + type + "]";
	}
}
