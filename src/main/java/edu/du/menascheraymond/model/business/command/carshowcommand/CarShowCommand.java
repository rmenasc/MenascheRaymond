/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 * File: CarShowCommand.java
 */
package edu.du.menascheraymond.model.business.command.carshowcommand;

import edu.du.menascheraymond.model.business.command.Command;
import edu.du.menascheraymond.model.domain.CarShow;
import java.util.Map;

/**
 * Interface for the CarShow Commands.
 * @author raymondmenasche
 */
public interface CarShowCommand extends Command {
    
    /**
     * Performs ADD command to insert CarShow object to collection.
     * @param command
     * @return CarShow object if successful, null if not.
     */
    public CarShow add(Map<String,String> command);
    
    /**
     * Performs REM command to remove a CarShow object from collection.
     * @param command
     * @return CarShow object if successful, null if not.
     */
    public CarShow remove(Map<String,String> command);
}
