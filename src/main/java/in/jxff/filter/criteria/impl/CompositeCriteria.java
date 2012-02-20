package in.jxff.filter.criteria.impl;

import in.jxff.filter.CriteriaMode;
import in.jxff.filter.criteria.CriteriaApplier;
import in.jxff.filter.criteria.FileCriteria;
import in.jxff.filter.criteria.FileNameCriteria;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Composite criteria used while evaluating multiple conditions for a same file
 * with application mode i.e. AND/OR different from the FileLister
 * for e.g. if the FileLister mode is set as OR and the search has to be for 
 * all files with extension of *.doc and non-zero sized files with extension *.txt
 * from a directory, this criteria can be used as shown below
 * <pre>
 * 		FileLister lister = FileListerFactory
				.getFileLister("/home/testfiles");
		lister.setRecursive(true);
		lister.setCriteriaMode(CriteriaMode.OR);
		lister.addCriteria(new FileExtensionCriteria("doc"));
		//Create a composite criteria
		CompositeCriteria c = new CompositeCriteria(CriteriaMode.AND);
		//add File size greater than zero check
		c.addCriteria(new FileSizeCriteria(0,Operator.GRT_THAN));
		//add an extension check for *.txt
		c.addCriteria(new FileExtensionCriteria("txt"));
		lister.addCriteria(c);				
 * </pre>
 * @author raam
 *
 */
public class CompositeCriteria implements FileCriteria{

	private CriteriaMode mode;
	
	private List<FileCriteria> fileCriterias = new ArrayList<FileCriteria>();

	private List<FileNameCriteria> fileNameCriterias = new ArrayList<FileNameCriteria>();

	private CriteriaApplier applier = new CriteriaApplier();
	
	/**
	 * Overloaded constructor with application mode
	 * @param mode AND/OR
	 */
	public CompositeCriteria(CriteriaMode mode){
		this.mode = mode;
	}
	
	/*
	 * (non-Javadoc)
	 * @see in.jxff.filter.criteria.FileCriteria#apply(java.io.File)
	 */
	public boolean apply(File f) {
		return applier.applyNameCriterias(f, fileNameCriterias, mode) &&
					applier.applyFileCriterias(f, fileCriterias, mode);
	}

	/**
	 * Set the list of FileCriteria objects to be applied by this composite criteria
	 * @param fileCriterias
	 */
	public void setFileCriterias(List<FileCriteria> fileCriterias) {
		this.fileCriterias = fileCriterias;
	}

	/**
	 * Set the list of FileNameCriteria objects to be applied by this composite criteria
	 * @param fileNameCriterias
	 */	
	public void setFileNameCriterias(List<FileNameCriteria> fileNameCriterias) {
		this.fileNameCriterias = fileNameCriterias;
	}
	
	/**
	 * Adds the specified FileCriteria to the list of criteria to be applied
	 * @param criteria
	 */
	public void addCriteria(FileCriteria criteria){
		fileCriterias.add(criteria);
	}
	
	

}
