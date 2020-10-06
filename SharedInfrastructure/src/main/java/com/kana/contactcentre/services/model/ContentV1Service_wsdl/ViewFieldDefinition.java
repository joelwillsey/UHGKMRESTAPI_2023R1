/**
 * ViewFieldDefinition.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.ContentV1Service_wsdl;

public class ViewFieldDefinition  implements java.io.Serializable {
    private java.lang.String fieldType;

    private java.math.BigInteger viewSequence;

    private boolean showLabel;

    private java.lang.String fieldDisplayName;

    private java.lang.String fieldName;

    public ViewFieldDefinition() {
    }

    public ViewFieldDefinition(
           java.lang.String fieldType,
           java.math.BigInteger viewSequence,
           boolean showLabel,
           java.lang.String fieldDisplayName,
           java.lang.String fieldName) {
           this.fieldType = fieldType;
           this.viewSequence = viewSequence;
           this.showLabel = showLabel;
           this.fieldDisplayName = fieldDisplayName;
           this.fieldName = fieldName;
    }


    /**
     * Gets the fieldType value for this ViewFieldDefinition.
     * 
     * @return fieldType
     */
    public java.lang.String getFieldType() {
        return fieldType;
    }


    /**
     * Sets the fieldType value for this ViewFieldDefinition.
     * 
     * @param fieldType
     */
    public void setFieldType(java.lang.String fieldType) {
        this.fieldType = fieldType;
    }


    /**
     * Gets the viewSequence value for this ViewFieldDefinition.
     * 
     * @return viewSequence
     */
    public java.math.BigInteger getViewSequence() {
        return viewSequence;
    }


    /**
     * Sets the viewSequence value for this ViewFieldDefinition.
     * 
     * @param viewSequence
     */
    public void setViewSequence(java.math.BigInteger viewSequence) {
        this.viewSequence = viewSequence;
    }


    /**
     * Gets the showLabel value for this ViewFieldDefinition.
     * 
     * @return showLabel
     */
    public boolean isShowLabel() {
        return showLabel;
    }


    /**
     * Sets the showLabel value for this ViewFieldDefinition.
     * 
     * @param showLabel
     */
    public void setShowLabel(boolean showLabel) {
        this.showLabel = showLabel;
    }


    /**
     * Gets the fieldDisplayName value for this ViewFieldDefinition.
     * 
     * @return fieldDisplayName
     */
    public java.lang.String getFieldDisplayName() {
        return fieldDisplayName;
    }


    /**
     * Sets the fieldDisplayName value for this ViewFieldDefinition.
     * 
     * @param fieldDisplayName
     */
    public void setFieldDisplayName(java.lang.String fieldDisplayName) {
        this.fieldDisplayName = fieldDisplayName;
    }


    /**
     * Gets the fieldName value for this ViewFieldDefinition.
     * 
     * @return fieldName
     */
    public java.lang.String getFieldName() {
        return fieldName;
    }


    /**
     * Sets the fieldName value for this ViewFieldDefinition.
     * 
     * @param fieldName
     */
    public void setFieldName(java.lang.String fieldName) {
        this.fieldName = fieldName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ViewFieldDefinition)) return false;
        ViewFieldDefinition other = (ViewFieldDefinition) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fieldType==null && other.getFieldType()==null) || 
             (this.fieldType!=null &&
              this.fieldType.equals(other.getFieldType()))) &&
            ((this.viewSequence==null && other.getViewSequence()==null) || 
             (this.viewSequence!=null &&
              this.viewSequence.equals(other.getViewSequence()))) &&
            this.showLabel == other.isShowLabel() &&
            ((this.fieldDisplayName==null && other.getFieldDisplayName()==null) || 
             (this.fieldDisplayName!=null &&
              this.fieldDisplayName.equals(other.getFieldDisplayName()))) &&
            ((this.fieldName==null && other.getFieldName()==null) || 
             (this.fieldName!=null &&
              this.fieldName.equals(other.getFieldName())));
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
        if (getFieldType() != null) {
            _hashCode += getFieldType().hashCode();
        }
        if (getViewSequence() != null) {
            _hashCode += getViewSequence().hashCode();
        }
        _hashCode += (isShowLabel() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getFieldDisplayName() != null) {
            _hashCode += getFieldDisplayName().hashCode();
        }
        if (getFieldName() != null) {
            _hashCode += getFieldName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ViewFieldDefinition.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "ViewFieldDefinition"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fieldType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fieldType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("viewSequence");
        elemField.setXmlName(new javax.xml.namespace.QName("", "viewSequence"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("showLabel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "showLabel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fieldDisplayName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fieldDisplayName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fieldName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fieldName"));
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
