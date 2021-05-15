/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * CarShowOwnerCommandImplTest.java
 */
package edu.du.menascheraymond.model.business.command.CarShowOwner;

import edu.du.menascheraymond.model.business.command.carshowownercommand.CarShowOwnerCommandImpl;
import edu.du.menascheraymond.model.business.command.carshowownercommand.CarShowOwnerCommand;
import edu.du.menascheraymond.model.business.manager.Manager;
import edu.du.menascheraymond.model.domain.CarShow;
import edu.du.menascheraymond.model.domain.CarShowOwner;
import edu.du.menascheraymond.model.domain.Owner;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit tests for the CarShowOwnerCommandImpl class.
 * @author raymondmenasche
 */
public class CarShowOwnerCommandImplTest {
    private Manager manager;
    private Map<String,String> command1;
    private Map<String,String> command2;
    private Map<String,String> command3;
    private Map<String,String> command4;
    
    public CarShowOwnerCommandImplTest() {
        manager = new Manager();
        
        Owner owner = new Owner.Builder("O123", "Jack", "Johnson").build();
        manager.getOwnerCollection().add(owner);
        
        CarShow carShow = new CarShow.Builder("C321", "The Great Show").build();
        manager.getCarShowCollection().add(carShow);
        
        command1 = new HashMap<>();
        command1.put("TYPE", "CSO");
        command1.put("ACTION", "ADD");
        command1.put("ownerId", "O123");
        command1.put("carShowId", "C321");
        
        command2 = new HashMap<>();
        command2.put("TYPE", "CSO");
        command2.put("ACTION", "REM");
        command2.put("ownerId", "O123");
        command2.put("carShowId", "C321");
        
        command3 = new HashMap<>();
        command3.put("TYPE", "CSO");
        command3.put("ACTION", "ADD");
        command3.put("VALUE1", "O123");
        command3.put("VALUE2", "C321");
        
        command4 = new HashMap<>();
        command4.put("TYPE", "CSO");
        command4.put("ACTION", "REM");
        command4.put("VALUE1", "O123");
        command4.put("VALUE2", "C321");
    }

    /**
     * Test of add method, of class CarShowOwnerCommandImpl.
     */
    @Test
    public void testAdd() {
        //happy path
        CarShowOwnerCommand instance = new CarShowOwnerCommandImpl();
        instance.setManager(manager);
        CarShowOwner expResult = new CarShowOwner("C321", "O123");
        CarShowOwner result = instance.add(command1);
        assertEquals(expResult, result);
        
        //sad path
        CarShowOwner expResult2 = null;
        CarShowOwner result2 = instance.add(command1);
        assertEquals(expResult2, result2);
        
        //exceptions
        try {
            Map<String,String> cmd = new HashMap<>();
            cmd.put("TYPE", "CSO");
            cmd.put("ACTION", "ADD");
            CarShowOwner cso = instance.add(cmd);
            fail("An exception should have been caught");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Error building array. Size mismatch.");
        }
        
        //no key happy path
        CarShowOwner expResult3 = new CarShowOwner("C321", "O123");
        manager.getCarShowOwnerCollection().remove(expResult3);
        CarShowOwner result3 = instance.add(command3);
        assertEquals(expResult3, result3);
        
        //no key sad path
        CarShowOwner expResult4 = null;
        CarShowOwner result4 = instance.add(command3);
        assertEquals(expResult4, result4);
    }

    /**
     * Test of remove method, of class CarShowOwnerCommandImpl.
     */
    @Test
    public void testRemove() {
        //happy path
        CarShowOwnerCommand instance = new CarShowOwnerCommandImpl();
        instance.setManager(manager);
        CarShowOwner expResult = new CarShowOwner("C321", "O123");
        manager.getCarShowOwnerCollection().add(expResult);
        CarShowOwner result = instance.remove(command2);
        assertEquals(expResult, result);
        
        //sad path
        CarShowOwner expResult2 = null;
        CarShowOwner result2 = instance.remove(command2);
        assertEquals(expResult2, result2);
        
        //no key happy path
        CarShowOwner expResult3 = new CarShowOwner("C321", "O123");
        manager.getCarShowOwnerCollection().add(expResult3);
        CarShowOwner result3 = instance.remove(command4);
        assertEquals(expResult3, result3);
        
        //no key sad path
        CarShowOwner expResult4 = null;
        CarShowOwner result4 = instance.remove(command4);
        assertEquals(expResult4, result4);
    }

    /**
     * Test of performCommands method, of class CarShowOwnerCommandImpl.
     */
    @Test
    public void testPerformCommands() {
        //happy path add
        CarShowOwnerCommand instance = new CarShowOwnerCommandImpl();
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
