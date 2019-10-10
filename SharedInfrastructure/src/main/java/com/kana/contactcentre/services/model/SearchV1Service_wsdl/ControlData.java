/**
 * ControlData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.SearchV1Service_wsdl;

public class ControlData  implements java.io.Serializable {
    private boolean spellCheckEnabled;

    private java.math.BigInteger maxNumberOfUnitsPerGroup;

    private java.math.BigInteger paginationStartIndex;

    private java.math.BigInteger maxNumberOfGroupResults;

    public ControlData() {
    }

    public ControlData(
           boolean spellCheckEnabled,
           java.math.BigInteger maxNumberOfUnitsPerGroup,
           java.math.BigInteger paginationStartIndex,
           java.math.BigInteger maxNumberOfGroupResults) {
           this.spellCheckEnabled = spellCheckEnabled;
           this.maxNumberOfUnitsPerGroup = maxNumberOfUnitsPerGroup;
           this.paginationStartIndex = paginationStartIndex;
           this.maxNumberOfGroupResults = maxNumberOfGroupResults;
    }


    /**
     * Gets the spellCheckEnabled value for this ControlData.
     * 
     * @return spellCheckEnabled
     */
    public boolean isSpellCheckEnabled() {
        return spellCheckEnabled;
    }


    /**
     * Sets the spellCheckEnabled value for this ControlData.
     * 
     * @param spellCheckEnabled
     */
    public void setSpellCheckEnabled(boolean spellCheckEnabled) {
        this.spellCheckEnabled = spellCheckEnabled;
    }


    /**
     * Gets the maxNumberOfUnitsPerGroup value for this ControlData.
     * 
     * @return maxNumberOfUnitsPerGroup
     */
    public java.math.BigInteger getMaxNumberOfUnitsPerGroup() {
        return maxNumberOfUnitsPerGroup;
    }


    /**
     * Sets the maxNumberOfUnitsPerGroup value for this ControlData.
     * 
     * @param maxNumberOfUnitsPerGroup
     */
    public void setMaxNumberOfUnitsPerGroup(java.math.BigInteger maxNumberOfUnitsPerGroup) {
        this.maxNumberOfUnitsPerGroup = maxNumberOfUnitsPerGroup;
    }


    /**
     * Gets the paginationStartIndex value for this ControlData.
     * 
     * @return paginationStartIndex
     */
    public java.math.BigInteger getPaginationStartIndex() {
        return paginationStartIndex;
    }


    /**
     * Sets the paginationStartIndex value for this ControlData.
     * 
     * @param paginationStartIndex
     */
    public void setPaginationStartIndex(java.math.BigInteger paginationStartIndex) {
        this.paginationStartIndex = paginationStartIndex;
    }


    /**
     * Gets the maxNumberOfGroupResults value for this ControlData.
     * 
     * @return maxNumberOfGroupResults
     */
    public java.math.BigInteger getMaxNumberOfGroupResults() {
        return maxNumberOfGroupResults;
    }


    /**
     * Sets the maxNumberOfGroupResults value for this ControlData.
     * 
     * @param maxNumberOfGroupResults
     */
    public void setMaxNumberOfGroupResults(java.math.BigInteger maxNumberOfGroupResults) {
        this.maxNumberOfGroupResults = maxNumberOfGroupResults;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ControlData)) return false;
        ControlData other = (ControlData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.spellCheckEnabled == other.isSpellCheckEnabled() &&
            ((this.maxNumberOfUnitsPerGroup==null && other.getMaxNumberOfUnitsPerGroup()==null) || 
             (this.maxNumberOfUnitsPerGroup!=null &&
              this.maxNumberOfUnitsPerGroup.equals(other.getMaxNumberOfUnitsPerGroup()))) &&
            ((this.paginationStartIndex==null && other.getPaginationStartIndex()==null) || 
             (this.paginationStartIndex!=null &&
              this.paginationStartIndex.equals(other.getPaginationStartIndex()))) &&
            ((this.maxNumberOfGroupResults==null && other.getMaxNumberOfGroupResults()==null) || 
             (this.maxNumberOfGroupResults!=null &&
              this.maxNumberOfGroupResults.equals(other.getMaxNumberOfGroupResults())));
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
        _hashCode += (isSpellCheckEnabled() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getMaxNumberOfUnitsPerGroup() != null) {
            _hashCode += getMaxNumberOfUnitsPerGroup().hashCode();
        }
        if (getPaginationStartIndex() != null) {
            _hashCode += getPaginationStartIndex().hashCode();
        }
        if (getMaxNumberOfGroupResults() != null) {
            _hashCode += getMaxNumberOfGroupResults().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ControlData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "ControlData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("spellCheckEnabled");
        elemField.setXmlName(new javax.xml.namespace.QName("", "spellCheckEnabled"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxNumberOfUnitsPerGroup");
        elemField.setXmlName(new javax.xml.namespace.QName("", "maxNumberOfUnitsPerGroup"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paginationStartIndex");
        elemField.setXmlName(new javax.xml.namespace.QName("", "paginationStartIndex"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxNumberOfGroupResults");
        elemField.setXmlName(new javax.xml.namespace.QName("", "maxNumberOfGroupResults"));
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
