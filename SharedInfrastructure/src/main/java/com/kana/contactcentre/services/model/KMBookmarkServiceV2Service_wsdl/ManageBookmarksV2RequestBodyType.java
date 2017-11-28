/**
 * ManageBookmarksV2RequestBodyType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl;

public class ManageBookmarksV2RequestBodyType  implements java.io.Serializable {
    private java.lang.String userAction;

    private java.lang.String userName;

    private java.lang.String applicationID;

    private java.lang.String contentID;

    private java.lang.String localeName;

    private java.lang.String password;

    private java.lang.String folderID;

    private java.lang.String folderName;

    public ManageBookmarksV2RequestBodyType() {
    }

    public ManageBookmarksV2RequestBodyType(
           java.lang.String userAction,
           java.lang.String userName,
           java.lang.String applicationID,
           java.lang.String contentID,
           java.lang.String localeName,
           java.lang.String password,
           java.lang.String folderID,
           java.lang.String folderName) {
           this.userAction = userAction;
           this.userName = userName;
           this.applicationID = applicationID;
           this.contentID = contentID;
           this.localeName = localeName;
           this.password = password;
           this.folderID = folderID;
           this.folderName = folderName;
    }


    /**
     * Gets the userAction value for this ManageBookmarksV2RequestBodyType.
     * 
     * @return userAction
     */
    public java.lang.String getUserAction() {
        return userAction;
    }


    /**
     * Sets the userAction value for this ManageBookmarksV2RequestBodyType.
     * 
     * @param userAction
     */
    public void setUserAction(java.lang.String userAction) {
        this.userAction = userAction;
    }


    /**
     * Gets the userName value for this ManageBookmarksV2RequestBodyType.
     * 
     * @return userName
     */
    public java.lang.String getUserName() {
        return userName;
    }


    /**
     * Sets the userName value for this ManageBookmarksV2RequestBodyType.
     * 
     * @param userName
     */
    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }


    /**
     * Gets the applicationID value for this ManageBookmarksV2RequestBodyType.
     * 
     * @return applicationID
     */
    public java.lang.String getApplicationID() {
        return applicationID;
    }


    /**
     * Sets the applicationID value for this ManageBookmarksV2RequestBodyType.
     * 
     * @param applicationID
     */
    public void setApplicationID(java.lang.String applicationID) {
        this.applicationID = applicationID;
    }


    /**
     * Gets the contentID value for this ManageBookmarksV2RequestBodyType.
     * 
     * @return contentID
     */
    public java.lang.String getContentID() {
        return contentID;
    }


    /**
     * Sets the contentID value for this ManageBookmarksV2RequestBodyType.
     * 
     * @param contentID
     */
    public void setContentID(java.lang.String contentID) {
        this.contentID = contentID;
    }


    /**
     * Gets the localeName value for this ManageBookmarksV2RequestBodyType.
     * 
     * @return localeName
     */
    public java.lang.String getLocaleName() {
        return localeName;
    }


    /**
     * Sets the localeName value for this ManageBookmarksV2RequestBodyType.
     * 
     * @param localeName
     */
    public void setLocaleName(java.lang.String localeName) {
        this.localeName = localeName;
    }


    /**
     * Gets the password value for this ManageBookmarksV2RequestBodyType.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this ManageBookmarksV2RequestBodyType.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the folderID value for this ManageBookmarksV2RequestBodyType.
     * 
     * @return folderID
     */
    public java.lang.String getFolderID() {
        return folderID;
    }


    /**
     * Sets the folderID value for this ManageBookmarksV2RequestBodyType.
     * 
     * @param folderID
     */
    public void setFolderID(java.lang.String folderID) {
        this.folderID = folderID;
    }


    /**
     * Gets the folderName value for this ManageBookmarksV2RequestBodyType.
     * 
     * @return folderName
     */
    public java.lang.String getFolderName() {
        return folderName;
    }


    /**
     * Sets the folderName value for this ManageBookmarksV2RequestBodyType.
     * 
     * @param folderName
     */
    public void setFolderName(java.lang.String folderName) {
        this.folderName = folderName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ManageBookmarksV2RequestBodyType)) return false;
        ManageBookmarksV2RequestBodyType other = (ManageBookmarksV2RequestBodyType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.userAction==null && other.getUserAction()==null) || 
             (this.userAction!=null &&
              this.userAction.equals(other.getUserAction()))) &&
            ((this.userName==null && other.getUserName()==null) || 
             (this.userName!=null &&
              this.userName.equals(other.getUserName()))) &&
            ((this.applicationID==null && other.getApplicationID()==null) || 
             (this.applicationID!=null &&
              this.applicationID.equals(other.getApplicationID()))) &&
            ((this.contentID==null && other.getContentID()==null) || 
             (this.contentID!=null &&
              this.contentID.equals(other.getContentID()))) &&
            ((this.localeName==null && other.getLocaleName()==null) || 
             (this.localeName!=null &&
              this.localeName.equals(other.getLocaleName()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.folderID==null && other.getFolderID()==null) || 
             (this.folderID!=null &&
              this.folderID.equals(other.getFolderID()))) &&
            ((this.folderName==null && other.getFolderName()==null) || 
             (this.folderName!=null &&
              this.folderName.equals(other.getFolderName())));
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
        if (getUserAction() != null) {
            _hashCode += getUserAction().hashCode();
        }
        if (getUserName() != null) {
            _hashCode += getUserName().hashCode();
        }
        if (getApplicationID() != null) {
            _hashCode += getApplicationID().hashCode();
        }
        if (getContentID() != null) {
            _hashCode += getContentID().hashCode();
        }
        if (getLocaleName() != null) {
            _hashCode += getLocaleName().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getFolderID() != null) {
            _hashCode += getFolderID().hashCode();
        }
        if (getFolderName() != null) {
            _hashCode += getFolderName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ManageBookmarksV2RequestBodyType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ManageBookmarksV2RequestBodyType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userAction");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userAction"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userName"));
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
        elemField.setFieldName("contentID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contentID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("localeName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "localeName"));
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
        elemField.setFieldName("folderID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "folderID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("folderName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "folderName"));
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
