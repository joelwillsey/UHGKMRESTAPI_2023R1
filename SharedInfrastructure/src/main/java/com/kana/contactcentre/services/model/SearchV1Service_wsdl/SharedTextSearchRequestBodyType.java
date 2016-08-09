/**
 * SharedTextSearchRequestBodyType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.SearchV1Service_wsdl;

public class SharedTextSearchRequestBodyType  implements java.io.Serializable {
    private java.lang.String applicationID;

    private java.lang.String contentCategory;

    private java.lang.String contentOwner;

    private com.kana.contactcentre.services.model.SearchV1Service_wsdl.ControlData controlData;

    private com.kana.contactcentre.services.model.SearchV1Service_wsdl.SearchDateType expirationDate;

    private java.lang.String locale;

    private com.kana.contactcentre.services.model.SearchV1Service_wsdl.SearchDateType modifiedDate;

    private java.lang.String password;

    private com.kana.contactcentre.services.model.SearchV1Service_wsdl.SearchDateType publishedDate;

    private java.lang.String publishedId;

    private java.lang.String searchText;

    private java.lang.String sortFieldName;

    private java.lang.String tags;

    private java.lang.String username;

    private java.lang.String workflowState;

    public SharedTextSearchRequestBodyType() {
    }

    public SharedTextSearchRequestBodyType(
           java.lang.String applicationID,
           java.lang.String contentCategory,
           java.lang.String contentOwner,
           com.kana.contactcentre.services.model.SearchV1Service_wsdl.ControlData controlData,
           com.kana.contactcentre.services.model.SearchV1Service_wsdl.SearchDateType expirationDate,
           java.lang.String locale,
           com.kana.contactcentre.services.model.SearchV1Service_wsdl.SearchDateType modifiedDate,
           java.lang.String password,
           com.kana.contactcentre.services.model.SearchV1Service_wsdl.SearchDateType publishedDate,
           java.lang.String publishedId,
           java.lang.String searchText,
           java.lang.String sortFieldName,
           java.lang.String tags,
           java.lang.String username,
           java.lang.String workflowState) {
           this.applicationID = applicationID;
           this.contentCategory = contentCategory;
           this.contentOwner = contentOwner;
           this.controlData = controlData;
           this.expirationDate = expirationDate;
           this.locale = locale;
           this.modifiedDate = modifiedDate;
           this.password = password;
           this.publishedDate = publishedDate;
           this.publishedId = publishedId;
           this.searchText = searchText;
           this.sortFieldName = sortFieldName;
           this.tags = tags;
           this.username = username;
           this.workflowState = workflowState;
    }


    /**
     * Gets the applicationID value for this SharedTextSearchRequestBodyType.
     * 
     * @return applicationID
     */
    public java.lang.String getApplicationID() {
        return applicationID;
    }


    /**
     * Sets the applicationID value for this SharedTextSearchRequestBodyType.
     * 
     * @param applicationID
     */
    public void setApplicationID(java.lang.String applicationID) {
        this.applicationID = applicationID;
    }


    /**
     * Gets the contentCategory value for this SharedTextSearchRequestBodyType.
     * 
     * @return contentCategory
     */
    public java.lang.String getContentCategory() {
        return contentCategory;
    }


    /**
     * Sets the contentCategory value for this SharedTextSearchRequestBodyType.
     * 
     * @param contentCategory
     */
    public void setContentCategory(java.lang.String contentCategory) {
        this.contentCategory = contentCategory;
    }


    /**
     * Gets the contentOwner value for this SharedTextSearchRequestBodyType.
     * 
     * @return contentOwner
     */
    public java.lang.String getContentOwner() {
        return contentOwner;
    }


    /**
     * Sets the contentOwner value for this SharedTextSearchRequestBodyType.
     * 
     * @param contentOwner
     */
    public void setContentOwner(java.lang.String contentOwner) {
        this.contentOwner = contentOwner;
    }


    /**
     * Gets the controlData value for this SharedTextSearchRequestBodyType.
     * 
     * @return controlData
     */
    public com.kana.contactcentre.services.model.SearchV1Service_wsdl.ControlData getControlData() {
        return controlData;
    }


    /**
     * Sets the controlData value for this SharedTextSearchRequestBodyType.
     * 
     * @param controlData
     */
    public void setControlData(com.kana.contactcentre.services.model.SearchV1Service_wsdl.ControlData controlData) {
        this.controlData = controlData;
    }


    /**
     * Gets the expirationDate value for this SharedTextSearchRequestBodyType.
     * 
     * @return expirationDate
     */
    public com.kana.contactcentre.services.model.SearchV1Service_wsdl.SearchDateType getExpirationDate() {
        return expirationDate;
    }


    /**
     * Sets the expirationDate value for this SharedTextSearchRequestBodyType.
     * 
     * @param expirationDate
     */
    public void setExpirationDate(com.kana.contactcentre.services.model.SearchV1Service_wsdl.SearchDateType expirationDate) {
        this.expirationDate = expirationDate;
    }


    /**
     * Gets the locale value for this SharedTextSearchRequestBodyType.
     * 
     * @return locale
     */
    public java.lang.String getLocale() {
        return locale;
    }


    /**
     * Sets the locale value for this SharedTextSearchRequestBodyType.
     * 
     * @param locale
     */
    public void setLocale(java.lang.String locale) {
        this.locale = locale;
    }


    /**
     * Gets the modifiedDate value for this SharedTextSearchRequestBodyType.
     * 
     * @return modifiedDate
     */
    public com.kana.contactcentre.services.model.SearchV1Service_wsdl.SearchDateType getModifiedDate() {
        return modifiedDate;
    }


    /**
     * Sets the modifiedDate value for this SharedTextSearchRequestBodyType.
     * 
     * @param modifiedDate
     */
    public void setModifiedDate(com.kana.contactcentre.services.model.SearchV1Service_wsdl.SearchDateType modifiedDate) {
        this.modifiedDate = modifiedDate;
    }


    /**
     * Gets the password value for this SharedTextSearchRequestBodyType.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this SharedTextSearchRequestBodyType.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the publishedDate value for this SharedTextSearchRequestBodyType.
     * 
     * @return publishedDate
     */
    public com.kana.contactcentre.services.model.SearchV1Service_wsdl.SearchDateType getPublishedDate() {
        return publishedDate;
    }


    /**
     * Sets the publishedDate value for this SharedTextSearchRequestBodyType.
     * 
     * @param publishedDate
     */
    public void setPublishedDate(com.kana.contactcentre.services.model.SearchV1Service_wsdl.SearchDateType publishedDate) {
        this.publishedDate = publishedDate;
    }


    /**
     * Gets the publishedId value for this SharedTextSearchRequestBodyType.
     * 
     * @return publishedId
     */
    public java.lang.String getPublishedId() {
        return publishedId;
    }


    /**
     * Sets the publishedId value for this SharedTextSearchRequestBodyType.
     * 
     * @param publishedId
     */
    public void setPublishedId(java.lang.String publishedId) {
        this.publishedId = publishedId;
    }


    /**
     * Gets the searchText value for this SharedTextSearchRequestBodyType.
     * 
     * @return searchText
     */
    public java.lang.String getSearchText() {
        return searchText;
    }


    /**
     * Sets the searchText value for this SharedTextSearchRequestBodyType.
     * 
     * @param searchText
     */
    public void setSearchText(java.lang.String searchText) {
        this.searchText = searchText;
    }


    /**
     * Gets the sortFieldName value for this SharedTextSearchRequestBodyType.
     * 
     * @return sortFieldName
     */
    public java.lang.String getSortFieldName() {
        return sortFieldName;
    }


    /**
     * Sets the sortFieldName value for this SharedTextSearchRequestBodyType.
     * 
     * @param sortFieldName
     */
    public void setSortFieldName(java.lang.String sortFieldName) {
        this.sortFieldName = sortFieldName;
    }


    /**
     * Gets the tags value for this SharedTextSearchRequestBodyType.
     * 
     * @return tags
     */
    public java.lang.String getTags() {
        return tags;
    }


    /**
     * Sets the tags value for this SharedTextSearchRequestBodyType.
     * 
     * @param tags
     */
    public void setTags(java.lang.String tags) {
        this.tags = tags;
    }


    /**
     * Gets the username value for this SharedTextSearchRequestBodyType.
     * 
     * @return username
     */
    public java.lang.String getUsername() {
        return username;
    }


    /**
     * Sets the username value for this SharedTextSearchRequestBodyType.
     * 
     * @param username
     */
    public void setUsername(java.lang.String username) {
        this.username = username;
    }


    /**
     * Gets the workflowState value for this SharedTextSearchRequestBodyType.
     * 
     * @return workflowState
     */
    public java.lang.String getWorkflowState() {
        return workflowState;
    }


    /**
     * Sets the workflowState value for this SharedTextSearchRequestBodyType.
     * 
     * @param workflowState
     */
    public void setWorkflowState(java.lang.String workflowState) {
        this.workflowState = workflowState;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SharedTextSearchRequestBodyType)) return false;
        SharedTextSearchRequestBodyType other = (SharedTextSearchRequestBodyType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.applicationID==null && other.getApplicationID()==null) || 
             (this.applicationID!=null &&
              this.applicationID.equals(other.getApplicationID()))) &&
            ((this.contentCategory==null && other.getContentCategory()==null) || 
             (this.contentCategory!=null &&
              this.contentCategory.equals(other.getContentCategory()))) &&
            ((this.contentOwner==null && other.getContentOwner()==null) || 
             (this.contentOwner!=null &&
              this.contentOwner.equals(other.getContentOwner()))) &&
            ((this.controlData==null && other.getControlData()==null) || 
             (this.controlData!=null &&
              this.controlData.equals(other.getControlData()))) &&
            ((this.expirationDate==null && other.getExpirationDate()==null) || 
             (this.expirationDate!=null &&
              this.expirationDate.equals(other.getExpirationDate()))) &&
            ((this.locale==null && other.getLocale()==null) || 
             (this.locale!=null &&
              this.locale.equals(other.getLocale()))) &&
            ((this.modifiedDate==null && other.getModifiedDate()==null) || 
             (this.modifiedDate!=null &&
              this.modifiedDate.equals(other.getModifiedDate()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.publishedDate==null && other.getPublishedDate()==null) || 
             (this.publishedDate!=null &&
              this.publishedDate.equals(other.getPublishedDate()))) &&
            ((this.publishedId==null && other.getPublishedId()==null) || 
             (this.publishedId!=null &&
              this.publishedId.equals(other.getPublishedId()))) &&
            ((this.searchText==null && other.getSearchText()==null) || 
             (this.searchText!=null &&
              this.searchText.equals(other.getSearchText()))) &&
            ((this.sortFieldName==null && other.getSortFieldName()==null) || 
             (this.sortFieldName!=null &&
              this.sortFieldName.equals(other.getSortFieldName()))) &&
            ((this.tags==null && other.getTags()==null) || 
             (this.tags!=null &&
              this.tags.equals(other.getTags()))) &&
            ((this.username==null && other.getUsername()==null) || 
             (this.username!=null &&
              this.username.equals(other.getUsername()))) &&
            ((this.workflowState==null && other.getWorkflowState()==null) || 
             (this.workflowState!=null &&
              this.workflowState.equals(other.getWorkflowState())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getApplicationID() != null) {
            _hashCode += getApplicationID().hashCode();
        }
        if (getContentCategory() != null) {
            _hashCode += getContentCategory().hashCode();
        }
        if (getContentOwner() != null) {
            _hashCode += getContentOwner().hashCode();
        }
        if (getControlData() != null) {
            _hashCode += getControlData().hashCode();
        }
        if (getExpirationDate() != null) {
            _hashCode += getExpirationDate().hashCode();
        }
        if (getLocale() != null) {
            _hashCode += getLocale().hashCode();
        }
        if (getModifiedDate() != null) {
            _hashCode += getModifiedDate().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getPublishedDate() != null) {
            _hashCode += getPublishedDate().hashCode();
        }
        if (getPublishedId() != null) {
            _hashCode += getPublishedId().hashCode();
        }
        if (getSearchText() != null) {
            _hashCode += getSearchText().hashCode();
        }
        if (getSortFieldName() != null) {
            _hashCode += getSortFieldName().hashCode();
        }
        if (getTags() != null) {
            _hashCode += getTags().hashCode();
        }
        if (getUsername() != null) {
            _hashCode += getUsername().hashCode();
        }
        if (getWorkflowState() != null) {
            _hashCode += getWorkflowState().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SharedTextSearchRequestBodyType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "SharedTextSearchRequestBodyType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contentCategory");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contentCategory"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contentOwner");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contentOwner"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("controlData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "controlData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "ControlData"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expirationDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "expirationDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "SearchDateType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("locale");
        elemField.setXmlName(new javax.xml.namespace.QName("", "locale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modifiedDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "modifiedDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "SearchDateType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("", "password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("publishedDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "publishedDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "SearchDateType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("publishedId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "publishedId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("searchText");
        elemField.setXmlName(new javax.xml.namespace.QName("", "searchText"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sortFieldName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sortFieldName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tags");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tags"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("username");
        elemField.setXmlName(new javax.xml.namespace.QName("", "username"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("workflowState");
        elemField.setXmlName(new javax.xml.namespace.QName("", "workflowState"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
