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
import edu.du.menascheraymond.model.service.ArrayListImpl;
import edu.du.menascheraymond.model.service.JoinArrayListImpl;
import edu.du.menascheraymond.model.services.carshowownerservice.CarShowOwnerArrayListImpl;
import edu.du.menascheraymond.model.services.carshowservice.CarShowArrayListImpl;
import edu.du.menascheraymond.model.services.ownerservice.OwnerArrayListImpl;
import edu.du.menascheraymond.model.services.vehicleservice.VehicleArrayListImpl;
import java.time.LocalDate;

/**
 * Main method class.
 * @author raymondmenasche
 */
public class MainMethod {
    private static ArrayListImpl ownerArrayList = new OwnerArrayListImpl();
    private static ArrayListImpl vehicleArrayList = new VehicleArrayListImpl();
    private static ArrayListImpl carShowArrayList = new CarShowArrayListImpl();
    private static JoinArrayListImpl carShowOwnerArrayList = new CarShowOwnerArrayListImpl();
    
    public static void main(String[] args) {
        
        
        //Create Owner objects.
        doOwners();
        
        
        //Create Vehicle objects.
        doVehicles();
        
        
        //Create CarShow objects.
        doCarShows();
        
        
        //Create CarShowOwner objects.
        doCarShowOwners();
    }
    
    public static ArrayListImpl perform(Object[] objects, ArrayListImpl arrayListImpl) {
        for (Object o: objects) {
            if(arrayListImpl.isPresent(o)) {
                System.out.println(o.toString() + " already exists in List.");
            } else {
                if (arrayListImpl.add(o)) {
                    System.out.println("Added: " + o.toString());
                }
            }
        }
        return arrayListImpl;
    }
    
    public static JoinArrayListImpl perform(Object[] objects, JoinArrayListImpl arrayListImpl) {
        for (Object o: objects) {
            if(arrayListImpl.isPresent(o)) {
                System.out.println(o.toString() + " already exists in List.");
            } else {
                if (arrayListImpl.add(o)) {
                    System.out.println("Added: " + o.toString());
                }
            }
        }
        return arrayListImpl;
    }
    
    public static void performExists(Object[] objects) {
        for (Object o: objects) {
            if (o != null) {
                System.out.println(o.toString() + " exists");
            } else {
                System.out.println("The return value is null. Owner does not exists.");
            }
        }
    }
    
    public static void doOwners() {
        Address address1 = new Address();
        System.out.println(address1.toString());
        Address address2 = new Address("111 Somewhere St", "Apt 404", "Thereville",
                "Colorado", "80001");
        System.out.println(address2.toString());
        Address address3 = new Address.Builder().withStreet1("123 Nowhere Ln")
                .withCity("Denver").withState("Colorado").withZip("80001").build();
        System.out.println(address3.toString());
        
        Owner owner1 = new Owner.Builder("O1234", "Rockie", "Balboa")
                .withPhoneNumber("555-555-5555").withAddress(address3)
                .withNumYears(50).build();
        
        Owner owner2 = new Owner("O1246", "John", "Mayer", "555-433-6645",
                16, address2);
        
        Owner owner3 = new Owner.Builder("O1246", "Don", "Added").build();
        
        Object[] ownerArray = {owner1, owner2, owner3};
        ownerArrayList = perform(ownerArray, ownerArrayList);
        
        owner3.setOwnerId("O1249");
        ownerArray[2] = owner3;
        ownerArrayList.add(owner3);
        
        for (Object o: ownerArray) {
            Owner owner = (Owner)o;
            if(owner.isSeniorOwner()) {
                System.out.println(owner.getFirstName() + " is a senior owner.");
            } else {
                System.out.println(owner.getFirstName() + " is not a senior owner.");
            }
        }
        //Run again to check fail messages for objects that already exists.
        ownerArrayList = perform(ownerArray, ownerArrayList);
        
        Owner ownerDoesExists = (Owner)ownerArrayList.find("O1249");
        Owner ownerDoesNotExists = (Owner)ownerArrayList.find("O1111");
        Object[] doThisOwnersExists = {ownerDoesExists, ownerDoesNotExists};
        performExists(doThisOwnersExists);
        
        if (ownerArrayList.remove(owner3)) {
            int counter = 0;
            System.out.println("Last object from Owners Array removed.");
        }
        ownerArrayList.dump();
    }
    
