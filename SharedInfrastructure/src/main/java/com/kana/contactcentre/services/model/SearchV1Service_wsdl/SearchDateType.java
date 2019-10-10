/**
 * SearchDateType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.SearchV1Service_wsdl;

public class SearchDateType  implements java.io.Serializable {
    private java.lang.String toDate;

    private java.lang.String fromDate;

    public SearchDateType() {
    }

    public SearchDateType(
           java.lang.String toDate,
           java.lang.String fromDate) {
           this.toDate = toDate;
           this.fromDate = fromDate;
    }


    /**
     * Gets the toDate value for this SearchDateType.
     * 
     * @return toDate
     */
    public java.lang.String getToDate() {
        return toDate;
    }


    /**
     * Sets the toDate value for this SearchDateType.
     * 
     * @param toDate
     */
    public void setToDate(java.lang.String toDate) {
        this.toDate = toDate;
    }


    /**
     * Gets the fromDate value for this SearchDateType.
     * 
     * @return fromDate
     */
    public java.lang.String getFromDate() {
        return fromDate;
    }


    /**
     * Sets the fromDate value for this SearchDateType.
     * 
     * @param fromDate
     */
    public void setFromDate(java.lang.String fromDate) {
        this.fromDate = fromDate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SearchDateType)) return false;
        SearchDateType other = (SearchDateType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.toDate==null && other.getToDate()==null) || 
             (this.toDate!=null &&
              this.toDate.equals(other.getToDate()))) &&
            ((this.fromDate==null && other.getFromDate()==null) || 
             (this.fromDate!=null &&
              this.fromDate.equals(other.getFromDate())));
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
        if (getToDate() != null) {
            _hashCode += getToDate().hashCode();
        }
        if (getFromDate() != null) {
            _hashCode += getFromDate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SearchDateType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "SearchDateType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("toDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "toDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fromDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fromDate"));
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
