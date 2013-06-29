package in.jxff;

import static in.jxff.filter.criteria.Criteria.not;
import static in.jxff.filter.criteria.Criteria.extension;
import static in.jxff.filter.criteria.Criteria.size;
import in.jxff.filter.CriteriaMode;
import in.jxff.filter.FileLister;
import in.jxff.filter.FileListerFactory;
import in.jxff.filter.Operator;

import java.io.File;


public class TestXFF {

	public static void main(String[] args) {
		FileLister fl = FileListerFactory.getFileLister("K:\\xff_test_files");
		fl.recursive(true).withMode(CriteriaMode.OR);
		fl.with(not(extension("txt"))).with(size(0, Operator.GRT_THAN_EQ));
		for(File f:fl.listFiles()){
			System.out.println(f);
		}
	}
	
}
