/**
 * 
 */
package com.verint.services.km.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author jmiller
 *
 */
@XmlRootElement(name = "AlertsResponse")
@XmlAccessorType(XmlAccessType.NONE)
public class AlertsResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement
	//private Set<CrossTag> readAlerts = new HashSet<CrossTag>();
	private ArrayList readAlerts = new ArrayList();
	/**
	 * Constructor 
	 */
	public AlertsResponse() {
		super();
	}

	/**
	 * @return the readAlerts
	 */
	public ArrayList getReadAlerts() {
		return readAlerts;
	}

	/**
	 * @param readAlerts the readAlerts to set
	 */
	public void setReadAlerts(ArrayList readAlerts) {
		this.readAlerts = readAlerts;
	}

	/**
	 * 
	 * @param readAlert
	 */

	public void addReadAlert(ReadAlert readAlert) {
		this.readAlerts.add(readAlert);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AlertsResponse [readAlerts=" + readAlerts + "]";
	}
}