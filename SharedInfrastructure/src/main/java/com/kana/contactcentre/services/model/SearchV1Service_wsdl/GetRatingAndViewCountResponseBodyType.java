/**
 * GetRatingAndViewCountResponseBodyType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.SearchV1Service_wsdl;

public class GetRatingAndViewCountResponseBodyType  implements java.io.Serializable {
    private java.math.BigInteger viewCount;

    private float avgRating;

    private com.kana.contactcentre.services.model.SearchV1Service_wsdl.ErrorMessage[] errorList;

    private java.math.BigInteger ratingCount;

    public GetRatingAndViewCountResponseBodyType() {
    }

    public GetRatingAndViewCountResponseBodyType(
           java.math.BigInteger viewCount,
           float avgRating,
           com.kana.contactcentre.services.model.SearchV1Service_wsdl.ErrorMessage[] errorList,
           java.math.BigInteger ratingCount) {
           this.viewCount = viewCount;
           this.avgRating = avgRating;
           this.errorList = errorList;
           this.ratingCount = ratingCount;
    }


    /**
     * Gets the viewCount value for this GetRatingAndViewCountResponseBodyType.
     * 
     * @return viewCount
     */
    public java.math.BigInteger getViewCount() {
        return viewCount;
    }


    /**
     * Sets the viewCount value for this GetRatingAndViewCountResponseBodyType.
     * 
     * @param viewCount
     */
    public void setViewCount(java.math.BigInteger viewCount) {
        this.viewCount = viewCount;
    }


    /**
     * Gets the avgRating value for this GetRatingAndViewCountResponseBodyType.
     * 
     * @return avgRating
     */
    public float getAvgRating() {
        return avgRating;
    }


    /**
     * Sets the avgRating value for this GetRatingAndViewCountResponseBodyType.
     * 
     * @param avgRating
     */
    public void setAvgRating(float avgRating) {
        this.avgRating = avgRating;
    }


    /**
     * Gets the errorList value for this GetRatingAndViewCountResponseBodyType.
     * 
     * @return errorList
     */
    public com.kana.contactcentre.services.model.SearchV1Service_wsdl.ErrorMessage[] getErrorList() {
        return errorList;
    }


    /**
     * Sets the errorList value for this GetRatingAndViewCountResponseBodyType.
     * 
     * @param errorList
     */
    public void setErrorList(com.kana.contactcentre.services.model.SearchV1Service_wsdl.ErrorMessage[] errorList) {
        this.errorList = errorList;
    }


    /**
     * Gets the ratingCount value for this GetRatingAndViewCountResponseBodyType.
     * 
     * @return ratingCount
     */
    public java.math.BigInteger getRatingCount() {
        return ratingCount;
    }


    /**
     * Sets the ratingCount value for this GetRatingAndViewCountResponseBodyType.
     * 
     * @param ratingCount
     */
    public void setRatingCount(java.math.BigInteger ratingCount) {
        this.ratingCount = ratingCount;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetRatingAndViewCountResponseBodyType)) return false;
        GetRatingAndViewCountResponseBodyType other = (GetRatingAndViewCountResponseBodyType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.viewCount==null && other.getViewCount()==null) || 
             (this.viewCount!=null &&
              this.viewCount.equals(other.getViewCount()))) &&
            this.avgRating == other.getAvgRating() &&
            ((this.errorList==null && other.getErrorList()==null) || 
             (this.errorList!=null &&
              java.util.Arrays.equals(this.errorList, other.getErrorList()))) &&
            ((this.ratingCount==null && other.getRatingCount()==null) || 
             (this.ratingCount!=null &&
              this.ratingCount.equals(other.getRatingCount())));
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
        if (getViewCount() != null) {
            _hashCode += getViewCount().hashCode();
        }
        _hashCode += new Float(getAvgRating()).hashCode();
        if (getErrorList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getErrorList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getErrorList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRatingCount() != null) {
            _hashCode += getRatingCount().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetRatingAndViewCountResponseBodyType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "GetRatingAndViewCountResponseBodyType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("viewCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "viewCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("avgRating");
        elemField.setXmlName(new javax.xml.namespace.QName("", "avgRating"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errorList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "ErrorMessage"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "ErrorMessage"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ratingCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ratingCount"));
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
