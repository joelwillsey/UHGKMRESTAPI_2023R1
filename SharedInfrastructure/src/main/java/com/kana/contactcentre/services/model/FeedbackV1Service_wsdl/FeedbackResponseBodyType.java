/**
 * FeedbackResponseBodyType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.FeedbackV1Service_wsdl;

public class FeedbackResponseBodyType  implements java.io.Serializable {
    private com.kana.contactcentre.services.model.FeedbackV1Service_wsdl.FeedbackServiceResponse returnResponse;

    public FeedbackResponseBodyType() {
    }

    public FeedbackResponseBodyType(
           com.kana.contactcentre.services.model.FeedbackV1Service_wsdl.FeedbackServiceResponse returnResponse) {
           this.returnResponse = returnResponse;
    }


    /**
     * Gets the returnResponse value for this FeedbackResponseBodyType.
     * 
     * @return returnResponse
     */
    public com.kana.contactcentre.services.model.FeedbackV1Service_wsdl.FeedbackServiceResponse getReturnResponse() {
        return returnResponse;
    }


    /**
     * Sets the returnResponse value for this FeedbackResponseBodyType.
     * 
     * @param returnResponse
     */
    public void setReturnResponse(com.kana.contactcentre.services.model.FeedbackV1Service_wsdl.FeedbackServiceResponse returnResponse) {
        this.returnResponse = returnResponse;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FeedbackResponseBodyType)) return false;
        FeedbackResponseBodyType other = (FeedbackResponseBodyType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.returnResponse==null && other.getReturnResponse()==null) || 
             (this.returnResponse!=null &&
              this.returnResponse.equals(other.getReturnResponse())));
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
        if (getReturnResponse() != null) {
            _hashCode += getReturnResponse().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FeedbackResponseBodyType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/FeedbackV1Service.wsdl", "FeedbackResponseBodyType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnResponse");
        elemField.setXmlName(new javax.xml.namespace.QName("", "returnResponse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/FeedbackV1Service.wsdl", "FeedbackServiceResponse"));
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
