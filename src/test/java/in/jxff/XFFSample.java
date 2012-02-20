package in.jxff;

import in.jxff.filter.CriteriaMode;
import in.jxff.filter.FileLister;
import in.jxff.filter.FileListerFactory;
import in.jxff.filter.Operator;
import in.jxff.filter.criteria.impl.CompositeCriteria;
import in.jxff.filter.criteria.impl.FileExtensionCriteria;
import in.jxff.filter.criteria.impl.FileSizeCriteria;

import java.io.File;
import java.text.ParseException;


/**
 * Sample class to illustrate the usage of XFF. Refer classes in package
 * <b>in.jxff.filter.criteria.impl</b> for further usage
 * <pre>
	public static void main(String[] args) throws ParseException {
		FileLister lister = FileListerFactory
				.getFileLister("/home/testfiles");
		lister.setRecursive(true);
		lister.setCriteriaMode(CriteriaMode.OR);
		 lister.addCriteria(new FileExtensionCriteria("doc"));
		// lister.addCriteria(new FileExtensionCriteria("rtf"));
		// lister.addCriteria(new NameCriteria("folder1"));
		lister.addCriteria(new NegatedNameCriteria(
				new RegExpNameCriteria("a11.txt|b11.txt")));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		Date d = sdf.parse("23/09/2010 20:00:00");
		lister.addCriteria(new ModifiedTimeCriteria(d,Operator.GRT_THAN));
//		CompositeCriteria c = new CompositeCriteria(CriteriaMode.AND);
//		c.addCriteria(new FileSizeCriteria(0,Operator.GRT_THAN));
//		c.addCriteria(new FileExtensionCriteria("txt"));
//		lister.addCriteria(c);
		for (File f : lister.listFiles()) {
			System.out.println(f);
		}
		
	} 
 * </pre>
 * @author raam
 *
 */
public class XFFSample {

	public static void main(String[] args) throws ParseException {
		FileLister lister = FileListerFactory
				.getFileLister("K://test");
		lister.setRecursive(true);
		lister.setCriteriaMode(CriteriaMode.OR);
		 //lister.addCriteria(new FileExtensionCriteria("docx"));
		 //lister.addCriteria(new FileExtensionCriteria("pptx"));
		//lister.addCriteria(new FileExtensionCriteria("pdf"));
		// lister.addCriteria(new FileSizeCriteria(5000000,Operator.GRT_THAN));
		//lister.addCriteria(new NameCriteria("a1.txt"));
		//lister.addCriteria(new NegatedFileCriteria(new FileSizeCriteria(300000,Operator.EQUALS)));
		
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		//Date d = sdf.parse("28/09/2010 20:45:00");
		//lister.addCriteria(new ModifiedTimeCriteria(d,Operator.GRT_THAN));
		CompositeCriteria c = new CompositeCriteria(CriteriaMode.AND);
		c.addCriteria(new FileSizeCriteria(0,Operator.EQUALS));
		c.addCriteria(new FileExtensionCriteria("txt"));
		lister.addCriteria(c);
		for (File f : lister.listFiles()) {
			System.out.println(f);
		}
	   	
	}

}
