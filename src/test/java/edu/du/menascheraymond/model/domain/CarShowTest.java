/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 * File: CarShowTest.java
 */
package edu.du.menascheraymond.model.domain;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests for CarShow Domain Class.
 * @author raymond
 */
public class CarShowTest {

    /**
     * Test setIsSanctioned overloaded methods.
     */
    @Test
    public void testSetIsSanctioned() {
        CarShow instance = new CarShow.Builder("123", "Test1")
                .withCarShowDate(LocalDate.of(2021, 12, 12)).isSanctioned(false)
                .build();
        
        //happy road
        //check set to true overloaded methods.
        boolean expResult = true;
        try {
            instance.setIsSanctioned("yes");
        } catch(IllegalArgumentException e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isSanctioned());
        instance.setIsSanctioned(false);
        try {
            instance.setIsSanctioned("y");
        } catch(IllegalArgumentException e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isSanctioned());
        instance.setIsSanctioned(false);
        try {
            instance.setIsSanctioned("YeS");
        } catch(IllegalArgumentException e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isSanctioned());
        instance.setIsSanctioned(false);
        try {
            instance.setIsSanctioned('y');
        } catch(IllegalArgumentException e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isSanctioned());
        instance.setIsSanctioned(false);
        try {
            instance.setIsSanctioned('Y');
        } catch(IllegalArgumentException e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isSanctioned());
        instance.setIsSanctioned(false);
        try {
            instance.setIsSanctioned(1);
        } catch(IllegalArgumentException e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isSanctioned());
        
        //check set to false overloaded methods.
        expResult = false;
        try {
            instance.setIsSanctioned("n");
        } catch(IllegalArgumentException e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isSanctioned());
        instance.setIsSanctioned(true);
        try {
            instance.setIsSanctioned("nO");
        } catch(IllegalArgumentException e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isSanctioned());
        instance.setIsSanctioned(true);
        try {
            instance.setIsSanctioned('n');
        } catch(IllegalArgumentException e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isSanctioned());
        instance.setIsSanctioned(true);
        try {
            instance.setIsSanctioned('N');
        } catch(IllegalArgumentException e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isSanctioned());
        instance.setIsSanctioned(true);
        try {
            instance.setIsSanctioned(0);
        } catch(IllegalArgumentException e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isSanctioned());
        
        //sad road
        try {
            instance.setIsSanctioned("Maybe");
            fail("An Exeption should have been caught");
        } catch(IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Invalid input string");
        }
        
        try {
            instance.setIsSanctioned('0');
            fail("An Exeption should have been caught");
        } catch(IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Invalid character input");
        }
        
        try {
            instance.setIsSanctioned(35);
            fail("An Exception should have been caught");
        } catch(IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Invalid input integer");
        }
    }
    
}
