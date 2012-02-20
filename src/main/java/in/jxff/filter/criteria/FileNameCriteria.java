package in.jxff.filter.criteria;

/**
 * Interface definition for a File name criteria which will be applied on a
 * File name by FileLister while searching for matching files
 * @author raam
 *
 */
public interface FileNameCriteria {

	public boolean apply(String fileName);
	
}
