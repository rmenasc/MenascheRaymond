/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 * File: VehicleCommand.java
 */
package edu.du.menascheraymond.model.business.command.vehiclecommand;

import edu.du.menascheraymond.model.business.command.Command;
import edu.du.menascheraymond.model.domain.Vehicle;
import java.util.Map;

/**
 * Vehicle Command interface
 * @author raymondmenasche
 */
public interface VehicleCommand extends Command {
    
    /**
     * Performs ADD command to insert Vehicle object into collection.
     * @param command
     * @return Vehicle object if successful, null if not.
     */
    public Vehicle add(Map<String,String> command);
    
    /**
     * Performs REM command to remove a Vehicle object from collection.
     * @param command
     * @return Vehicle object if successful, null if not.
     */
    public Vehicle remove(Map<String,String> command);
}
