package in.jxff.filter;

import in.jxff.filter.criteria.FileCriteria;

import java.io.File;
import java.util.List;


/**
 * Core interface defining the methods available for usage by external client by eXtended File Filter API. Contains
 * capability to choose files from a folder based on multiple criteria unlike inbuilt Java APIs
 * @author raam
 *
 */
public interface FileLister {

	/**
	 * Specifies whether the lister implementation will search sub folders recursively 
	 * @return
	 */
	public boolean isRecursive();
	
	/**
	 * Add an appropriate FileCriteria implementation to the lister to be applied while searching
	 * for files
	 * @param criteria
	 */
	public FileLister with(FileCriteria criteria);
	
	
	/**
	 * Set the root folder name from which this lister will start scanning for matching
	 * files
	 * @param folderName
	 */
	public FileLister withRoot(String folderName);
	
	/**
	 * Return the current root folder used by this lister
	 * @return
	 */
	public String getRootFolder();
	
	/**
	 * Return the list of configured FileCriteria implementations that will be used 
	 * by this lister
	 * @return
	 */
	public List<FileCriteria> getCriterias();

	/**
	 * Set whether this lister will search the root folder recursively or search only up to one level
	 * @param recursive
	 */
	public FileLister recursive(boolean recursive);
	
	/**
	 * Set the criteria mode as to whether the conditions for matching files
	 * to be compared in AND or OR mode
	 * @param mode
	 * @see CriteriaMode 
	 */
	public FileLister withMode(CriteriaMode mode);
	
	/**
	 * Get the Criteria mode of this lister
	 * @return
	 */
	public CriteriaMode getCriteriaMode();
	
	/**
	 * Returns an array of sub folders matching the provided criteria
	 * @return
	 */
	public File[] listFolders();
	
	/**
	 * Returns an array of Files matching the provided criteria
	 * @return
	 */
	public File[] listFiles();
	
	/**
	 * Setting this property to true enables caching of searched files until and unless
	 * the search criteria is modified, the caching is applicable only for files and not 
	 * sub folders
	 * @param cacheable
	 */
	public FileLister cacheable(boolean cacheable);
	
	/**
	 * Return whether the lister caches the results of search until
	 * search criteria is changed, the caching is applicable only for files and not 
	 * sub folders
	 * @return
	 */
	public boolean isCacheable();
	
}
