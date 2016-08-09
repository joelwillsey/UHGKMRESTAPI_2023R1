package com.verint.services.km.dao.parser;

/**
 * 
 * @author jmiller
 *
 */
public class ParserInfo {
	private int startLocation;
	private int endLocation;
	private boolean emptyElement;
	private String elementData;
	private String data;

	/**
	 * 
	 */
	public ParserInfo() {
		super();
	}

	/**
	 * @return the startLocation
	 */
	public int getStartLocation() {
		return startLocation;
	}

	/**
	 * @param startLocation the startLocation to set
	 */
	public void setStartLocation(int startLocation) {
		this.startLocation = startLocation;
	}

	/**
	 * @return the endLocation
	 */
	public int getEndLocation() {
		return endLocation;
	}

	/**
	 * @param endLocation the endLocation to set
	 */
	public void setEndLocation(int endLocation) {
		this.endLocation = endLocation;
	}

	/**
	 * @return the emptyElement
	 */
	public boolean isEmptyElement() {
		return emptyElement;
	}

	/**
	 * @param emptyElement the emptyElement to set
	 */
	public void setEmptyElement(boolean emptyElement) {
		this.emptyElement = emptyElement;
	}

	/**
	 * @return the elementData
	 */
	public String getElementData() {
		return elementData;
	}

	/**
	 * @param elementData the elementData to set
	 */
	public void setElementData(String elementData) {
		this.elementData = elementData;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ParserInfo [startLocation=" + startLocation + ", endLocation=" + endLocation + ", emptyElement="
				+ emptyElement + ", elementData=" + elementData + ", data=" + data + "]";
	}
}