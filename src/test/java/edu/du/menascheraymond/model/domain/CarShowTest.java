/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
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
    
    public CarShowTest() {
    }

    /**
     * Test setIsSanctioned overloaded methods.
     */
    @Test
    public void testSetIsSanctioned() {
        CarShow instance = new CarShow.Builder("123").withCarShowTitle("Test1")
                .withCarShowDate(LocalDate.of(2021, 12, 12)).isSanctioned(false)
                .build();
        
        //happy road
        //check set to true overloaded methods.
        boolean expResult = true;
        try {
            instance.setIsSanctioned("yes");
        } catch(Exception e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isSanctioned());
        instance.setIsSanctioned(false);
        try {
            instance.setIsSanctioned("y");
        } catch(Exception e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isSanctioned());
        instance.setIsSanctioned(false);
        try {
            instance.setIsSanctioned("YeS");
        } catch(Exception e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isSanctioned());
        instance.setIsSanctioned(false);
        try {
            instance.setIsSanctioned('y');
        } catch(Exception e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isSanctioned());
        instance.setIsSanctioned(false);
        try {
            instance.setIsSanctioned('Y');
        } catch(Exception e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isSanctioned());
        instance.setIsSanctioned(false);
        try {
            instance.setIsSanctioned(1);
        } catch(Exception e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isSanctioned());
        
        //check set to false overloaded methods.
        expResult = false;
        try {
            instance.setIsSanctioned("n");
        } catch(Exception e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isSanctioned());
        instance.setIsSanctioned(true);
        try {
            instance.setIsSanctioned("nO");
        } catch(Exception e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isSanctioned());
        instance.setIsSanctioned(true);
        try {
            instance.setIsSanctioned('n');
        } catch(Exception e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isSanctioned());
        instance.setIsSanctioned(true);
        try {
            instance.setIsSanctioned('N');
        } catch(Exception e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isSanctioned());
        instance.setIsSanctioned(true);
        try {
            instance.setIsSanctioned(0);
        } catch(Exception e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isSanctioned());
        
        //sad road
        try {
            instance.setIsSanctioned("Maybe");
            fail("An Exeption should have been caught");
        } catch(Exception e) {
            assertEquals(e.getMessage(), "Invalid input string");
        }
        
        try {
            instance.setIsSanctioned('0');
            fail("An Exeption should have been caught");
        } catch(Exception e) {
            assertEquals(e.getMessage(), "Invalid character input");
        }
        
        try {
            instance.setIsSanctioned(35);
            fail("An Exception should have been caught");
        } catch(Exception e) {
            assertEquals(e.getMessage(), "Invalid input integer");
        }
    }
    
}
