/**
 * ReorderBookmarkAndFolderRequestBodyType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl;

public class ReorderBookmarkAndFolderRequestBodyType  implements java.io.Serializable {
    private java.lang.String applicationID;

    private java.lang.String contentID;

    private java.lang.String direction;

    private java.math.BigInteger folderID;

    private java.lang.String password;

    private java.lang.String userName;

    private java.math.BigInteger numMoved;

    private java.math.BigInteger destinationFolderID;

    public ReorderBookmarkAndFolderRequestBodyType() {
    }

    public ReorderBookmarkAndFolderRequestBodyType(
           java.lang.String applicationID,
           java.lang.String contentID,
           java.lang.String direction,
           java.math.BigInteger folderID,
           java.lang.String password,
           java.lang.String userName,
           java.math.BigInteger numMoved,
           java.math.BigInteger destinationFolderID) {
           this.applicationID = applicationID;
           this.contentID = contentID;
           this.direction = direction;
           this.folderID = folderID;
           this.password = password;
           this.userName = userName;
           this.numMoved = numMoved;
           this.destinationFolderID = destinationFolderID;
    }


    /**
     * Gets the applicationID value for this ReorderBookmarkAndFolderRequestBodyType.
     * 
     * @return applicationID
     */
    public java.lang.String getApplicationID() {
        return applicationID;
    }


    /**
     * Sets the applicationID value for this ReorderBookmarkAndFolderRequestBodyType.
     * 
     * @param applicationID
     */
    public void setApplicationID(java.lang.String applicationID) {
        this.applicationID = applicationID;
    }


    /**
     * Gets the contentID value for this ReorderBookmarkAndFolderRequestBodyType.
     * 
     * @return contentID
     */
    public java.lang.String getContentID() {
        return contentID;
    }


    /**
     * Sets the contentID value for this ReorderBookmarkAndFolderRequestBodyType.
     * 
     * @param contentID
     */
    public void setContentID(java.lang.String contentID) {
        this.contentID = contentID;
    }


    /**
     * Gets the direction value for this ReorderBookmarkAndFolderRequestBodyType.
     * 
     * @return direction
     */
    public java.lang.String getDirection() {
        return direction;
    }


    /**
     * Sets the direction value for this ReorderBookmarkAndFolderRequestBodyType.
     * 
     * @param direction
     */
    public void setDirection(java.lang.String direction) {
        this.direction = direction;
    }


    /**
     * Gets the folderID value for this ReorderBookmarkAndFolderRequestBodyType.
     * 
     * @return folderID
     */
    public java.math.BigInteger getFolderID() {
        return folderID;
    }


    /**
     * Sets the folderID value for this ReorderBookmarkAndFolderRequestBodyType.
     * 
     * @param folderID
     */
    public void setFolderID(java.math.BigInteger folderID) {
        this.folderID = folderID;
    }


    /**
     * Gets the password value for this ReorderBookmarkAndFolderRequestBodyType.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this ReorderBookmarkAndFolderRequestBodyType.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the userName value for this ReorderBookmarkAndFolderRequestBodyType.
     * 
     * @return userName
     */
    public java.lang.String getUserName() {
        return userName;
    }


    /**
     * Sets the userName value for this ReorderBookmarkAndFolderRequestBodyType.
     * 
     * @param userName
     */
    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }


    /**
     * Gets the numMoved value for this ReorderBookmarkAndFolderRequestBodyType.
     * 
     * @return numMoved
     */
    public java.math.BigInteger getNumMoved() {
        return numMoved;
    }


    /**
     * Sets the numMoved value for this ReorderBookmarkAndFolderRequestBodyType.
     * 
     * @param numMoved
     */
    public void setNumMoved(java.math.BigInteger numMoved) {
        this.numMoved = numMoved;
    }


    /**
     * Gets the destinationFolderID value for this ReorderBookmarkAndFolderRequestBodyType.
     * 
     * @return destinationFolderID
     */
    public java.math.BigInteger getDestinationFolderID() {
        return destinationFolderID;
    }


    /**
     * Sets the destinationFolderID value for this ReorderBookmarkAndFolderRequestBodyType.
     * 
     * @param destinationFolderID
     */
    public void setDestinationFolderID(java.math.BigInteger destinationFolderID) {
        this.destinationFolderID = destinationFolderID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReorderBookmarkAndFolderRequestBodyType)) return false;
        ReorderBookmarkAndFolderRequestBodyType other = (ReorderBookmarkAndFolderRequestBodyType) obj;
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
            ((this.direction==null && other.getDirection()==null) || 
             (this.direction!=null &&
              this.direction.equals(other.getDirection()))) &&
            ((this.folderID==null && other.getFolderID()==null) || 
             (this.folderID!=null &&
              this.folderID.equals(other.getFolderID()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.userName==null && other.getUserName()==null) || 
             (this.userName!=null &&
              this.userName.equals(other.getUserName()))) &&
            ((this.numMoved==null && other.getNumMoved()==null) || 
             (this.numMoved!=null &&
              this.numMoved.equals(other.getNumMoved()))) &&
            ((this.destinationFolderID==null && other.getDestinationFolderID()==null) || 
             (this.destinationFolderID!=null &&
              this.destinationFolderID.equals(other.getDestinationFolderID())));
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
        if (getDirection() != null) {
            _hashCode += getDirection().hashCode();
        }
        if (getFolderID() != null) {
            _hashCode += getFolderID().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getUserName() != null) {
            _hashCode += getUserName().hashCode();
        }
        if (getNumMoved() != null) {
            _hashCode += getNumMoved().hashCode();
        }
        if (getDestinationFolderID() != null) {
            _hashCode += getDestinationFolderID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReorderBookmarkAndFolderRequestBodyType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ReorderBookmarkAndFolderRequestBodyType"));
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
        elemField.setFieldName("direction");
        elemField.setXmlName(new javax.xml.namespace.QName("", "direction"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("folderID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "folderID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("", "password"));
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
        elemField.setFieldName("numMoved");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numMoved"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destinationFolderID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "destinationFolderID"));
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
