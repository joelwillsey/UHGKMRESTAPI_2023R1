/**
 * 
 */
package com.verint.services.km.util;

import java.util.Comparator;

import com.verint.services.km.model.TagSet;

/**
 * @author jmiller
 *
 */
public class TagSetComp implements Comparator<TagSet> {

	/**
	 * 
	 */
	public TagSetComp() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
    @Override
    public int compare(TagSet ts1, TagSet ts2) {
    	if (ts1.getSystemTagName() != null && ts2.getSystemTagName() != null) {
    		if (ts1.getSystemTagName().equalsIgnoreCase("product") && 
    			ts2.getSystemTagName().equalsIgnoreCase("product")) {
    			return 0;
    		} else if (ts1.getSystemTagName().equalsIgnoreCase("product") && 
        		ts2.getSystemTagName().equalsIgnoreCase("topic")) {
        		return -1;
    		} else if (ts1.getSystemTagName().equalsIgnoreCase("product") && 
            		ts2.getSystemTagName().equalsIgnoreCase("region")) {
    			return -1;
    		} else if (ts2.getSystemTagName().equalsIgnoreCase("product"))  {
    			return 1;
    		} else if (ts1.getSystemTagName().equalsIgnoreCase("topic") && 
            		ts2.getSystemTagName().equalsIgnoreCase("topic")) {
            	return 0;
    		} else if (ts1.getSystemTagName().equalsIgnoreCase("topic") && 
    				ts2.getSystemTagName().equalsIgnoreCase("region")) {
    			return -1;
			} else if (ts1.getSystemTagName().equalsIgnoreCase("region") && 
					ts2.getSystemTagName().equalsIgnoreCase("region")) {
				return 0;
			} else if (ts1.getSystemTagName().equalsIgnoreCase("region") && 
					ts2.getSystemTagName().equalsIgnoreCase("topic")) {
				return 1;
			} else if (ts1.getSystemTagName().equalsIgnoreCase(ts2.getSystemTagName())) {
				return 0;
			} else if (ts1.getSystemTagName().equalsIgnoreCase("topic")) {
				return -1;
			} else if (ts2.getSystemTagName().equalsIgnoreCase("topic")) {
				return 1;
			} else if (ts1.getSystemTagName().equalsIgnoreCase("region")) {
				return -1;
			} else if (ts2.getSystemTagName().equalsIgnoreCase("region")) {
				return 1;
			} else {
				return -1;
			}
    	}
    	return 0;
    }
}
