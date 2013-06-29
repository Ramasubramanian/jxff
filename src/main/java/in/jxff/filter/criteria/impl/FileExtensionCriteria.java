package in.jxff.filter.criteria.impl;


/**
 * Criteria implementation to check for a file's extension, usage as below:
 * <pre>
 *  	FileLister lister = FileListerFactory
				.getFileLister("/home/testfiles");
		lister.withMode(CriteriaMode.OR);
		lister.with(extension("txt"));
		//provide an array of *.txt files from the folder and its subfolders
		lister.listFiles();
 * </pre>
 * By default the extension comparison is case sensitive, if the comparison
 * should be case insensitive use the code below:
 * <pre>
 * .
 * .
 * lister.add(new FileExtensionCriteria("txt",true));
 * .
 * .
 * </pre>
 * @author raam
 *
 */
public class FileExtensionCriteria extends AbstractFileNameCriteria {

	private String extension = ""; 
	
	private boolean ignoreCase = false;
	
	/**
	 * Overloaded constructor with extension string
	 * @param extension
	 */
	public FileExtensionCriteria(String extension){
		this(extension,true);
	}
	
	/**
	 * Overloaded constructor with extension name and flag to specify ignore case
	 * @param extension 
	 * 						extension of the files to be searched 
	 * @param ignoreCase 
	 * 						true/false
	 */
	public FileExtensionCriteria(String extension,boolean ignoreCase){
		this.extension = extension;
		this.ignoreCase= ignoreCase;
	}
	
	/*
	 * (non-Javadoc)
	 * @see in.jxff.filter.criteria.FileNameCriteria#apply(java.lang.String)
	 */
	@Override
	public boolean apply(String fileName) {
		String ext = getExtension(fileName);
		return ignoreCase?extension.equalsIgnoreCase(ext):extension.equals(ext);
	}

	private String getExtension(String fileName) {
		if(fileName.indexOf('.')>-1){
			String[] temp = fileName.split("\\.");
			return temp[temp.length-1];
		}		
		return fileName;
	}

}
