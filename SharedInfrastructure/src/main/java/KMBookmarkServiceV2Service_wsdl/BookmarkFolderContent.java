/**
 * BookmarkFolderContent.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package KMBookmarkServiceV2Service_wsdl;

public class BookmarkFolderContent  implements java.io.Serializable {
    private KMBookmarkServiceV2Service_wsdl.BookmarkedContentV2[] bookmarkedContentList;

    private java.lang.String localeName;

    private java.lang.String folderId;

    private float sequenceNumber;

    private java.util.Date dateCreated;

    private java.lang.String title;

    private java.lang.String parentFolderId;

    public BookmarkFolderContent() {
    }

    public BookmarkFolderContent(
           KMBookmarkServiceV2Service_wsdl.BookmarkedContentV2[] bookmarkedContentList,
           java.lang.String localeName,
           java.lang.String folderId,
           float sequenceNumber,
           java.util.Date dateCreated,
           java.lang.String title,
           java.lang.String parentFolderId) {
           this.bookmarkedContentList = bookmarkedContentList;
           this.localeName = localeName;
           this.folderId = folderId;
           this.sequenceNumber = sequenceNumber;
           this.dateCreated = dateCreated;
           this.title = title;
           this.parentFolderId = parentFolderId;
    }


    /**
     * Gets the bookmarkedContentList value for this BookmarkFolderContent.
     * 
     * @return bookmarkedContentList
     */
    public KMBookmarkServiceV2Service_wsdl.BookmarkedContentV2[] getBookmarkedContentList() {
        return bookmarkedContentList;
    }


    /**
     * Sets the bookmarkedContentList value for this BookmarkFolderContent.
     * 
     * @param bookmarkedContentList
     */
    public void setBookmarkedContentList(KMBookmarkServiceV2Service_wsdl.BookmarkedContentV2[] bookmarkedContentList) {
        this.bookmarkedContentList = bookmarkedContentList;
    }


    /**
     * Gets the localeName value for this BookmarkFolderContent.
     * 
     * @return localeName
     */
    public java.lang.String getLocaleName() {
        return localeName;
    }


    /**
     * Sets the localeName value for this BookmarkFolderContent.
     * 
     * @param localeName
     */
    public void setLocaleName(java.lang.String localeName) {
        this.localeName = localeName;
    }


    /**
     * Gets the folderId value for this BookmarkFolderContent.
     * 
     * @return folderId
     */
    public java.lang.String getFolderId() {
        return folderId;
    }


    /**
     * Sets the folderId value for this BookmarkFolderContent.
     * 
     * @param folderId
     */
    public void setFolderId(java.lang.String folderId) {
        this.folderId = folderId;
    }


    /**
     * Gets the sequenceNumber value for this BookmarkFolderContent.
     * 
     * @return sequenceNumber
     */
    public float getSequenceNumber() {
        return sequenceNumber;
    }


    /**
     * Sets the sequenceNumber value for this BookmarkFolderContent.
     * 
     * @param sequenceNumber
     */
    public void setSequenceNumber(float sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }


    /**
     * Gets the dateCreated value for this BookmarkFolderContent.
     * 
     * @return dateCreated
     */
    public java.util.Date getDateCreated() {
        return dateCreated;
    }


    /**
     * Sets the dateCreated value for this BookmarkFolderContent.
     * 
     * @param dateCreated
     */
    public void setDateCreated(java.util.Date dateCreated) {
        this.dateCreated = dateCreated;
    }


    /**
     * Gets the title value for this BookmarkFolderContent.
     * 
     * @return title
     */
    public java.lang.String getTitle() {
        return title;
    }


    /**
     * Sets the title value for this BookmarkFolderContent.
     * 
     * @param title
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }


    /**
     * Gets the parentFolderId value for this BookmarkFolderContent.
     * 
     * @return parentFolderId
     */
    public java.lang.String getParentFolderId() {
        return parentFolderId;
    }


    /**
     * Sets the parentFolderId value for this BookmarkFolderContent.
     * 
     * @param parentFolderId
     */
    public void setParentFolderId(java.lang.String parentFolderId) {
        this.parentFolderId = parentFolderId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BookmarkFolderContent)) return false;
        BookmarkFolderContent other = (BookmarkFolderContent) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.bookmarkedContentList==null && other.getBookmarkedContentList()==null) || 
             (this.bookmarkedContentList!=null &&
              java.util.Arrays.equals(this.bookmarkedContentList, other.getBookmarkedContentList()))) &&
            ((this.localeName==null && other.getLocaleName()==null) || 
             (this.localeName!=null &&
              this.localeName.equals(other.getLocaleName()))) &&
            ((this.folderId==null && other.getFolderId()==null) || 
             (this.folderId!=null &&
              this.folderId.equals(other.getFolderId()))) &&
            this.sequenceNumber == other.getSequenceNumber() &&
            ((this.dateCreated==null && other.getDateCreated()==null) || 
             (this.dateCreated!=null &&
              this.dateCreated.equals(other.getDateCreated()))) &&
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              this.title.equals(other.getTitle()))) &&
            ((this.parentFolderId==null && other.getParentFolderId()==null) || 
             (this.parentFolderId!=null &&
              this.parentFolderId.equals(other.getParentFolderId())));
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
        if (getBookmarkedContentList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getBookmarkedContentList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getBookmarkedContentList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getLocaleName() != null) {
            _hashCode += getLocaleName().hashCode();
        }
        if (getFolderId() != null) {
            _hashCode += getFolderId().hashCode();
        }
        _hashCode += new Float(getSequenceNumber()).hashCode();
        if (getDateCreated() != null) {
            _hashCode += getDateCreated().hashCode();
        }
        if (getTitle() != null) {
            _hashCode += getTitle().hashCode();
        }
        if (getParentFolderId() != null) {
            _hashCode += getParentFolderId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BookmarkFolderContent.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "BookmarkFolderContent"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bookmarkedContentList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bookmarkedContentList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "BookmarkedContentV2"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "BookmarkedContentV2"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("localeName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "localeName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("folderId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "folderId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sequenceNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sequenceNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateCreated");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dateCreated"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("title");
        elemField.setXmlName(new javax.xml.namespace.QName("", "title"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parentFolderId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "parentFolderId"));
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
