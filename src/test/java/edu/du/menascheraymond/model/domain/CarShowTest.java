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
            instance.setSanctioned("yes");
        } catch(IllegalArgumentException e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isSanctioned());
        instance.setSanctioned(false);
        try {
            instance.setSanctioned("y");
        } catch(IllegalArgumentException e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isSanctioned());
        instance.setSanctioned(false);
        try {
            instance.setSanctioned("YeS");
        } catch(IllegalArgumentException e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isSanctioned());
        instance.setSanctioned(false);
        try {
            instance.setSanctioned('y');
        } catch(IllegalArgumentException e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isSanctioned());
        instance.setSanctioned(false);
        try {
            instance.setSanctioned('Y');
        } catch(IllegalArgumentException e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isSanctioned());
        instance.setSanctioned(false);
        try {
            instance.setSanctioned(1);
        } catch(IllegalArgumentException e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isSanctioned());
        
        //check set to false overloaded methods.
        expResult = false;
        try {
            instance.setSanctioned("n");
        } catch(IllegalArgumentException e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isSanctioned());
        instance.setSanctioned(true);
        try {
            instance.setSanctioned("nO");
        } catch(IllegalArgumentException e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isSanctioned());
        instance.setSanctioned(true);
        try {
            instance.setSanctioned('n');
        } catch(IllegalArgumentException e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isSanctioned());
        instance.setSanctioned(true);
        try {
            instance.setSanctioned('N');
        } catch(IllegalArgumentException e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isSanctioned());
        instance.setSanctioned(true);
        try {
            instance.setSanctioned(0);
        } catch(IllegalArgumentException e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isSanctioned());
        
        //sad road
        try {
            instance.setSanctioned("Maybe");
            fail("An Exeption should have been caught");
        } catch(IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Invalid input string");
        }
        
        try {
            instance.setSanctioned('0');
            fail("An Exeption should have been caught");
        } catch(IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Invalid character input");
        }
        
        try {
            instance.setSanctioned(35);
            fail("An Exception should have been caught");
        } catch(IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Invalid input integer");
        }
    }
    
}
