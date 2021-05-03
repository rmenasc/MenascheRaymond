/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 */
package edu.du.menascheraymond.model.business.command.properties;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author raymondmenasche
 */
public class PropertiesFileStreamTest {
    LinkedHashMap<String,String> data;
    Properties properties;
    
    public PropertiesFileStreamTest() {
        data = new LinkedHashMap<>();
        data.put("TYPE", "OWNER");
        data.put("ACTION", "REM");
        data.put("ownerId", "O123");
        
        properties = new Properties();
        properties.setProperty("TYPE", "OWNER");
        properties.setProperty("ACTION", "REM");
        properties.setProperty("ownerId", "O123");
    }

    /**
     * Test of getCommands method, of class PropertiesFileStream.
     */
    @Test
    public void testGetCommands() {
        PropertiesCommand instance = new PropertiesFileStream("target/testfilestream.txt",
                "target/testfilestream.txt");
        List<LinkedHashMap<String, String>> expResultList = new LinkedList<>();
        expResultList.add(data);
        instance.sendCommands(expResultList);
        List<Map<String, String>> resultList = instance.getCommands();
        Map<String,String> resultMap = resultList.get(0);
        boolean result = false;
        if (resultMap.get("TYPE").equals(data.get("TYPE"))
                && resultMap.get("ACTION").equals(data.get("ACTION"))
                && resultMap.get("ownerId").equals(data.get("ownerId"))) {
            result = true;
        }
        assertTrue(result);
        
        instance.sendProperties(properties);
        List<Map<String,String>> resultList2 = instance.getCommands();
        Map<String,String> resultMap2 = resultList2.get(0);
        result = false;
        if (resultMap2.get("TYPE").equals(properties.getProperty("TYPE"))
                && resultMap2.get("ACTION").equals(properties.getProperty("ACTION"))
                && resultMap2.get("ownerId").equals(properties.getProperty("ownerId"))) {
            result = true;
        }
        assertTrue(result);
    }

    /**
     * Test of sendCommands method, of class PropertiesFileStream.
     */
    @Test
    public void testSendCommands() {
        List<LinkedHashMap<String, String>> cmns = new LinkedList<>();
        cmns.add(data);
        PropertiesCommand instance = new PropertiesFileStream("target/testfilestream.txt",
                "target/testfilestream.txt");
        boolean expResult = true;
        boolean result = instance.sendCommands(cmns);
        assertEquals(expResult, result);
        Properties resultProp = instance.getProperties();
        result = false;
        if (resultProp.getProperty("TYPE").equals(properties.getProperty("TYPE"))
                && resultProp.getProperty("ACTION").equals(properties.getProperty("ACTION"))
                && resultProp.getProperty("ownerId").equals(properties.getProperty("ownerId"))) {
            result = true;
        }
        assertTrue(result);
    }

    /**
     * Test of sendProperties method, of class PropertiesFileStream.
     */
    @Test
    public void testSendProperties() {
        PropertiesCommand instance = new PropertiesFileStream
                ("target/textfilestream.txt", "target/textfilestream.txt");
        boolean expResult = true;
        boolean result = instance.sendProperties(properties);
        assertEquals(expResult, result);
        Properties resultProp = instance.getProperties();
        result = false;
        if (resultProp.getProperty("TYPE").equals(properties.getProperty("TYPE"))
                && resultProp.getProperty("ACTION").equals(properties.getProperty("ACTION"))
                && resultProp.getProperty("ownerId").equals(properties.getProperty("ownerId"))) {
            result = true;
        }
        assertTrue(result);
    }

    /**
     * Test of getProperties method, of class PropertiesFileStream.
     */
    @Test
    public void testGetProperties() {
        PropertiesFileStream instance = new PropertiesFileStream
                ("target/testfilestream.txt", "target/testfilestream.txt");
        instance.sendProperties(properties);
        Properties expResult = properties;
        Properties result = instance.getProperties();
        assertEquals(expResult, result);
    }
    
}