    public static void doVehicles() {
        Vehicle vehicle1 = new Vehicle();
        if (vehicleArrayList.isPresent("V122")) {
            System.out.println("Could not set vehicleId because another object already has it.");
        } else {
            vehicle1.setVehicleId("V122");
        }
        if (ownerArrayList.isPresent("O1246")) {
            vehicle1.setOwnerId("O1246");
        } else {
            System.out.println("Could not set ownerId since owner does not exists.");
        }
        vehicle1.setManufacturer("Ford");
        vehicle1.setModelYear(1978);
        vehicle1.setModel("Mustang");
        vehicle1.setSubModel("Cobra");
        vehicle1.setVehicleClassification(vehicle1.findVehicleClassification(
                vehicle1.getModelYear()));
        vehicle1.setIsInsured(true);
        
        if(vehicle1.validateVehicleClassification()) {
            System.out.println("Vehicle " + vehicle1.getVehicleId()
                    + " has the correct classification");
        } else {
            System.out.println("Vehicle " + vehicle1.getVehicleId()
                    + " has an incorrect classification");
        }
        
        Vehicle vehicle2 = null;
        if (ownerArrayList.isPresent("O1246")) {
            vehicle2 = new Vehicle("V143", "O1246", "Chevrolet",
                2004, "Camaro", "Turbo", VehicleClassification.MODERN, true);
        } else {
            System.out.println("Could not create Vehicle object");
        }
        
        if(vehicle2.validateVehicleClassification()) {
            System.out.println("Vehicle " + vehicle2.getVehicleId()
                    + " has the correct classification");
        } else {
            System.out.println("Vehicle " + vehicle2.getVehicleId()
                    + " has an incorrect classification");
        }
        
        Vehicle vehicle3 = null;
        if (ownerArrayList.isPresent("O1234")) {
            vehicle3 = new Vehicle.Builder("V426", "O1234")
                .withManufacturer("Chevrolet").withModelYear(1948).withModel("Corvette")
                .withVehicleClassification(VehicleClassification.CLASSIC).build();
        } else {
            System.out.println("Could not create Vehicle object.");
        }
        if(vehicle3.validateVehicleClassification()) {
            System.out.println("Vehicle " + vehicle3.getVehicleId() 
                    + " has the correct classification");
        } else {
            System.out.println("Vehicle " + vehicle3.getVehicleId()
                    + " has an incorrect classification");
        }
        vehicle3.setVehicleClassification(vehicle3.findVehicleClassification(
                vehicle3.getModelYear()));
        System.out.println("Modified: " + vehicle3.toString());
        
        Vehicle[] vehicles = {vehicle1, vehicle2, vehicle3};
        vehicleArrayList = perform(vehicles, vehicleArrayList);
        
        //try to add a vehicle that already exists.
        vehicleArrayList = perform(vehicles, vehicleArrayList);
        
        Vehicle vehicleDoesExists = (Vehicle)vehicleArrayList.find("V122");
        Vehicle vehicleDoesNotExists = (Vehicle)vehicleArrayList.find("V1111");
        Vehicle[] doThisVehiclesExists = {vehicleDoesExists, vehicleDoesNotExists};
        performExists(doThisVehiclesExists);
        
        if (vehicleArrayList.remove(vehicles[2])) {
            System.out.println("The last Vehicle Object was removed from the ArrayList.");
        }
        vehicleArrayList.dump();
    }
    
