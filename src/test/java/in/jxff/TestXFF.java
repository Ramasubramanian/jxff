package in.jxff;

import in.jxff.filter.CriteriaMode;
import in.jxff.filter.FileLister;
import in.jxff.filter.FileListerFactory;
import in.jxff.filter.Operator;
import in.jxff.filter.criteria.impl.FileExtensionCriteria;
import in.jxff.filter.criteria.impl.FileSizeCriteria;
import in.jxff.filter.criteria.impl.NegatedFileCriteria;

import java.io.File;


public class TestXFF {

	public static void main(String[] args) {
		FileLister fl = FileListerFactory.getFileLister("K:\\xff_test_files");
		fl.setRecursive(true);
		fl.setCriteriaMode(CriteriaMode.OR);
		fl.addCriteria(new NegatedFileCriteria(new FileExtensionCriteria("txt")));
		fl.addCriteria(new FileSizeCriteria(0,Operator.GRT_THAN_EQ));
//		fl.addCriteria(new FileExtensionCriteria("xlsx"));
		for(File f:fl.listFiles()){
			System.out.println(f);
		}
	}
	
}
