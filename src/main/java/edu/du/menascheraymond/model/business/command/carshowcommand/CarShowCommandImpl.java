/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 * File: CarShowCommandImpl.java
 */
package edu.du.menascheraymond.model.business.command.carshowcommand;

import edu.du.menascheraymond.model.business.manager.Manager;
import edu.du.menascheraymond.model.domain.CarShow;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * Implementation of the CarShowCommand interface.
 * @author raymondmenasche
 */
public class CarShowCommandImpl implements CarShowCommand {
    private final String COMMANDTYPE = "CARSHOW";  
    private Manager manager;

    @Override
    public CarShow add(Map<String,String> command) {
        CarShow rv = null;
        //Check if array contains all required attributes
        if (command.size() == 6 && command.containsKey("carShowId")) {
            String id = command.get("carShowId");
            String title = command.get("title");                           //Title
            String strDate = command.get("date").strip();                  //String date ex. 12/12/2020 or 2020-12-12
            LocalDate date = null;
            if (strDate.contains("-")) {
                date = LocalDate.parse(strDate);
            } else if (strDate.contains("/")) {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                date = LocalDate.parse(strDate, format);
            }
            String sanctioned = command.get("sanctioned").strip();              //Sanctioned: Y/Yes/N/No
            CarShow carShow = new CarShow.Builder(id, title)
                    .withCarShowDate(date).build();
            carShow.setSanctioned(sanctioned);
            if (manager.getCarShowCollection().add(carShow)) {
                rv = carShow;
            }
        } else if (command.size() == 6 && command.containsKey("VALUE1")) {
            //for non key=value data
            String id = command.get("VALUE1");
            String title = command.get("VALUE2");                           //Title
            String strDate = command.get("VALUE3").strip();                  //String date ex. 12/12/2020 or 2020-12-12
            LocalDate date = null;
            if (strDate.contains("-")) {
                date = LocalDate.parse(strDate);
            } else if (strDate.contains("/")) {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                date = LocalDate.parse(strDate, format);
            }
            String sanctioned = command.get("VALUE4").strip();              //Sanctioned: Y/Yes/N/No
            CarShow carShow = new CarShow.Builder(id, title)
                    .withCarShowDate(date).build();
            carShow.setSanctioned(sanctioned);
            if (manager.getCarShowCollection().add(carShow)) {
                rv = carShow;
            }
        } else {
            throw new NullPointerException("Error creating array. Size mismatch");
        }
        return rv;
    }

    @Override
    public CarShow remove(Map<String,String> command) {
        String id = "";
        if (command.containsKey("carShowId")) {
            id = command.get("carShowId");
        } else if (command.containsKey("VALUE1")) {
            // for non key=value data
            id = command.get("VALUE1");
        }
        CarShow rv = manager.getCarShowCollection().find(id);
        //Removes CarShow Object
        if (manager.getCarShowCollection().remove(id)) {
            //If successfully removed CarShow Object, removes
            //CarShowOwner objects with matching CarShowId.
            manager.getCarShowOwnerCollection().removeByCarShowId(id);
        } else {
            System.out.println("Error removing CarShow: " + id);
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
            CarShow c = add(cmd);
            if (c != null) {
                rv = true;
            }
        }
        if (cmd.get("ACTION").equalsIgnoreCase("REM")) {
            CarShow c = remove(cmd);
            if (c != null) {
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
