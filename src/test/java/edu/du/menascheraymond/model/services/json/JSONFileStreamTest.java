/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche.
 * File: JSONFileStreamText.java
 */
package edu.du.menascheraymond.model.services.json;

import edu.du.menascheraymond.model.business.command.Command;
import edu.du.menascheraymond.model.business.factory.CommandFactory;
import edu.du.menascheraymond.model.business.factory.CommandFactoryImpl;
import edu.du.menascheraymond.model.business.manager.Manager;
import edu.du.menascheraymond.model.domain.Owner;
import edu.du.menascheraymond.model.domain.Vehicle;
import edu.du.menascheraymond.model.domain.VehicleClassification;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests for JSONFileStream class.
 * @author raymondmenasche
 */
public class JSONFileStreamTest {
    private final String INPUTFILE;
    private final String OUTPUTFILE;
    private Manager manager;
    
    public JSONFileStreamTest() {
        INPUTFILE = "target/JSONtest.json";
        OUTPUTFILE = "target/JSONOutTest.json";
        manager = new Manager();
    }

    /**
     * Test of getCommands method, of class JSONFileStream.
     */
    @Test
    public void testGetCommands() {
        JSONService instance = new JSONFileStream(INPUTFILE, OUTPUTFILE);
        List<Map<String, String>> result = instance.getCommands();
        for (Map<String, String> cmd: result) {
            CommandFactory cf = new CommandFactoryImpl();
            Command command = cf.getCommand(cmd.get("TYPE").toUpperCase());
            if (command != null) {
                command.setManager(manager);
                command.performCommands(cmd);
            }
        }
        //TODO: had to run twice because Map is out of order and vehicle depends on owner. Fix this.
        for (Map<String, String> cmd: result) {
            CommandFactory cf = new CommandFactoryImpl();
            Command command = cf.getCommand(cmd.get("TYPE").toUpperCase());
            if (command != null) {
                command.setManager(manager);
                command.performCommands(cmd);
            }
        }
        Owner owner = new Owner.Builder("GW", "George", "Washington").build();
        assertEquals(owner, manager.getOwnerCollection().find("GW"));
        Vehicle vehicle = new Vehicle.Builder("V123", "GW")
                .withManufacturer("Ford")
                .withModelYear(1999)
                .withModel("Mustang")
                .withSubModel("Cobra")
                .withVehicleClassification(VehicleClassification.MODERN)
                .build();
        assertEquals(vehicle, manager.getVehicleCollection().find("V123"));
    }

    /**
     * Test of sendCommands method, of class JSONFileStream.
     */
    @Test
    public void testSendCommands() {
        List<LinkedHashMap<String, String>> cmns = new LinkedList<>();
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("TYPE", "OWNER");
        map.put("ownerId", "O123");
        map.put("firstName", "Jack");
        map.put("lastName", "Johnson");
        map.put("phone", "555-555-5555");
        map.put("years", "10");
        cmns.add(map);
        LinkedHashMap<String,String> map2 = new LinkedHashMap<>();
        map2.put("TYPE", "VEHICLE");
        map2.put("vehicleId", "V321");
        map2.put("ownerId", "O123");
        map2.put("manufacturer", "Ford");
        map2.put("year", "1999");
        map2.put("model", "Mustang");
        map2.put("subModel", "Cobra");
        map2.put("classification", "MODERN");
        map2.put("insudred", "true");
        cmns.add(map2);
        LinkedHashMap<String,String> map3 = new LinkedHashMap<>();
        map3.put("TYPE", "OWNER");
        map3.put("ownerId", "O124");
        map3.put("firstName", "Mike");
        map3.put("lastName", "Tyson");
        map3.put("phone", "555-555-5555");
        map3.put("years", "10");
        cmns.add(map3);
        JSONFileStream instance = new JSONFileStream();
        boolean expResult = true;
        boolean result = instance.sendCommands(cmns);
        assertEquals(expResult, result);
    }
    
}
