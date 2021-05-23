/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * MainMethod.java
 */
package edu.du.menascheraymond;

import edu.du.menascheraymond.model.business.command.Command;
import edu.du.menascheraymond.model.business.factory.CommandFactory;
import edu.du.menascheraymond.model.business.factory.CommandFactoryImpl;
import edu.du.menascheraymond.model.business.manager.Manager;
import edu.du.menascheraymond.model.domain.Address;
import edu.du.menascheraymond.model.domain.CarShow;
import edu.du.menascheraymond.model.domain.Owner;
import edu.du.menascheraymond.model.domain.Vehicle;
import edu.du.menascheraymond.model.services.PersistenceService;
import edu.du.menascheraymond.view.MainMenuFrame;
import java.io.FileWriter;
import java.io.IOException;
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
    private static Address address = null;
    
    public static void main(String[] args) {
        //Store all the lines from CarShowData.txt and stores them in fileReaderLines
        //ArrayList.
//        readData();
//        
//        //TODO: Change this.
//        manager.getOwnerCollection().dump();
//        manager.getVehicleCollection().dump();
//        manager.getCarShowCollection().dump();
//        manager.getCarShowOwnerCollection().dump();
//        
//        
//        setFileWriterLines();
//        writeToFile();
        try {
            MainMenuFrame.runApplication();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void readData() {
        PersistenceService ps = manager.getPersistenceService();
        List<Map<String,String>> commands = ps.getCommands();
        for (Map<String,String> cmd: commands) {
            
            CommandFactory cf = new CommandFactoryImpl();
            Command command = cf.getCommand(cmd.get("TYPE"));
            if (command != null) {
                command.setManager(manager);
                command.performCommands(cmd);
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
    
    public static void setFileWriterLines() {
        
        for (String oID: owners) {
            if (manager.getOwnerCollection().isPresent(oID)) {
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
                    if (v.getOwnerId().equals(oID)) {
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
}
