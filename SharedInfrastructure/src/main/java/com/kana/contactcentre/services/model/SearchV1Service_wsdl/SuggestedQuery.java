/**
 * SuggestedQuery.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.SearchV1Service_wsdl;

public class SuggestedQuery  implements java.io.Serializable {
    private com.kana.contactcentre.services.model.SearchV1Service_wsdl.KnowledgeGroupUnit[] knowledgeGroupUnits;

    private java.lang.String queryText;

    private com.kana.contactcentre.services.model.SearchV1Service_wsdl.ReplacedTerm[] replacedTermsList;

    private java.lang.String type;

    private java.math.BigInteger numberOfGroupResultsFound;

    public SuggestedQuery() {
    }

    public SuggestedQuery(
           com.kana.contactcentre.services.model.SearchV1Service_wsdl.KnowledgeGroupUnit[] knowledgeGroupUnits,
           java.lang.String queryText,
           com.kana.contactcentre.services.model.SearchV1Service_wsdl.ReplacedTerm[] replacedTermsList,
           java.lang.String type,
           java.math.BigInteger numberOfGroupResultsFound) {
           this.knowledgeGroupUnits = knowledgeGroupUnits;
           this.queryText = queryText;
           this.replacedTermsList = replacedTermsList;
           this.type = type;
           this.numberOfGroupResultsFound = numberOfGroupResultsFound;
    }


    /**
     * Gets the knowledgeGroupUnits value for this SuggestedQuery.
     * 
     * @return knowledgeGroupUnits
     */
    public com.kana.contactcentre.services.model.SearchV1Service_wsdl.KnowledgeGroupUnit[] getKnowledgeGroupUnits() {
        return knowledgeGroupUnits;
    }


    /**
     * Sets the knowledgeGroupUnits value for this SuggestedQuery.
     * 
     * @param knowledgeGroupUnits
     */
    public void setKnowledgeGroupUnits(com.kana.contactcentre.services.model.SearchV1Service_wsdl.KnowledgeGroupUnit[] knowledgeGroupUnits) {
        this.knowledgeGroupUnits = knowledgeGroupUnits;
    }


    /**
     * Gets the queryText value for this SuggestedQuery.
     * 
     * @return queryText
     */
    public java.lang.String getQueryText() {
        return queryText;
    }


    /**
     * Sets the queryText value for this SuggestedQuery.
     * 
     * @param queryText
     */
    public void setQueryText(java.lang.String queryText) {
        this.queryText = queryText;
    }


    /**
     * Gets the replacedTermsList value for this SuggestedQuery.
     * 
     * @return replacedTermsList
     */
    public com.kana.contactcentre.services.model.SearchV1Service_wsdl.ReplacedTerm[] getReplacedTermsList() {
        return replacedTermsList;
    }


    /**
     * Sets the replacedTermsList value for this SuggestedQuery.
     * 
     * @param replacedTermsList
     */
    public void setReplacedTermsList(com.kana.contactcentre.services.model.SearchV1Service_wsdl.ReplacedTerm[] replacedTermsList) {
        this.replacedTermsList = replacedTermsList;
    }


    /**
     * Gets the type value for this SuggestedQuery.
     * 
     * @return type
     */
    public java.lang.String getType() {
        return type;
    }


    /**
     * Sets the type value for this SuggestedQuery.
     * 
     * @param type
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }


    /**
     * Gets the numberOfGroupResultsFound value for this SuggestedQuery.
     * 
     * @return numberOfGroupResultsFound
     */
    public java.math.BigInteger getNumberOfGroupResultsFound() {
        return numberOfGroupResultsFound;
    }


    /**
     * Sets the numberOfGroupResultsFound value for this SuggestedQuery.
     * 
     * @param numberOfGroupResultsFound
     */
    public void setNumberOfGroupResultsFound(java.math.BigInteger numberOfGroupResultsFound) {
        this.numberOfGroupResultsFound = numberOfGroupResultsFound;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SuggestedQuery)) return false;
        SuggestedQuery other = (SuggestedQuery) obj;
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
            ((this.queryText==null && other.getQueryText()==null) || 
             (this.queryText!=null &&
              this.queryText.equals(other.getQueryText()))) &&
            ((this.replacedTermsList==null && other.getReplacedTermsList()==null) || 
             (this.replacedTermsList!=null &&
              java.util.Arrays.equals(this.replacedTermsList, other.getReplacedTermsList()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.numberOfGroupResultsFound==null && other.getNumberOfGroupResultsFound()==null) || 
             (this.numberOfGroupResultsFound!=null &&
              this.numberOfGroupResultsFound.equals(other.getNumberOfGroupResultsFound())));
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
        if (getQueryText() != null) {
            _hashCode += getQueryText().hashCode();
        }
        if (getReplacedTermsList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getReplacedTermsList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getReplacedTermsList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getNumberOfGroupResultsFound() != null) {
            _hashCode += getNumberOfGroupResultsFound().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SuggestedQuery.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "SuggestedQuery"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("knowledgeGroupUnits");
        elemField.setXmlName(new javax.xml.namespace.QName("", "knowledgeGroupUnits"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "KnowledgeGroupUnit"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "KnowledgeGroupUnit"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("queryText");
        elemField.setXmlName(new javax.xml.namespace.QName("", "queryText"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("replacedTermsList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "replacedTermsList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "ReplacedTerm"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "ReplacedTerm"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberOfGroupResultsFound");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numberOfGroupResultsFound"));
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
