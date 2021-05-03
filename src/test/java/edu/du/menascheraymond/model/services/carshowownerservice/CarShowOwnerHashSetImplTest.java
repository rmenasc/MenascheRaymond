/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 */
package edu.du.menascheraymond.model.services.carshowownerservice;

import edu.du.menascheraymond.model.domain.CarShowOwner;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author raymondmenasche
 */
public class CarShowOwnerHashSetImplTest {
    
    CarShowOwner carShowOwner1 = new CarShowOwner("0123", "42258");
    CarShowOwner carShowOwner2 = new CarShowOwner("3252", "42532");
    CarShowOwner carShowOwner3 = new CarShowOwner("0423", "32324");
    CarShowOwner carShowOwner4 = new CarShowOwner("0123", "32324");
    
    /**
     * Tests add method for CarShowOwnerHashSetImpl class.
     */
    @Test
    public void testAdd() {
        CarShowOwnerService instance = new CarShowOwnerHashSetImpl();
        boolean expResult = true;
        boolean result = instance.add(carShowOwner1);
        assertEquals(expResult, result);
        expResult = false;
        result = instance.add(carShowOwner1);
        assertEquals(expResult, result);
        expResult = true;
        result = instance.add(carShowOwner2);
        assertEquals(expResult, result);
        result = instance.add(carShowOwner3);
        assertEquals(expResult, result);
        expResult = false;
        result = instance.add(carShowOwner2);
        assertEquals(expResult, result);
    }
    
    /**
     * Test remove method for CarShowOwnerHashSetImpl class.
     */
    @Test
    public void testRemove() {
        CarShowOwnerService instance = new CarShowOwnerHashSetImpl();
        boolean expResult = false;
        boolean result = instance.remove(carShowOwner1);
        assertEquals(expResult, result);
        instance.add(carShowOwner1);
        instance.add(carShowOwner2);
        instance.add(carShowOwner3);
        expResult = true;
        result = instance.remove(carShowOwner2);
        assertEquals(expResult, result);
        result = instance.remove("42258", "0123");
        assertEquals(expResult, result);
        expResult = false;
        result = instance.remove(carShowOwner2);
        assertEquals(expResult, result);
        result = instance.remove("0000", "0423");
        assertEquals(expResult, result);
        result = instance.remove("32324", "0000");
        assertEquals(expResult, result);
    }
    
    /**
     * Test the removeByOwnerId method for CarShowOwnerHashSetImpl class.
     */
    @Test
    public void testRemoveByOwnerId() {
        CarShowOwnerService instance = new CarShowOwnerHashSetImpl();
        boolean expResult = false;
        boolean result = instance.removeByOwnerId("42258");
        assertEquals(expResult, result);
        instance.add(carShowOwner1);
        expResult = true;
        result = instance.removeByOwnerId("42258");
        assertEquals(expResult, result);
        instance.add(carShowOwner1);
        instance.add(carShowOwner2);
        instance.add(carShowOwner3);
        instance.add(carShowOwner4);
        instance.removeByOwnerId("32324");
        int expResult2 = 2;
        int result2 = instance.size();
        assertEquals(expResult2, result2);
    }
    
    
    @Test
    public void testRemoveByCarShowId() {
        CarShowOwnerService instance = new CarShowOwnerHashSetImpl();
        boolean expResult = false;
        boolean result = instance.removeByCarShowId("0123");
        assertEquals(expResult, result);
        instance.add(carShowOwner1);
        expResult = true;
        result = instance.removeByCarShowId("0123");
        assertEquals(expResult, result);
        instance.add(carShowOwner1);
        instance.add(carShowOwner2);
        instance.add(carShowOwner3);
        instance.add(carShowOwner4);
        instance.removeByCarShowId("0123");
        int expResult2 = 2;
        int result2 = instance.size();
        assertEquals(expResult2, result2);
    }
    
    /**
     * Test find method for CarShowOwnerHashSetImpl class.
     */
    @Test
    public void testFind() {
        CarShowOwnerService instance = new CarShowOwnerHashSetImpl();
        CarShowOwner expResult = null;
        CarShowOwner result = instance.find("42258", "0123");
        assertEquals(expResult, result);
        instance.add(carShowOwner1);
        CarShowOwner expResult2 = carShowOwner1;
        CarShowOwner result2 = instance.find("42258", "0123");
        assertEquals(expResult2, result2);
    }
    
    /**
     * Test isPresent method for CarShowOwnerHashSetImpl class.
     */
    @Test
    public void testIsPresent() {
        CarShowOwnerService instance = new CarShowOwnerHashSetImpl();
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
