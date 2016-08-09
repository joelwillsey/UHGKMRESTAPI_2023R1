/**
 * KnowledgeResultSet.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.SearchV1Service_wsdl;

public class KnowledgeResultSet  implements java.io.Serializable {
    private com.kana.contactcentre.services.model.SearchV1Service_wsdl.KnowledgeGroupUnit[] knowledgeGroupUnits;

    private java.math.BigInteger numberOfResults;

    private com.kana.contactcentre.services.model.SearchV1Service_wsdl.SuggestedQuery[] suggestedQueryList;

    public KnowledgeResultSet() {
    }

    public KnowledgeResultSet(
           com.kana.contactcentre.services.model.SearchV1Service_wsdl.KnowledgeGroupUnit[] knowledgeGroupUnits,
           java.math.BigInteger numberOfResults,
           com.kana.contactcentre.services.model.SearchV1Service_wsdl.SuggestedQuery[] suggestedQueryList) {
           this.knowledgeGroupUnits = knowledgeGroupUnits;
           this.numberOfResults = numberOfResults;
           this.suggestedQueryList = suggestedQueryList;
    }


    /**
     * Gets the knowledgeGroupUnits value for this KnowledgeResultSet.
     * 
     * @return knowledgeGroupUnits
     */
    public com.kana.contactcentre.services.model.SearchV1Service_wsdl.KnowledgeGroupUnit[] getKnowledgeGroupUnits() {
        return knowledgeGroupUnits;
    }


    /**
     * Sets the knowledgeGroupUnits value for this KnowledgeResultSet.
     * 
     * @param knowledgeGroupUnits
     */
    public void setKnowledgeGroupUnits(com.kana.contactcentre.services.model.SearchV1Service_wsdl.KnowledgeGroupUnit[] knowledgeGroupUnits) {
        this.knowledgeGroupUnits = knowledgeGroupUnits;
    }


    /**
     * Gets the numberOfResults value for this KnowledgeResultSet.
     * 
     * @return numberOfResults
     */
    public java.math.BigInteger getNumberOfResults() {
        return numberOfResults;
    }


    /**
     * Sets the numberOfResults value for this KnowledgeResultSet.
     * 
     * @param numberOfResults
     */
    public void setNumberOfResults(java.math.BigInteger numberOfResults) {
        this.numberOfResults = numberOfResults;
    }


    /**
     * Gets the suggestedQueryList value for this KnowledgeResultSet.
     * 
     * @return suggestedQueryList
     */
    public com.kana.contactcentre.services.model.SearchV1Service_wsdl.SuggestedQuery[] getSuggestedQueryList() {
        return suggestedQueryList;
    }


    /**
     * Sets the suggestedQueryList value for this KnowledgeResultSet.
     * 
     * @param suggestedQueryList
     */
    public void setSuggestedQueryList(com.kana.contactcentre.services.model.SearchV1Service_wsdl.SuggestedQuery[] suggestedQueryList) {
        this.suggestedQueryList = suggestedQueryList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof KnowledgeResultSet)) return false;
        KnowledgeResultSet other = (KnowledgeResultSet) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.knowledgeGroupUnits==null && other.getKnowledgeGroupUnits()==null) || 
             (this.knowledgeGroupUnits!=null &&
              java.util.Arrays.equals(this.knowledgeGroupUnits, other.getKnowledgeGroupUnits()))) &&
            ((this.numberOfResults==null && other.getNumberOfResults()==null) || 
             (this.numberOfResults!=null &&
              this.numberOfResults.equals(other.getNumberOfResults()))) &&
            ((this.suggestedQueryList==null && other.getSuggestedQueryList()==null) || 
             (this.suggestedQueryList!=null &&
              java.util.Arrays.equals(this.suggestedQueryList, other.getSuggestedQueryList())));
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
        if (getKnowledgeGroupUnits() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getKnowledgeGroupUnits());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getKnowledgeGroupUnits(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNumberOfResults() != null) {
            _hashCode += getNumberOfResults().hashCode();
        }
        if (getSuggestedQueryList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSuggestedQueryList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSuggestedQueryList(), i);
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
        new org.apache.axis.description.TypeDesc(KnowledgeResultSet.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "KnowledgeResultSet"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("knowledgeGroupUnits");
        elemField.setXmlName(new javax.xml.namespace.QName("", "knowledgeGroupUnits"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "KnowledgeGroupUnit"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "KnowledgeGroupUnit"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberOfResults");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numberOfResults"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("suggestedQueryList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "suggestedQueryList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "SuggestedQuery"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "SuggestedQuery"));
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
