/**
 * GetFeaturedContentRequestBodyType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.FeaturedV1Service_wsdl;

public class GetFeaturedContentRequestBodyType  implements java.io.Serializable {
    private java.lang.String locale;

    private java.lang.String password;

    private java.lang.String username;

    private java.lang.String applicationID;

    private java.lang.String kbase_tags;

    private java.math.BigInteger maxNumberOfUnitsPerGroup;

    private java.math.BigInteger paginationStartIndex;

    private java.math.BigInteger maxNumberOfGroupResults;

    public GetFeaturedContentRequestBodyType() {
    }

    public GetFeaturedContentRequestBodyType(
           java.lang.String locale,
           java.lang.String password,
           java.lang.String username,
           java.lang.String applicationID,
           java.lang.String kbase_tags,
           java.math.BigInteger maxNumberOfUnitsPerGroup,
           java.math.BigInteger paginationStartIndex,
           java.math.BigInteger maxNumberOfGroupResults) {
           this.locale = locale;
           this.password = password;
           this.username = username;
           this.applicationID = applicationID;
           this.kbase_tags = kbase_tags;
           this.maxNumberOfUnitsPerGroup = maxNumberOfUnitsPerGroup;
           this.paginationStartIndex = paginationStartIndex;
           this.maxNumberOfGroupResults = maxNumberOfGroupResults;
    }


    /**
     * Gets the locale value for this GetFeaturedContentRequestBodyType.
     * 
     * @return locale
     */
    public java.lang.String getLocale() {
        return locale;
    }


    /**
     * Sets the locale value for this GetFeaturedContentRequestBodyType.
     * 
     * @param locale
     */
    public void setLocale(java.lang.String locale) {
        this.locale = locale;
    }


    /**
     * Gets the password value for this GetFeaturedContentRequestBodyType.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this GetFeaturedContentRequestBodyType.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the username value for this GetFeaturedContentRequestBodyType.
     * 
     * @return username
     */
    public java.lang.String getUsername() {
        return username;
    }


    /**
     * Sets the username value for this GetFeaturedContentRequestBodyType.
     * 
     * @param username
     */
    public void setUsername(java.lang.String username) {
        this.username = username;
    }


    /**
     * Gets the applicationID value for this GetFeaturedContentRequestBodyType.
     * 
     * @return applicationID
     */
    public java.lang.String getApplicationID() {
        return applicationID;
    }


    /**
     * Sets the applicationID value for this GetFeaturedContentRequestBodyType.
     * 
     * @param applicationID
     */
    public void setApplicationID(java.lang.String applicationID) {
        this.applicationID = applicationID;
    }


    /**
     * Gets the kbase_tags value for this GetFeaturedContentRequestBodyType.
     * 
     * @return kbase_tags
     */
    public java.lang.String getKbase_tags() {
        return kbase_tags;
    }


    /**
     * Sets the kbase_tags value for this GetFeaturedContentRequestBodyType.
     * 
     * @param kbase_tags
     */
    public void setKbase_tags(java.lang.String kbase_tags) {
        this.kbase_tags = kbase_tags;
    }


    /**
     * Gets the maxNumberOfUnitsPerGroup value for this GetFeaturedContentRequestBodyType.
     * 
     * @return maxNumberOfUnitsPerGroup
     */
    public java.math.BigInteger getMaxNumberOfUnitsPerGroup() {
        return maxNumberOfUnitsPerGroup;
    }


    /**
     * Sets the maxNumberOfUnitsPerGroup value for this GetFeaturedContentRequestBodyType.
     * 
     * @param maxNumberOfUnitsPerGroup
     */
    public void setMaxNumberOfUnitsPerGroup(java.math.BigInteger maxNumberOfUnitsPerGroup) {
        this.maxNumberOfUnitsPerGroup = maxNumberOfUnitsPerGroup;
    }


    /**
     * Gets the paginationStartIndex value for this GetFeaturedContentRequestBodyType.
     * 
     * @return paginationStartIndex
     */
    public java.math.BigInteger getPaginationStartIndex() {
        return paginationStartIndex;
    }


    /**
     * Sets the paginationStartIndex value for this GetFeaturedContentRequestBodyType.
     * 
     * @param paginationStartIndex
     */
    public void setPaginationStartIndex(java.math.BigInteger paginationStartIndex) {
        this.paginationStartIndex = paginationStartIndex;
    }


    /**
     * Gets the maxNumberOfGroupResults value for this GetFeaturedContentRequestBodyType.
     * 
     * @return maxNumberOfGroupResults
     */
    public java.math.BigInteger getMaxNumberOfGroupResults() {
        return maxNumberOfGroupResults;
    }


    /**
     * Sets the maxNumberOfGroupResults value for this GetFeaturedContentRequestBodyType.
     * 
     * @param maxNumberOfGroupResults
     */
    public void setMaxNumberOfGroupResults(java.math.BigInteger maxNumberOfGroupResults) {
        this.maxNumberOfGroupResults = maxNumberOfGroupResults;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetFeaturedContentRequestBodyType)) return false;
        GetFeaturedContentRequestBodyType other = (GetFeaturedContentRequestBodyType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.locale==null && other.getLocale()==null) || 
             (this.locale!=null &&
              this.locale.equals(other.getLocale()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.username==null && other.getUsername()==null) || 
             (this.username!=null &&
              this.username.equals(other.getUsername()))) &&
            ((this.applicationID==null && other.getApplicationID()==null) || 
             (this.applicationID!=null &&
              this.applicationID.equals(other.getApplicationID()))) &&
            ((this.kbase_tags==null && other.getKbase_tags()==null) || 
             (this.kbase_tags!=null &&
              this.kbase_tags.equals(other.getKbase_tags()))) &&
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
        if (getLocale() != null) {
            _hashCode += getLocale().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getUsername() != null) {
            _hashCode += getUsername().hashCode();
        }
        if (getApplicationID() != null) {
            _hashCode += getApplicationID().hashCode();
        }
        if (getKbase_tags() != null) {
            _hashCode += getKbase_tags().hashCode();
        }
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
        new org.apache.axis.description.TypeDesc(GetFeaturedContentRequestBodyType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/FeaturedV1Service.wsdl", "GetFeaturedContentRequestBodyType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("locale");
        elemField.setXmlName(new javax.xml.namespace.QName("", "locale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("", "password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("username");
        elemField.setXmlName(new javax.xml.namespace.QName("", "username"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("kbase_tags");
        elemField.setXmlName(new javax.xml.namespace.QName("", "kbase_tags"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
