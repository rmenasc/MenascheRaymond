/*
 * University College, University of Denver student project.
 * Not intended for production or distribution.
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 * File: CarShowOwnerArrayListImplTest.java
 */
package edu.du.menascheraymond.model.services.carshowownerservice;

import edu.du.menascheraymond.model.domain.CarShowOwner;
import edu.du.menascheraymond.model.service.JoinArrayListImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Tests for CarShowOwnerArrayListImpl class.
 * @author raymond
 */
public class CarShowOwnerArrayListImplTest {
    CarShowOwner carShowOwner1 = new CarShowOwner("0123", "42258");
    CarShowOwner carShowOwner2 = new CarShowOwner("3252", "42532");
    CarShowOwner carShowOwner3 = new CarShowOwner("0423", "32324");
    
    /**
     * Tests add method for CarShowOwnerArrayListImpl class.
     */
    @Test
    public void testAdd() {
        JoinArrayListImpl instance = new CarShowOwnerArrayListImpl();
        boolean expResult = true;
        boolean result = instance.add(carShowOwner1);
        assertEquals(expResult, result);
        expResult = false;
        result = instance.add(carShowOwner1);
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test remove method for CarShowOwnerArrayListImpl class.
     */
    @Test
    public void testRemove() {
        JoinArrayListImpl instance = new CarShowOwnerArrayListImpl();
        boolean expResult = false;
        boolean result = instance.remove(carShowOwner1);
        assertEquals(expResult, result);
        instance.add(carShowOwner1);
        instance.add(carShowOwner2);
        instance.add(carShowOwner3);
        expResult = true;
        result = instance.remove(carShowOwner2);
        assertEquals(expResult, result);
        expResult = false;
        result = instance.remove(carShowOwner2);
        assertEquals(expResult, result);
    }
    
    /**
     * Test isPresent method for CarShowOwnerArrayListImpl class.
     */
    @Test
    public void testIsPresent() {
        JoinArrayListImpl instance = new CarShowOwnerArrayListImpl();
        boolean expResult = false;
        boolean result = instance.isPresent(carShowOwner1);
        assertEquals(expResult, result);
        result = instance.isPresent("42258", "0123");
        assertEquals(expResult, result);
        expResult = true;
        instance.add(carShowOwner1);
        result = instance.isPresent(carShowOwner1);
        assertEquals(expResult, result);
        result = instance.isPresent("42258", "0123");
        assertEquals(expResult, result);
    }
}
