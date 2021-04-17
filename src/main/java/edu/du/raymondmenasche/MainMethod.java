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
    private static OwnerArrayListImpl ownerArrayList = new OwnerArrayListImpl();
    private static VehicleArrayListImpl vehicleArrayList = new VehicleArrayListImpl();
    private static CarShowArrayListImpl carShowArrayList = new CarShowArrayListImpl();
    private static CarShowOwnerArrayListImpl carShowOwnerArrayList = new CarShowOwnerArrayListImpl();
    
    public static void main(String[] args) {
        Address address1 = new Address();
        System.out.println(address1.toString());
        Address address2 = new Address("111 Somewhere St", "Apt 404", "Thereville",
                "Colorado", "80001");
        System.out.println(address2.toString());
        Address address3 = new Address.Builder().withStreet1("123 Nowhere Ln")
                .withCity("Denver").withState("Colorado").withZip("80001").build();
        System.out.println(address3.toString());
        
        Owner owner1 = null;
        if(ownerArrayList.isPresent("1234")) {
            System.out.println("Owner ID is present. Cannot create object.");
        } else {
            owner1 = new Owner.Builder("1234", "Rockie", "Balboa")
                .withPhoneNumber("555-555-5555").withAddress(address3)
                .withNumYears(50).build();
        }
        if(ownerArrayList.isPresent(owner1)) {
            System.out.println("Owner object already exists");
        } else {
            ownerArrayList.add(owner1);
        }
        System.out.println(owner1.toString());
        if(owner1.isSeniorOwner()) {
            System.out.println(owner1.getFirstName() + " is a senior owner.");
        } else {
            System.out.println(owner1.getFirstName() + " is not a senior owner");
        }
        
        Owner owner2 = null;
        if (ownerArrayList.isPresent("1246")) {
            System.out.println("Owner ID is present. Cannot create object.");
        } else {
            owner2 = new Owner("1246", "John", "Mayer", "555-433-6645",
                16, address2);
        }
        if (!ownerArrayList.add(owner2)) {
            System.out.println("Owner object already exists");
        }
        System.out.println(owner2.toString());
        if(owner2.isSeniorOwner()) {
            System.out.println(owner2.getFirstName() + " is a senior owner.");
        } else {
            System.out.println(owner2.getFirstName() + " is not a senior owner");
        }
        
        Owner owner3 = null;
        if (ownerArrayList.isPresent("1246")) {
            System.out.println("Owner ID is present. Cannot create object.");
            // create object with different user ID
            owner3 = new Owner.Builder("1249", "Don", "Added").build();
        } else {
            owner3 = new Owner.Builder("1246", "Don", "Added").build();
        }
        if (!ownerArrayList.add(owner3)) {
            System.out.println("Owner object already exists");
        }
        Owner ownerDoesExist = ownerArrayList.find("1249");
        if (ownerDoesExist != null) {
            System.out.println(ownerDoesExist.toString());
        }
        Owner ownerDoesNotExist = ownerArrayList.find("1111");
        if (ownerDoesNotExist != null) {
            System.out.println(ownerDoesNotExist.toString());
        }
        if (ownerArrayList.remove(owner3)) {
            int counter = 0;
            System.out.println("Last object from Owners Array removed.");
        }
        ownerArrayList.dump();
        
        Vehicle vehicle1 = new Vehicle();
        if (vehicleArrayList.isPresent("122")) {
            System.out.println("Could not set vehicleId because another object already has it.");
        } else {
            vehicle1.setVehicleId("122");
        }
        if (ownerArrayList.isPresent(owner2)) {
            vehicle1.setOwnerId(owner2.getOwnerId());
        }
        vehicle1.setManufacturer("Ford");
        vehicle1.setModelYear(1978);
        vehicle1.setModel("Mustang");
        vehicle1.setSubModel("Cobra");
        vehicle1.setVehicleClassification(vehicle1.findVehicleClassification(
                vehicle1.getModelYear()));
        vehicle1.setIsInsured(true);
        if (!vehicleArrayList.isPresent(vehicle1) && ownerArrayList.isPresent(owner2)) {
            vehicleArrayList.add(vehicle1);
        } else {
            System.out.println("Could not add vehicle object to ArrayList");
        }
        System.out.println(vehicle1.toString());
        if(vehicle1.validateVehicleClassification()) {
            System.out.println("Vehicle " + vehicle1.getOwnerId()
                    + " has the correct classification");
        } else {
            System.out.println("Vehicle " + vehicle1.getVehicleId()
                    + " has an incorrect classification");
        }
        
        Vehicle vehicle2 = null;
        if (!vehicleArrayList.isPresent("143") && ownerArrayList.isPresent(owner2)) {
            vehicle2 = new Vehicle("143", owner2.getOwnerId(), "Chevrolet",
                2004, "Camaro", "Turbo", VehicleClassification.MODERN, true);
            System.out.println(vehicle2.toString());
        } else {
            System.out.println("Could not create Vehicle object");
        }
        if (!vehicleArrayList.isPresent(vehicle2) && ownerArrayList.isPresent(owner2)) {
            vehicleArrayList.add(vehicle2);
        } else {
            System.out.println("Could not add Vehicle object to ArrayList.");
        }
        if(vehicle2.validateVehicleClassification()) {
            System.out.println("Vehicle " + vehicle2.getVehicleId()
                    + " has the correct classification");
        } else {
            System.out.println("Vehicle " + vehicle2.getVehicleId()
                    + " has an incorrect classification");
        }
        
        Vehicle vehicle3 = null;
        if (!vehicleArrayList.isPresent("426") && ownerArrayList.isPresent(owner1)) {
            vehicle3 = new Vehicle.Builder("426", owner1.getOwnerId())
                .withManufacturer("Chevrolet").withModelYear(1948).withModel("Corvette")
                .withVehicleClassification(VehicleClassification.CLASSIC).build();
            System.out.println(vehicle3.toString());
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
        System.out.println(vehicle3.toString());
        if (!vehicleArrayList.isPresent(vehicle3) && ownerArrayList.isPresent(owner1)) {
            vehicleArrayList.add(vehicle3);
        } else {
            System.out.println("Could not add Vehicle Object to ArrayList.");
        }
        
        Vehicle vehicle4 = null;
        if (!vehicleArrayList.isPresent("426") && ownerArrayList.isPresent(owner1)) {
            vehicle4 = new Vehicle.Builder("426", owner1.getOwnerId()).build();
            System.out.println(vehicle4.toString());
        } else {
            System.out.println("Could not create Vehicle Object.");
        }
        
        Vehicle vehicleDoesExist = vehicleArrayList.find("426");
        if (vehicleDoesExist != null) {
            System.out.println(vehicleDoesExist.toString());
        }
        Vehicle vehicleDoesNotExist = vehicleArrayList.find("1111");
        if (vehicleDoesNotExist != null) {
            System.out.println(vehicleDoesNotExist.toString());
        }
        
        if (vehicleArrayList.remove(vehicle3)) {
            System.out.println("The last Vehicle Object was removed from the ArrayList.");
        }
        vehicleArrayList.dump();
        
        CarShow carShow1 = null;
        if (!carShowArrayList.isPresent("123")) {
            carShow1 = new CarShow("123", "The Great Car Show",
                LocalDate.of(2021, 12, 12), true);
            System.out.println(carShow1.toString());
        } else {
            System.out.println("CarShow object already exist.");
        }
        if(carShow1.isSanctioned()) {
            System.out.println(carShow1.getCarShowTitle() + " is Sanctioned");
        } else {
            System.out.println(carShow1.getCarShowTitle() + " is not Sanctioned");
        }
        if (!carShowArrayList.isPresent(carShow1)) {
            carShowArrayList.add(carShow1);
        } else {
            System.out.println("Could not add CarShow Object to ArrayList.");
        }
        
        CarShow carShow2 = null;
        if (!carShowArrayList.isPresent("124")) {
            carShow2 = new CarShow.Builder("124").withCarShowTitle("Next Gen Auto")
                .withCarShowDate(LocalDate.of(2022, 2, 15)).isSanctioned(false).build();
            System.out.println(carShow2.toString());
        }
        if(carShow2.isSanctioned()) {
            System.out.println(carShow2.getCarShowTitle() + " is Sanctioned");
        } else {
            System.out.println(carShow2.getCarShowTitle() + " is not Sanctioned");
        }
        try {
            carShow2.setIsSanctioned('Y');
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(carShow2.toString());
        try {
            carShow2.setIsSanctioned("YES");
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(carShow2.toString());
        try {
            carShow2.setIsSanctioned(0);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(carShow2.toString());
        if (!carShowArrayList.isPresent(carShow2)) {
            carShowArrayList.add(carShow2);
        } else {
            System.out.println("Could not add CarShow Object to ArrayList.");
        }
        
        CarShow carShow3 = null;
        if (!carShowArrayList.isPresent("124")) {
            carShow3 = new CarShow.Builder("124").build();
            System.out.println(carShow3.toString());
        } else {
            System.out.println("Could not create CarShow Object.");
        }
        if (!carShowArrayList.isPresent("125")) {
            carShow3 = new CarShow.Builder("125").withCarShowTitle("Done").build();
            System.out.println(carShow3.toString());
        } else {
            System.out.println("Could not create CarShow object.");
        }
        CarShow carShowDoesExist = carShowArrayList.find("125");
        if (carShowDoesExist != null) {
            System.out.println(carShowDoesExist.toString());
        }
        CarShow carShowDoesNotExist = carShowArrayList.find("12888");
        if (carShowDoesNotExist != null) {
            System.out.println(carShowDoesNotExist.toString());
        }
        if (carShowArrayList.remove(carShow3)) {
            System.out.println("The last CarShow object was removed from ArrayList.");
        }
        carShowArrayList.dump();
        
        
        CarShowOwner carShowOwner1 = null;
        if (!carShowOwnerArrayList.isPresent(owner2.getOwnerId(), carShow1.getCarShowId())) {
            carShowOwner1 = new CarShowOwner(carShow1.getCarShowId(),
                owner2.getOwnerId());
            System.out.println(carShowOwner1.toString());
        } else {
            System.out.println("Could not create CarShowOwner. It already exist.");
        }
        if (!carShowOwnerArrayList.isPresent(carShowOwner1)) {
            carShowOwnerArrayList.add(carShowOwner1);
        } else {
            System.out.println("Could not add CarShowOwner object to ArrayList.");
        }
        
        CarShowOwner carShowOwner2 = null;
        if (!carShowOwnerArrayList.isPresent(owner1.getOwnerId(), carShow2.getCarShowId())) {
            carShowOwner2 = new CarShowOwner(carShow2.getCarShowId(),
                owner1.getOwnerId());
            System.out.println(carShowOwner2.toString());
        } else {
            System.out.println("Could not create CarShowOwner. It already exist.");
        }
        if (!carShowOwnerArrayList.isPresent(carShowOwner2)) {
            carShowOwnerArrayList.add(carShowOwner2);
        } else {
            System.out.println("Could not add CarShowOwner object to ArrayList");
        }
        
        CarShowOwner carShowOwner3 = null;
        if (!carShowOwnerArrayList.isPresent(owner1.getOwnerId(), carShow2.getCarShowId())) {
            carShowOwner3 = new CarShowOwner(carShow2.getCarShowId(), owner1.getOwnerId());
            System.out.println(carShowOwner3.toString());
        } else {
            System.out.println("Could not create CarShowOwner. It already exist.");
        }
        if (!carShowOwnerArrayList.isPresent(owner2.getOwnerId(), carShow2.getCarShowId())) {
            carShowOwner3 = new CarShowOwner(carShow2.getCarShowId(), owner1.getOwnerId());
            System.out.println(carShowOwner3.toString());
        } else {
            System.out.println("Could not create CarShowOwner. It already exist.");
        }
        if (!carShowOwnerArrayList.isPresent(carShowOwner3)) {
            carShowOwnerArrayList.add(carShowOwner3);
        } else {
            System.out.println("Could not add CarShowOwner object to ArrayList.");
        }
        if (carShowOwnerArrayList.remove(carShowOwner3)) {
            System.out.println("Removed the last CarShowOwner from ArrayList.");
        }
        carShowOwnerArrayList.dump();
    }
}
