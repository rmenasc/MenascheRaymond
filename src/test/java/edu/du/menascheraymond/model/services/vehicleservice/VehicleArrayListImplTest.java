/*
 * University College, University of Denver student project.
 * Not intended for production or distribution.
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 * File: VehicleArrayListImplTest.java
 */
package edu.du.menascheraymond.model.services.vehicleservice;

import edu.du.menascheraymond.model.domain.Vehicle;
import edu.du.menascheraymond.model.domain.VehicleClassification;
import edu.du.menascheraymond.model.service.ArrayListImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for VehicleArrayListImpl class.
 * @author raymond
 */
public class VehicleArrayListImplTest {
    Vehicle vehicle1;
    Vehicle vehicle2;
    Vehicle vehicle3;
    
    public VehicleArrayListImplTest() {
        vehicle1 = new Vehicle.Builder("0212", "34131")
                .withManufacturer("Ford")
                .withModelYear(1969)
                .withModel("Mustang")
                .withSubModel("Cobra")
                .withVehicleClassification(VehicleClassification.CLASSIC)
                .build();
        vehicle2 = new Vehicle.Builder("0324", "22432")
                .withManufacturer("Chevrolet")
                .withModelYear(1945)
                .withModel("Impala")
                .withVehicleClassification(VehicleClassification.ANTIQUE)
                .build();
        vehicle3 = new Vehicle.Builder("6453", "23212")
                .withManufacturer("Chevrolet")
                .withModelYear(2021)
                .withModel("Corvette")
                .withVehicleClassification(VehicleClassification.MODERN)
                .build();
    }

    /**
     * Test add method for VehicleArrayListImpl class.
     */
    @Test
    public void testAdd() {
        ArrayListImpl instance = new VehicleArrayListImpl();
        boolean expResult = true;
        boolean result = instance.add(vehicle1);
        assertEquals(expResult, result);
        expResult = false;
        result = instance.add(vehicle1);
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test remove method for VehicleArrayListImpl class.
     */
    @Test
    public void testRemove() {
        ArrayListImpl instance = new VehicleArrayListImpl();
        boolean expResult = false;
        boolean result = instance.remove(vehicle1);
        assertEquals(expResult, result);
        instance.add(vehicle1);
        instance.add(vehicle2);
        instance.add(vehicle3);
        expResult = true;
        result = instance.remove(vehicle1);
        assertEquals(expResult, result);
        instance.add(vehicle1);
        result = instance.remove("0212");
        assertEquals(expResult, result);
    }
    
    /**
     * Test find method for VehicleArrayListImpl class.
     */
    @Test
    public void testFind() {
        ArrayListImpl instance = new VehicleArrayListImpl();
        Vehicle expResult = null;
        Vehicle result = (Vehicle)instance.find("0212");
        assertEquals(expResult, result);
        instance.add(vehicle1);
        instance.add(vehicle2);
        instance.add(vehicle3);
        Vehicle expResult2 = vehicle2;
        Vehicle result2 = (Vehicle)instance.find("0324");
        assertEquals(expResult2, result2);
        Vehicle expResult3 = vehicle1;
        Vehicle result3 = (Vehicle)instance.find("0212");
        assertEquals(expResult3, result3);
    }
    
    /**
     * Test isPresent method for VehicleArrayListImpl class.
     */
    @Test
    public void testIsPresent() {
        ArrayListImpl instance = new VehicleArrayListImpl();
        boolean expResult = false;
        boolean result = instance.isPresent(vehicle3);
        assertEquals(expResult, result);
        result = instance.isPresent("0212");
        assertEquals(expResult, result);
        instance.add(vehicle1);
        instance.add(vehicle2);
        instance.add(vehicle3);
        expResult = true;
        result = instance.isPresent(vehicle3);
        assertEquals(expResult, result);
        result = instance.isPresent("0324");
        assertEquals(expResult, result);
    }
}
