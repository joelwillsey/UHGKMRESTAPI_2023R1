/**
 * IEvaId.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl;

public class IEvaId  implements java.io.Serializable {
    private java.math.BigInteger environmentId;

    private java.math.BigInteger releaseId;

    private java.math.BigInteger id;

    public IEvaId() {
    }

    public IEvaId(
           java.math.BigInteger environmentId,
           java.math.BigInteger releaseId,
           java.math.BigInteger id) {
           this.environmentId = environmentId;
           this.releaseId = releaseId;
           this.id = id;
    }


    /**
     * Gets the environmentId value for this IEvaId.
     * 
     * @return environmentId
     */
    public java.math.BigInteger getEnvironmentId() {
        return environmentId;
    }


    /**
     * Sets the environmentId value for this IEvaId.
     * 
     * @param environmentId
     */
    public void setEnvironmentId(java.math.BigInteger environmentId) {
        this.environmentId = environmentId;
    }


    /**
     * Gets the releaseId value for this IEvaId.
     * 
     * @return releaseId
     */
    public java.math.BigInteger getReleaseId() {
        return releaseId;
    }


    /**
     * Sets the releaseId value for this IEvaId.
     * 
     * @param releaseId
     */
    public void setReleaseId(java.math.BigInteger releaseId) {
        this.releaseId = releaseId;
    }


    /**
     * Gets the id value for this IEvaId.
     * 
     * @return id
     */
    public java.math.BigInteger getId() {
        return id;
    }


    /**
     * Sets the id value for this IEvaId.
     * 
     * @param id
     */
    public void setId(java.math.BigInteger id) {
        this.id = id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IEvaId)) return false;
        IEvaId other = (IEvaId) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.environmentId==null && other.getEnvironmentId()==null) || 
             (this.environmentId!=null &&
              this.environmentId.equals(other.getEnvironmentId()))) &&
            ((this.releaseId==null && other.getReleaseId()==null) || 
             (this.releaseId!=null &&
              this.releaseId.equals(other.getReleaseId()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId())));
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
        if (getEnvironmentId() != null) {
            _hashCode += getEnvironmentId().hashCode();
        }
        if (getReleaseId() != null) {
            _hashCode += getReleaseId().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IEvaId.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "IEvaId"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("environmentId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "environmentId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("releaseId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "releaseId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
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
