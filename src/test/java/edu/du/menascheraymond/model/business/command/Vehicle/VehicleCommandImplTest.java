/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * VehicleCommandImplTest.java
 */
package edu.du.menascheraymond.model.business.command.Vehicle;

import edu.du.menascheraymond.model.business.command.vehiclecommand.VehicleCommand;
import edu.du.menascheraymond.model.business.command.vehiclecommand.VehicleCommandImpl;
import edu.du.menascheraymond.model.business.manager.Manager;
import edu.du.menascheraymond.model.domain.Owner;
import edu.du.menascheraymond.model.domain.Vehicle;
import edu.du.menascheraymond.model.domain.VehicleClassification;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit tests for the VehicleCommandImpl class
 * @author raymondmenasche
 */
public class VehicleCommandImplTest {
    private Manager manager;
    private Map<String,String> command1;
    private Map<String,String> command2;
    private Map<String,String> command3;
    private Map<String,String> command4;
    private Vehicle vehicle;
    
    public VehicleCommandImplTest() {
        manager = new Manager();
        
        Owner o = new Owner.Builder("O123", "Jack", "Johnson").build();
        manager.getOwnerCollection().add(o);
        
        command1 = new HashMap<>();
        command1.put("TYPE", "VEHICLE");
        command1.put("ACTION", "ADD");
        command1.put("vehicleId", "V321");
        command1.put("ownerId", "O123");
        command1.put("year", "1999");
        command1.put("manufacturer", "Ford");
        command1.put("model", "Mustang");
        command1.put("subModel", "Cobra");
        command1.put("classification", "MODERN");
        command1.put("insured", "Y");
        
        command2 = new HashMap<>();
        command2.put("TYPE", "VEHICLE");
        command2.put("ACTION", "REM");
        command2.put("vehicleId", "V321");
        
        command3 = new HashMap<>();
        command3.put("TYPE", "VEHICLE");
        command3.put("ACTION", "ADD");
        command3.put("VALUE1", "V321");
        command3.put("VALUE2", "O123");
        command3.put("VALUE3", "1999");
        command3.put("VALUE4", "Ford");
        command3.put("VALUE5", "Mustang");
        command3.put("VALUE6", "Cobra");
        command3.put("VALUE7", "MODERN");
        command3.put("VALUE8", "Y");
        
        command4 = new HashMap<>();
        command4.put("TYPE", "VEHICLE");
        command4.put("ACTION", "REM");
        command4.put("VALUE1", "V321");
        
        vehicle = new Vehicle.Builder("V321", "O123")
                .withManufacturer("Ford")
                .withModel("Mustang")
                .withSubModel("Cobra")
                .withModelYear(1999)
                .withVehicleClassification(VehicleClassification.MODERN).build();
    }

    /**
     * Test of add method, of class VehicleCommandImpl.
     */
    @Test
    public void testAdd() {
        //happy path
        VehicleCommand instance = new VehicleCommandImpl();
        instance.setManager(manager);
        Vehicle expResult = vehicle;
        Vehicle result = instance.add(command1);
        assertEquals(expResult, result);
        
        //sad path
        Vehicle expResult2 = null;
        Vehicle result2 = instance.add(command1);
        assertEquals(expResult2, result2);
        
        //exceptions
        try {
            Map<String,String> cmd = new HashMap<>();
            cmd.put("TYPE", "VEHICLE");
            cmd.put("ACTION", "ADD");
            cmd.put("vehicleId", "V424");
            Vehicle v = instance.add(cmd);
            fail("An exception should have been caught");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Error creating array. Size mismatch");
        }
        
        //no key happy path
        Vehicle expResult3 = vehicle;
        manager.getVehicleCollection().remove(vehicle);
        Vehicle result3 = instance.add(command3);
        assertEquals(expResult3, result3);
        
        //no key sad path
        Vehicle expResult4 = null;
        Vehicle result4 = instance.add(command3);
        assertEquals(expResult4, result4);
    }

    /**
     * Test of remove method, of class VehicleCommandImpl.
     */
    @Test
    public void testRemove() {
        //happy path
        VehicleCommand instance = new VehicleCommandImpl();
        instance.setManager(manager);
        manager.getVehicleCollection().add(vehicle);
        Vehicle expResult = vehicle;
        Vehicle result = instance.remove(command2);
        assertEquals(expResult, result);
        
        //sad path
        Vehicle expResult2 = null;
        Vehicle result2 = instance.remove(command2);
        assertEquals(expResult2, result2);
        
        //no key happy path
        Vehicle expResult3 = vehicle;
        manager.getVehicleCollection().add(vehicle);
        Vehicle result3 = instance.remove(command4);
        assertEquals(expResult3, result3);
        
        //no key sad path
        Vehicle expResult4 = null;
        Vehicle result4 = instance.remove(command4);
        assertEquals(expResult4, result4);
    }

    /**
     * Test of performCommands method, of class VehicleCommandImpl.
     */
    @Test
    public void testPerformCommands() {
        //happy path add
        VehicleCommand instance = new VehicleCommandImpl();
        instance.setManager(manager);
        boolean expResult = true;
        boolean result = instance.performCommands(command1);
        assertEquals(expResult, result);
        
        //sad path add
        expResult = false;
        result = instance.performCommands(command1);
        assertEquals(expResult, result);
        
        //happy path remove
        expResult = true;
        result = instance.performCommands(command2);
        assertEquals(expResult, result);
        
        //sad path remove
        expResult = false;
        result = instance.performCommands(command2);
        assertEquals(expResult, result);
        
        //no key add
        expResult = true;
        result = instance.performCommands(command3);
        assertEquals(expResult, result);
        
        //no key remove
        result = instance.performCommands(command4);
        assertEquals(expResult, result);
    }
    
}
