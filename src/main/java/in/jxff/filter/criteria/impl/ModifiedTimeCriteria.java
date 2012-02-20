package in.jxff.filter.criteria.impl;

import in.jxff.filter.Operator;
import in.jxff.filter.criteria.FileCriteria;

import java.io.File;
import java.util.Date;


/**
 * Criteria implementation to search for files based on last modified time, usage as below
 * <pre>
 *		FileLister lister = FileListerFactory
				.getFileLister("/home/testfiles");
		lister.withMode(CriteriaMode.OR);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		Date d = sdf.parse("23/09/2010 20:00:00");
		lister.with(changedAt(d,Operator.GRT_THAN));
		//provide an array of files with last modified time greater than
		// 8 PM on 23rd September 2010 from the folder and its subfolders
		lister.listFiles();
 * </pre> 
 * @author raam
 * @see Operator
 */
public class ModifiedTimeCriteria implements FileCriteria {


	private Operator op;
	
	private Date time;
	
	/**
	 * Overloaded constructor
	 * @param date
	 * 				Reference Date for file comparison 
	 * @param opr
	 * 				Operator
	 */
	public ModifiedTimeCriteria(Date date,Operator opr){
		time = date==null?new Date():date;
		op = opr;
	}

	/*
	 * (non-Javadoc)
	 * @see in.jxff.filter.criteria.FileCriteria#apply(java.io.File)
	 */
	public boolean apply(File f) {
		int val = new Date(f.lastModified()).compareTo(time);
		switch(op){
		case EQUALS:
			return val == 0;
		case NOT_EQUALS:
			return val != 0;
		case GRT_THAN:
			return val > 0;
		case GRT_THAN_EQ:
			return val >= 0;
		case LESS_THAN:
			return val < 0;
		case LESS_THAN_EQ:
			return val <= 0;			
		}
		return false;
	}

}
