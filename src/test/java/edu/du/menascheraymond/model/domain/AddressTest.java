/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 * File: AddressTest.java
 */
package edu.du.menascheraymond.model.domain;

import java.security.InvalidParameterException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

/**
 * Unit Tests for Address Domain Class.
 * @author raymond
 */
public class AddressTest {
    

    /**
     * Test Builder class and methods for Address class.
     */
    @Test
    public void testBuilder() {
        try {
            // test empty build.
            Address instance = new Address.Builder().build();
            fail("An expeption should have been caught");
        } catch (InvalidParameterException e) {
            assertEquals(e.getMessage(), "street1 attribute must be assigned a value but it is null");
        }
        try {
            //test missing city
            Address instance = new Address.Builder().withStreet1("111 There st")
                    .withState("Florida")
                    .withZip("33186").build();
            fail("An exception should have been caught");
        } catch (InvalidParameterException e) {
            assertEquals(e.getMessage(), "city attribute must be assigned a value, but it is null");
        }
        try {
            //test missing state
            Address instance = new Address.Builder().withStreet1("111 There st")
                    .withCity("Miami")
                    .withZip("23455").build();
            fail("An exception should have been caught");
        } catch (InvalidParameterException e) {
            assertEquals(e.getMessage(), "state attribute must be assigned a value, but it is null");
        }
        try {
            //test missing zip code
            Address instance = new Address.Builder().withStreet1("111 There st")
                    .withCity("Miami")
                    .withState("Florida").build();
            fail("An exception should have been caught");
        } catch (InvalidParameterException e) {
            assertEquals(e.getMessage(), "zipCode attribute must be assigned a value, but it is null");
        }
        try {
            //test missing optional value
            Address instance = new Address.Builder().withStreet1("111 There st")
                    .withCity("Miami")
                    .withState("Florida")
                    .withZip("33189").build();
        } catch (InvalidParameterException e) {
            fail("An exception should not have been caught");
        }
    }
    
}
