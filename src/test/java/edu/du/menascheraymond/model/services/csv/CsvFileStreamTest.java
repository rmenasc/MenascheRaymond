/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * Author: Raymond Menasche
 * File: CsvFileStreamTest.java
 */
package edu.du.menascheraymond.model.services.csv;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit tests for the CsvFileStream class.
 * @author raymondmenasche
 */
public class CsvFileStreamTest {
    
    private List<LinkedHashMap<String,String>> commands = new LinkedList<>();
    
    public CsvFileStreamTest() {
        LinkedHashMap<String,String> command1 = new LinkedHashMap<>();
        command1.put("TYPE", "OWNER");
        command1.put("ACTION", "ADD");
        command1.put("ownerId", "O123");
        command1.put("firstName", "John");
        command1.put("lastName", "Jackson");
        LinkedHashMap<String, String> command2 = new LinkedHashMap<>();
        command2.put("TYPE", "VEHICLE");
        command2.put("ACTION", "REM");
        command2.put("vehicleId", "V232");
        LinkedHashMap<String, String> command3 = new LinkedHashMap<>();
        command3.put("TYPE", "OWNER");
        command3.put("ACTION", "REM");
        command3.put("ownerId", "O124");
        commands.add(command1);
        commands.add(command2);
        commands.add(command3);
    }

    /**
     * Test of getCommands method, of class CsvFileStream.
     */
    @Test
    public void testGetCommands() {
        try {
            CsvService instance = new CsvFileStream("target/testfilestream.txt",
                    "target/testfilestream.txt");
            Map<String, String> data = new LinkedHashMap<>();
            data.put("TYPE", "OWNER");
            data.put("ACTION", "ADD");
            data.put("ownerId", "O123");
            data.put("firstName", "John");
            data.put("lastName", "Jackson");
            instance.sendCommands(commands);
            List<Map<String, String>> resultList = instance.getCommands();
            boolean result = false;
            for (Map<String,String> c: resultList) {
                if (c.get("TYPE").equals(data.get("TYPE"))
                        && c.get("ACTION").equals(data.get("ACTION"))
                        && c.get("ownerId").equals(data.get("ownerId"))
                        && c.get("firstName").equals(data.get("firstName"))
                        && c.get("lastName").equals(data.get("lastName"))) {
                    result = true;
                    break;
                }
            }
            assertTrue(result);
        } catch (IllegalArgumentException e) {
            fail("An IllegalArguementException should not have been called.");
        }
    }

    /**
     * Test of sendCommands method, of class CsvFileStream.
     */
    @Test
    public void testSendCommands() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("TYPE", "OWNER");
        data.put("ACTION", "REM");
        data.put("ownerId", "O123");
        List<LinkedHashMap<String,String>> cmds = new LinkedList<>();
        cmds.add(data);
        CsvService instance = new CsvFileStream("target/testfilestream.txt",
                "target/testfilestream.txt");
        boolean expResult = true;
        boolean result = instance.sendCommands(cmds);
        assertEquals(expResult, result);
        result = false;
        List<Map<String,String>> resultList = instance.getCommands();
        Map<String,String> resultMap = resultList.get(0);
        if (resultMap.get("TYPE").equals(data.get("TYPE"))
                && resultMap.get("ACTION").equals(data.get("ACTION"))
                && resultMap.get("ownerId").equals(data.get("ownerId"))) {
            result = true;
        }
        assertTrue(result);
    }
    
}
