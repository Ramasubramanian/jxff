package in.jxff.filter.impl;

import in.jxff.filter.CriteriaMode;
import in.jxff.filter.FileLister;
import in.jxff.filter.criteria.CriteriaApplier;
import in.jxff.filter.criteria.FileCriteria;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


/**
 * Default implementation of FileLister interface
 * 
 * @author raam
 * 
 */
public class FileListerImpl implements FileLister {

	private boolean recursive = false;

	private List<FileCriteria> fileCriterias = new ArrayList<FileCriteria>();

	private String rootFolder = "";

	private Set<File> files = new LinkedHashSet<File>();

	private Set<File> folders = new LinkedHashSet<File>();

	private boolean modified = true;

	private CriteriaMode criteriaMode = CriteriaMode.AND;

	private CriteriaApplier applier = new CriteriaApplier();

	private boolean cacheable = true;

	public FileListerImpl() {

	}

	public FileListerImpl(String rootFolder) {
		this.rootFolder = rootFolder;
	}

	/**
	 * Set the modified flag to refresh cached file list
	 */
	private void setMod() {
		modified = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.xff.filter.FileLister#addCriteria(in.jxff.filter.criteria.
	 * FileCriteria)
	 */
	public void addCriteria(FileCriteria criteria) {
		fileCriterias.add(criteria);
		setMod();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.jxff.filter.FileLister#getFileCriterias()
	 */
	public List<FileCriteria> getCriterias() {
		return fileCriterias;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.jxff.filter.FileLister#getRootFolder()
	 */
	public String getRootFolder() {
		return rootFolder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.jxff.filter.FileLister#isRecursive()
	 */
	public boolean isRecursive() {
		return recursive;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.jxff.filter.FileLister#listFiles()
	 */
	public File[] listFiles() {
		if (!cacheable || modified) {
			modified = !modified;
			refresh();
		}
		return files.toArray(new File[0]);
	}

	/**
	 * Validate and return the corresponding file Object of root folder
	 * 
	 * @return
	 */
	private File checkAndGetRoot() {
		File root = new File(rootFolder);
		if (!root.exists())
			throw new RuntimeException("Root folder " + rootFolder
					+ " does not exist!");
		if (!root.isDirectory())
			throw new RuntimeException("Root folder " + rootFolder
					+ " is not a directory!");
		return root;
	}

	/**
	 * Refresh the cached files list
	 */
	private void refresh() {
		files.clear();
		getMatchingFiles(checkAndGetRoot());
	}

	/**
	 * Method to list all the files in the folder and filter based on provided
	 * criteria
	 * 
	 * @param folder
	 *            Folder to be scanned for matching files
	 */
	private void getMatchingFiles(File folder) {
		File[] allFiles = folder.listFiles();
		if (allFiles == null)
			return;
		// apply the file criterias on files matching with name
		if (!fileCriterias.isEmpty()) {
			for (File f : allFiles) {
				if (recursive && f.isDirectory())
					getMatchingFiles(f);
				else {
					if (applier.applyFileCriterias(f, fileCriterias,
							criteriaMode)) {
						files.add(f);
					}
				}
			}
		} 
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.jxff.filter.FileLister#setRecursive(boolean)
	 */
	public void setRecursive(boolean recursive) {
		this.recursive = recursive;
		setMod();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.jxff.filter.FileLister#setRootFolder(java.lang.String)
	 */
	public void setRootFolder(String folderName) {
		rootFolder = folderName;
		setMod();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.jxff.filter.FileLister#getCriteriaMode()
	 */
	public CriteriaMode getCriteriaMode() {
		return criteriaMode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.jxff.filter.FileLister#setCriteriaMode(in.jxff.filter.CriteriaMode)
	 */
	public void setCriteriaMode(CriteriaMode mode) {
		this.criteriaMode = mode;
	}

	/**
	 * Method to list all the sub folders in the specified folder and filter
	 * based on provided criteria
	 * 
	 * @param parentFolder
	 *            Folder to be scanned for matching files
	 */
	private void getMatchingFolders(File parentFolder) {
		folders.clear();
		// fetch the list of all folders
		File[] subFolders = parentFolder.listFiles(new FileFilter() {

			public boolean accept(File arg0) {
				return arg0.isDirectory();
			}
		});
		if (subFolders == null)
			return;
		// then apply the file criteria on folders matching with name
		if (!fileCriterias.isEmpty()) {
			for (File f : subFolders) {
				if (applier.applyFileCriterias(f, fileCriterias, criteriaMode)) {
					folders.add(f);
					if (recursive) {
						getMatchingFolders(f);
					}
				}
			}
		} 
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.jxff.filter.FileLister#listFolders()
	 */
	public File[] listFolders() {
		getMatchingFolders(checkAndGetRoot());
		return folders.toArray(new File[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.jxff.filter.FileLister#isCacheable()
	 */
	public boolean isCacheable() {
		return cacheable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.jxff.filter.FileLister#setCacheable(boolean)
	 */
	public void setCacheable(boolean cacheable) {
		this.cacheable = cacheable;
	}

}
