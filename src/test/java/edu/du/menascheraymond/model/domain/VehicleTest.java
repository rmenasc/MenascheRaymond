/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 * File: VehicleTest.java
 */
package edu.du.menascheraymond.model.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests for Vehicle class.
 * @author raymond
 */
public class VehicleTest {
    Vehicle instance;
    
    public VehicleTest() {
        this.instance = new Vehicle.Builder("1", "2")
                .withManufacturer("Ford").withModel("Mustang").withSubModel("Cobra")
                .withModelYear(1969)
                .withVehicleClassification(VehicleClassification.CLASSIC).build();
    }

    /**
     * Tests ValidateVehicleClassification method.
     */
    @Test
    public void testValidateVehicleClassification() {
        
        boolean expResult = true;
        boolean result = instance.validateVehicleClassification();
        assertEquals(expResult, result);
        
        instance.setVehicleClassification(VehicleClassification.ANTIQUE);
        expResult = false;
        result = instance.validateVehicleClassification();
        assertEquals(expResult, result);
    }
    
    /**
     * Tests FindVehcileClassification method.
     */
    @Test
    public void testFindVehicleClassification() {
        VehicleClassification expResult = VehicleClassification.CLASSIC;
        VehicleClassification result = instance.findVehicleClassification(1960);
        assertEquals(expResult, result);
        
        expResult = VehicleClassification.ANTIQUE;
        result = instance.findVehicleClassification(1936);
        assertEquals(expResult, result);
        
        expResult = VehicleClassification.MODERN;
        result = instance.findVehicleClassification(2004);
        assertEquals(expResult, result);
        
        expResult = VehicleClassification.ANTIQUE;
        result = instance.findVehicleClassification(1950);
        assertEquals(expResult, result);
        
        expResult = VehicleClassification.CLASSIC;
        result = instance.findVehicleClassification(1951);
        assertEquals(expResult, result);
        result = instance.findVehicleClassification(1980);
        assertEquals(expResult, result);
        
        expResult = VehicleClassification.MODERN;
        result = instance.findVehicleClassification(1981);
        assertEquals(expResult, result);
   }
    
    /**
     * Test overloaded isInsured methods.
     */
    @Test
    public void testIsInsured() {
        //happy path
        //test set to true overloaded methods
        instance.setInsured(false);
        boolean expResult = true;
        try {
            instance.setInsured("y");
        } catch(IllegalArgumentException e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isInsured());
        instance.setInsured(false);
        try {
            instance.setInsured("yEs");
        } catch(IllegalArgumentException e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isInsured());
        instance.setInsured(false);
        try {
            instance.setInsured('y');
        } catch(IllegalArgumentException e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isInsured());
        instance.setInsured(false);
        try {
            instance.setInsured('Y');
        } catch(IllegalArgumentException e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isInsured());
        instance.setInsured(false);
        try {
            instance.setInsured(1);
        } catch(IllegalArgumentException e) {
            fail("An Exception should not have been caught");
        }
        
        //test set to false overloaded methods
        expResult = false;
        try {
            instance.setInsured("n");
        } catch(IllegalArgumentException e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isInsured());
        instance.setInsured(true);
        try {
            instance.setInsured("no");
        } catch(IllegalArgumentException e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isInsured());
        instance.setInsured(true);
        try {
            instance.setInsured('n');
        } catch(IllegalArgumentException e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isInsured());
        instance.setInsured(true);
        try {
            instance.setInsured('N');
        } catch(IllegalArgumentException e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isInsured());
        instance.setInsured(true);
        try {
            instance.setInsured(0);
        } catch(IllegalArgumentException e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isInsured());
        
        //sad path
        try {
            instance.setInsured("yeah");
            fail("An Exception should be caught");
        } catch(IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Invalid string input");
        }
        
        try {
            instance.setInsured('1');
            fail("An Exception should be caught");
        } catch(IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Invalid character input");
        }
        
        try {
            instance.setInsured(45);
            fail("An Exception should have been caught");
        } catch(IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Invalid integer input");
        }
    }
}
