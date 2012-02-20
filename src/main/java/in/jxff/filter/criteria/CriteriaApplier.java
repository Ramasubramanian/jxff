package in.jxff.filter.criteria;

import in.jxff.filter.CriteriaMode;

import java.io.File;
import java.util.List;


/**
 * Convenience class to apply multiple criteria validation on files/file names,used
 * internally by the APIs
 * @author raam
 *
 */
public class CriteriaApplier {

	/**
	 * Applies the specified name criteria on the input file and returns
	 * appropriate boolean value
	 * @param f
	 * 					Input file
	 * @param criterias
	 * 					List of name criteria to be applied
	 * @param mode
	 * 					Criteria application mode AND/OR
	 * @return
	 * 			true - If the file matches all the criteria
	 * 			false - If the file does not match all the criteria  
	 */
	public boolean applyNameCriterias(File f,List<FileNameCriteria> criterias,CriteriaMode mode){
		if(mode == CriteriaMode.AND)
			return applyAndNameCriterias(f,criterias,mode);
		else
			return applyOrNameCriterias(f,criterias,mode);
	}

	private boolean applyOrNameCriterias(File f, List<FileNameCriteria> criterias,
			CriteriaMode mode) {
		String fName = f.getName();
		for(FileNameCriteria fc:criterias){
			if(fc.apply(fName))
				return true;
		}
		return false;
	}

	private boolean applyAndNameCriterias(File f,
			List<FileNameCriteria> criterias, CriteriaMode mode) {
		String fName = f.getName();
		for(FileNameCriteria fc:criterias){
			if(!fc.apply(fName))
				return false;
		}
		return true;		
	}

	/**
	 * Applies the specified file criteria on the input file and returns
	 * appropriate boolean value
	 * @param f
	 * 					Input file
	 * @param criterias
	 * 					List of file criteria to be applied
	 * @param mode
	 * 					Criteria application mode AND/OR
	 * @return
	 * 			true - If the file matches all the criteria
	 * 			false - If the file does not match all the criteria  
	 */
	public boolean applyFileCriterias(File f,List<FileCriteria> criterias,CriteriaMode mode){
		if(mode == CriteriaMode.AND)
			return applyAndFileCriterias(f,criterias,mode);
		else
			return applyOrFileCriterias(f,criterias,mode);
	}

	private boolean applyOrFileCriterias(File f, List<FileCriteria> criterias,
			CriteriaMode mode) {
		for(FileCriteria fc:criterias){
			if(fc.apply(f))
				return true;
		}
		return false;
	}

	private boolean applyAndFileCriterias(File f, List<FileCriteria> criterias,
			CriteriaMode mode) {
		for(FileCriteria fc:criterias){
			if(!fc.apply(f))
				return false;
		}
		return true;
	}
	
	
}
