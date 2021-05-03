/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * MainMethod.java
 */
package edu.du.menascheraymond;

import edu.du.menascheraymond.model.business.command.Command;
import edu.du.menascheraymond.model.business.manager.Manager;
import edu.du.menascheraymond.model.domain.Address;
import edu.du.menascheraymond.model.domain.CarShow;
import edu.du.menascheraymond.model.domain.CarShowOwner;
import edu.du.menascheraymond.model.domain.Owner;
import edu.du.menascheraymond.model.domain.Vehicle;
import edu.du.menascheraymond.model.domain.VehicleClassification;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Main method class.
 * @author raymondmenasche
 */
public class MainMethod {
    private static Manager manager = new Manager();
    private static List<String> fileReaderLines = new ArrayList<>();
    private static List<String> fileWriterLines = new ArrayList<>();
    private static List<String> owners = new ArrayList<>();
    private static List<String> vehicles = new ArrayList<>();
    private static List<String> carShows = new ArrayList<>();
    private static List<String> csos = new ArrayList<>();
    private static Address address = null;
    
    public static void main(String[] args) {
        //Store all the lines from CarShowData.txt and stores them in fileReaderLines
        //ArrayList.
        readFile();
        
        //Create address.
        
        
        setFileWriterLines();
        writeToFile();
    }
    
    public static void readFile() {
        Command command = manager.getCommand();
        List<Map<String,String>> commands = command.getCommands();
        for (Map<String,String> cmd: commands) {

            try {
                doAddresses(cmd);
                doOwners(cmd);
                doVehicles(cmd);
                doCarShows(cmd);
                doCarShowOwners(cmd);
            } catch (NullPointerException e) {
                System.out.println("Error " + e);
            }
        }
    }
    
    public static void writeToFile() {
        try (FileWriter fw = new FileWriter("target/OwnerReport.txt"))
        {
            for (String line: fileWriterLines) {
                fw.write(line);
            }
        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
        }
    }
    
    public static LocalDate dateFromString(String s) {
        LocalDate rv = null;
        if (s.contains("-")) {
            String[] da = s.split("-");
            int year = Integer.parseInt(da[0]);
            int month = Integer.parseInt(da[1]);
            int day = Integer.parseInt(da[2]);
            rv = LocalDate.of(year, month, day);
        } else if (s.contains("/")) {
            String[] da = s.split("/");
            int month = Integer.parseInt(da[0]);
            int day = Integer.parseInt(da[1]);
            int year = Integer.parseInt(da[2]);
            rv = LocalDate.of(year, month, day);
        } else {
            throw new IllegalArgumentException("Invalid date string format or unsupported");
        }
        return rv;
    }
    
    public static void doAddresses(Map<String,String> cmd) {

        if (cmd.get("TYPE").strip().equalsIgnoreCase("ADDRESS")) {
            if (cmd.size() == 7) {
                String street1 = cmd.get("street1");               //Street 1
                String street2 = cmd.get("street2");               //Street 2
                String city = cmd.get("city");                     //City
                String state = cmd.get("state");                   //State
                String zipCode = cmd.get("zipcode").strip();       //Zip Code
                address = new Address.Builder().withStreet1(street1)
                        .withStreet2(street2).withCity(city).withState(state)
                        .withZip(zipCode).build();
                System.out.println("Created: " + address.toString());
            } else {
                throw new NullPointerException("Error building Array. Size mismatch");
            }
        }
        
    }
    
    public static void doOwners(Map<String,String> cmd) {

        if (cmd.get("TYPE").strip().equalsIgnoreCase("OWNER")) {

            String id = cmd.get("ownerId").strip();                                //Owner ID

            if (cmd.get("ACTION").equalsIgnoreCase("ADD")) {
                //Check array lenght for all necessary fields. 
                if (cmd.size() == 7) {
                    String firstName = cmd.get("firstName");                        //First Name
                    String lastName = cmd.get("lastName");                          //Last Name
                    String phoneNumber = cmd.get("phone").strip();                  //Phone Number
                    int numYears = Integer.parseInt(cmd.get("years").strip());      //Number of Years
                    Owner o = new Owner.Builder(id, firstName, lastName)
                            .withPhoneNumber(phoneNumber).withAddress(address)
                            .withNumYears(numYears).build();
                    if (manager.getOwnerCollection().add(o)) {
                        owners.add(id);
                        System.out.println("Added: " + o.toString());
                    }
                } else {
                    throw new NullPointerException("Error creating array. Size mismatch");
                }
            }
            if (cmd.get("ACTION").equalsIgnoreCase("REM")) {
                //Removes Owner object.
                if (manager.getOwnerCollection().remove(id)) {
                    owners.remove(id);
                    System.out.println("Removed owner: " + id);
                    //If succesfully removed Owner, removes vehicles with
                    //objects that include ownerId, and removes CarShowOwner
                    //with matching ownerId.
                    manager.getVehicleCollection().removeByOwnerId(id);
                    manager.getCarShowOwnerCollection().removeByOwnerId(id);
                } else {
                    System.out.println("Error removing owner: " + id);
                }
            }
        }
    }
    
