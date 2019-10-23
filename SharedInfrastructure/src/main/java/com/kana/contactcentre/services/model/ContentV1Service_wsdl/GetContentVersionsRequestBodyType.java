/**
 * GetContentVersionsRequestBodyType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.ContentV1Service_wsdl;

public class GetContentVersionsRequestBodyType  implements java.io.Serializable {
    private java.lang.String applicationID;

    private java.lang.String locale;

    private java.lang.String username;

    private java.lang.String contentID;

    private java.math.BigInteger startRow;

    private java.math.BigInteger pageSize;

    private boolean sortAscending;

    private boolean showMinorVersions;

    public GetContentVersionsRequestBodyType() {
    }

    public GetContentVersionsRequestBodyType(
           java.lang.String applicationID,
           java.lang.String locale,
           java.lang.String username,
           java.lang.String contentID,
           java.math.BigInteger startRow,
           java.math.BigInteger pageSize,
           boolean sortAscending,
           boolean showMinorVersions) {
           this.applicationID = applicationID;
           this.locale = locale;
           this.username = username;
           this.contentID = contentID;
           this.startRow = startRow;
           this.pageSize = pageSize;
           this.sortAscending = sortAscending;
           this.showMinorVersions = showMinorVersions;
    }


    /**
     * Gets the applicationID value for this GetContentVersionsRequestBodyType.
     * 
     * @return applicationID
     */
    public java.lang.String getApplicationID() {
        return applicationID;
    }


    /**
     * Sets the applicationID value for this GetContentVersionsRequestBodyType.
     * 
     * @param applicationID
     */
    public void setApplicationID(java.lang.String applicationID) {
        this.applicationID = applicationID;
    }


    /**
     * Gets the locale value for this GetContentVersionsRequestBodyType.
     * 
     * @return locale
     */
    public java.lang.String getLocale() {
        return locale;
    }


    /**
     * Sets the locale value for this GetContentVersionsRequestBodyType.
     * 
     * @param locale
     */
    public void setLocale(java.lang.String locale) {
        this.locale = locale;
    }


    /**
     * Gets the username value for this GetContentVersionsRequestBodyType.
     * 
     * @return username
     */
    public java.lang.String getUsername() {
        return username;
    }


    /**
     * Sets the username value for this GetContentVersionsRequestBodyType.
     * 
     * @param username
     */
    public void setUsername(java.lang.String username) {
        this.username = username;
    }


    /**
     * Gets the contentID value for this GetContentVersionsRequestBodyType.
     * 
     * @return contentID
     */
    public java.lang.String getContentID() {
        return contentID;
    }


    /**
     * Sets the contentID value for this GetContentVersionsRequestBodyType.
     * 
     * @param contentID
     */
    public void setContentID(java.lang.String contentID) {
        this.contentID = contentID;
    }


    /**
     * Gets the startRow value for this GetContentVersionsRequestBodyType.
     * 
     * @return startRow
     */
    public java.math.BigInteger getStartRow() {
        return startRow;
    }


    /**
     * Sets the startRow value for this GetContentVersionsRequestBodyType.
     * 
     * @param startRow
     */
    public void setStartRow(java.math.BigInteger startRow) {
        this.startRow = startRow;
    }


    /**
     * Gets the pageSize value for this GetContentVersionsRequestBodyType.
     * 
     * @return pageSize
     */
    public java.math.BigInteger getPageSize() {
        return pageSize;
    }


    /**
     * Sets the pageSize value for this GetContentVersionsRequestBodyType.
     * 
     * @param pageSize
     */
    public void setPageSize(java.math.BigInteger pageSize) {
        this.pageSize = pageSize;
    }


    /**
     * Gets the sortAscending value for this GetContentVersionsRequestBodyType.
     * 
     * @return sortAscending
     */
    public boolean isSortAscending() {
        return sortAscending;
    }


    /**
     * Sets the sortAscending value for this GetContentVersionsRequestBodyType.
     * 
     * @param sortAscending
     */
    public void setSortAscending(boolean sortAscending) {
        this.sortAscending = sortAscending;
    }


    /**
     * Gets the showMinorVersions value for this GetContentVersionsRequestBodyType.
     * 
     * @return showMinorVersions
     */
    public boolean isShowMinorVersions() {
        return showMinorVersions;
    }


    /**
     * Sets the showMinorVersions value for this GetContentVersionsRequestBodyType.
     * 
     * @param showMinorVersions
     */
    public void setShowMinorVersions(boolean showMinorVersions) {
        this.showMinorVersions = showMinorVersions;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetContentVersionsRequestBodyType)) return false;
        GetContentVersionsRequestBodyType other = (GetContentVersionsRequestBodyType) obj;
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
            ((this.username==null && other.getUsername()==null) || 
             (this.username!=null &&
              this.username.equals(other.getUsername()))) &&
            ((this.contentID==null && other.getContentID()==null) || 
             (this.contentID!=null &&
              this.contentID.equals(other.getContentID()))) &&
            ((this.startRow==null && other.getStartRow()==null) || 
             (this.startRow!=null &&
              this.startRow.equals(other.getStartRow()))) &&
            ((this.pageSize==null && other.getPageSize()==null) || 
             (this.pageSize!=null &&
              this.pageSize.equals(other.getPageSize()))) &&
            this.sortAscending == other.isSortAscending() &&
            this.showMinorVersions == other.isShowMinorVersions();
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
        if (getUsername() != null) {
            _hashCode += getUsername().hashCode();
        }
        if (getContentID() != null) {
            _hashCode += getContentID().hashCode();
        }
        if (getStartRow() != null) {
            _hashCode += getStartRow().hashCode();
        }
        if (getPageSize() != null) {
            _hashCode += getPageSize().hashCode();
        }
        _hashCode += (isSortAscending() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isShowMinorVersions() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetContentVersionsRequestBodyType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "GetContentVersionsRequestBodyType"));
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
        elemField.setFieldName("username");
        elemField.setXmlName(new javax.xml.namespace.QName("", "username"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contentID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contentID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startRow");
        elemField.setXmlName(new javax.xml.namespace.QName("", "startRow"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pageSize");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pageSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sortAscending");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sortAscending"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("showMinorVersions");
        elemField.setXmlName(new javax.xml.namespace.QName("", "showMinorVersions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
