/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 */
package edu.du.menascheraymond.model.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 *
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
        instance.setIsInsured(false);
        boolean expResult = true;
        try {
            instance.setIsInsured("y");
        } catch(Exception e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isInsured());
        instance.setIsInsured(false);
        try {
            instance.setIsInsured("yEs");
        } catch(Exception e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isInsured());
        instance.setIsInsured(false);
        try {
            instance.setIsInsured('y');
        } catch(Exception e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isInsured());
        instance.setIsInsured(false);
        try {
            instance.setIsInsured('Y');
        } catch(Exception e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isInsured());
        instance.setIsInsured(false);
        try {
            instance.setIsInsured(1);
        } catch(Exception e) {
            fail("An Exception should not have been caught");
        }
        
        //test set to false overloaded methods
        expResult = false;
        try {
            instance.setIsInsured("n");
        } catch(Exception e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isInsured());
        instance.setIsInsured(true);
        try {
            instance.setIsInsured("no");
        } catch(Exception e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isInsured());
        instance.setIsInsured(true);
        try {
            instance.setIsInsured('n');
        } catch(Exception e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isInsured());
        instance.setIsInsured(true);
        try {
            instance.setIsInsured('N');
        } catch(Exception e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isInsured());
        instance.setIsInsured(true);
        try {
            instance.setIsInsured(0);
        } catch(Exception e) {
            fail("An Exception should not have been caught");
        }
        assertEquals(expResult, instance.isInsured());
        
        //sad path
        try {
            instance.setIsInsured("yeah");
            fail("An Exception should be caught");
        } catch(Exception e) {
            assertEquals(e.getMessage(), "Invalid string input");
        }
        
        try {
            instance.setIsInsured('1');
            fail("An Exception should be caught");
        } catch(Exception e) {
            assertEquals(e.getMessage(), "Invalid character input");
        }
        
        try {
            instance.setIsInsured(45);
            fail("An Exception should have been caught");
        } catch(Exception e) {
            assertEquals(e.getMessage(), "Invalid integer input");
        }
    }
}
