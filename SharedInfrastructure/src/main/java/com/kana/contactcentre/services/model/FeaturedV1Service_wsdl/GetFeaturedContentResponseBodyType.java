/**
 * GetFeaturedContentResponseBodyType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.FeaturedV1Service_wsdl;

public class GetFeaturedContentResponseBodyType  implements java.io.Serializable {
    private KnowledgeResultSet response;

    private java.math.BigInteger maxNumberOfGroupResults;

    private java.math.BigInteger paginationStartIndex;

    private java.math.BigInteger maxNumberOfUnitsPerGroup;

    public GetFeaturedContentResponseBodyType() {
    }

    public GetFeaturedContentResponseBodyType(
           KnowledgeResultSet response,
           java.math.BigInteger maxNumberOfGroupResults,
           java.math.BigInteger paginationStartIndex,
           java.math.BigInteger maxNumberOfUnitsPerGroup) {
           this.response = response;
           this.maxNumberOfGroupResults = maxNumberOfGroupResults;
           this.paginationStartIndex = paginationStartIndex;
           this.maxNumberOfUnitsPerGroup = maxNumberOfUnitsPerGroup;
    }


    /**
     * Gets the response value for this GetFeaturedContentResponseBodyType.
     * 
     * @return response
     */
    public KnowledgeResultSet getResponse() {
        return response;
    }


    /**
     * Sets the response value for this GetFeaturedContentResponseBodyType.
     * 
     * @param response
     */
    public void setResponse(KnowledgeResultSet response) {
        this.response = response;
    }


    /**
     * Gets the maxNumberOfGroupResults value for this GetFeaturedContentResponseBodyType.
     * 
     * @return maxNumberOfGroupResults
     */
    public java.math.BigInteger getMaxNumberOfGroupResults() {
        return maxNumberOfGroupResults;
    }


    /**
     * Sets the maxNumberOfGroupResults value for this GetFeaturedContentResponseBodyType.
     * 
     * @param maxNumberOfGroupResults
     */
    public void setMaxNumberOfGroupResults(java.math.BigInteger maxNumberOfGroupResults) {
        this.maxNumberOfGroupResults = maxNumberOfGroupResults;
    }


    /**
     * Gets the paginationStartIndex value for this GetFeaturedContentResponseBodyType.
     * 
     * @return paginationStartIndex
     */
    public java.math.BigInteger getPaginationStartIndex() {
        return paginationStartIndex;
    }


    /**
     * Sets the paginationStartIndex value for this GetFeaturedContentResponseBodyType.
     * 
     * @param paginationStartIndex
     */
    public void setPaginationStartIndex(java.math.BigInteger paginationStartIndex) {
        this.paginationStartIndex = paginationStartIndex;
    }


    /**
     * Gets the maxNumberOfUnitsPerGroup value for this GetFeaturedContentResponseBodyType.
     * 
     * @return maxNumberOfUnitsPerGroup
     */
    public java.math.BigInteger getMaxNumberOfUnitsPerGroup() {
        return maxNumberOfUnitsPerGroup;
    }


    /**
     * Sets the maxNumberOfUnitsPerGroup value for this GetFeaturedContentResponseBodyType.
     * 
     * @param maxNumberOfUnitsPerGroup
     */
    public void setMaxNumberOfUnitsPerGroup(java.math.BigInteger maxNumberOfUnitsPerGroup) {
        this.maxNumberOfUnitsPerGroup = maxNumberOfUnitsPerGroup;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetFeaturedContentResponseBodyType)) return false;
        GetFeaturedContentResponseBodyType other = (GetFeaturedContentResponseBodyType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.response==null && other.getResponse()==null) || 
             (this.response!=null &&
              this.response.equals(other.getResponse()))) &&
            ((this.maxNumberOfGroupResults==null && other.getMaxNumberOfGroupResults()==null) || 
             (this.maxNumberOfGroupResults!=null &&
              this.maxNumberOfGroupResults.equals(other.getMaxNumberOfGroupResults()))) &&
            ((this.paginationStartIndex==null && other.getPaginationStartIndex()==null) || 
             (this.paginationStartIndex!=null &&
              this.paginationStartIndex.equals(other.getPaginationStartIndex()))) &&
            ((this.maxNumberOfUnitsPerGroup==null && other.getMaxNumberOfUnitsPerGroup()==null) || 
             (this.maxNumberOfUnitsPerGroup!=null &&
              this.maxNumberOfUnitsPerGroup.equals(other.getMaxNumberOfUnitsPerGroup())));
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
        if (getResponse() != null) {
            _hashCode += getResponse().hashCode();
        }
        if (getMaxNumberOfGroupResults() != null) {
            _hashCode += getMaxNumberOfGroupResults().hashCode();
        }
        if (getPaginationStartIndex() != null) {
            _hashCode += getPaginationStartIndex().hashCode();
        }
        if (getMaxNumberOfUnitsPerGroup() != null) {
            _hashCode += getMaxNumberOfUnitsPerGroup().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetFeaturedContentResponseBodyType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/FeaturedV1Service.wsdl", "GetFeaturedContentResponseBodyType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("response");
        elemField.setXmlName(new javax.xml.namespace.QName("", "response"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/FeaturedV1Service.wsdl", "KnowledgeResultSet"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxNumberOfGroupResults");
        elemField.setXmlName(new javax.xml.namespace.QName("", "maxNumberOfGroupResults"));
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
        elemField.setFieldName("maxNumberOfUnitsPerGroup");
        elemField.setXmlName(new javax.xml.namespace.QName("", "maxNumberOfUnitsPerGroup"));
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
