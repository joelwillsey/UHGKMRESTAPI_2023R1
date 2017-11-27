/**
 * ContentBookmark.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl;

public class ContentBookmark  implements java.io.Serializable {
    private java.lang.String synopsis;

    private com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.IEvaId bookmarkOwnerTypeId;

    private java.lang.String typeSystemName;

    private boolean isFeatured;

    private java.math.BigInteger sequenceNumber;

    private java.lang.String title;

    private java.lang.String viewId;

    private com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.EIContentType contentType;

    private com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.IEvaId bookmarkOwnerId;

    private com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.IPersistableEntity bookmarkOwner;

    private java.util.Date createdDate;

    private com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.IEvaId contentTypeId;

    public ContentBookmark() {
    }

    public ContentBookmark(
           java.lang.String synopsis,
           com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.IEvaId bookmarkOwnerTypeId,
           java.lang.String typeSystemName,
           boolean isFeatured,
           java.math.BigInteger sequenceNumber,
           java.lang.String title,
           java.lang.String viewId,
           com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.EIContentType contentType,
           com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.IEvaId bookmarkOwnerId,
           com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.IPersistableEntity bookmarkOwner,
           java.util.Date createdDate,
           com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.IEvaId contentTypeId) {
           this.synopsis = synopsis;
           this.bookmarkOwnerTypeId = bookmarkOwnerTypeId;
           this.typeSystemName = typeSystemName;
           this.isFeatured = isFeatured;
           this.sequenceNumber = sequenceNumber;
           this.title = title;
           this.viewId = viewId;
           this.contentType = contentType;
           this.bookmarkOwnerId = bookmarkOwnerId;
           this.bookmarkOwner = bookmarkOwner;
           this.createdDate = createdDate;
           this.contentTypeId = contentTypeId;
    }


    /**
     * Gets the synopsis value for this ContentBookmark.
     * 
     * @return synopsis
     */
    public java.lang.String getSynopsis() {
        return synopsis;
    }


    /**
     * Sets the synopsis value for this ContentBookmark.
     * 
     * @param synopsis
     */
    public void setSynopsis(java.lang.String synopsis) {
        this.synopsis = synopsis;
    }


    /**
     * Gets the bookmarkOwnerTypeId value for this ContentBookmark.
     * 
     * @return bookmarkOwnerTypeId
     */
    public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.IEvaId getBookmarkOwnerTypeId() {
        return bookmarkOwnerTypeId;
    }


    /**
     * Sets the bookmarkOwnerTypeId value for this ContentBookmark.
     * 
     * @param bookmarkOwnerTypeId
     */
    public void setBookmarkOwnerTypeId(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.IEvaId bookmarkOwnerTypeId) {
        this.bookmarkOwnerTypeId = bookmarkOwnerTypeId;
    }


    /**
     * Gets the typeSystemName value for this ContentBookmark.
     * 
     * @return typeSystemName
     */
    public java.lang.String getTypeSystemName() {
        return typeSystemName;
    }


    /**
     * Sets the typeSystemName value for this ContentBookmark.
     * 
     * @param typeSystemName
     */
    public void setTypeSystemName(java.lang.String typeSystemName) {
        this.typeSystemName = typeSystemName;
    }


    /**
     * Gets the isFeatured value for this ContentBookmark.
     * 
     * @return isFeatured
     */
    public boolean isIsFeatured() {
        return isFeatured;
    }


    /**
     * Sets the isFeatured value for this ContentBookmark.
     * 
     * @param isFeatured
     */
    public void setIsFeatured(boolean isFeatured) {
        this.isFeatured = isFeatured;
    }


    /**
     * Gets the sequenceNumber value for this ContentBookmark.
     * 
     * @return sequenceNumber
     */
    public java.math.BigInteger getSequenceNumber() {
        return sequenceNumber;
    }


    /**
     * Sets the sequenceNumber value for this ContentBookmark.
     * 
     * @param sequenceNumber
     */
    public void setSequenceNumber(java.math.BigInteger sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }


    /**
     * Gets the title value for this ContentBookmark.
     * 
     * @return title
     */
    public java.lang.String getTitle() {
        return title;
    }


    /**
     * Sets the title value for this ContentBookmark.
     * 
     * @param title
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }


    /**
     * Gets the viewId value for this ContentBookmark.
     * 
     * @return viewId
     */
    public java.lang.String getViewId() {
        return viewId;
    }


    /**
     * Sets the viewId value for this ContentBookmark.
     * 
     * @param viewId
     */
    public void setViewId(java.lang.String viewId) {
        this.viewId = viewId;
    }


    /**
     * Gets the contentType value for this ContentBookmark.
     * 
     * @return contentType
     */
    public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.EIContentType getContentType() {
        return contentType;
    }


    /**
     * Sets the contentType value for this ContentBookmark.
     * 
     * @param contentType
     */
    public void setContentType(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.EIContentType contentType) {
        this.contentType = contentType;
    }


    /**
     * Gets the bookmarkOwnerId value for this ContentBookmark.
     * 
     * @return bookmarkOwnerId
     */
    public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.IEvaId getBookmarkOwnerId() {
        return bookmarkOwnerId;
    }


    /**
     * Sets the bookmarkOwnerId value for this ContentBookmark.
     * 
     * @param bookmarkOwnerId
     */
    public void setBookmarkOwnerId(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.IEvaId bookmarkOwnerId) {
        this.bookmarkOwnerId = bookmarkOwnerId;
    }


    /**
     * Gets the bookmarkOwner value for this ContentBookmark.
     * 
     * @return bookmarkOwner
     */
    public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.IPersistableEntity getBookmarkOwner() {
        return bookmarkOwner;
    }


    /**
     * Sets the bookmarkOwner value for this ContentBookmark.
     * 
     * @param bookmarkOwner
     */
    public void setBookmarkOwner(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.IPersistableEntity bookmarkOwner) {
        this.bookmarkOwner = bookmarkOwner;
    }


    /**
     * Gets the createdDate value for this ContentBookmark.
     * 
     * @return createdDate
     */
    public java.util.Date getCreatedDate() {
        return createdDate;
    }


    /**
     * Sets the createdDate value for this ContentBookmark.
     * 
     * @param createdDate
     */
    public void setCreatedDate(java.util.Date createdDate) {
        this.createdDate = createdDate;
    }


    /**
     * Gets the contentTypeId value for this ContentBookmark.
     * 
     * @return contentTypeId
     */
    public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.IEvaId getContentTypeId() {
        return contentTypeId;
    }


    /**
     * Sets the contentTypeId value for this ContentBookmark.
     * 
     * @param contentTypeId
     */
    public void setContentTypeId(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.IEvaId contentTypeId) {
        this.contentTypeId = contentTypeId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ContentBookmark)) return false;
        ContentBookmark other = (ContentBookmark) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.synopsis==null && other.getSynopsis()==null) || 
             (this.synopsis!=null &&
              this.synopsis.equals(other.getSynopsis()))) &&
            ((this.bookmarkOwnerTypeId==null && other.getBookmarkOwnerTypeId()==null) || 
             (this.bookmarkOwnerTypeId!=null &&
              this.bookmarkOwnerTypeId.equals(other.getBookmarkOwnerTypeId()))) &&
            ((this.typeSystemName==null && other.getTypeSystemName()==null) || 
             (this.typeSystemName!=null &&
              this.typeSystemName.equals(other.getTypeSystemName()))) &&
            this.isFeatured == other.isIsFeatured() &&
            ((this.sequenceNumber==null && other.getSequenceNumber()==null) || 
             (this.sequenceNumber!=null &&
              this.sequenceNumber.equals(other.getSequenceNumber()))) &&
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              this.title.equals(other.getTitle()))) &&
            ((this.viewId==null && other.getViewId()==null) || 
             (this.viewId!=null &&
              this.viewId.equals(other.getViewId()))) &&
            ((this.contentType==null && other.getContentType()==null) || 
             (this.contentType!=null &&
              this.contentType.equals(other.getContentType()))) &&
            ((this.bookmarkOwnerId==null && other.getBookmarkOwnerId()==null) || 
             (this.bookmarkOwnerId!=null &&
              this.bookmarkOwnerId.equals(other.getBookmarkOwnerId()))) &&
            ((this.bookmarkOwner==null && other.getBookmarkOwner()==null) || 
             (this.bookmarkOwner!=null &&
              this.bookmarkOwner.equals(other.getBookmarkOwner()))) &&
            ((this.createdDate==null && other.getCreatedDate()==null) || 
             (this.createdDate!=null &&
              this.createdDate.equals(other.getCreatedDate()))) &&
            ((this.contentTypeId==null && other.getContentTypeId()==null) || 
             (this.contentTypeId!=null &&
              this.contentTypeId.equals(other.getContentTypeId())));
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
        if (getSynopsis() != null) {
            _hashCode += getSynopsis().hashCode();
        }
        if (getBookmarkOwnerTypeId() != null) {
            _hashCode += getBookmarkOwnerTypeId().hashCode();
        }
        if (getTypeSystemName() != null) {
            _hashCode += getTypeSystemName().hashCode();
        }
        _hashCode += (isIsFeatured() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getSequenceNumber() != null) {
            _hashCode += getSequenceNumber().hashCode();
        }
        if (getTitle() != null) {
            _hashCode += getTitle().hashCode();
        }
        if (getViewId() != null) {
            _hashCode += getViewId().hashCode();
        }
        if (getContentType() != null) {
            _hashCode += getContentType().hashCode();
        }
        if (getBookmarkOwnerId() != null) {
            _hashCode += getBookmarkOwnerId().hashCode();
        }
        if (getBookmarkOwner() != null) {
            _hashCode += getBookmarkOwner().hashCode();
        }
        if (getCreatedDate() != null) {
            _hashCode += getCreatedDate().hashCode();
        }
        if (getContentTypeId() != null) {
            _hashCode += getContentTypeId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ContentBookmark.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "ContentBookmark"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("synopsis");
        elemField.setXmlName(new javax.xml.namespace.QName("", "synopsis"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bookmarkOwnerTypeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bookmarkOwnerTypeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "IEvaId"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("typeSystemName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "typeSystemName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isFeatured");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isFeatured"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sequenceNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sequenceNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("title");
        elemField.setXmlName(new javax.xml.namespace.QName("", "title"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("viewId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "viewId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contentType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contentType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "EIContentType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bookmarkOwnerId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bookmarkOwnerId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "IEvaId"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bookmarkOwner");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bookmarkOwner"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "IPersistableEntity"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createdDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "createdDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contentTypeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contentTypeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "IEvaId"));
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
