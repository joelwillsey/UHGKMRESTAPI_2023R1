package com.verint.services.km.model;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ViewFieldDefinition")
@XmlAccessorType(XmlAccessType.NONE)
public class ViewFieldDefinition {

	@XmlElement(nillable=true)
    private String fieldType;
	
	@XmlElement(nillable=true)
    private BigInteger viewSequence = new BigInteger("0");;
	
	@XmlElement(nillable=true)
    private Boolean showLabel = new Boolean(false);
	
	@XmlElement(nillable=true)
    private String fieldDisplayName;
	
	@XmlElement(nillable=true)
    private String fieldName;

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public BigInteger getViewSequence() {
		return viewSequence;
	}

	public void setViewSequence(BigInteger viewSequence) {
		this.viewSequence = viewSequence;
	}

	public Boolean getShowLabel() {
		return showLabel;
	}

	public void setShowLabel(Boolean showLabel) {
		this.showLabel = showLabel;
	}

	public String getFieldDisplayName() {
		return fieldDisplayName;
	}

	public void setFieldDisplayName(String fieldDisplayName) {
		this.fieldDisplayName = fieldDisplayName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
}
