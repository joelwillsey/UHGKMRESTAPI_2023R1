/**
 * ContentVersionsResultSet.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.ContentV1Service_wsdl;

public class ContentVersionsResultSet  implements java.io.Serializable {
    private com.kana.contactcentre.services.model.ContentV1Service_wsdl.Version[] versions;

    private java.math.BigInteger totalVersions;

    public ContentVersionsResultSet() {
    }

    public ContentVersionsResultSet(
           com.kana.contactcentre.services.model.ContentV1Service_wsdl.Version[] versions,
           java.math.BigInteger totalVersions) {
           this.versions = versions;
           this.totalVersions = totalVersions;
    }


    /**
     * Gets the versions value for this ContentVersionsResultSet.
     * 
     * @return versions
     */
    public com.kana.contactcentre.services.model.ContentV1Service_wsdl.Version[] getVersions() {
        return versions;
    }


    /**
     * Sets the versions value for this ContentVersionsResultSet.
     * 
     * @param versions
     */
    public void setVersions(com.kana.contactcentre.services.model.ContentV1Service_wsdl.Version[] versions) {
        this.versions = versions;
    }


    /**
     * Gets the totalVersions value for this ContentVersionsResultSet.
     * 
     * @return totalVersions
     */
    public java.math.BigInteger getTotalVersions() {
        return totalVersions;
    }


    /**
     * Sets the totalVersions value for this ContentVersionsResultSet.
     * 
     * @param totalVersions
     */
    public void setTotalVersions(java.math.BigInteger totalVersions) {
        this.totalVersions = totalVersions;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ContentVersionsResultSet)) return false;
        ContentVersionsResultSet other = (ContentVersionsResultSet) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.versions==null && other.getVersions()==null) || 
             (this.versions!=null &&
              java.util.Arrays.equals(this.versions, other.getVersions()))) &&
            ((this.totalVersions==null && other.getTotalVersions()==null) || 
             (this.totalVersions!=null &&
              this.totalVersions.equals(other.getTotalVersions())));
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
        if (getVersions() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getVersions());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getVersions(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTotalVersions() != null) {
            _hashCode += getTotalVersions().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ContentVersionsResultSet.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "ContentVersionsResultSet"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("versions");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Versions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "Version"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "Version"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalVersions");
        elemField.setXmlName(new javax.xml.namespace.QName("", "totalVersions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
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
