/*
 * University College, University of Denver student project.
 * Not intended for production or distribution.
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 */
package edu.du.menascheraymond.model.regularexpressions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author raymond
 */
public class RegExTest {
    
    public RegExTest() {
    }

    @Test
    public void testIsPhoneNumber() {
        RegEx instance = new RegEx();
        String s = "555-555-5555";
        assertTrue(instance.isPhoneNumber(s));
        s = "5555555555";
        assertFalse(instance.isPhoneNumber(s));
        s = "hello world";
        assertFalse(instance.isPhoneNumber(s));
        s = "abc-abc-aabb";
        assertFalse(instance.isPhoneNumber(s));
        s = "";
        assertFalse(instance.isPhoneNumber(s));
    }
    
    @Test
    public void testIsNumber() {
        RegEx instance = new RegEx();
        String s = "3";
        assertTrue(instance.isNumber(s));
        s = "33";
        assertTrue(instance.isNumber(s));
        s = "a";
        assertFalse(instance.isNumber(s));
        s = "aaakd";
        assertFalse(instance.isNumber(s));
        s = "akd 5'adk";
        assertFalse(instance.isNumber(s));
    }
    
    @Test
    public void testIsZipCode() {
        RegEx instance = new RegEx();
        String s = "33310";
        assertTrue(instance.isZipCode(s));
        s = "33";
        assertFalse(instance.isZipCode(s));
        s = "aaaaa";
        assertFalse(instance.isZipCode(s));
    }
    
    @Test
    public void testIsYear() {
        RegEx instance = new RegEx();
        String s = "2020";
        assertTrue(instance.isYear(s));
        s = "adbdk";
        assertFalse(instance.isYear(s));
        s = "345";
        assertFalse(instance.isYear(s));
        s = "20201";
        assertFalse(instance.isYear(s));
        s = "";
        assertFalse(instance.isYear(s));
    }
}