    public static void doCarShows() {
        CarShow carShow1 = new CarShow("C123", "The Great Car Show",
                LocalDate.of(2021, 12, 12), true);;
        if(carShow1.isSanctioned()) {
            System.out.println(carShow1.getCarShowTitle() + " is Sanctioned");
        } else {
            System.out.println(carShow1.getCarShowTitle() + " is not Sanctioned");
        }
        
        CarShow carShow2 = new CarShow.Builder("C124", "Next Gen Auto")
                .withCarShowDate(LocalDate.of(2022, 2, 15)).isSanctioned(false).build();;
        if(carShow2.isSanctioned()) {
            System.out.println(carShow2.getCarShowTitle() + " is Sanctioned");
        } else {
            System.out.println(carShow2.getCarShowTitle() + " is not Sanctioned");
        }
        carShow2.setIsSanctioned('Y');
        System.out.println("Modified: " + carShow2.toString());
        carShow2.setIsSanctioned("YES");
        System.out.println("Modified: " + carShow2.toString());
        carShow2.setIsSanctioned(0);
        System.out.println("Modified: " + carShow2.toString());
        
        CarShow carShow3 = new CarShow.Builder("V124", "The Old One").build();
        
        CarShow[] carShows = {carShow1, carShow2, carShow3};
        carShowArrayList = perform(carShows, carShowArrayList);

        //Since above carShow3 will not add create a different carShow3 id.
        carShow3.setCarShowId("V125");
        carShows[2] = carShow3;
        //try to add an object that alrady exists.
        carShowArrayList = perform(carShows, carShowArrayList);

        CarShow carShowDoesExists = (CarShow)carShowArrayList.find("125");
        CarShow carShowDoesNotExists = (CarShow)carShowArrayList.find("12888");
        CarShow[] doThisCarShowsExists = {carShowDoesExists, carShowDoesNotExists};
        performExists(doThisCarShowsExists);
        
        if (carShowArrayList.remove(carShow3)) {
            System.out.println("The last CarShow object was removed from ArrayList.");
        }
        carShowArrayList.dump();
    }
    
    public static void doCarShowOwners() {
        CarShowOwner carShowOwner1 = null;
        if (ownerArrayList.isPresent("O1246")
                && carShowArrayList.isPresent("C123")) {
            carShowOwner1 = new CarShowOwner("C123", "O1246");
            System.out.println("Created: " + carShowOwner1.toString());
        } else {
            System.out.println("Could not create CarShowOwner. No supporting objects.");
        }
        
        CarShowOwner carShowOwner2 = null;
        if (ownerArrayList.isPresent("O1234") 
                && carShowArrayList.isPresent("C124")) {
            carShowOwner2 = new CarShowOwner("C124","O1234");
            System.out.println("Created: " + carShowOwner2.toString());
        } else {
            System.out.println("Could not create CarShowOwner. No supporting objects.");
        }
        
        CarShowOwner carShowOwner3 = null;
        if (ownerArrayList.isPresent("O1234")
                && carShowArrayList.isPresent("C124")) {
            carShowOwner3 = new CarShowOwner("C124", "O1234");
            System.out.println("Created: " + carShowOwner3.toString());
        } else {
            System.out.println("Could not create CarShowOwner. No supporting objects.");
        }
        //Since above will fail, create a different object. 
        if (ownerArrayList.isPresent("O1246")
                && carShowArrayList.isPresent("C124")) {
            carShowOwner3 = new CarShowOwner("C124", "O1246");
            System.out.println("Created: " + carShowOwner3.toString());
        } else {
            System.out.println("Could not create CarShowOwner. No supporting objects.");
        }
        CarShowOwner[] carShowOwners = {carShowOwner1, carShowOwner2, carShowOwner3};
        carShowOwnerArrayList = perform(carShowOwners, carShowOwnerArrayList);
        
        //try to add object that exist
        carShowOwnerArrayList = perform(carShowOwners, carShowOwnerArrayList);
        if (carShowOwnerArrayList.remove(carShowOwner3)) {
            System.out.println("Removed the last CarShowOwner from ArrayList.");
        }
        carShowOwnerArrayList.dump();
    }
}
