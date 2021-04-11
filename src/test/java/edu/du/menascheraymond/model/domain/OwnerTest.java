/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 */
package edu.du.menascheraymond.model.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests for Owner Domain Class.
 * @author raymond
 */
public class OwnerTest {
    
    public OwnerTest() {
    }

    /**
     * Test isSenior method.
     */
    @Test
    public void testIsInsured() {
        Address address = new Address.Builder().withStreet1("111 There St")
                .withCity("Denver").withState("CO").withZip("80001").build();
        Owner instance = new Owner.Builder("1", "Ray", "Jones")
                .withPhoneNumber("555-555-5555").withAddress(address)
                .withNumYears(10).build();
        boolean expResult = false;
        boolean result = instance.isSeniorOwner();
        assertEquals(expResult, result);
        
        instance.setNumYears(26);
        expResult = true;
        result = instance.isSeniorOwner();
        assertEquals(expResult, result);
    }
    
}
