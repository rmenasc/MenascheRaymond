/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 */
package edu.du.raymondmenasche;

import edu.du.menascheraymond.model.domain.Address;
import edu.du.menascheraymond.model.domain.Owner;
import edu.du.menascheraymond.model.domain.Vehicle;
import edu.du.menascheraymond.model.domain.VehicleClassification;

/**
 *
 * @author raymondmenasche
 */
public class MainMethod {
    public static void main(String[] args) {
        Address address1 = new Address();
        System.out.println(address1.toString());
        Address address2 = new Address("111 Somewhere St", "Apt 404", "Thereville",
                "Colorado", "80001");
        System.out.println(address2.toString());
        Address address3 = new Address.Builder().withStreet1("123 Nowhere Ln")
                .withCity("Denver").withState("Colorado").withZip("80001").build();
        System.out.println(address3.toString());
        
        Owner owner1 = new Owner.Builder("1234", "Rockie", "Balboa")
                .withPhoneNumber("555-555-5555").withAddress(address3)
                .withNumYears(50).build();
        System.out.println(owner1.toString());
        if(owner1.isSeniorOwner()) {
            System.out.println(owner1.getFirstName() + " is a senior owner.");
        } else {
            System.out.println(owner1.getFirstName() + " is not a senior owner");
        }
        Owner owner2 = new Owner("1246", "John", "Mayer", "555-433-6645",
                16, address2);
        System.out.println(owner2.toString());
        if(owner2.isSeniorOwner()) {
            System.out.println(owner2.getFirstName() + " is a senior owner.");
        } else {
            System.out.println(owner2.getFirstName() + " is not a senior owner");
        }
        
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setVehicleID("122");
        vehicle1.setOwnerID(owner2.getOwnerID());
        vehicle1.setManufacturer("Ford");
        vehicle1.setModelYear(1978);
        vehicle1.setModel("Mustang");
        vehicle1.setSubModel("Cobra");
        vehicle1.setVehicleClassification(vehicle1.findVehicleClassification(
                vehicle1.getModelYear()));
        vehicle1.setIsInsured(true);
        System.out.println(vehicle1.toString());
        if(vehicle1.validateVehicleClassification()) {
            System.out.println("Vehicle " + vehicle1.getOwnerID()
                    + " has the correct classification");
        } else {
            System.out.println("Vehicle " + vehicle1.getVehicleID()
                    + " has an incorrect classification");
        }
        
        Vehicle vehicle2 = new Vehicle("143", owner2.getOwnerID(), "Chevrolet",
                2004, "Camaro", "Turbo", VehicleClassification.MODERN, true);
        System.out.println(vehicle2.toString());
        if(vehicle2.validateVehicleClassification()) {
            System.out.println("Vehicle " + vehicle2.getVehicleID()
                    + " has the correct classification");
        } else {
            System.out.println("Vehicle " + vehicle2.getVehicleID()
                    + " has an incorrect classification");
        }
        
        Vehicle vehicle3 = new Vehicle.Builder("426", owner2.getOwnerID())
                .withManufacturer("Chevrolet").withModelYear(1948).withModel("Corvette")
                .withVehicleClassification(VehicleClassification.CLASSIC).build();
        System.out.println(vehicle3.toString());
        if(vehicle3.validateVehicleClassification()) {
            System.out.println("Vehicle " + vehicle3.getVehicleID() 
                    + " has the correct classification");
        } else {
            System.out.println("Vehicle " + vehicle3.getVehicleID()
                    + " has an incorrect classification");
        }
        vehicle3.setVehicleClassification(vehicle3.findVehicleClassification(
                vehicle3.getModelYear()));
        System.out.println(vehicle3.toString());
    }
}
