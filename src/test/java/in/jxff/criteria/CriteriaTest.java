package in.jxff.criteria;

import static in.jxff.filter.criteria.Criteria.changedAt;
import static in.jxff.filter.criteria.Criteria.compose;
import static in.jxff.filter.criteria.Criteria.compose2;
import static in.jxff.filter.criteria.Criteria.not;
import static in.jxff.filter.criteria.Criteria.ofName;
import static in.jxff.filter.criteria.Criteria.ofNameNoCase;
import static in.jxff.filter.criteria.Criteria.withExt;
import static in.jxff.filter.criteria.Criteria.withExtNoCase;
import static in.jxff.filter.criteria.Criteria.withRegEx;
import static in.jxff.filter.criteria.Criteria.withSize;
import in.jxff.filter.CriteriaMode;
import in.jxff.filter.Operator;

import java.util.Date;

import junit.framework.Assert;
import junit.framework.TestCase;

public class CriteriaTest extends TestCase {

    public void testWithExt() {
        Assert.assertNotNull(withExt("txt"));
    }

    public void testWithExtNoCase() {
        Assert.assertNotNull(withExtNoCase("txt"));
    }

    public void testWithSize() {
        Assert.assertNotNull(withSize(1024L, Operator.EQUALS));
    }

    public void testChangedAt() {
        Assert.assertNotNull(changedAt(new Date(System.currentTimeMillis()), Operator.LESS_THAN));
    }

    public void testOfName() {
        Assert.assertNotNull(ofName("test.txt"));
    }

    public void testOfNameNoCase() {
        Assert.assertNotNull(ofNameNoCase("test.txt"));
    }

    public void testNot() {
        Assert.assertNotNull(not(ofNameNoCase("test.txt")));
    }

    public void testWithRegEx() {
        Assert.assertNotNull(withRegEx("[0-9]+.txt"));
    }

    public void testComposeCriteriaModeFileCriteriaArray() {
        Assert.assertNotNull(compose2(CriteriaMode.OR, ofName("data"), ofName("sample")));
    }

    public void testComposeCriteriaModeFileNameCriteriaArray() {
        Assert.assertNotNull(compose(CriteriaMode.OR, changedAt(new Date(System.currentTimeMillis()), Operator.LESS_THAN),
                withSize(1024L, Operator.EQUALS)));
    }

}
