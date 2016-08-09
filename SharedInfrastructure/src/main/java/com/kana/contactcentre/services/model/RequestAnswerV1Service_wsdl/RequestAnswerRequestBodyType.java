/**
 * RequestAnswerRequestBodyType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.RequestAnswerV1Service_wsdl;

public class RequestAnswerRequestBodyType  implements java.io.Serializable {
    private java.lang.String keyword;

    private java.lang.String expectation;

    private java.lang.String selectedFilter;

    private java.util.Date searchDate;

    private java.lang.String username;

    private java.lang.String password;

    private java.lang.String locale;

    public RequestAnswerRequestBodyType() {
    }

    public RequestAnswerRequestBodyType(
           java.lang.String keyword,
           java.lang.String expectation,
           java.lang.String selectedFilter,
           java.util.Date searchDate,
           java.lang.String username,
           java.lang.String password,
           java.lang.String locale) {
           this.keyword = keyword;
           this.expectation = expectation;
           this.selectedFilter = selectedFilter;
           this.searchDate = searchDate;
           this.username = username;
           this.password = password;
           this.locale = locale;
    }


    /**
     * Gets the keyword value for this RequestAnswerRequestBodyType.
     * 
     * @return keyword
     */
    public java.lang.String getKeyword() {
        return keyword;
    }


    /**
     * Sets the keyword value for this RequestAnswerRequestBodyType.
     * 
     * @param keyword
     */
    public void setKeyword(java.lang.String keyword) {
        this.keyword = keyword;
    }


    /**
     * Gets the expectation value for this RequestAnswerRequestBodyType.
     * 
     * @return expectation
     */
    public java.lang.String getExpectation() {
        return expectation;
    }


    /**
     * Sets the expectation value for this RequestAnswerRequestBodyType.
     * 
     * @param expectation
     */
    public void setExpectation(java.lang.String expectation) {
        this.expectation = expectation;
    }


    /**
     * Gets the selectedFilter value for this RequestAnswerRequestBodyType.
     * 
     * @return selectedFilter
     */
    public java.lang.String getSelectedFilter() {
        return selectedFilter;
    }


    /**
     * Sets the selectedFilter value for this RequestAnswerRequestBodyType.
     * 
     * @param selectedFilter
     */
    public void setSelectedFilter(java.lang.String selectedFilter) {
        this.selectedFilter = selectedFilter;
    }


    /**
     * Gets the searchDate value for this RequestAnswerRequestBodyType.
     * 
     * @return searchDate
     */
    public java.util.Date getSearchDate() {
        return searchDate;
    }


    /**
     * Sets the searchDate value for this RequestAnswerRequestBodyType.
     * 
     * @param searchDate
     */
    public void setSearchDate(java.util.Date searchDate) {
        this.searchDate = searchDate;
    }


    /**
     * Gets the username value for this RequestAnswerRequestBodyType.
     * 
     * @return username
     */
    public java.lang.String getUsername() {
        return username;
    }


    /**
     * Sets the username value for this RequestAnswerRequestBodyType.
     * 
     * @param username
     */
    public void setUsername(java.lang.String username) {
        this.username = username;
    }


    /**
     * Gets the password value for this RequestAnswerRequestBodyType.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this RequestAnswerRequestBodyType.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the locale value for this RequestAnswerRequestBodyType.
     * 
     * @return locale
     */
    public java.lang.String getLocale() {
        return locale;
    }


    /**
     * Sets the locale value for this RequestAnswerRequestBodyType.
     * 
     * @param locale
     */
    public void setLocale(java.lang.String locale) {
        this.locale = locale;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RequestAnswerRequestBodyType)) return false;
        RequestAnswerRequestBodyType other = (RequestAnswerRequestBodyType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.keyword==null && other.getKeyword()==null) || 
             (this.keyword!=null &&
              this.keyword.equals(other.getKeyword()))) &&
            ((this.expectation==null && other.getExpectation()==null) || 
             (this.expectation!=null &&
              this.expectation.equals(other.getExpectation()))) &&
            ((this.selectedFilter==null && other.getSelectedFilter()==null) || 
             (this.selectedFilter!=null &&
              this.selectedFilter.equals(other.getSelectedFilter()))) &&
            ((this.searchDate==null && other.getSearchDate()==null) || 
             (this.searchDate!=null &&
              this.searchDate.equals(other.getSearchDate()))) &&
            ((this.username==null && other.getUsername()==null) || 
             (this.username!=null &&
              this.username.equals(other.getUsername()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.locale==null && other.getLocale()==null) || 
             (this.locale!=null &&
              this.locale.equals(other.getLocale())));
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
        if (getKeyword() != null) {
            _hashCode += getKeyword().hashCode();
        }
        if (getExpectation() != null) {
            _hashCode += getExpectation().hashCode();
        }
        if (getSelectedFilter() != null) {
            _hashCode += getSelectedFilter().hashCode();
        }
        if (getSearchDate() != null) {
            _hashCode += getSearchDate().hashCode();
        }
        if (getUsername() != null) {
            _hashCode += getUsername().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getLocale() != null) {
            _hashCode += getLocale().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RequestAnswerRequestBodyType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/RequestAnswerV1Service.wsdl", "RequestAnswerRequestBodyType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("keyword");
        elemField.setXmlName(new javax.xml.namespace.QName("", "keyword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expectation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "expectation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("selectedFilter");
        elemField.setXmlName(new javax.xml.namespace.QName("", "selectedFilter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("searchDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "searchDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("username");
        elemField.setXmlName(new javax.xml.namespace.QName("", "username"));
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
        elemField.setFieldName("locale");
        elemField.setXmlName(new javax.xml.namespace.QName("", "locale"));
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
