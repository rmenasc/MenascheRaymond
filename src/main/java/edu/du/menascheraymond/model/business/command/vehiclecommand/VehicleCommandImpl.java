/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 * File: VehicleCommandImpl.java
 */
package edu.du.menascheraymond.model.business.command.vehiclecommand;

import edu.du.menascheraymond.model.business.manager.Manager;
import edu.du.menascheraymond.model.domain.Vehicle;
import edu.du.menascheraymond.model.domain.VehicleClassification;
import edu.du.menascheraymond.model.services.vehicleservice.VehicleService;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the VehilceCommand interface.
 * @author raymondmenasche
 */
public class VehicleCommandImpl implements VehicleCommand {
    private final String COMMANDTYPE = "VEHICLE";
    private Manager manager;

    @Override
    public Vehicle add(Map<String,String> command) {
        Vehicle rv = null;
        if (manager != null) {
            if (command.size() == 10 && command.containsKey("vehicleId")) {
                String id = command.get("vehicleId");
                String ownerId = command.get("ownerId").strip();             //Owner id
                int year = Integer.parseInt(command.get("year"));            //Vehicle year
                String manufacturer = command.get("manufacturer");                   //Manufacturer
                String model = command.get("model");                         //Vehicle model
                String subModel = command.get("subModel");                   //Vehicle sub model
                VehicleClassification classification = null;
                switch (command.get("classification").toUpperCase().strip()) {       //Vehicle Classification
                    case "ANTIQUE":
                        classification = VehicleClassification.ANTIQUE;
                        break;
                    case "CLASSIC":
                        classification = VehicleClassification.CLASSIC;
                        break;
                    case "MODERN":
                        classification = VehicleClassification.MODERN;
                        break;
                    default:
                        classification = VehicleClassification.MODERN; 
                        break;
                }
                String insured = command.get("insured").strip();             //Insured? Y/Yes/N/No

                Vehicle vehicle = new Vehicle.Builder(id, ownerId)
                        .withModelYear(year)
                        .withManufacturer(manufacturer)
                        .withModel(model)
                        .withSubModel(subModel)
                        .withVehicleClassification(classification).build();

                if (insured.equalsIgnoreCase("true")) {
                    vehicle.setInsured(true);
                } else if (insured.equalsIgnoreCase("false")) {
                    vehicle.setInsured(false);
                } else {
                    try {
                        vehicle.setInsured(insured);
                    } catch (IllegalArgumentException e) {
                        System.out.println ("Error: " + e);
                    }
                }
                if (manager.getVehicleCollection().add(vehicle)) {
                    rv = vehicle;
                }
            } else if (command.size() == 10 && command.containsKey("VALUE1")) {
                //for non key=value data
                String id = command.get("VALUE1");
                String ownerId = command.get("VALUE2").strip();             //Owner id
                int year = Integer.parseInt(command.get("VALUE3"));            //Vehicle year
                String manufacturer = command.get("VALUE4");                   //Manufacturer
                String model = command.get("VALUE5");                         //Vehicle model
                String subModel = command.get("VALUE6");                   //Vehicle sub model
                VehicleClassification classification = null;
                switch (command.get("VALUE7").toUpperCase().strip()) {       //Vehicle Classification
                    case "ANTIQUE":
                        classification = VehicleClassification.ANTIQUE;
                        break;
                    case "CLASSIC":
                        classification = VehicleClassification.CLASSIC;
                        break;
                    case "MODERN":
                        classification = VehicleClassification.MODERN;
                        break;
                    default:
                        classification = VehicleClassification.MODERN; 
                        break;
                }
                String insured = command.get("VALUE8").strip();             //Insured? Y/Yes/N/No

                Vehicle vehicle = new Vehicle.Builder(id, ownerId)
                        .withModelYear(year)
                        .withManufacturer(manufacturer)
                        .withModel(model)
                        .withSubModel(subModel)
                        .withVehicleClassification(classification).build();
                vehicle.setInsured(insured);
                if (manager.getVehicleCollection().add(vehicle)) {
                    rv = vehicle;
                }
            } else {
                throw new NullPointerException("Error creating array. Size mismatch");
            }
        } else {
            throw new NullPointerException("No specified Manager in VehicleCommand");
        }
        return rv;
    }

    @Override
    public Vehicle remove(Map<String,String> command) {
        String id = "";
        if (command.containsKey("vehicleId")) {
            id = command.get("vehicleId");
        } else if (command.containsKey("VALUE1")) {
            //for non key=value data.
            id = command.get("VALUE1");
        }
        Vehicle rv = null;
        if (manager != null) {
            rv = manager.getVehicleCollection().find(id);
            if (manager.getVehicleCollection().remove(id)) {
                //TODO: implement this condition.
                System.out.println("Removed vehicle: " + id);
            } else {
                System.out.println("Error removing vehicle: " + id);
            }
        } else {
            throw new NullPointerException("No specified Manager in VehicleCommand");
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
            Vehicle v = add(cmd);
            if (v != null) {
                rv = true;
            }
        }   
        if (cmd.get("ACTION").equalsIgnoreCase("REM")) {
            Vehicle v = remove(cmd);
            if (v != null) {
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
            VehicleService vs = manager.getVehicleCollection();
            for (String id: vs.getIds()) {
                Vehicle v = vs.find(id);
                LinkedHashMap<String,String> cmd = new LinkedHashMap<>();
                cmd.put("ACTION", "ADD");
                cmd.put("TYPE", "VEHICLE");
                cmd.put("vehicleId", id);
                cmd.put("ownerId", v.getOwnerId());
                cmd.put("manufacturer", v.getManufacturer());
                Integer y = v.getModelYear();
                cmd.put("year", y.toString());
                cmd.put("model", v.getModel());
                cmd.put("subModel", v.getSubModel());
                cmd.put("classification", v.getVehicleClassification().toString());
                if (v.isInsured()) {
                    cmd.put("insured", "true");
                } else {
                    cmd.put("insured", "false");
                }
                commands.add(cmd);
            }
        } else {
            throw new NullPointerException("No specified Manager in VehicleCommand");
        }
        return commands;
    }
    
}
