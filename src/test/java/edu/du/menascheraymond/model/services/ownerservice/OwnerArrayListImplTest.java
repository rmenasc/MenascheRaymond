/*
 * University College, University of Denver student project.
 * Not intended for production or distribution.
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 * File: OwnerArrayListImplTest.java
 */
package edu.du.menascheraymond.model.services.ownerservice;

import edu.du.menascheraymond.model.domain.Owner;
import edu.du.menascheraymond.model.services.ArrayListImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Unit Tests for OwnerArrayListImpl class.
 * @author raymond
 */
public class OwnerArrayListImplTest {
    private Owner owner1;
    private Owner owner2;
    private Owner owner3;
    
    public OwnerArrayListImplTest() {
        owner1 = new Owner.Builder("1234", "John", "Smith").build();
        owner2 = new Owner.Builder("1432", "Don", "Ron").build();
        owner3 = new Owner.Builder("5423", "Jack", "Johnson").build();
    }

    /**
     * Tests the Add method for OwnerArrayListImpl class.
     */
    @Test
    public void testAdd() {
        ArrayListImpl instance = new OwnerArrayListImpl();
        boolean expResult = true;
        boolean result = instance.add(owner1);
        assertEquals(expResult, result);
        expResult = false;
        result = instance.add(owner1);
        assertEquals(expResult, result);
        expResult = true;
        result = instance.add(owner2);
        assertEquals(expResult, result);
        result = instance.add(owner3);
        assertEquals(expResult, result);
        expResult = false;
        result = instance.add(owner2);
        assertEquals(expResult, result);
    }
    
    /**
     * Tests the Remove method for OwnerArrayListImpl class.
     */
    @Test
    public void testRemove() {
        ArrayListImpl instance = new OwnerArrayListImpl();
        boolean expResult = false;
        boolean result = instance.remove(owner1);
        assertEquals(expResult, result);
        result = instance.remove("1234");
        assertEquals(expResult, result);
        instance.add(owner1);
        instance.add(owner2);
        instance.add(owner3);
        expResult = true;
        result = instance.remove(owner2);
        assertEquals(expResult, result);
        instance.add(owner2);
        result = instance.remove("5423");
        assertEquals(expResult, result);
        expResult = false;
        result = instance.remove(owner3);
        assertEquals(expResult, result);
        result = instance.remove("5423");
        assertEquals(expResult, result);
    }
    
    /**
     * Test find method for OwnerArrayListImpl class.
     */
    @Test
    public void testFind() {
        OwnerArrayListImpl instance = new OwnerArrayListImpl();
        Owner expResult = null;
        Owner result = (Owner)instance.find("1234");
        assertEquals(expResult, result);
        instance.add(owner1);
        Owner expResult2 = owner1;
        Owner result2 = (Owner)instance.find("1234");
        assertEquals(expResult2, result2);
        instance.add(owner2);
        instance.add(owner3);
        Owner expResult3 = owner2;
        Owner result3 = (Owner)instance.find("1432");
        assertEquals(expResult3, result3);
    }
    
    /**
     * Test isPresent method for OwnerArrayListImpl class.
     */
    @Test
    public void testIsPresent() {
        ArrayListImpl instance = new OwnerArrayListImpl();
        //test with no items in array.
        boolean expResult = false;
        boolean result = instance.isPresent(owner1);
        assertEquals(expResult, result);
        result = instance.isPresent("1234");
        assertEquals(expResult, result);
        //test with only one item in array
        instance.add(owner1);
        expResult = true;
        result = instance.isPresent(owner1);
        assertEquals(expResult, result);
        result = instance.isPresent("1234");
        assertEquals(expResult, result);
        expResult = false;
        result = instance.isPresent(owner2);
        assertEquals(expResult, result);
        result = instance.isPresent("5423");
        assertEquals(expResult, result);
        //test with all items in array
        instance.add(owner2);
        instance.add(owner3);
        expResult = true;
        result = instance.isPresent(owner2);
        assertEquals(expResult, result);
        result = instance.isPresent("5423");
        assertEquals(expResult, result);
        expResult = false;
        result = instance.isPresent("7777");
        assertEquals(expResult, result);
    }
}
