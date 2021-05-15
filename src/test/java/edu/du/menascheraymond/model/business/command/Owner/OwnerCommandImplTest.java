/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * OwnerCommandImplTest.java
 */
package edu.du.menascheraymond.model.business.command.Owner;

import edu.du.menascheraymond.model.business.command.ownercommand.OwnerCommand;
import edu.du.menascheraymond.model.business.command.ownercommand.OwnerCommandImpl;
import edu.du.menascheraymond.model.business.manager.Manager;
import edu.du.menascheraymond.model.domain.Owner;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit tests for the OwnerCommandImpl class.
 * @author raymondmenasche
 */
public class OwnerCommandImplTest {
    private Manager manager;
    private Map<String,String> command1;
    private Map<String,String> command2;
    private Map<String,String> command3;
    private Map<String,String> command4;
    
    public OwnerCommandImplTest() {
        manager = new Manager();
        
        command1 = new HashMap<>();
        command1.put("TYPE", "OWNER");
        command1.put("ACTION", "ADD");
        command1.put("ownerId", "O123");
        command1.put("firstName", "Jack");
        command1.put("lastName", "Johnson");
        command1.put("phoneNumber", "555-555-5555");
        command1.put("street1", "111 There st");
        command1.put("street2", "suite 2");
        command1.put("city", "Denver");
        command1.put("state", "Colorado");
        command1.put("zipCode", "80021");
        command1.put("numYears", "10");
        
        command2 = new HashMap<>();
        command2.put("TYPE", "OWNER");
        command2.put("ACTION", "REM");
        command2.put("ownerId", "O123");
        
        command3 = new HashMap<>();
        command3.put("TYPE", "OWNER");
        command3.put("ACTION", "ADD");
        command3.put("VALUE1", "O123");
        command3.put("VALUE2", "Jack");
        command3.put("VALUE3", "Johnson");
        command3.put("VALUE4", "555-555-5555");
        command3.put("VALUE5", "10");
        
        command4 = new HashMap<>();
        command4.put("TYPE", "OWNER");
        command4.put("ACTION", "REM");
        command4.put("VALUE1", "O123");
    }

    /**
     * Test of add method, of class OwnerCommandImpl.
     */
    @Test
    public void testAdd() {
        //happy path
        OwnerCommand instance = new OwnerCommandImpl();
        instance.setManager(manager);
        Owner expResult = new Owner.Builder("O123", "Jack", "Johnson").build();
        Owner result = instance.add(command1);
        assertEquals(expResult, result);
        
        //sad path
        Owner expResult2 = null;
        Owner result2 = instance.add(command1);
        assertEquals(expResult2, result2);
        
        //exceptions
        try {
            Map<String,String> cmd = new HashMap<>();
            cmd.put("TYPE", "OWNER");
            cmd.put("ACTION", "ADD");
            cmd.put("ownerId", "O432");
            Owner o = instance.add(cmd);
            fail("An exception should have been caught");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Error creating array. Size mismatch");
        }
        
        //no key happy path
        Owner expResult3 = new Owner.Builder("O123", "Jack", "Johnson").build();
        manager.getOwnerCollection().remove(expResult3);
        Owner result3 = instance.add(command3);
        assertEquals(expResult3, result3);
        
        //no key sad path
        Owner expResult4 = null;
        Owner result4 = instance.add(command3);
        assertEquals(expResult4, result4);
    }

    /**
     * Test of remove method, of class OwnerCommandImpl.
     */
    @Test
    public void testRemove() {
        //happy path
        OwnerCommand instance = new OwnerCommandImpl();
        instance.setManager(manager);
        Owner expResult = new Owner.Builder("O123", "Jack", "Johnson").build();
        manager.getOwnerCollection().add(expResult);
        Owner result = instance.remove(command2);
        assertEquals(expResult, result);
        
        //sad path
        Owner expResult2 = null;
        Owner result2 = instance.remove(command2);
        assertEquals(expResult2, result2);
        
        //no key happy path
        Owner expResult3 = new Owner.Builder("O123", "Jack", "Johnson").build();
        manager.getOwnerCollection().add(expResult3);
        Owner result3 = instance.remove(command4);
        assertEquals(expResult3, result3);
        
        //no key sad path
        Owner expResult4 = null;
        Owner result4 = instance.remove(command4);
        assertEquals(expResult4, result4);
    }

    /**
     * Test of performCommands method, of class OwnerCommandImpl.
     */
    @Test
    public void testPerformCommands() {
        //happy path add
        OwnerCommand instance = new OwnerCommandImpl();
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
