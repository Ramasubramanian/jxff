package in.jxff.filter.criteria.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Criteria implementation to validate a file name with the provided
 * regular expression. Usage as below:
 * <pre>
 *		FileLister lister = FileListerFactory
				.getFileLister("/home/testfiles");
		lister.setRecursive(true);
		lister.setCriteriaMode(CriteriaMode.OR);
		lister.addCriteria(new RegExpNameCriteria("a11.txt|b11.txt"));
		//provide an array of files with names a11.txt or b11.txt
		//from the folder and its sub folders
		lister.listFiles();
 * </pre>
 * @author raam
 * @see java.util.regex.Pattern
 * @see java.util.regex.Matcher
 */
public class RegExpNameCriteria extends AbstractFileNameCriteria {

	private Pattern p;
	
	/**
	 * Overloaded constructor
	 * @param regExp
	 * 					Regular expression to be matched
	 */
	public RegExpNameCriteria(String regExp){
		p = Pattern.compile(regExp);
	}
	
	/*
	 * (non-Javadoc)
	 * @see in.jxff.filter.criteria.FileNameCriteria#apply(java.lang.String)
	 */
	@Override
	public boolean apply(String fileName) {
		Matcher m = p.matcher(fileName);
		return m.matches();
	}

}
