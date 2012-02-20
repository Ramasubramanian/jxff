package in.jxff.filter;

import in.jxff.filter.impl.FileListerImpl;

/**
 * Factory class to return appropriate FileLister implementation
 * Usage shown below:
 * <pre>
 * FileLister fl = FileListerFactory.getFileLister();
 * </pre>
 * or
 * <pre>
 * FileLister fl = FileListerFactory.getFileLister("{ABSOLUTE FOLDER NAME}");
 * </pre>  
 * @author raam
 *
 */
public class FileListerFactory {

	private FileListerFactory(){/*Static usage*/}
	
	public static FileLister getFileLister(){
		return new FileListerImpl();
	}
	
	public static FileLister getFileLister(String rootFolder){
		return new FileListerImpl(rootFolder);
	}
	
}
