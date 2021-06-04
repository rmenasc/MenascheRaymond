/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * CarShowCommandImplTest.java
 */
package edu.du.menascheraymond.model.business.command.CarShow;

import edu.du.menascheraymond.model.business.command.carshowcommand.CarShowCommand;
import edu.du.menascheraymond.model.business.command.carshowcommand.CarShowCommandImpl;
import edu.du.menascheraymond.model.business.manager.Manager;
import edu.du.menascheraymond.model.domain.CarShow;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit tests for the CarShowCommandImpl class.
 * @author raymondmenasche
 */
public class CarShowCommandImplTest {
    private Map<String,String> command1;
    private Map<String,String> command2;
    private Map<String,String> command3;
    private Map<String,String> command4;
    private Manager manager;
    
    public CarShowCommandImplTest() {
        manager = new Manager();
        
        command1 = new HashMap<>();
        command1.put("TYPE", "CARSHOW");
        command1.put("ACTION", "ADD");
        command1.put("carShowId", "C123");
        command1.put("title", "Mile Hight Show");
        command1.put("date", "2021-12-12");
        command1.put("sanctioned", "Y");
        
        command2 = new HashMap<>();
        command2.put("TYPE", "CARSHOW");
        command2.put("ACTION", "REM");
        command2.put("carShowId", "C123");
        
        command3 = new HashMap<>();
        command3.put("TYPE", "CARSHOW");
        command3.put("ACTION", "ADD");
        command3.put("VALUE1", "C444");
        command3.put("VALUE2", "The Great Show");
        command3.put("VALUE3", "12/12/2021");
        command3.put("VALUE4", "Y");
        
        command4 = new HashMap<>();
        command4.put("TYPE", "CARSHOW");
        command4.put("ACTION", "REM");
        command4.put("VALUE1", "C444");
    }

    /**
     * Test of add method, of class CarShowCommandImpl.
     */
    @Test
    public void testAdd() {
        //happy path
        CarShowCommand instance = new CarShowCommandImpl();
        instance.setManager(manager);
        CarShow expResult = new CarShow.Builder(command1.get("carShowId"),
                command1.get("title"))
                .withCarShowDate(LocalDate.of(2021,12,12)).build();
        expResult.setSanctioned('Y');
        CarShow result = instance.add(command1);
        assertEquals(expResult, result);

        //sad path
        CarShow expResult2 = null;
        CarShow result2 = instance.add(command1);
        assertEquals(expResult2, result2);
        
        //exceptions
        try {
            Map<String,String> cmd = new HashMap<>();
            cmd.put("TYPE", "CARSHOW");
            cmd.put("ACTION", "ADD");
            cmd.put("carShowId", "C848");
            CarShow c = instance.add(cmd);
            fail("An exception should have been caught");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Error creating array. Size mismatch");
        }
        
        //CSV no key happy path
        CarShow expResult3 = new CarShow.Builder("C444", "The Great Show")
                .withCarShowDate(LocalDate.of(2021, 12, 12)).build();
        expResult3.setSanctioned('y');
        CarShow result3 = instance.add(command3);
        assertEquals(expResult3, result3);
        
        //CSV no key sad path
        CarShow expResult4 = null;
        CarShow result4 = instance.add(command3);
        assertEquals(expResult4, result4);
    }

    /**
     * Test of remove method, of class CarShowCommandImpl.
     */
    @Test
    public void testRemove() {

        //happy path
        CarShowCommand instance = new CarShowCommandImpl();
        instance.setManager(manager);
        CarShow expResult = new CarShow.Builder(command1.get("carShowId"),
                command1.get("title"))
                .withCarShowDate(LocalDate.of(2021,12,12)).build();
        manager.getCarShowCollection().add(expResult);
        CarShow result = instance.remove(command2);
        assertEquals(expResult, result);

        //sad path
        CarShow expResult2 = null;
        CarShow result2 = instance.remove(command2);
        assertEquals(expResult2, result2);

        //no key happy path
        CarShow expResult3 = new CarShow.Builder("C444", "The Great Show")
                .withCarShowDate(LocalDate.of(2021,12,12))
                .isSanctioned(true).build();
        manager.getCarShowCollection().add(expResult3);
        CarShow result3 = instance.remove(command4);
        assertEquals(expResult3, result3);
        
        //no key sad path
        CarShow expResult4 = null;
        CarShow result4 = instance.remove(command4);
        assertEquals(expResult4, result4);
                
    }

    /**
     * Test of performCommands method, of class CarShowCommandImpl.
     */
    @Test
    public void testPerformCommands() {
        //test happy path add
        CarShowCommand instance = new CarShowCommandImpl();
        instance.setManager(manager);
        boolean expResult = true;
        boolean result = instance.performCommands(command1);
        assertEquals(expResult, result);
        
        //test sad path add
        expResult = false;
        result = instance.performCommands(command1);
        assertEquals(expResult, result);
        
        //test happy path remove
        expResult = true;
        result = instance.performCommands(command2);
        assertEquals(expResult, result);
        
        //test sad path remove
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
    
    /**
     * Test CreateCommand method for CarShowCommandImpl class.
     */
    @Test
    public void testCreateCommand() {
        CarShowCommand instance = new CarShowCommandImpl();
        instance.setManager(manager);
        LocalDate date = LocalDate.of(2020, 12, 12);
        CarShow cs = new CarShow.Builder("CS1", "The Great Show")
                .withCarShowDate(date)
                .isSanctioned(true).build();
        manager.getCarShowCollection().add(cs);
        List<LinkedHashMap<String,String>> result = instance.createCommand();
        for (LinkedHashMap<String,String> cmd: result) {
            String type = cmd.get("TYPE");
            String carShowId = cmd.get("carShowId");
            String title = cmd.get("title");
            String strDate = cmd.get("date").strip();                  //String date ex. 12/12/2020 or 2020-12-12
            LocalDate d = null;
            if (strDate.contains("-")) {
                d = LocalDate.parse(strDate);
            } else if (strDate.contains("/")) {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                d = LocalDate.parse(strDate, format);
            }
            assertEquals(type, "CARSHOW");
            assertEquals(carShowId, cs.getCarShowId());
            assertEquals(title, cs.getCarShowTitle());
            assertEquals(d, cs.getCarShowDate());
        }
    }
    
}
