/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 * File: MainMethod.java
 */
package edu.du.raymondmenasche;

import edu.du.menascheraymond.model.domain.Address;
import edu.du.menascheraymond.model.domain.CarShow;
import edu.du.menascheraymond.model.domain.CarShowOwner;
import edu.du.menascheraymond.model.domain.Owner;
import edu.du.menascheraymond.model.domain.Vehicle;
import edu.du.menascheraymond.model.domain.VehicleClassification;
import edu.du.menascheraymond.model.services.ArrayListImpl;
import edu.du.menascheraymond.model.services.JoinArrayListImpl;
import edu.du.menascheraymond.model.services.carshowownerservice.CarShowOwnerArrayListImpl;
import edu.du.menascheraymond.model.services.carshowservice.CarShowArrayListImpl;
import edu.du.menascheraymond.model.services.ownerservice.OwnerArrayListImpl;
import edu.du.menascheraymond.model.services.vehicleservice.VehicleArrayListImpl;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Main method class.
 * @author raymondmenasche
 */
public class MainMethod {
    private static ArrayListImpl ownerArrayList = new OwnerArrayListImpl();
    private static ArrayListImpl vehicleArrayList = new VehicleArrayListImpl();
    private static ArrayListImpl carShowArrayList = new CarShowArrayListImpl();
    private static JoinArrayListImpl carShowOwnerArrayList = new CarShowOwnerArrayListImpl();
    private static List<String> fileReaderLines = new ArrayList<>();
    private static List<String> fileWriterLines = new ArrayList<>();
    private static Address address = null;
    
    public static void main(String[] args) {
        //Store all the lines from CarShowData.txt and stores them in fileReaderLines
        //ArrayList.
        readFile();
        
        //Create address.
        for (String line: fileReaderLines) {
            String[] data = line.split(",");

            try {
                doAddresses(data);
                doOwners(data);
                doVehicles(data);
                doCarShows(data);
                doCarShowOwners(data);
            } catch (NullPointerException e) {
                System.out.println("Error " + e);
            }
        }
        
        //perform data check for all objects
        ownerDataCheck();
        vehicleDataCheck();
        carShowDataCheck();
        
        setFileWriterLines();
        writeToFile();
    }
    
    public static ArrayListImpl perform(Object o, String command,
            ArrayListImpl arrayListImpl) {
        
        switch (command.toUpperCase()) {
            case "ADD":
                if(arrayListImpl.isPresent(o)) {
                    System.out.println(o.toString() + " already exists in List.");
                } else {
                    if (arrayListImpl.add(o)) {
                        System.out.println("Added: " + o.toString());
                    } else {
                        System.out.println("Error adding: " + o.toString());
                    }
                }

                break;
            case "REM":
                if (arrayListImpl.remove(o)) {
                    System.out.println("Removed: " + o.toString());
                } else {
                    System.out.println("Error removing: " + o.toString());
                }

                break;
            default:
                System.out.println("Nothing happened?!");
                break;
        }

        return arrayListImpl;
    }
    
    public static JoinArrayListImpl perform(Object o, String command,
            JoinArrayListImpl arrayListImpl) {
        switch (command.toUpperCase()) {
            case "ADD":
                if(arrayListImpl.isPresent(o)) {
                    System.out.println(o.toString() + " already exists in List.");
                } else {
                    if (arrayListImpl.add(o)) {
                        System.out.println("Added: " + o.toString());
                    } else {
                        System.out.println("Error adding: " + o.toString());
                    }
                }
                break;
            case "REM":
                if (arrayListImpl.remove(o)) {
                    System.out.println("Removed: " + o.toString());
                } else {
                    System.out.println("Error removing: " + o.toString());
                }
                break;
            default:
                System.out.println("Nothing happened?!");
                break;
        }
        return arrayListImpl;
    }
    
    public static void performExists(Object[] objects) {
        for (Object o: objects) {
            if (o != null) {
                System.out.println(o.toString() + " exists");
            } else {
                System.out.println("The return value is null. Object does not exists.");
            }
        }
    }
    
