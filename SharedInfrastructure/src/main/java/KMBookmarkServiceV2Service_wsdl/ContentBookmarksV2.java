/**
 * ContentBookmarksV2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package KMBookmarkServiceV2Service_wsdl;

public class ContentBookmarksV2  extends KMBookmarkServiceV2Service_wsdl.ContentBookmark  implements java.io.Serializable {
    private KMBookmarkServiceV2Service_wsdl.BookmarkFolderContents[] folders;

    private KMBookmarkServiceV2Service_wsdl.BookmarkedContentV2[] bookmarks;

    public ContentBookmarksV2() {
    }

    public ContentBookmarksV2(
           java.lang.String synopsis,
           KMBookmarkServiceV2Service_wsdl.IEvaId bookmarkOwnerTypeId,
           java.lang.String typeSystemName,
           boolean isFeatured,
           java.math.BigInteger sequenceNumber,
           java.lang.String title,
           java.lang.String viewId,
           KMBookmarkServiceV2Service_wsdl.EIContentType contentType,
           KMBookmarkServiceV2Service_wsdl.IEvaId bookmarkOwnerId,
           KMBookmarkServiceV2Service_wsdl.IPersistableEntity bookmarkOwner,
           java.util.Date createdDate,
           KMBookmarkServiceV2Service_wsdl.IEvaId contentTypeId,
           KMBookmarkServiceV2Service_wsdl.BookmarkFolderContents[] folders,
           KMBookmarkServiceV2Service_wsdl.BookmarkedContentV2[] bookmarks) {
        super(
            synopsis,
            bookmarkOwnerTypeId,
            typeSystemName,
            isFeatured,
            sequenceNumber,
            title,
            viewId,
            contentType,
            bookmarkOwnerId,
            bookmarkOwner,
            createdDate,
            contentTypeId);
        this.folders = folders;
        this.bookmarks = bookmarks;
    }


    /**
     * Gets the folders value for this ContentBookmarksV2.
     * 
     * @return folders
     */
    public KMBookmarkServiceV2Service_wsdl.BookmarkFolderContents[] getFolders() {
        return folders;
    }


    /**
     * Sets the folders value for this ContentBookmarksV2.
     * 
     * @param folders
     */
    public void setFolders(KMBookmarkServiceV2Service_wsdl.BookmarkFolderContents[] folders) {
        this.folders = folders;
    }


    /**
     * Gets the bookmarks value for this ContentBookmarksV2.
     * 
     * @return bookmarks
     */
    public KMBookmarkServiceV2Service_wsdl.BookmarkedContentV2[] getBookmarks() {
        return bookmarks;
    }


    /**
     * Sets the bookmarks value for this ContentBookmarksV2.
     * 
     * @param bookmarks
     */
    public void setBookmarks(KMBookmarkServiceV2Service_wsdl.BookmarkedContentV2[] bookmarks) {
        this.bookmarks = bookmarks;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ContentBookmarksV2)) return false;
        ContentBookmarksV2 other = (ContentBookmarksV2) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.folders==null && other.getFolders()==null) || 
             (this.folders!=null &&
              java.util.Arrays.equals(this.folders, other.getFolders()))) &&
            ((this.bookmarks==null && other.getBookmarks()==null) || 
             (this.bookmarks!=null &&
              java.util.Arrays.equals(this.bookmarks, other.getBookmarks())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getFolders() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFolders());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFolders(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getBookmarks() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getBookmarks());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getBookmarks(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ContentBookmarksV2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "ContentBookmarksV2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("folders");
        elemField.setXmlName(new javax.xml.namespace.QName("", "folders"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "BookmarkFolderContents"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "BookmarkFolderContents"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bookmarks");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bookmarks"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "BookmarkedContentV2"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "BookmarkedContentV2"));
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
