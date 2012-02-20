package in.jxff.filter.criteria.impl;


/**
 * Criteria implementation to match the whole file name including extension,
 * case insensitive by default
 * @author raam
 *
 */
public class NameCriteria extends AbstractFileNameCriteria {

	private String name = "";
	
	private boolean ignoreCase = true;
	
	/**
	 * Overloaded constructor
	 * @param name
	 * 				Name of the file to be searched
	 */
	public NameCriteria(String name){
		this.name = name;
	}
	
	/**
	 * Overloaded constructor to make the search case sensitive
	 * @param name
	 * 						Name of the file to be searched
	 * @param ignoreCase
	 * 						true/false
	 */
	public NameCriteria(String name,boolean ignoreCase){
		this.name = name;
		this.ignoreCase = ignoreCase;
	}
	
	/*
	 * (non-Javadoc)
	 * @see in.jxff.filter.criteria.FileNameCriteria#apply(java.lang.String)
	 */
	@Override
	public boolean apply(String fileName) {
		return ignoreCase?name.equalsIgnoreCase(fileName):name.equals(fileName);
	}

}
