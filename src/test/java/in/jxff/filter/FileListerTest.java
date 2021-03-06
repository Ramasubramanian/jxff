package in.jxff.filter;

import in.jxff.filter.impl.FileListerImpl;
import junit.framework.TestCase;
import static junit.framework.Assert.*;
import static in.jxff.filter.criteria.Criteria.*;

public class FileListerTest extends TestCase {

    public void testFileListerImplString() {
        assertEquals("/root",new FileListerImpl("/root").getRootFolder());
    }

    public void testWith() {
        FileLister fl = FileListerFactory.getFileLister("/home/raam/jxff_test");
        fl.with(extension("txt"));
        assertEquals(4, fl.listFiles().length);
        fl.recursive(true);
        assertEquals(7, fl.listFiles().length);
    }

    public void testListFolders() {
        FileLister fl = FileListerFactory.getFileLister("/home/raam/jxff_test");
        fl.with(name("subdir1"));
        assertEquals(1,fl.listFolders().length);
    }


}
