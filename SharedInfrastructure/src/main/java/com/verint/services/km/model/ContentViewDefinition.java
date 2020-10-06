package com.verint.services.km.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ContentViewDefinition")
@XmlAccessorType(XmlAccessType.NONE)
public class ContentViewDefinition {
	
	@XmlElement(nillable=true)
	private List<ViewFieldDefinition> viewFields = new ArrayList<>();
	
	public ContentViewDefinition() {
		super();
	}
	

	public List<ViewFieldDefinition> getViewFields() {
		return viewFields;
	}

	public void setViewFields(List<ViewFieldDefinition> viewFields) {
		this.viewFields = viewFields;
	}

	public void addViewField(ViewFieldDefinition viewField) {
		this.viewFields.add(viewField);
	}	
}
