/*
 * University College, University of Denver student project.
 * Not intended for production or distribution.
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 * File: RegEx.java
 */
package edu.du.menascheraymond.model.regularexpressions;

/**
 * Regular Expressions
 * @author raymond
 */
public class RegEx {
    
    public boolean isPhoneNumber(String s) {
        String regex = "\\d{3}-\\d{3}-\\d{4}?";
        return s.matches(regex);
    }
    
    public boolean isNumber(String s) {
        String regex = "\\d+";
        return s.matches(regex);
    }
    
    public boolean isZipCode(String s) {
        String regex = "\\d{5}(-\\d{4})?";
        return s.matches(regex);
    }
    
    public boolean isYear(String s) {
        String regex = "\\d{4}?";
        return s.matches(regex);
    }
}
