/**
 * ReportSharedTextSearchRequestBodyType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.SearchV1Service_wsdl;

public class ReportSharedTextSearchRequestBodyType  implements java.io.Serializable {
    private java.lang.String applicationID;

    private java.lang.String locale;

    private java.lang.String password;

    private java.lang.String searchText;

    private java.lang.String username;

    private java.math.BigInteger searchResultCount;

    private java.lang.String searchTriggerType;

    private java.lang.String searchData;

    private java.lang.String searchContextual;

    private java.lang.String siteName;

    public ReportSharedTextSearchRequestBodyType() {
    }

    public ReportSharedTextSearchRequestBodyType(
           java.lang.String applicationID,
           java.lang.String locale,
           java.lang.String password,
           java.lang.String searchText,
           java.lang.String username,
           java.math.BigInteger searchResultCount,
           java.lang.String searchTriggerType,
           java.lang.String searchData,
           java.lang.String searchContextual,
           java.lang.String siteName) {
           this.applicationID = applicationID;
           this.locale = locale;
           this.password = password;
           this.searchText = searchText;
           this.username = username;
           this.searchResultCount = searchResultCount;
           this.searchTriggerType = searchTriggerType;
           this.searchData = searchData;
           this.searchContextual = searchContextual;
           this.siteName = siteName;
    }


    /**
     * Gets the applicationID value for this ReportSharedTextSearchRequestBodyType.
     * 
     * @return applicationID
     */
    public java.lang.String getApplicationID() {
        return applicationID;
    }


    /**
     * Sets the applicationID value for this ReportSharedTextSearchRequestBodyType.
     * 
     * @param applicationID
     */
    public void setApplicationID(java.lang.String applicationID) {
        this.applicationID = applicationID;
    }


    /**
     * Gets the locale value for this ReportSharedTextSearchRequestBodyType.
     * 
     * @return locale
     */
    public java.lang.String getLocale() {
        return locale;
    }


    /**
     * Sets the locale value for this ReportSharedTextSearchRequestBodyType.
     * 
     * @param locale
     */
    public void setLocale(java.lang.String locale) {
        this.locale = locale;
    }


    /**
     * Gets the password value for this ReportSharedTextSearchRequestBodyType.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this ReportSharedTextSearchRequestBodyType.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the searchText value for this ReportSharedTextSearchRequestBodyType.
     * 
     * @return searchText
     */
    public java.lang.String getSearchText() {
        return searchText;
    }


    /**
     * Sets the searchText value for this ReportSharedTextSearchRequestBodyType.
     * 
     * @param searchText
     */
    public void setSearchText(java.lang.String searchText) {
        this.searchText = searchText;
    }


    /**
     * Gets the username value for this ReportSharedTextSearchRequestBodyType.
     * 
     * @return username
     */
    public java.lang.String getUsername() {
        return username;
    }


    /**
     * Sets the username value for this ReportSharedTextSearchRequestBodyType.
     * 
     * @param username
     */
    public void setUsername(java.lang.String username) {
        this.username = username;
    }


    /**
     * Gets the searchResultCount value for this ReportSharedTextSearchRequestBodyType.
     * 
     * @return searchResultCount
     */
    public java.math.BigInteger getSearchResultCount() {
        return searchResultCount;
    }


    /**
     * Sets the searchResultCount value for this ReportSharedTextSearchRequestBodyType.
     * 
     * @param searchResultCount
     */
    public void setSearchResultCount(java.math.BigInteger searchResultCount) {
        this.searchResultCount = searchResultCount;
    }


    /**
     * Gets the searchTriggerType value for this ReportSharedTextSearchRequestBodyType.
     * 
     * @return searchTriggerType
     */
    public java.lang.String getSearchTriggerType() {
        return searchTriggerType;
    }


    /**
     * Sets the searchTriggerType value for this ReportSharedTextSearchRequestBodyType.
     * 
     * @param searchTriggerType
     */
    public void setSearchTriggerType(java.lang.String searchTriggerType) {
        this.searchTriggerType = searchTriggerType;
    }


    /**
     * Gets the searchData value for this ReportSharedTextSearchRequestBodyType.
     * 
     * @return searchData
     */
    public java.lang.String getSearchData() {
        return searchData;
    }


    /**
     * Sets the searchData value for this ReportSharedTextSearchRequestBodyType.
     * 
     * @param searchData
     */
    public void setSearchData(java.lang.String searchData) {
        this.searchData = searchData;
    }


    /**
     * Gets the searchContextual value for this ReportSharedTextSearchRequestBodyType.
     * 
     * @return searchContextual
     */
    public java.lang.String getSearchContextual() {
        return searchContextual;
    }


    /**
     * Sets the searchContextual value for this ReportSharedTextSearchRequestBodyType.
     * 
     * @param searchContextual
     */
    public void setSearchContextual(java.lang.String searchContextual) {
        this.searchContextual = searchContextual;
    }


    /**
     * Gets the siteName value for this ReportSharedTextSearchRequestBodyType.
     * 
     * @return siteName
     */
    public java.lang.String getSiteName() {
        return siteName;
    }


    /**
     * Sets the siteName value for this ReportSharedTextSearchRequestBodyType.
     * 
     * @param siteName
     */
    public void setSiteName(java.lang.String siteName) {
        this.siteName = siteName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReportSharedTextSearchRequestBodyType)) return false;
        ReportSharedTextSearchRequestBodyType other = (ReportSharedTextSearchRequestBodyType) obj;
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
            ((this.locale==null && other.getLocale()==null) || 
             (this.locale!=null &&
              this.locale.equals(other.getLocale()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.searchText==null && other.getSearchText()==null) || 
             (this.searchText!=null &&
              this.searchText.equals(other.getSearchText()))) &&
            ((this.username==null && other.getUsername()==null) || 
             (this.username!=null &&
              this.username.equals(other.getUsername()))) &&
            ((this.searchResultCount==null && other.getSearchResultCount()==null) || 
             (this.searchResultCount!=null &&
              this.searchResultCount.equals(other.getSearchResultCount()))) &&
            ((this.searchTriggerType==null && other.getSearchTriggerType()==null) || 
             (this.searchTriggerType!=null &&
              this.searchTriggerType.equals(other.getSearchTriggerType()))) &&
            ((this.searchData==null && other.getSearchData()==null) || 
             (this.searchData!=null &&
              this.searchData.equals(other.getSearchData()))) &&
            ((this.searchContextual==null && other.getSearchContextual()==null) || 
             (this.searchContextual!=null &&
              this.searchContextual.equals(other.getSearchContextual()))) &&
            ((this.siteName==null && other.getSiteName()==null) || 
             (this.siteName!=null &&
              this.siteName.equals(other.getSiteName())));
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
        if (getLocale() != null) {
            _hashCode += getLocale().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getSearchText() != null) {
            _hashCode += getSearchText().hashCode();
        }
        if (getUsername() != null) {
            _hashCode += getUsername().hashCode();
        }
        if (getSearchResultCount() != null) {
            _hashCode += getSearchResultCount().hashCode();
        }
        if (getSearchTriggerType() != null) {
            _hashCode += getSearchTriggerType().hashCode();
        }
        if (getSearchData() != null) {
            _hashCode += getSearchData().hashCode();
        }
        if (getSearchContextual() != null) {
            _hashCode += getSearchContextual().hashCode();
        }
        if (getSiteName() != null) {
            _hashCode += getSiteName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReportSharedTextSearchRequestBodyType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "ReportSharedTextSearchRequestBodyType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("locale");
        elemField.setXmlName(new javax.xml.namespace.QName("", "locale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("", "password"));
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
        elemField.setFieldName("username");
        elemField.setXmlName(new javax.xml.namespace.QName("", "username"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("searchResultCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "searchResultCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("searchTriggerType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "searchTriggerType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("searchData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "searchData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("searchContextual");
        elemField.setXmlName(new javax.xml.namespace.QName("", "searchContextual"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("siteName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "siteName"));
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
