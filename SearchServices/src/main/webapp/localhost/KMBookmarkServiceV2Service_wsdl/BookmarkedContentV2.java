/**
 * BookmarkedContentV2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.KMBookmarkServiceV2Service_wsdl;

public class BookmarkedContentV2  implements java.io.Serializable {
    private java.lang.String contentId;

    private java.lang.String synopsis;

    private java.lang.String parentFolderId;

    private boolean isFeatured;

    private float sequenceNumber;

    private boolean isNewOrChanged;

    private java.lang.String title;

    private java.lang.String contentType;

    private java.util.Date createdDate;

    private java.lang.String localeName;

    public BookmarkedContentV2() {
    }

    public BookmarkedContentV2(
           java.lang.String contentId,
           java.lang.String synopsis,
           java.lang.String parentFolderId,
           boolean isFeatured,
           float sequenceNumber,
           boolean isNewOrChanged,
           java.lang.String title,
           java.lang.String contentType,
           java.util.Date createdDate,
           java.lang.String localeName) {
           this.contentId = contentId;
           this.synopsis = synopsis;
           this.parentFolderId = parentFolderId;
           this.isFeatured = isFeatured;
           this.sequenceNumber = sequenceNumber;
           this.isNewOrChanged = isNewOrChanged;
           this.title = title;
           this.contentType = contentType;
           this.createdDate = createdDate;
           this.localeName = localeName;
    }


    /**
     * Gets the contentId value for this BookmarkedContentV2.
     * 
     * @return contentId
     */
    public java.lang.String getContentId() {
        return contentId;
    }


    /**
     * Sets the contentId value for this BookmarkedContentV2.
     * 
     * @param contentId
     */
    public void setContentId(java.lang.String contentId) {
        this.contentId = contentId;
    }


    /**
     * Gets the synopsis value for this BookmarkedContentV2.
     * 
     * @return synopsis
     */
    public java.lang.String getSynopsis() {
        return synopsis;
    }


    /**
     * Sets the synopsis value for this BookmarkedContentV2.
     * 
     * @param synopsis
     */
    public void setSynopsis(java.lang.String synopsis) {
        this.synopsis = synopsis;
    }


    /**
     * Gets the parentFolderId value for this BookmarkedContentV2.
     * 
     * @return parentFolderId
     */
    public java.lang.String getParentFolderId() {
        return parentFolderId;
    }


    /**
     * Sets the parentFolderId value for this BookmarkedContentV2.
     * 
     * @param parentFolderId
     */
    public void setParentFolderId(java.lang.String parentFolderId) {
        this.parentFolderId = parentFolderId;
    }


    /**
     * Gets the isFeatured value for this BookmarkedContentV2.
     * 
     * @return isFeatured
     */
    public boolean isIsFeatured() {
        return isFeatured;
    }


    /**
     * Sets the isFeatured value for this BookmarkedContentV2.
     * 
     * @param isFeatured
     */
    public void setIsFeatured(boolean isFeatured) {
        this.isFeatured = isFeatured;
    }


    /**
     * Gets the sequenceNumber value for this BookmarkedContentV2.
     * 
     * @return sequenceNumber
     */
    public float getSequenceNumber() {
        return sequenceNumber;
    }


    /**
     * Sets the sequenceNumber value for this BookmarkedContentV2.
     * 
     * @param sequenceNumber
     */
    public void setSequenceNumber(float sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }


    /**
     * Gets the isNewOrChanged value for this BookmarkedContentV2.
     * 
     * @return isNewOrChanged
     */
    public boolean isIsNewOrChanged() {
        return isNewOrChanged;
    }


    /**
     * Sets the isNewOrChanged value for this BookmarkedContentV2.
     * 
     * @param isNewOrChanged
     */
    public void setIsNewOrChanged(boolean isNewOrChanged) {
        this.isNewOrChanged = isNewOrChanged;
    }


    /**
     * Gets the title value for this BookmarkedContentV2.
     * 
     * @return title
     */
    public java.lang.String getTitle() {
        return title;
    }


    /**
     * Sets the title value for this BookmarkedContentV2.
     * 
     * @param title
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }


    /**
     * Gets the contentType value for this BookmarkedContentV2.
     * 
     * @return contentType
     */
    public java.lang.String getContentType() {
        return contentType;
    }


    /**
     * Sets the contentType value for this BookmarkedContentV2.
     * 
     * @param contentType
     */
    public void setContentType(java.lang.String contentType) {
        this.contentType = contentType;
    }


    /**
     * Gets the createdDate value for this BookmarkedContentV2.
     * 
     * @return createdDate
     */
    public java.util.Date getCreatedDate() {
        return createdDate;
    }


    /**
     * Sets the createdDate value for this BookmarkedContentV2.
     * 
     * @param createdDate
     */
    public void setCreatedDate(java.util.Date createdDate) {
        this.createdDate = createdDate;
    }


    /**
     * Gets the localeName value for this BookmarkedContentV2.
     * 
     * @return localeName
     */
    public java.lang.String getLocaleName() {
        return localeName;
    }


    /**
     * Sets the localeName value for this BookmarkedContentV2.
     * 
     * @param localeName
     */
    public void setLocaleName(java.lang.String localeName) {
        this.localeName = localeName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BookmarkedContentV2)) return false;
        BookmarkedContentV2 other = (BookmarkedContentV2) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.contentId==null && other.getContentId()==null) || 
             (this.contentId!=null &&
              this.contentId.equals(other.getContentId()))) &&
            ((this.synopsis==null && other.getSynopsis()==null) || 
             (this.synopsis!=null &&
              this.synopsis.equals(other.getSynopsis()))) &&
            ((this.parentFolderId==null && other.getParentFolderId()==null) || 
             (this.parentFolderId!=null &&
              this.parentFolderId.equals(other.getParentFolderId()))) &&
            this.isFeatured == other.isIsFeatured() &&
            this.sequenceNumber == other.getSequenceNumber() &&
            this.isNewOrChanged == other.isIsNewOrChanged() &&
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              this.title.equals(other.getTitle()))) &&
            ((this.contentType==null && other.getContentType()==null) || 
             (this.contentType!=null &&
              this.contentType.equals(other.getContentType()))) &&
            ((this.createdDate==null && other.getCreatedDate()==null) || 
             (this.createdDate!=null &&
              this.createdDate.equals(other.getCreatedDate()))) &&
            ((this.localeName==null && other.getLocaleName()==null) || 
             (this.localeName!=null &&
              this.localeName.equals(other.getLocaleName())));
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
        if (getContentId() != null) {
            _hashCode += getContentId().hashCode();
        }
        if (getSynopsis() != null) {
            _hashCode += getSynopsis().hashCode();
        }
        if (getParentFolderId() != null) {
            _hashCode += getParentFolderId().hashCode();
        }
        _hashCode += (isIsFeatured() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += new Float(getSequenceNumber()).hashCode();
        _hashCode += (isIsNewOrChanged() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getTitle() != null) {
            _hashCode += getTitle().hashCode();
        }
        if (getContentType() != null) {
            _hashCode += getContentType().hashCode();
        }
        if (getCreatedDate() != null) {
            _hashCode += getCreatedDate().hashCode();
        }
        if (getLocaleName() != null) {
            _hashCode += getLocaleName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BookmarkedContentV2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "BookmarkedContentV2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contentId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contentId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("synopsis");
        elemField.setXmlName(new javax.xml.namespace.QName("", "synopsis"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parentFolderId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "parentFolderId"));
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
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isNewOrChanged");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isNewOrChanged"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("title");
        elemField.setXmlName(new javax.xml.namespace.QName("", "title"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contentType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contentType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createdDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "createdDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("localeName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "localeName"));
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
