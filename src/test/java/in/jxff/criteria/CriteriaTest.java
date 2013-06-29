package in.jxff.criteria;

import static in.jxff.filter.criteria.Criteria.changedAt;
import static in.jxff.filter.criteria.Criteria.compose;
import static in.jxff.filter.criteria.Criteria.compose2;
import static in.jxff.filter.criteria.Criteria.not;
import static in.jxff.filter.criteria.Criteria.name;
import static in.jxff.filter.criteria.Criteria.nameNoCase;
import static in.jxff.filter.criteria.Criteria.extension;
import static in.jxff.filter.criteria.Criteria.extNoCase;
import static in.jxff.filter.criteria.Criteria.regEx;
import static in.jxff.filter.criteria.Criteria.size;
import in.jxff.filter.CriteriaMode;
import in.jxff.filter.Operator;

import java.util.Date;

import junit.framework.Assert;
import junit.framework.TestCase;

public class CriteriaTest extends TestCase {

    public void testWithExt() {
        Assert.assertNotNull(extension("txt"));
    }

    public void testWithExtNoCase() {
        Assert.assertNotNull(extNoCase("txt"));
    }

    public void testWithSize() {
        Assert.assertNotNull(size(1024L, Operator.EQUALS));
    }

    public void testChangedAt() {
        Assert.assertNotNull(changedAt(new Date(System.currentTimeMillis()), Operator.LESS_THAN));
    }

    public void testOfName() {
        Assert.assertNotNull(name("test.txt"));
    }

    public void testOfNameNoCase() {
        Assert.assertNotNull(nameNoCase("test.txt"));
    }

    public void testNot() {
        Assert.assertNotNull(not(nameNoCase("test.txt")));
    }

    public void testWithRegEx() {
        Assert.assertNotNull(regEx("[0-9]+.txt"));
    }

    public void testComposeCriteriaModeFileCriteriaArray() {
        Assert.assertNotNull(compose2(CriteriaMode.OR, name("data"), name("sample")));
    }

    public void testComposeCriteriaModeFileNameCriteriaArray() {
        Assert.assertNotNull(compose(CriteriaMode.OR, changedAt(new Date(System.currentTimeMillis()), Operator.LESS_THAN),
                size(1024L, Operator.EQUALS)));
    }

}
