package in.jxff.filter.criteria.impl;

import in.jxff.filter.criteria.FileCriteria;
import in.jxff.filter.criteria.FileNameCriteria;

import java.io.File;


/**
 * Abstract implementation for classes that apply criteria on the file name
 * alone
 * 
 * @author raam
 * 
 */
public abstract class AbstractFileNameCriteria implements FileCriteria,
		FileNameCriteria {

	/**
	 * Invoke the file name criteria application in turn
	 */
	public boolean apply(File f) {
		return apply(f.getName());
	}

	/**
	 * Return boolean true based on file name criteria match, logic to
	 * be implented by all extensions
	 */
	public abstract boolean apply(String fileName);

}
