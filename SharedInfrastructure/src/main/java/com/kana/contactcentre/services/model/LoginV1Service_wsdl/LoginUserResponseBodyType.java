/**
 * LoginUserResponseBodyType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.LoginV1Service_wsdl;

public class LoginUserResponseBodyType  implements java.io.Serializable {
    private com.kana.contactcentre.services.model.LoginV1Service_wsdl.LoginResponse loginResponse;

    public LoginUserResponseBodyType() {
    }

    public LoginUserResponseBodyType(
           com.kana.contactcentre.services.model.LoginV1Service_wsdl.LoginResponse loginResponse) {
           this.loginResponse = loginResponse;
    }


    /**
     * Gets the loginResponse value for this LoginUserResponseBodyType.
     * 
     * @return loginResponse
     */
    public com.kana.contactcentre.services.model.LoginV1Service_wsdl.LoginResponse getLoginResponse() {
        return loginResponse;
    }


    /**
     * Sets the loginResponse value for this LoginUserResponseBodyType.
     * 
     * @param loginResponse
     */
    public void setLoginResponse(com.kana.contactcentre.services.model.LoginV1Service_wsdl.LoginResponse loginResponse) {
        this.loginResponse = loginResponse;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LoginUserResponseBodyType)) return false;
        LoginUserResponseBodyType other = (LoginUserResponseBodyType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.loginResponse==null && other.getLoginResponse()==null) || 
             (this.loginResponse!=null &&
              this.loginResponse.equals(other.getLoginResponse())));
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
        if (getLoginResponse() != null) {
            _hashCode += getLoginResponse().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LoginUserResponseBodyType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/LoginV1Service.wsdl", "LoginUserResponseBodyType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loginResponse");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loginResponse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/LoginV1Service.wsdl", "LoginResponse"));
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
