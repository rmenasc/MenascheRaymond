/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 * File: CarShowOwnerCommandImpl.java
 */
package edu.du.menascheraymond.model.business.command.carshowownercommand;

import edu.du.menascheraymond.model.business.manager.Manager;
import edu.du.menascheraymond.model.domain.CarShowOwner;
import java.util.Map;

/**
 * Implementation of the CarShowOwnerCommand interface.
 * @author raymondmenasche
 */
public class CarShowOwnerCommandImpl implements CarShowOwnerCommand {
    private final String COMMANDTYPE = "CSO";
    private Manager manager;

    @Override
    public CarShowOwner add(Map<String, String> command) {
        CarShowOwner rv = null;
        if (command.size() == 4 && command.containsKey("ownerId")) {
            String ownerId = command.get("ownerId");
            String carShowId = command.get("carShowId");
            CarShowOwner cso = new CarShowOwner(carShowId, ownerId);
            if (manager.getOwnerCollection().isPresent(ownerId)
                            && manager.getCarShowCollection().isPresent(carShowId)) {
                if (manager.getCarShowOwnerCollection().add(cso)) {
                    rv = cso;
                }
            } else {
                System.out.println("Could not add: " + cso.toString()
                        + ". Missing dependencies");
            }
        } else if (command.size() == 4 && command.containsKey("VALUE1")) {
            //for non key=value data
            String ownerId = command.get("VALUE1");
            String carShowId = command.get("VALUE2");
            CarShowOwner cso = new CarShowOwner(carShowId, ownerId);
            if (manager.getOwnerCollection().isPresent(ownerId)
                            && manager.getCarShowCollection().isPresent(carShowId)) {
                if (manager.getCarShowOwnerCollection().add(cso)) {
                    rv = cso;
                }
            } else {
                System.out.println("Could not add: " + cso.toString()
                        + ". Missing dependencies");
            }
        } else {
            throw new NullPointerException("Error building array. Size mismatch.");
        }
        return rv;
    }

    @Override
    public CarShowOwner remove(Map<String, String> command) {
        CarShowOwner cso = null;
        if (command.containsKey("carShowId")) {
            cso = new CarShowOwner(command.get("carShowId"),
                command.get("ownerId"));
        } else if (command.containsKey("VALUE1")) {
            // this is for non key=value files. A bit ambiguous but the first
            // value should be the ownerId and the socond is carShowId.
            // CarShowOwner constructor takes in the carShowId first. 
            cso = new CarShowOwner(command.get("VALUE2"), command.get("VALUE1"));
        }
        if (manager.getCarShowOwnerCollection().remove(cso)) {
            System.out.println("Removed; " + cso.toString());
        } else {
            System.out.println("Error removing: " + cso.toString());
            cso = null;
        }
        return cso;
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
            CarShowOwner cso = add(cmd);
            if (cso != null) {
                rv = true;
            }
        }

        if (cmd.get("ACTION").equalsIgnoreCase("REM")) {
            CarShowOwner cso = remove(cmd);
            if (cso != null) {
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
