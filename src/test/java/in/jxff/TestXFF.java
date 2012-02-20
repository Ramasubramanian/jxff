package in.jxff;

import static in.jxff.filter.criteria.Criteria.not;
import static in.jxff.filter.criteria.Criteria.withExt;
import static in.jxff.filter.criteria.Criteria.withSize;
import in.jxff.filter.CriteriaMode;
import in.jxff.filter.FileLister;
import in.jxff.filter.FileListerFactory;
import in.jxff.filter.Operator;

import java.io.File;


public class TestXFF {

	public static void main(String[] args) {
		FileLister fl = FileListerFactory.getFileLister("K:\\xff_test_files");
		fl.recursive(true).withMode(CriteriaMode.OR);
		fl.with(not(withExt("txt"))).with(withSize(0,Operator.GRT_THAN_EQ));
		for(File f:fl.listFiles()){
			System.out.println(f);
		}
	}
	
}
