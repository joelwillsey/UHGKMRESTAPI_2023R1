/**
 * TagDescriptor.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.TagV1Service_wsdl;

public class TagDescriptor  implements java.io.Serializable {
    private java.lang.String parentTagName;

    private java.lang.String systemTagName;

    private java.lang.String displayTagName;

    public TagDescriptor() {
    }

    public TagDescriptor(
           java.lang.String parentTagName,
           java.lang.String systemTagName,
           java.lang.String displayTagName) {
           this.parentTagName = parentTagName;
           this.systemTagName = systemTagName;
           this.displayTagName = displayTagName;
    }


    /**
     * Gets the parentTagName value for this TagDescriptor.
     * 
     * @return parentTagName
     */
    public java.lang.String getParentTagName() {
        return parentTagName;
    }


    /**
     * Sets the parentTagName value for this TagDescriptor.
     * 
     * @param parentTagName
     */
    public void setParentTagName(java.lang.String parentTagName) {
        this.parentTagName = parentTagName;
    }


    /**
     * Gets the systemTagName value for this TagDescriptor.
     * 
     * @return systemTagName
     */
    public java.lang.String getSystemTagName() {
        return systemTagName;
    }


    /**
     * Sets the systemTagName value for this TagDescriptor.
     * 
     * @param systemTagName
     */
    public void setSystemTagName(java.lang.String systemTagName) {
        this.systemTagName = systemTagName;
    }


    /**
     * Gets the displayTagName value for this TagDescriptor.
     * 
     * @return displayTagName
     */
    public java.lang.String getDisplayTagName() {
        return displayTagName;
    }


    /**
     * Sets the displayTagName value for this TagDescriptor.
     * 
     * @param displayTagName
     */
    public void setDisplayTagName(java.lang.String displayTagName) {
        this.displayTagName = displayTagName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TagDescriptor)) return false;
        TagDescriptor other = (TagDescriptor) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parentTagName==null && other.getParentTagName()==null) || 
             (this.parentTagName!=null &&
              this.parentTagName.equals(other.getParentTagName()))) &&
            ((this.systemTagName==null && other.getSystemTagName()==null) || 
             (this.systemTagName!=null &&
              this.systemTagName.equals(other.getSystemTagName()))) &&
            ((this.displayTagName==null && other.getDisplayTagName()==null) || 
             (this.displayTagName!=null &&
              this.displayTagName.equals(other.getDisplayTagName())));
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
        if (getParentTagName() != null) {
            _hashCode += getParentTagName().hashCode();
        }
        if (getSystemTagName() != null) {
            _hashCode += getSystemTagName().hashCode();
        }
        if (getDisplayTagName() != null) {
            _hashCode += getDisplayTagName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TagDescriptor.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/TagV1Service.wsdl", "TagDescriptor"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parentTagName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "parentTagName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("systemTagName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "systemTagName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("displayTagName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "displayTagName"));
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
