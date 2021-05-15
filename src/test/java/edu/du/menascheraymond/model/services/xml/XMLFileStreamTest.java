/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche.
 * File: XMLFileStreamTest.java
 */
package edu.du.menascheraymond.model.services.xml;

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
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for the XMLFileStream implementation.
 * @author raymondmenasche
 */
public class XMLFileStreamTest {
    private Manager manager;
    private final String INPUTFILE;
    private final String OUTPUTFILE;
    
    public XMLFileStreamTest() {
        manager = new Manager();
        INPUTFILE = "target/XMLtest.xml";
        OUTPUTFILE = "target/XMLout.xml";
    }

    /**
     * Test of getCommands method, of class XMLFileStream.
     */
    @Test
    public void testGetCommands() {
        XMLService instance = new XMLFileStream(INPUTFILE, OUTPUTFILE);
        List<Map<String, String>> result = instance.getCommands();
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
                .withVehicleClassification(VehicleClassification.MODERN).build();
        assertEquals(vehicle, manager.getVehicleCollection().find("V123"));
    }

    /**
     * Test of sendCommands method, of class XMLFileStream.
     */
    @Test
    public void testSendCommands() {
        System.out.println("sendCommands");
        List<LinkedHashMap<String, String>> cmns = new LinkedList<>();
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("TYPE", "OWNER");
        map.put("ownerId", "O123");
        map.put("firstName", "Jack");
        map.put("lastName", "Johnson");
        map.put("phone", "555-555-5555");
        map.put("years", "10");
        cmns.add(map);
        LinkedHashMap<String, String> map2 = new LinkedHashMap<>();
        map2.put("TYPE", "VEHICLE");
        map2.put("vehicleId", "V123");
        map2.put("ownerId", "O123");
        map2.put("manufacturer", "Ford");
        map2.put("year", "1999");
        map2.put("model", "Mustang");
        map2.put("subModel", "Cobra");
        map2.put("classification", "Modern");
        map2.put("insured", "yes");
        cmns.add(map2);
        XMLFileStream instance = new XMLFileStream();
        boolean expResult = true;
        boolean result = instance.sendCommands(cmns);
        assertEquals(expResult, result);

    }
    
}
