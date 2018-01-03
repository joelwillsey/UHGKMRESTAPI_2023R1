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
@XmlRootElement(name = "AlertsRequest")
@XmlAccessorType(XmlAccessType.NONE)
public class AlertsRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
//I don't think this actually gets used anywhere, we're pinging the DB directly as in the CrossTags service
	 
	@Override
	public String toString() {
		return "AlertsRequest [no parameters at present]";
	}
}