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
        Owner rv = manager.getOwnerCollection().find(id);
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
    
}
