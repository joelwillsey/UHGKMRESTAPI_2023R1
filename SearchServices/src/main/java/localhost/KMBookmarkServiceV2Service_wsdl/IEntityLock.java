/**
 * IEntityLock.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.KMBookmarkServiceV2Service_wsdl;

public class IEntityLock  implements java.io.Serializable {
    private java.util.Date lockTimestamp;

    private java.lang.String entityId;

    private java.math.BigInteger entityDefId;

    private java.lang.String currentUsername;

    private java.util.Date lockLastModifiedTimestamp;

    private java.lang.String lockUsername;

    public IEntityLock() {
    }

    public IEntityLock(
           java.util.Date lockTimestamp,
           java.lang.String entityId,
           java.math.BigInteger entityDefId,
           java.lang.String currentUsername,
           java.util.Date lockLastModifiedTimestamp,
           java.lang.String lockUsername) {
           this.lockTimestamp = lockTimestamp;
           this.entityId = entityId;
           this.entityDefId = entityDefId;
           this.currentUsername = currentUsername;
           this.lockLastModifiedTimestamp = lockLastModifiedTimestamp;
           this.lockUsername = lockUsername;
    }


    /**
     * Gets the lockTimestamp value for this IEntityLock.
     * 
     * @return lockTimestamp
     */
    public java.util.Date getLockTimestamp() {
        return lockTimestamp;
    }


    /**
     * Sets the lockTimestamp value for this IEntityLock.
     * 
     * @param lockTimestamp
     */
    public void setLockTimestamp(java.util.Date lockTimestamp) {
        this.lockTimestamp = lockTimestamp;
    }


    /**
     * Gets the entityId value for this IEntityLock.
     * 
     * @return entityId
     */
    public java.lang.String getEntityId() {
        return entityId;
    }


    /**
     * Sets the entityId value for this IEntityLock.
     * 
     * @param entityId
     */
    public void setEntityId(java.lang.String entityId) {
        this.entityId = entityId;
    }


    /**
     * Gets the entityDefId value for this IEntityLock.
     * 
     * @return entityDefId
     */
    public java.math.BigInteger getEntityDefId() {
        return entityDefId;
    }


    /**
     * Sets the entityDefId value for this IEntityLock.
     * 
     * @param entityDefId
     */
    public void setEntityDefId(java.math.BigInteger entityDefId) {
        this.entityDefId = entityDefId;
    }


    /**
     * Gets the currentUsername value for this IEntityLock.
     * 
     * @return currentUsername
     */
    public java.lang.String getCurrentUsername() {
        return currentUsername;
    }


    /**
     * Sets the currentUsername value for this IEntityLock.
     * 
     * @param currentUsername
     */
    public void setCurrentUsername(java.lang.String currentUsername) {
        this.currentUsername = currentUsername;
    }


    /**
     * Gets the lockLastModifiedTimestamp value for this IEntityLock.
     * 
     * @return lockLastModifiedTimestamp
     */
    public java.util.Date getLockLastModifiedTimestamp() {
        return lockLastModifiedTimestamp;
    }


    /**
     * Sets the lockLastModifiedTimestamp value for this IEntityLock.
     * 
     * @param lockLastModifiedTimestamp
     */
    public void setLockLastModifiedTimestamp(java.util.Date lockLastModifiedTimestamp) {
        this.lockLastModifiedTimestamp = lockLastModifiedTimestamp;
    }


    /**
     * Gets the lockUsername value for this IEntityLock.
     * 
     * @return lockUsername
     */
    public java.lang.String getLockUsername() {
        return lockUsername;
    }


    /**
     * Sets the lockUsername value for this IEntityLock.
     * 
     * @param lockUsername
     */
    public void setLockUsername(java.lang.String lockUsername) {
        this.lockUsername = lockUsername;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IEntityLock)) return false;
        IEntityLock other = (IEntityLock) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.lockTimestamp==null && other.getLockTimestamp()==null) || 
             (this.lockTimestamp!=null &&
              this.lockTimestamp.equals(other.getLockTimestamp()))) &&
            ((this.entityId==null && other.getEntityId()==null) || 
             (this.entityId!=null &&
              this.entityId.equals(other.getEntityId()))) &&
            ((this.entityDefId==null && other.getEntityDefId()==null) || 
             (this.entityDefId!=null &&
              this.entityDefId.equals(other.getEntityDefId()))) &&
            ((this.currentUsername==null && other.getCurrentUsername()==null) || 
             (this.currentUsername!=null &&
              this.currentUsername.equals(other.getCurrentUsername()))) &&
            ((this.lockLastModifiedTimestamp==null && other.getLockLastModifiedTimestamp()==null) || 
             (this.lockLastModifiedTimestamp!=null &&
              this.lockLastModifiedTimestamp.equals(other.getLockLastModifiedTimestamp()))) &&
            ((this.lockUsername==null && other.getLockUsername()==null) || 
             (this.lockUsername!=null &&
              this.lockUsername.equals(other.getLockUsername())));
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
        if (getLockTimestamp() != null) {
            _hashCode += getLockTimestamp().hashCode();
        }
        if (getEntityId() != null) {
            _hashCode += getEntityId().hashCode();
        }
        if (getEntityDefId() != null) {
            _hashCode += getEntityDefId().hashCode();
        }
        if (getCurrentUsername() != null) {
            _hashCode += getCurrentUsername().hashCode();
        }
        if (getLockLastModifiedTimestamp() != null) {
            _hashCode += getLockLastModifiedTimestamp().hashCode();
        }
        if (getLockUsername() != null) {
            _hashCode += getLockUsername().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IEntityLock.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "IEntityLock"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lockTimestamp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lockTimestamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entityId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "entityId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entityDefId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "entityDefId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currentUsername");
        elemField.setXmlName(new javax.xml.namespace.QName("", "currentUsername"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lockLastModifiedTimestamp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lockLastModifiedTimestamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lockUsername");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lockUsername"));
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
