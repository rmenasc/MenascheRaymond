/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 * File: CarShowOwnerCommand.java
 */
package edu.du.menascheraymond.model.business.command.carshowownercommand;

import edu.du.menascheraymond.model.business.command.Command;
import edu.du.menascheraymond.model.domain.CarShowOwner;
import java.util.Map;

/**
 * Interface for CarShowOwner commands.
 * @author raymondmenasche
 */
public interface CarShowOwnerCommand extends Command {
    
    /**
     * Performs ADD command to insert a CarShowOwner object to collection.
     * @param command
     * @return CarShowOwner object if successful, null if not.
     */
    public CarShowOwner add(Map<String,String> command);
    
    /**
     * Performs REM command to remove a CarShowOwner object from collection.
     * @param command
     * @return CarShowOwner if successful, null if not.
     */
    public CarShowOwner remove(Map<String,String> command);
}
