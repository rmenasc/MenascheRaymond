/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * Manager.java
 */
package edu.du.menascheraymond.model.business.manager;

import edu.du.menascheraymond.model.business.command.Command;
import edu.du.menascheraymond.model.business.factory.CommandFactory;
import edu.du.menascheraymond.model.business.factory.ServiceFactory;
import edu.du.menascheraymond.model.services.carshowownerservice.CarShowOwnerService;
import edu.du.menascheraymond.model.services.carshowservice.CarShowService;
import edu.du.menascheraymond.model.services.ownerservice.OwnerService;
import edu.du.menascheraymond.model.services.vehicleservice.VehicleService;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author raymondmenasche
 */
public class Manager {
    private ServiceFactory serviceFactory = new ServiceFactory();
    private CommandFactory commandFactory = new CommandFactory();
    private Properties serviceConfiguration;
    private Properties commandConfiguration;
    private OwnerService ownerCollection;
    private VehicleService vehicleCollection;
    private CarShowService carShowCollection;
    private CarShowOwnerService carShowOwnerCollection;
    private Command command;
    
    public Manager() {
        try (FileReader reader = new FileReader("target/serviceproperties.txt"))
        {
            serviceConfiguration = new Properties();
            serviceConfiguration.load(reader);
            
            ownerCollection = (OwnerService)serviceFactory.getService
                    (serviceConfiguration.getProperty("OwnerService"));
            
            vehicleCollection = (VehicleService)serviceFactory.getService
                    (serviceConfiguration.getProperty("VehicleService"));
            
            carShowCollection = (CarShowService)serviceFactory.getService
                    (serviceConfiguration.getProperty("CarShowService"));
            
            carShowOwnerCollection = (CarShowOwnerService)serviceFactory.getService
                    (serviceConfiguration.getProperty("CSOService"));
            
            
        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        try (FileReader reader = new FileReader("target/commandproperties.txt"))
        {
            commandConfiguration = new Properties();
            commandConfiguration.load(reader);
            command = commandFactory.getCommand(commandConfiguration.getProperty
                ("command"));
        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public OwnerService getOwnerCollection() {
        return ownerCollection;
    }

    public VehicleService getVehicleCollection() {
        return vehicleCollection;
    }

    public CarShowService getCarShowCollection() {
        return carShowCollection;
    }

    public CarShowOwnerService getCarShowOwnerCollection() {
        return carShowOwnerCollection;
    }
    
    public Command getCommand() {
        return command;
    }
}
