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
import edu.du.menascheraymond.model.domain.Address;
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
    private List<LinkedHashMap<String,String>> commands = new LinkedList<>();
    
    public XMLFileStreamTest() {
        manager = new Manager();
        INPUTFILE = "target/XMLtest.xml";
        OUTPUTFILE = "target/XMLtest.xml";
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("TYPE", "OWNER");
        map.put("ownerId", "O123");
        map.put("firstName", "Jack");
        map.put("lastName", "Johnson");
        map.put("phoneNumber", "555-555-5555");
        map.put("numYears", "10");
        map.put("street1", "111 There st");
        map.put("street2", "Suite 545");
        map.put("city", "Denver");
        map.put("state", "CO");
        map.put("zipCode", "83322");
        commands.add(map);
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
        commands.add(map2);
    }

    /**
     * Test of getCommands method, of class XMLFileStream.
     */
    @Test
    public void testGetCommands() {
        XMLService instance = new XMLFileStream(INPUTFILE, OUTPUTFILE);
        List<Map<String, String>> result = instance.getCommands();
        System.out.println(result.size());
        for (Map<String, String> cmd: result) {
            CommandFactory cf = new CommandFactoryImpl();
            Command command = cf.getCommand(cmd.get("TYPE"));
            if (command != null) {
                command.setManager(manager);
                command.performCommands(cmd);
            }
        }
        System.out.println(manager.getOwnerCollection().size());
        Owner owner = new Owner.Builder("O123", "Jack", "Johnson")
                .withPhoneNumber("555-555-5555")
                .withNumYears(10).build();
        Address address = new Address.Builder()
                .withStreet1("111 There st")
                .withStreet2("Suite 545")
                .withCity("Denver")
                .withState("CO")
                .withZip("83322").build();
        owner.setAddress(address);
        Owner resOwner = manager.getOwnerCollection().find("O123");
        if (resOwner == null) {
            System.out.println("Failure");
        } else {
            System.out.println(resOwner.toString());
        }
        assertEquals(owner, resOwner);
        Vehicle vehicle = new Vehicle.Builder("V123", "O123")
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
        XMLFileStream instance = new XMLFileStream(INPUTFILE, OUTPUTFILE);
        boolean expResult = true;
        boolean result = instance.sendCommands(commands);
        assertEquals(expResult, result);

    }
    
}
