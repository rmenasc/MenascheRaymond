/*
 * University College, University of Denver student project.
 * Not intended for production or distribution.
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 */
package edu.du.menascheraymond.model.regularexpressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author raymond
 */
public class RegEx {
    
    public boolean isPhoneNumber(String s) {
        boolean rv = false;
        Pattern pattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
        Matcher matcher = pattern.matcher(s);
        rv = matcher.find();
        return rv;
    }
    
    public boolean isNumber(String s) {
        boolean rv = false;
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(s);
        rv = matcher.find();
        return rv;
    }
    
    public boolean isZipCode(String s) {
        boolean rv = false;
        Pattern pattern = Pattern.compile("\\d{5}");
        Matcher matcher = pattern.matcher(s);
        rv = matcher.find();
        return rv;
    }
}
