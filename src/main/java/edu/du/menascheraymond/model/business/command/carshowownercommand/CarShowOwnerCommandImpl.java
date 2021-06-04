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
import edu.du.menascheraymond.model.services.carshowownerservice.CarShowOwnerService;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
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
        if (manager != null) {
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
        } else {
            throw new NullPointerException("No specified Manager in CarShowOwnerCommand");
        }
        return rv;
    }

    @Override
    public CarShowOwner remove(Map<String, String> command) {
        CarShowOwner cso = null;
        if (manager != null) {
            if (command.containsKey("carShowId")) {
                cso = manager.getCarShowOwnerCollection()
                        .find(command.get("ownerId"), command.get("carShowId"));
                manager.getCarShowOwnerCollection().remove(cso);
            } else if (command.containsKey("VALUE1")) {
                // this is for non key=value files. A bit ambiguous but the first
                // value should be the ownerId and the socond is carShowId.
                // CarShowOwner constructor takes in the carShowId first. 
                cso = manager.getCarShowOwnerCollection()
                        .find(command.get("VALUE1"), command.get("VALUE2"));
                manager.getCarShowOwnerCollection().remove(cso);
            }
        } else {
            throw new NullPointerException("No specified Manager in CarShowOwnerCommand");
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

    @Override
    public List<LinkedHashMap<String, String>> createCommand() {
        List<LinkedHashMap<String,String>> commands = new LinkedList<>();
        if (manager != null) {
            List<String> oIds = manager.getOwnerCollection().getIds();
            List<String> csIds = manager.getCarShowCollection().getIds();
            CarShowOwnerService csos = manager.getCarShowOwnerCollection();
            for (String oid: oIds) {
                for (String csid: csIds) {
                    CarShowOwner cso = csos.find(oid, csid);
                    if (cso != null) {
                        LinkedHashMap<String,String> cmd = new LinkedHashMap<>();
                        cmd.put("ACTION", "ADD");
                        cmd.put("TYPE", "CSO");
                        cmd.put("carShowId", csid);
                        cmd.put("ownerId", oid);
                        commands.add(cmd);
                    }
                }
            }
        } else {
            throw new NullPointerException("No specified manager in CarShowOwnerCommand");
        }
        return commands;
    }
    
}
