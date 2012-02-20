package in.jxff.filter.criteria.impl;

import in.jxff.filter.Operator;
import in.jxff.filter.criteria.FileCriteria;
import in.jxff.filter.exception.UnsupportedCriteriaException;

import java.io.File;


/**
 * Criteria implementation to filter the files based on file size
 * with specified relational operator, usage as below:
 * <pre>
 *		FileLister lister = FileListerFactory
				.getFileLister("/home/testfiles");
		lister.setRecursive(true);
		lister.setCriteriaMode(CriteriaMode.OR);
		lister.addCriteria(new FileSizeCriteria(0,Operator.GRT_THAN));
		//provide an array of files with size greater than zero 
		//from the folder and its subfolders
		lister.listFiles();
 * </pre>
 * @author raam
 * @see Operator
 *
 */
public class FileSizeCriteria implements FileCriteria {

	long length = 0L;
	
	Operator op;
	
	/**
	 * Overloaded constructor
	 * @param size
	 * 				Size of the file in bytes
	 * @param op
	 * 				Operator
	 */				
	public FileSizeCriteria(long size,Operator op){
		length = size;
		this.op = op;
	}
	
	/*
	 * (non-Javadoc)
	 * @see in.jxff.filter.criteria.FileCriteria#apply(java.io.File)
	 */
	public boolean apply(File f) {
		if(f.isDirectory())
			throw new UnsupportedCriteriaException("This criteria is supported only for files!");
		long size = f.length();
		switch(op){
		case EQUALS:
			return length == size;
		case NOT_EQUALS:
			return length != size;
		case GRT_THAN:
			return length < size;
		case GRT_THAN_EQ:
			return length <= size;
		case LESS_THAN:
			return length > size;
		case LESS_THAN_EQ:
			return length >= size;			
		}
		return false;
	}

}
