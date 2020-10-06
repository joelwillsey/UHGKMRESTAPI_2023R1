/**
 * MarkAsViewedRequestBodyType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.SearchV1Service_wsdl;

public class MarkAsViewedRequestBodyType  implements java.io.Serializable {
    private java.lang.String applicationID;

    private java.lang.String contentID;

    private java.lang.String username;

    private java.lang.String password;

    private java.lang.String locale;

    private java.lang.String siteName = "";
    
    private java.lang.String externalSearchId;
    
    private java.math.BigInteger rank;
    
    private java.math.BigInteger searchResultCount;

    public MarkAsViewedRequestBodyType() {
    }

    public MarkAsViewedRequestBodyType(
           java.lang.String applicationID,
           java.lang.String contentID,
           java.lang.String username,
           java.lang.String password,
           java.lang.String locale,
           java.lang.String siteName,
           java.lang.String externalSearchId,
           java.math.BigInteger rank,
           java.math.BigInteger searchResultCount) {
           this.applicationID = applicationID;
           this.contentID = contentID;
           this.username = username;
           this.password = password;
           this.locale = locale;
           this.siteName = siteName;
           this.externalSearchId = externalSearchId;
           this.rank = rank;
           this.searchResultCount = searchResultCount;
    }


    /**
     * Gets the applicationID value for this MarkAsViewedRequestBodyType.
     * 
     * @return applicationID
     */
    public java.lang.String getApplicationID() {
        return applicationID;
    }


    /**
     * Sets the applicationID value for this MarkAsViewedRequestBodyType.
     * 
     * @param applicationID
     */
    public void setApplicationID(java.lang.String applicationID) {
        this.applicationID = applicationID;
    }


    /**
     * Gets the contentID value for this MarkAsViewedRequestBodyType.
     * 
     * @return contentID
     */
    public java.lang.String getContentID() {
        return contentID;
    }


    /**
     * Sets the contentID value for this MarkAsViewedRequestBodyType.
     * 
     * @param contentID
     */
    public void setContentID(java.lang.String contentID) {
        this.contentID = contentID;
    }


    /**
     * Gets the username value for this MarkAsViewedRequestBodyType.
     * 
     * @return username
     */
    public java.lang.String getUsername() {
        return username;
    }


    /**
     * Sets the username value for this MarkAsViewedRequestBodyType.
     * 
     * @param username
     */
    public void setUsername(java.lang.String username) {
        this.username = username;
    }


    /**
     * Gets the password value for this MarkAsViewedRequestBodyType.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this MarkAsViewedRequestBodyType.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the locale value for this MarkAsViewedRequestBodyType.
     * 
     * @return locale
     */
    public java.lang.String getLocale() {
        return locale;
    }


    /**
     * Sets the locale value for this MarkAsViewedRequestBodyType.
     * 
     * @param locale
     */
    public void setLocale(java.lang.String locale) {
        this.locale = locale;
    }


    /**
     * Gets the siteName value for this MarkAsViewedRequestBodyType.
     * 
     * @return siteName
     */
    public java.lang.String getSiteName() {
        return siteName;
    }


    /**
     * Sets the siteName value for this MarkAsViewedRequestBodyType.
     * 
     * @param siteName
     */
    public void setSiteName(java.lang.String siteName) {
        this.siteName = siteName;
    }
    
    
    /**
     * Gets the externalSearchId value for this MarkAsViewedRequestBodyType.
     * 
     * @return externalSearchId
     */
    public java.lang.String getexternalSearchId() {
        return externalSearchId;
    }


    /**
     * Sets the externalSearchId value for this MarkAsViewedRequestBodyType.
     * 
     * @param externalSearchId
     */
    public void setexternalSearchId(java.lang.String externalSearchId) {
        this.externalSearchId = externalSearchId;
    }
    
    
    /**
     * Gets the rank value for this MarkAsViewedRequestBodyType.
     * 
     * @return rank
     */
    public java.math.BigInteger getrank() {
        return rank;
    }


    /**
     * Sets the rank value for this MarkAsViewedRequestBodyType.
     * 
     * @param rank
     */
    public void setrank(java.math.BigInteger rank) {
        this.rank = rank;
    }
    
    
    
    /**
     * Gets the searchResultCount value for this MarkAsViewedRequestBodyType.
     * 
     * @return searchResultCount
     */
    public java.math.BigInteger getsearchResultCount() {
        return searchResultCount;
    }


    /**
     * Sets the searchResultCount value for this MarkAsViewedRequestBodyType.
     * 
     * @param searchResultCount
     */
    public void setsearchResultCount(java.math.BigInteger searchResultCount) {
        this.searchResultCount = searchResultCount;
    }
    
    

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MarkAsViewedRequestBodyType)) return false;
        MarkAsViewedRequestBodyType other = (MarkAsViewedRequestBodyType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.applicationID==null && other.getApplicationID()==null) || 
             (this.applicationID!=null &&
              this.applicationID.equals(other.getApplicationID()))) &&
            ((this.contentID==null && other.getContentID()==null) || 
             (this.contentID!=null &&
              this.contentID.equals(other.getContentID()))) &&
            ((this.username==null && other.getUsername()==null) || 
             (this.username!=null &&
              this.username.equals(other.getUsername()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.locale==null && other.getLocale()==null) || 
             (this.locale!=null &&
              this.locale.equals(other.getLocale()))) &&
            ((this.siteName==null && other.getSiteName()==null) || 
             (this.siteName!=null &&
              this.siteName.equals(other.getSiteName()))) &&
            ((this.externalSearchId==null && other.getexternalSearchId()==null) || 
             (this.externalSearchId!=null &&
              this.externalSearchId.equals(other.getexternalSearchId()))) &&        
            ((this.rank==null && other.getrank()==null) || 
             (this.rank!=null &&
              this.rank.equals(other.getrank()))) &&        
            ((this.searchResultCount==null && other.getsearchResultCount()==null) || 
             (this.searchResultCount!=null &&
              this.searchResultCount.equals(other.getsearchResultCount())));
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
        if (getApplicationID() != null) {
            _hashCode += getApplicationID().hashCode();
        }
        if (getContentID() != null) {
            _hashCode += getContentID().hashCode();
        }
        if (getUsername() != null) {
            _hashCode += getUsername().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getLocale() != null) {
            _hashCode += getLocale().hashCode();
        }
        if (getSiteName() != null) {
            _hashCode += getSiteName().hashCode();
        }
        if (getexternalSearchId() != null) {
            _hashCode += getexternalSearchId().hashCode();
        }
        if (getrank() != null) {
            _hashCode += getrank().hashCode();
        }
        if (getsearchResultCount() != null) {
            _hashCode += getsearchResultCount().hashCode();
        }
               
        
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MarkAsViewedRequestBodyType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "MarkAsViewedRequestBodyType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contentID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contentID"));
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
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("", "password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("locale");
        elemField.setXmlName(new javax.xml.namespace.QName("", "locale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("siteName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "siteName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("externalSearchId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "externalSearchId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rank");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rank"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("searchResultCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "searchResultCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
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
