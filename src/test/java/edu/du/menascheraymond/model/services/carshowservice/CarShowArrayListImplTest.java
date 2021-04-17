/*
 * University College, University of Denver student project.
 * Not intended for production or distribution.
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 * File: CarShowArrayListImplTest.java
 */
package edu.du.menascheraymond.model.services.carshowservice;

import edu.du.menascheraymond.model.domain.CarShow;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for CarShowArrayListImpl class.
 * @author raymond
 */
public class CarShowArrayListImplTest {
    CarShow carShow1;
    CarShow carShow2;
    CarShow carShow3;
    
    public CarShowArrayListImplTest() {
        carShow1 = new CarShow.Builder("0124")
                .withCarShowDate(LocalDate.of(2021, 3, 4))
                .withCarShowTitle("The Great Show").build();
        carShow2 = new CarShow.Builder("4352")
                .withCarShowDate(LocalDate.of(2021, 12, 12))
                .withCarShowTitle("The Winter Show").build();
        carShow3 = new CarShow.Builder("4523")
                .withCarShowDate(LocalDate.of(2022, 01, 03))
                .withCarShowTitle("Early Bird Show").build();
    }

    /**
     * Test add method for CarShowArrayListImpl class.
     */
    @Test
    public void testAdd() {
        CarShowArrayListImpl instance = new CarShowArrayListImpl();
        boolean expResult = true;
        boolean result = instance.add(carShow1);
        assertEquals(expResult, result);
        expResult = false;
        result = instance.add(carShow1);
        assertEquals(expResult, result);
        expResult = true;
        result = instance.add(carShow2);
        assertEquals(expResult, result);
        result = instance.add(carShow3);
        assertEquals(expResult, result);
        expResult = false;
        result = instance.add(carShow2);
        assertEquals(expResult, result);
    }
    
    /**
     * Test remove method for CarShowArrayListImpl class();
     */
    @Test
    public void testRemove() {
        CarShowArrayListImpl instance = new CarShowArrayListImpl();
        boolean expResult = false;
        boolean result = instance.remove(carShow1);
        assertEquals(expResult, result);
        instance.add(carShow1);
        instance.add(carShow2);
        instance.add(carShow3);
        expResult = true;
        result = instance.remove(carShow3);
        assertEquals(expResult, result);
        instance.add(carShow3);
        result = instance.remove("4352");
        assertEquals(expResult, result);
        expResult = false;
        result = instance.remove("7777");
        assertEquals(expResult, result);
    }
    
    /**
     * Test isPresent method for CarShowArrayListImpl class.
     */
    @Test
    public void testIsPresent() {
        CarShowArrayListImpl instance = new CarShowArrayListImpl();
        boolean expResult = false;
        boolean result = instance.isPresent(carShow2);
        assertEquals(expResult, result);
        result = instance.isPresent("4352");
        assertEquals(expResult, result);
        instance.add(carShow3);
        result = instance.isPresent(carShow1);
        assertEquals(expResult, result);
        result = instance.isPresent("0124");
        assertEquals(expResult, result);
        instance.add(carShow2);
        instance.add(carShow3);
        expResult = true;
        result = instance.isPresent(carShow2);
        assertEquals(expResult, result);
        result = instance.isPresent("4352");
        assertEquals(expResult, result);
        expResult = false;
        result = instance.isPresent("7777");
        assertEquals(expResult, result);
    }
    
    /**
     * Test find method for CarShowArrayListImpl class.
     */
    @Test
    public void testFind() {
        CarShowArrayListImpl instance = new CarShowArrayListImpl();
        CarShow expResult = null;
        CarShow result = instance.find("0124");
        assertEquals(expResult, result);
        expResult = carShow1;
        instance.add(carShow1);
        instance.add(carShow2);
        instance.add(carShow3);
        result = instance.find("0124");
        assertEquals(expResult, result);
        expResult = carShow2;
        result = instance.find("4352");
        assertEquals(expResult, result);
        expResult = carShow3;
        result = instance.find("4523");
        assertEquals(expResult, result);
        expResult = null;
        result = instance.find("7777");
        assertEquals(expResult, result);
    }
}
