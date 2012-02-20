package in.jxff.filter.criteria;

import java.io.File;

/**
 * Interface definition for a File criteria which will be applied on a
 * File object by FileLister while searching for matching files
 * @author raam
 *
 */
public interface FileCriteria {

	public boolean apply(File f);
		
}
