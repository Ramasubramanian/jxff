## ABOUT
The inbuilt Java's FileFilter and FilenameFilter does not provide capability to list files or folders dynamically based on multiple filter conditions. To achieve multiple conditions we need to code separate implementations for each combination. The purpose of this project is to provide a small utility API which can search folders recursively for matching multiple criteria with multiple combinations using lesser code. Hope it can be used in conjunction with other projects.....

import static in.jxff.filter.criteria.Criteria.*;
import in.jxff.filter.CriteriaMode;
import in.jxff.filter.FileLister;
import in.jxff.filter.FileListerFactory;
import in.jxff.filter.Operator;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Sample class to illustrate the usage of XFF. Refer classes in package
 * <b>in.jxff.filter.criteria.impl</b> for further usage
 * @author raam
 *
 */
public class XFFSample {

	public static void main(String[] args) throws ParseException {
		FileLister lister = FileListerFactory
				.getFileLister("/home/raam/jxff_test");
		lister.recursive(true).withMode(CriteriaMode.OR);//recursive mode
		lister.with(withExt("docx"))
		      .with(withExt("pptx"))
		      .with(withExt("pdf")) //extensions
		      .with(withSize(5000000, Operator.GRT_THAN))
		      .with(ofName("a1.txt"))//file size and name
		      .with(not(withSize(30000, Operator.EQUALS)));//negated file size
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		Date d = sdf.parse("28/09/2010 20:45:00");
		lister.with(changedAt(d, Operator.GRT_THAN))//modified at specific time
		      .with(compose(CriteriaMode.AND,withSize(0L, Operator.EQUALS),
		        withExt("txt"))); //composite criteria with 0 bytes and *.txt
		for (File f : lister.listFiles()) {
			System.out.println(f);
		}
	   	
	}

}

