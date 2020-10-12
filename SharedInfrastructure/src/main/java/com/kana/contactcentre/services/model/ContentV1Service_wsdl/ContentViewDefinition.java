/**
 * ContentViewDefinition.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.ContentV1Service_wsdl;

public class ContentViewDefinition  implements java.io.Serializable {
    private com.kana.contactcentre.services.model.ContentV1Service_wsdl.ViewFieldDefinition[] viewFields;

    public ContentViewDefinition() {
    }

    public ContentViewDefinition(
           com.kana.contactcentre.services.model.ContentV1Service_wsdl.ViewFieldDefinition[] viewFields) {
           this.viewFields = viewFields;
    }


    /**
     * Gets the viewFields value for this ContentViewDefinition.
     * 
     * @return viewFields
     */
    public com.kana.contactcentre.services.model.ContentV1Service_wsdl.ViewFieldDefinition[] getViewFields() {
        return viewFields;
    }


    /**
     * Sets the viewFields value for this ContentViewDefinition.
     * 
     * @param viewFields
     */
    public void setViewFields(com.kana.contactcentre.services.model.ContentV1Service_wsdl.ViewFieldDefinition[] viewFields) {
        this.viewFields = viewFields;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ContentViewDefinition)) return false;
        ContentViewDefinition other = (ContentViewDefinition) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.viewFields==null && other.getViewFields()==null) || 
             (this.viewFields!=null &&
              java.util.Arrays.equals(this.viewFields, other.getViewFields())));
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
        if (getViewFields() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getViewFields());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getViewFields(), i);
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
        new org.apache.axis.description.TypeDesc(ContentViewDefinition.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "ContentViewDefinition"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("viewFields");
        elemField.setXmlName(new javax.xml.namespace.QName("", "viewFields"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "ViewFieldDefinition"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "ViewFieldDefinition"));
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