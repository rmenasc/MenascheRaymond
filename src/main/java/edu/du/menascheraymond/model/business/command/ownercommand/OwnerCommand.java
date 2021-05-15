/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 * File: OwnerCommand.java
 */
package edu.du.menascheraymond.model.business.command.ownercommand;

import edu.du.menascheraymond.model.business.command.Command;
import edu.du.menascheraymond.model.domain.Owner;
import java.util.Map;

/**
 * Interface for Owner Commands. 
 * @author raymondmenasche
 */
public interface OwnerCommand extends Command {
    
    /**
     * Performs ADD command to insert Owner object to Collection.
     * @param command
     * @return Owner object if successful, null if not. 
     */
    public Owner add(Map<String,String> command);
    
    /**
     * Performs REM command to remove an Owner object from Collection.
     * @param command
     * @return Owner object if successful, null if not.
     */
    public Owner remove(Map<String,String> command);
}
