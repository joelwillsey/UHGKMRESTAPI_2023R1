/**
 * GetTagSetResponseBodyType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.TagV1Service_wsdl;

public class GetTagSetResponseBodyType  implements java.io.Serializable {
    private com.kana.contactcentre.services.model.TagV1Service_wsdl.TagDescriptor[] tagDescriptors;

    public GetTagSetResponseBodyType() {
    }

    public GetTagSetResponseBodyType(
           com.kana.contactcentre.services.model.TagV1Service_wsdl.TagDescriptor[] tagDescriptors) {
           this.tagDescriptors = tagDescriptors;
    }


    /**
     * Gets the tagDescriptors value for this GetTagSetResponseBodyType.
     * 
     * @return tagDescriptors
     */
    public com.kana.contactcentre.services.model.TagV1Service_wsdl.TagDescriptor[] getTagDescriptors() {
        return tagDescriptors;
    }


    /**
     * Sets the tagDescriptors value for this GetTagSetResponseBodyType.
     * 
     * @param tagDescriptors
     */
    public void setTagDescriptors(com.kana.contactcentre.services.model.TagV1Service_wsdl.TagDescriptor[] tagDescriptors) {
        this.tagDescriptors = tagDescriptors;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetTagSetResponseBodyType)) return false;
        GetTagSetResponseBodyType other = (GetTagSetResponseBodyType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tagDescriptors==null && other.getTagDescriptors()==null) || 
             (this.tagDescriptors!=null &&
              java.util.Arrays.equals(this.tagDescriptors, other.getTagDescriptors())));
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
        if (getTagDescriptors() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTagDescriptors());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTagDescriptors(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetTagSetResponseBodyType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/TagV1Service.wsdl", "GetTagSetResponseBodyType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tagDescriptors");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tagDescriptors"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/TagV1Service.wsdl", "TagDescriptor"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "TagDescriptor"));
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
