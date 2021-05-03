/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 */
package edu.du.menascheraymond.model.services.carshowservice;

import edu.du.menascheraymond.model.domain.CarShow;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author raymondmenasche
 */
public class CarShowHashSetImplTest {
    
    CarShow carShow1;
    CarShow carShow2;
    CarShow carShow3;
    
    public CarShowHashSetImplTest() {
        carShow1 = new CarShow.Builder("0124", "The Great Show")
                .withCarShowDate(LocalDate.of(2021, 3, 4)).build();
        carShow2 = new CarShow.Builder("4352", "The Winter Show")
                .withCarShowDate(LocalDate.of(2021, 12, 12)).build();
        carShow3 = new CarShow.Builder("4523", "The Early Bird  Show")
                .withCarShowDate(LocalDate.of(2022, 01, 03)).build();
    }

    /**
     * Test add method for CarShowHashSetImpl class.
     */
    @Test
    public void testAdd() {
        CarShowService instance = new CarShowHashSetImpl();
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
     * Test remove method for CarShowHashSetImpl class.
     */
    @Test
    public void testRemove() {
        CarShowService instance = new CarShowHashSetImpl();
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
     * Test isPresent method for CarShowHashSetImpl class.
     */
    @Test
    public void testIsPresent() {
        CarShowService instance = new CarShowHashSetImpl();
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
     * Test find method for CarShowHashSetImpl class.
     */
    @Test
    public void testFind() {
        CarShowService instance = new CarShowHashSetImpl();
        CarShow expResult = null;
        CarShow result = (CarShow)instance.find("0124");
        assertEquals(expResult, result);
        expResult = carShow1;
        instance.add(carShow1);
        instance.add(carShow2);
        instance.add(carShow3);
        result = (CarShow)instance.find("0124");
        assertEquals(expResult, result);
        expResult = carShow2;
        result = (CarShow)instance.find("4352");
        assertEquals(expResult, result);
        expResult = carShow3;
        result = (CarShow)instance.find("4523");
        assertEquals(expResult, result);
        expResult = null;
        result = (CarShow)instance.find("7777");
        assertEquals(expResult, result);
    }
    
}
