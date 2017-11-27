/**
 * ListAllBookmarksV2RequestBodyType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl;

public class ListAllBookmarksV2RequestBodyType  implements java.io.Serializable {
    private java.lang.String applicationID;

    private java.lang.String password;

    private java.lang.String sortColumnName;

    private java.lang.String sortOrder;

    private java.lang.String userName;

    public ListAllBookmarksV2RequestBodyType() {
    }

    public ListAllBookmarksV2RequestBodyType(
           java.lang.String applicationID,
           java.lang.String password,
           java.lang.String sortColumnName,
           java.lang.String sortOrder,
           java.lang.String userName) {
           this.applicationID = applicationID;
           this.password = password;
           this.sortColumnName = sortColumnName;
           this.sortOrder = sortOrder;
           this.userName = userName;
    }


    /**
     * Gets the applicationID value for this ListAllBookmarksV2RequestBodyType.
     * 
     * @return applicationID
     */
    public java.lang.String getApplicationID() {
        return applicationID;
    }


    /**
     * Sets the applicationID value for this ListAllBookmarksV2RequestBodyType.
     * 
     * @param applicationID
     */
    public void setApplicationID(java.lang.String applicationID) {
        this.applicationID = applicationID;
    }


    /**
     * Gets the password value for this ListAllBookmarksV2RequestBodyType.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this ListAllBookmarksV2RequestBodyType.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the sortColumnName value for this ListAllBookmarksV2RequestBodyType.
     * 
     * @return sortColumnName
     */
    public java.lang.String getSortColumnName() {
        return sortColumnName;
    }


    /**
     * Sets the sortColumnName value for this ListAllBookmarksV2RequestBodyType.
     * 
     * @param sortColumnName
     */
    public void setSortColumnName(java.lang.String sortColumnName) {
        this.sortColumnName = sortColumnName;
    }


    /**
     * Gets the sortOrder value for this ListAllBookmarksV2RequestBodyType.
     * 
     * @return sortOrder
     */
    public java.lang.String getSortOrder() {
        return sortOrder;
    }


    /**
     * Sets the sortOrder value for this ListAllBookmarksV2RequestBodyType.
     * 
     * @param sortOrder
     */
    public void setSortOrder(java.lang.String sortOrder) {
        this.sortOrder = sortOrder;
    }


    /**
     * Gets the userName value for this ListAllBookmarksV2RequestBodyType.
     * 
     * @return userName
     */
    public java.lang.String getUserName() {
        return userName;
    }


    /**
     * Sets the userName value for this ListAllBookmarksV2RequestBodyType.
     * 
     * @param userName
     */
    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListAllBookmarksV2RequestBodyType)) return false;
        ListAllBookmarksV2RequestBodyType other = (ListAllBookmarksV2RequestBodyType) obj;
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
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.sortColumnName==null && other.getSortColumnName()==null) || 
             (this.sortColumnName!=null &&
              this.sortColumnName.equals(other.getSortColumnName()))) &&
            ((this.sortOrder==null && other.getSortOrder()==null) || 
             (this.sortOrder!=null &&
              this.sortOrder.equals(other.getSortOrder()))) &&
            ((this.userName==null && other.getUserName()==null) || 
             (this.userName!=null &&
              this.userName.equals(other.getUserName())));
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
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getSortColumnName() != null) {
            _hashCode += getSortColumnName().hashCode();
        }
        if (getSortOrder() != null) {
            _hashCode += getSortOrder().hashCode();
        }
        if (getUserName() != null) {
            _hashCode += getUserName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ListAllBookmarksV2RequestBodyType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "ListAllBookmarksV2RequestBodyType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationID"));
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
        elemField.setFieldName("sortColumnName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sortColumnName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sortOrder");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sortOrder"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userName"));
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