    public static void doVehicles(Map<String, String> cmd) {

        if (cmd.get("TYPE").strip().equalsIgnoreCase("VEHICLE")) {
            String id = cmd.get("vehicleId");                                   //Vehicle id
            if (cmd.get("ACTION").equalsIgnoreCase("ADD")) {
                //Check array contains all required attributes. 
                if (cmd.size() == 10) {
                    String ownerId = cmd.get("ownerId").strip();             //Owner id
                    int year = Integer.parseInt(cmd.get("year"));            //Vehicle year
                    String manufacturer = cmd.get("make");                   //Manufacturer
                    String model = cmd.get("model");                         //Vehicle model
                    String subModel = cmd.get("subModel");                   //Vehicle sub model
                    VehicleClassification classification = null;
                    switch (cmd.get("classification").toUpperCase().strip()) {       //Vehicle Classification
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
                    String insured = cmd.get("insured").strip();             //Insured? Y/Yes/N/No

                    Vehicle vehicle = new Vehicle.Builder(id, ownerId)
                            .withModelYear(year)
                            .withManufacturer(manufacturer)
                            .withModel(model)
                            .withSubModel(subModel)
                            .withVehicleClassification(classification).build();
                    vehicle.setInsured(insured);
                    if (manager.getOwnerCollection().isPresent(ownerId)) {
                        if (manager.getVehicleCollection().add(vehicle)) {
                            vehicles.add(id);
                            System.out.println("Added: " + vehicle.toString());
                        }
                    } else {
                        System.out.println("Could not add: " + vehicle.toString()
                                + ". Missing dependencies.");
                    }
                } else {
                    throw new NullPointerException("Error creating array. Size mismatch");
                }
            }
            if (cmd.get("ACTION").equalsIgnoreCase("REM")) {
                if (manager.getVehicleCollection().remove(id)) {
                    vehicles.remove(id);
                    System.out.println("Removed vehicle: " + id);
                } else {
                    System.out.println("Error removing vehicle: " + id);
                }
            }

        }
    }
    
    public static void doCarShows(Map<String,String> cmd) {

        if (cmd.get("TYPE").strip().equalsIgnoreCase("CARSHOW")) {

            String id = cmd.get("carShowId").strip();                      //CarShow id

            if (cmd.get("ACTION").equalsIgnoreCase("ADD")) {
                //Check if array contains all required attributes
                if (cmd.size() == 6) {
                    String title = cmd.get("title");                           //Title
                    String strDate = cmd.get("date").strip();                  //String date ex. 12/12/2020 or 2020-12-12
                    LocalDate date = null;
                    try {
                        date = dateFromString(strDate);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error creating date" + e);
                    }
                    String sanctioned = cmd.get("sanctioned").strip();              //Sanctioned: Y/Yes/N/No
                    CarShow carShow = new CarShow.Builder(id, title)
                            .withCarShowDate(date).build();
                    carShow.setSanctioned(sanctioned);
                    if (manager.getCarShowCollection().add(carShow)) {
                        carShows.add(id);
                        System.out.println("Added: " + carShow.toString());
                    }
                } else {
                    throw new NullPointerException("Error creating array. Size mismatch");
                }
            }
            if (cmd.get("ACTION").equalsIgnoreCase("REM")) {
                //Removes CarShow Object
                if (manager.getCarShowCollection().remove(id)) {
                    carShows.remove(id);
                    System.out.println("Removed CarShow: " + id);
                    //If successfully removed CarShow Object, removes
                    //CarShowOwner objects with matching CarShowId.
                    manager.getCarShowOwnerCollection().removeByCarShowId(id);
                } else {
                    System.out.println("Error removing CarShow: " + id);
                }
            }
        }
    }
    
    public static void doCarShowOwners(Map<String, String> cmd) {

        if (cmd.get("TYPE").strip().equalsIgnoreCase("CSO")) {
            if (cmd.size() == 4) {
                String command = cmd.get("ACTION").strip();                  //Command: ADD, REM...
                String ownerId = cmd.get("ownerId").strip();
                String carShowId = cmd.get("carShowId").strip();
                CarShowOwner cso = new CarShowOwner(carShowId, ownerId);
                if (command.equalsIgnoreCase("ADD")) {
                    if (manager.getOwnerCollection().isPresent(ownerId)
                            && manager.getCarShowCollection().isPresent(carShowId)) {
                        if (manager.getCarShowOwnerCollection().add(cso)) {
                            System.out.println("Added: " + cso.toString());
                        }
                        //carShowOwnerArrayList = perform(cso, command, carShowOwnerArrayList);
                    } else {
                        System.out.println("Could not add: " + cso.toString()
                                + ". Missing dependencies");
                    }
                }

                if (command.equalsIgnoreCase("REM")) {
                    if (manager.getCarShowOwnerCollection().remove(cso)) {
                        System.out.println("Removed; " + cso.toString());
                    } else {
                        System.out.println("Error removing: " + cso.toString());
                    }
                }
            } else {
                throw new NullPointerException("Error building array. Size mismatch.");
            }
        }
    }
    
    public static void setFileWriterLines() {
        
        for (String oID: owners) {
            Owner o = manager.getOwnerCollection().find(oID);
            String special = "";
            if (o.isSeniorOwner()) {
                special = "**SO**";
            }
            fileWriterLines.add("OWNER " + o.getFirstName() + " " + o.getLastName()
                    + " " + special + "\n");
            fileWriterLines.add("VEHICLES\n");
            for (String vID: vehicles) {
                Vehicle v = manager.getVehicleCollection().find(vID);
                if (v.getOwnerId().equals(o.getOwnerId())) {
                    fileWriterLines.add("\t" + v.getModelYear() + " "
                            + v.getManufacturer() + " " + v.getModel()
                            + " " + v.getSubModel() + "\n");
                }
            }
            fileWriterLines.add("CAR SHOWS\n");
            for (String cID: carShows) {
                if (manager.getCarShowOwnerCollection().isPresent(oID, cID)) {
                    CarShow carShow = manager.getCarShowCollection().find(cID);
                    String exclamations = "";
                    if (carShow.isSanctioned()) {
                        exclamations = "!!!!";
                    }
                    fileWriterLines.add("\t" + carShow.getCarShowTitle() + " " 
                            + exclamations + "\n");
                }
            }
            fileWriterLines.add("\n");
        }
    }
}
