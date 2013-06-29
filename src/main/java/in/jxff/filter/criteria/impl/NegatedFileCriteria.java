package in.jxff.filter.criteria.impl;

import in.jxff.filter.criteria.FileCriteria;

import java.io.File;


/**
 * Decorator criteria implementation to provide the negated result of the specified file
 * criteria, usage as below
 * <pre>
 *		FileLister lister = FileListerFactory
				.getFileLister("/home/testfiles").recursive(true);
		lister.withMode(CriteriaMode.OR);
		lister.with(not(size(0,Operator.EQUALS)));
		//provide an array of files with size not equal to zero
		//from the folder and its sub folders
		lister.listFiles();
 * </pre>
 * @author raam
 *
 */
public class NegatedFileCriteria implements FileCriteria {

	private FileCriteria crit;
	
	/**
	 * Overloaded constructor
	 * @param criteria
	 * 					Criteria to be negated
	 */
	public NegatedFileCriteria(FileCriteria criteria){
		crit = criteria;
	}
	
	/*
	 * (non-Javadoc)
	 * @see in.jxff.filter.criteria.FileCriteria#apply(java.io.File)
	 */
	public boolean apply(File f) {
		return !crit.apply(f);
	}

}
