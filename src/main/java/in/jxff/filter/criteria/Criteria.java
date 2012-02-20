package in.jxff.filter.criteria;

import java.util.Date;

import in.jxff.filter.CriteriaMode;
import in.jxff.filter.Operator;
import in.jxff.filter.criteria.impl.CompositeCriteria;
import in.jxff.filter.criteria.impl.FileExtensionCriteria;
import in.jxff.filter.criteria.impl.FileSizeCriteria;
import in.jxff.filter.criteria.impl.ModifiedTimeCriteria;
import in.jxff.filter.criteria.impl.NameCriteria;
import in.jxff.filter.criteria.impl.NegatedFileCriteria;
import in.jxff.filter.criteria.impl.RegExpNameCriteria;

/**
 * Factory class for creating criteria objects
 * 
 * @author raam
 *
 */
public final class Criteria {

    private Criteria() {/*static usage*/}
    
    public static FileExtensionCriteria withExt(final String extension) {
        return new FileExtensionCriteria(extension);
    }
    
    public static FileExtensionCriteria withExtNoCase(final String extension) {
        return new FileExtensionCriteria(extension,false);
    }
    
    public static FileSizeCriteria withSize(long size, Operator op) {
        return new FileSizeCriteria(size, op);
    }
    
    public static ModifiedTimeCriteria changedAt(Date time, Operator op) {
        return new ModifiedTimeCriteria(time,op);
    }
    
    public static NameCriteria ofName(String name) {
        return new NameCriteria(name);
    }
    
    public static NameCriteria ofNameNoCase(String name) {
        return new NameCriteria(name, true);
    }
    
    public static NegatedFileCriteria not(FileCriteria criteria) {
        return new NegatedFileCriteria(criteria);
    }
    
    public static RegExpNameCriteria withRegEx(String regEx) {
        return new RegExpNameCriteria(regEx);
    }
    
    public static CompositeCriteria compose(CriteriaMode mode, FileCriteria ... criterias) {
        CompositeCriteria ret = new CompositeCriteria(mode);
        for(FileCriteria crit : criterias)
            ret.addCriteria(crit);
        return ret;
    }
    
    public static CompositeCriteria compose2(CriteriaMode mode, FileNameCriteria ... criterias) {
        CompositeCriteria ret = new CompositeCriteria(mode);
        ret.setFileNameCriterias(java.util.Arrays.asList(criterias));
        return ret;
    } 
}
