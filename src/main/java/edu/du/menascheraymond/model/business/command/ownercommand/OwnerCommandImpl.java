/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 * File: OwnerCommandImpl.java
 */
package edu.du.menascheraymond.model.business.command.ownercommand;

import edu.du.menascheraymond.model.business.manager.Manager;
import edu.du.menascheraymond.model.domain.Address;
import edu.du.menascheraymond.model.domain.Owner;
import edu.du.menascheraymond.model.services.ownerservice.OwnerService;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the OwnerCommand interface.
 * @author raymondmenasche
 */
public class OwnerCommandImpl implements OwnerCommand {
    private final String COMMANDTYPE = "OWNER";
    private Manager manager;
    

    @Override
    public Owner add(Map<String,String> command) {
        Owner rv = null;
        if (manager != null) {
            if (command.size() >= 7 && command.containsKey("ownerId")) {
                String id = command.get("ownerId");
                String firstName = command.get("firstName");                        //First Name
                String lastName = command.get("lastName");                          //Last Name
                String phoneNumber = command.get("phoneNumber");                  //Phone Number
                int numYears = Integer.parseInt(command.get("numYears").strip());      //Number of Years
                Owner o = new Owner.Builder(id, firstName, lastName)
                        .withPhoneNumber(phoneNumber)
                        .withNumYears(numYears).build();
                if (command.size() >= 12) { // this checks if the address is included in the command
                    String street1 = command.get("street1");                            //Address street1
                    String street2 = command.get("street2");                            //Address street2
                    String city = command.get("city");                                  //City
                    String state = command.get("state");                                //State
                    String zipCode = command.get("zipCode");                            //zipCode;
                    Address address = new Address.Builder()
                            .withStreet1(street1)
                            .withStreet2(street2)
                            .withCity(city)
                            .withState(state)
                            .withZip(zipCode).build();
                    o.setAddress(address);
                }
                if (manager.getOwnerCollection().add(o)) {
                    rv = o;
                }
            } else if (command.size() >= 7 && command.containsKey("VALUE1")) {
                //for non key=value data
                String id = command.get("VALUE1");
                String firstName = command.get("VALUE2");                        //First Name
                String lastName = command.get("VALUE3");                          //Last Name
                String phoneNumber = command.get("VALUE4").strip();                  //Phone Number
                int numYears = Integer.parseInt(command.get("VALUE5").strip());      //Number of Years
                Owner o = new Owner.Builder(id, firstName, lastName)
                        .withPhoneNumber(phoneNumber)
                        .withNumYears(numYears).build();
                if (command.size() >= 12) { //this checks if the address is included in the command
                    String street1 = command.get("VALUE6");                            //Address street1
                    String street2 = command.get("VALUE7");                            //Address street2
                    String city = command.get("VALUE8");                                  //City
                    String state = command.get("VALUE9");                                //State
                    String zipCode = command.get("VALUE10");                            //zipCode;
                    Address address = new Address.Builder()
                            .withStreet1(street1)
                            .withStreet2(street2)
                            .withCity(city)
                            .withState(state)
                            .withZip(zipCode).build();
                    o.setAddress(address);
                }
                if (manager.getOwnerCollection().add(o)) {
                    rv = o;
                }
            } else {
                throw new NullPointerException("Error creating array. Size mismatch");
            }
        } else {
            throw new NullPointerException("No specified Manager in OwnerCommand");
        }
        return rv;
    }

    @Override
    public Owner remove(Map<String,String> command) {
        //Removes Owner object.
        String id = "";
        if (command.containsKey("ownerId")) {
            id = command.get("ownerId");
        } else if (command.containsKey("VALUE1")) {
            //for non key=value data
            id = command.get("VALUE1");
        }
        Owner rv = null;
        if (manager != null) {
            rv = manager.getOwnerCollection().find(id);
            if (manager.getOwnerCollection().remove(id)) {
                System.out.println("Removed owner: " + id);
                //If succesfully removed Owner, removes vehicles with
                //objects that include ownerId, and removes CarShowOwner
                //with matching ownerId.
                manager.getVehicleCollection().removeByOwnerId(id);
                manager.getCarShowOwnerCollection().removeByOwnerId(id);
            } else {
                System.out.println("Error removing owner: " + id);
            }
        } else {
            throw new NullPointerException("No specified Manager in OwnerCommand");
        }
        return rv;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public String getCommandType() {
        return COMMANDTYPE;
    }

    @Override
    public boolean performCommands(Map<String, String> cmd) {
        boolean rv = false;
        if (cmd.get("ACTION").equalsIgnoreCase("ADD")) {
            Owner o = add(cmd);
            if (o != null) {
                rv = true;
            }
        }
        if (cmd.get("ACTION").equalsIgnoreCase("REM")) {
            Owner o = remove(cmd);
            if (o != null) {
                rv = true;
            }
        }
        return rv;
    }

    @Override
    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public List<LinkedHashMap<String, String>> createCommand() {
        List<LinkedHashMap<String,String>> commands = new LinkedList<>();
        if (manager != null) {
            OwnerService os = manager.getOwnerCollection();
            for (String id: os.getIds()) {
                Owner o = os.find(id);
                LinkedHashMap<String,String> cmd = new LinkedHashMap<>();
                cmd.put("ACTION", "ADD");
                cmd.put("TYPE", "OWNER");
                cmd.put("ownerId", id);
                cmd.put("firstName", o.getFirstName());
                cmd.put("lastName", o.getLastName());
                cmd.put("phoneNumber", o.getPhoneNumber());
                Integer y = o.getNumYears();
                cmd.put("numYears", y.toString());
                cmd.put("street1", o.getAddress().getStreet1());
                cmd.put("street2", o.getAddress().getStreet2());
                cmd.put("city", o.getAddress().getCity());
                cmd.put("state", o.getAddress().getState());
                cmd.put("zipCode", o.getAddress().getZipCode());
                commands.add(cmd);
            }
        } else {
            throw new NullPointerException("No specified Manager in OwnerService");
        }
        return commands;
    }
    
}