    public static void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("target/CarShowData.txt")))
        {
            String line;
            while ((line = br.readLine()) != null) {
                fileReaderLines.add(line); 
            }
        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
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
    
    public static void doAddresses(String[] data) {

        if (data[0].strip().equalsIgnoreCase("ADDRESS")) {
            if (data.length == 7) {
                String command = data[1].strip();       //Command: ADD, REM, ...
                String street1 = data[2];               //Street 1
                String street2 = data[3];               //Street 2
                String city = data[4];                  //City
                String state = data[5];                 //State
                String zipCode = data[6].strip();       //Zip Code
                address = new Address.Builder().withStreet1(street1)
                        .withStreet2(street2).withCity(city).withState(state)
                        .withZip(zipCode).build();
                System.out.println("Created: " + address.toString());
            } else {
                throw new NullPointerException("Error building Array. Size mismatch");
            }
        }
        
    }
    
    public static void doOwners(String[] data) {

        if (data[0].strip().equalsIgnoreCase("OWNER")) {
            //check minimum array lenght
            if (data.length >= 3) {
                String command = data[1].strip();                   //ADD, REM, ...
                String id = data[2].strip();                        //Owner ID

                if (command.equalsIgnoreCase("ADD")) {
                    //Check array lenght for all necessary fields. 
                    if (data.length == 7) {
                        String firstName = data[3];                         //First Name
                        String lastName = data[4];                          //Last Name
                        String phoneNumber = data[5].strip();               //Phone Number
                        int numYears = Integer.parseInt(data[6].strip());   //Number of Years
                        Owner o = new Owner.Builder(id, firstName, lastName)
                                .withPhoneNumber(phoneNumber).withAddress(address)
                                .withNumYears(numYears).build();
                        ownerArrayList = perform(o, command, ownerArrayList);
                    } else {
                        throw new NullPointerException("Error creating array. Size mismatch");
                    }
                }
                if (command.equalsIgnoreCase("REM")) {
                    //Removes Owner object.
                    if (ownerArrayList.remove(id)) {
                        System.out.println("Removed owner: " + id);
                        //If succesfully removed Owner, removes vehicles with
                        //objects that include ownerId, and removes CarShowOwner
                        //with matching ownerId.
                        Iterator<Vehicle> i = vehicleArrayList.getIterator();
                        while (i.hasNext()) {
                            Vehicle v = i.next();
                            if (v.getOwnerId().equals(id)) {
                                i.remove();
                                System.out.println("Removed: " + v.toString());
                            }
                        }
                        Iterator<CarShowOwner> is = carShowOwnerArrayList.getIterator();
                        while (is.hasNext()) {
                            CarShowOwner c = is.next();
                            if (c.getOwnerId().equals(id)) {
                                is.remove();
                                System.out.println("Removed: " + c.toString());
                            }
                        }
                    } else {
                        System.out.println("Error removing owner: " + id);
                    }
                }
            } else {
                throw new NullPointerException("Error building Array. Size mismatch");
            }
        }
        
        

    }
    
    public static void doVehicles(String[] data) {

        if (data[0].strip().equalsIgnoreCase("VEHICLE")) {
            //Check minimum array size
            if (data.length >= 3) {
                String command = data[1].strip();              //Command: ADD, REM, ...
                String id = data[2].strip();                   //Vehicle id
                
                if (command.equalsIgnoreCase("ADD")) {
                    //Check array contains all required attributes. 
                    if (data.length == 10) {
                        String ownerId = data[3].strip();              //Owner id
                        int year = Integer.parseInt(data[4]);          //Vehicle year
                        String manufacturer = data[5];                 //Manufacturer
                        String model = data[6];                        //Vehicle model
                        String subModel = data[7];                     //Vehicle sub model
                        VehicleClassification classification = null;
                        switch (data[8].toUpperCase().strip()) {       //Vehicle Classification
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
                                // think of something here. 
                                break;
                        }
                        String insured = data[9].strip();             //Insured? Y/Yes/N/No
                    
                        Vehicle vehicle = new Vehicle.Builder(id, ownerId)
                                .withModelYear(year)
                                .withManufacturer(manufacturer)
                                .withModel(model)
                                .withSubModel(subModel)
                                .withVehicleClassification(classification).build();
                        vehicle.setInsured(insured);
                        if (ownerArrayList.isPresent(ownerId)) {
                            vehicleArrayList = perform(vehicle, command, vehicleArrayList);
                        } else {
                            System.out.println("Could not add: " + vehicle.toString()
                                    + ". Missing dependencies.");
                        }
                    } else {
                        throw new NullPointerException("Error creating array. Size mismatch");
                    }
                }
                if (command.equalsIgnoreCase("REM")) {
                    if (vehicleArrayList.remove(id)) {
                        System.out.println("Removed vehicle: " + id);
                    } else {
                        System.out.println("Error removing vehicle: " + id);
                    }
                }
            } else {
                throw new NullPointerException("Error building array. Size mismatch");
            }
        }
        
        
        
    }
    
    public static void doCarShows(String[] data) {

        if (data[0].strip().equalsIgnoreCase("CARSHOW")) {
            //Check minimum array size
            if (data.length >= 3) {
                String command = data[1].strip();                 //Command: ADD, REM...
                String id = data[2].strip();                      //CarShow id
                

                if (command.equalsIgnoreCase("ADD")) {
                    //Check if array contains all required attributes
                    if (data.length == 6) {
                        String title = data[3];                           //Title
                        String strDate = data[4].strip();                 //String date ex. 12/12/2020 or 2020-12-12
                        LocalDate date = null;
                        try {
                            date = dateFromString(strDate);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error creating date" + e);
                        }
                        String sanctioned = data[5].strip();              //Sanctioned: Y/Yes/N/No
                        CarShow carShow = new CarShow.Builder(id, title)
                                .withCarShowDate(date).build();
                        carShow.setSanctioned(sanctioned);
                        carShowArrayList = perform(carShow, command, carShowArrayList);
                    } else {
                        throw new NullPointerException("Error creating array. Size mismatch");
                    }
                }
                if (command.equalsIgnoreCase("REM")) {
                    //Removes CarShow Object
                    if (carShowArrayList.remove(id)) {
                        System.out.println("Removed CarShow: " + id);
                        //If successfully removed CarShow Object, removes
                        //CarShowOwner objects with matching CarShowId.
                        Iterator<CarShowOwner> i = carShowOwnerArrayList.getIterator();
                        while (i.hasNext()) {
                            CarShowOwner c = i.next();
                            if (c.getCarShowId().equals(id)) {
                                i.remove();
                                System.out.println("Removed: " + c.toString());
                            }
                        }
                    } else {
                        System.out.println("Error removing CarShow: " + id);
                    }
                }
            } else {
                throw new NullPointerException("Error building Array. Size mismatch");
            }
        }
    }
    
    public static void doCarShowOwners(String[] data) {

        if (data[0].strip().equalsIgnoreCase("CSO") && data.length == 4) {
            if (data.length == 4) {
                String command = data[1].strip();                  //Command: ADD, REM...
                String ownerId = data[2].strip();
                String carShowId = data[3].strip();
                CarShowOwner cso = new CarShowOwner(carShowId, ownerId);
                if (command.equalsIgnoreCase("ADD")) {
                    if (ownerArrayList.isPresent(ownerId)
                            && carShowArrayList.isPresent(carShowId)) {
                        carShowOwnerArrayList = perform(cso, command, carShowOwnerArrayList);
                    } else {
                        System.out.println("Could not add: " + cso.toString()
                                + ". Missing dependencies");
                    }
                }

                if (command.equalsIgnoreCase("REM")) {
                    if (carShowOwnerArrayList.remove(cso)) {
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
    
    public static void ownerDataCheck() {
        // Check if owners in array are senior
        List<Owner> owners = ownerArrayList.getList();
        for (Owner o: owners) {
            if(o.isSeniorOwner()) {
                System.out.println(o.getFirstName() + " is a senior owner.");
            } else {
                System.out.println(o.getFirstName() + " is not a senior owner.");
            }
        }
        
        //Add existing Owners again to check fail messages for objects that already exists.
        for (Object o: owners) {
            ownerArrayList = perform(o, "ADD", ownerArrayList);
        }
        
        Owner ownerDoesExists = (Owner)ownerArrayList.find("O1");
        Owner ownerDoesNotExists = (Owner)ownerArrayList.find("O1111");
        Object[] doThisOwnersExists = {ownerDoesExists, ownerDoesNotExists};
        performExists(doThisOwnersExists);
        
        ownerArrayList.dump();
    }
    
    public static void vehicleDataCheck() {
        //Check vehicles for correct classification
        Iterator<Vehicle> i = vehicleArrayList.getIterator();
        while (i.hasNext()) {
            Vehicle v = i.next();
            if (v.validateVehicleClassification()) {
                System.out.println(v.getVehicleId() + " has the correct classification");
            } else {
                System.out.println(v.getVehicleId() + " has an incorrect classification");
                //Modify Vehicle object
                VehicleClassification vc = v
                        .findVehicleClassification(v.getModelYear());
                v.setVehicleClassification(vc);
                System.out.println("Modified: " + v.toString());
            }
        }
        
        //Try adding Vehicle objects that already exist in ArrayList
        List<Vehicle> vehicles = vehicleArrayList.getList();
        for (Vehicle v: vehicles) {
            vehicleArrayList = perform(v, "ADD", vehicleArrayList);
        }
        
        Vehicle vehicleDoesExists = (Vehicle)vehicleArrayList.find("V124");
        Vehicle vehicleDoesNotExists = (Vehicle)vehicleArrayList.find("V1111");
        Vehicle[] doThisVehiclesExists = {vehicleDoesExists, vehicleDoesNotExists};
        performExists(doThisVehiclesExists);
        
        vehicleArrayList.dump();
    }
    
    public static void carShowDataCheck() {
        //Check to see if CarShow objects are sanctioned
        List<CarShow> carShows = carShowArrayList.getList();
        for (CarShow c: carShows) {
            if (c.isSanctioned()) {
                System.out.println(c.getCarShowId() + " is sanctioned.");
            } else {
                System.out.println(c.getCarShowId() + " is not sanctioned");
            }
        }
        
        //Try to add CarShow objects that already exist in ArrayList
        for (CarShow c: carShows) {
            carShowArrayList = perform(c, "ADD", carShowArrayList);
        }
        
        CarShow carShowDoesExists = (CarShow)carShowArrayList.find("CS1");
        CarShow carShowDoesNotExists = (CarShow)carShowArrayList.find("CS5");
        CarShow[] doThisCarShowsExists = {carShowDoesExists, carShowDoesNotExists};
        performExists(doThisCarShowsExists);

        carShowArrayList.dump();
    }
    
    public static void setFileWriterLines() {
        List<Owner> owners = ownerArrayList.getList();
        List<Vehicle> vehicles = vehicleArrayList.getList();
        List<CarShowOwner> cso = carShowOwnerArrayList.getList();
        for (Owner o: owners) {
            String special = "";
            if (o.isSeniorOwner()) {
                special = "**SO**";
            }
            fileWriterLines.add("OWNER " + o.getFirstName() + " " + o.getLastName()
                    + " " + special + "\n");
            fileWriterLines.add("VEHICLES\n");
            for (Vehicle v: vehicles) {
                if (v.getOwnerId().equals(o.getOwnerId())) {
                    fileWriterLines.add("\t" + v.getModelYear() + " "
                            + v.getManufacturer() + " " + v.getModel()
                            + " " + v.getSubModel() + "\n");
                }
            }
            fileWriterLines.add("CAR SHOWS\n");
            for (CarShowOwner c: cso) {
                if (c.getOwnerId().equals(o.getOwnerId())) {
                    CarShow carShow = (CarShow)carShowArrayList.find(c.getCarShowId());
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
