/*
 * University College, University of Denver student project.
 * Not intended for production or distribution.
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 */
package edu.du.menascheraymond.model.services.ownerservice;

import edu.du.menascheraymond.model.domain.Owner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
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
        OwnerArrayListImpl instance = new OwnerArrayListImpl();
        boolean expResult = true;
        boolean result = instance.add(owner1);
        assertEquals(expResult, result);
        expResult = false;
        result = instance.add(owner1);
        assertEquals(expResult, result);
    }
    
    /**
     * Tests the Remove method for OwnerArrayListImpl class.
     */
    @Test
    public void testRemove() {
        OwnerArrayListImpl instance = new OwnerArrayListImpl();
        boolean expResult = false;
        boolean result = instance.remove(owner1);
        assertEquals(expResult, result);
        instance.add(owner1);
        instance.add(owner2);
        instance.add(owner3);
        expResult = true;
        result = instance.remove(owner2);
        assertEquals(expResult, result);
        expResult = false;
        result = instance.remove(owner2);
        assertEquals(expResult, result);
    }
}
