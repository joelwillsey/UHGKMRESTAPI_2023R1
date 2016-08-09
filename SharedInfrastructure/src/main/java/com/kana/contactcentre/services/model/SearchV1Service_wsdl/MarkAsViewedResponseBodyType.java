/**
 * MarkAsViewedResponseBodyType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.SearchV1Service_wsdl;

public class MarkAsViewedResponseBodyType  implements java.io.Serializable {
    private java.lang.String viewUUID;

    public MarkAsViewedResponseBodyType() {
    }

    public MarkAsViewedResponseBodyType(
           java.lang.String viewUUID) {
           this.viewUUID = viewUUID;
    }


    /**
     * Gets the viewUUID value for this MarkAsViewedResponseBodyType.
     * 
     * @return viewUUID
     */
    public java.lang.String getViewUUID() {
        return viewUUID;
    }


    /**
     * Sets the viewUUID value for this MarkAsViewedResponseBodyType.
     * 
     * @param viewUUID
     */
    public void setViewUUID(java.lang.String viewUUID) {
        this.viewUUID = viewUUID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MarkAsViewedResponseBodyType)) return false;
        MarkAsViewedResponseBodyType other = (MarkAsViewedResponseBodyType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.viewUUID==null && other.getViewUUID()==null) || 
             (this.viewUUID!=null &&
              this.viewUUID.equals(other.getViewUUID())));
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
        if (getViewUUID() != null) {
            _hashCode += getViewUUID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MarkAsViewedResponseBodyType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "MarkAsViewedResponseBodyType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("viewUUID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "viewUUID"));
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
