/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * Command.java
 */
package edu.du.menascheraymond.model.business.command;

import edu.du.menascheraymond.model.business.manager.Manager;
import java.util.Map;

/**
 * Command interface to handle input and output from different sources. 
 * @author raymondmenasche
 */
public interface Command {
    
    /**
     * Name of the command class.
     * @return String with fully qualified class name.
     */
    public String getName();
    
    /**
     * Retrieves a the type of command.
     * @return String name of the type of command.
     */
    public String getCommandType();
    
    /**
     * Takes a command as a LinkedHashMap collection to be to be processed
     * by the implemented class.
     * @param cmd
     * @return True if successfully processed the request. 
     */
    public boolean performCommands(Map<String,String> cmd);
    
    /**
     * Injects the manager object to the Command
     * @param manager 
     */
    public void setManager(Manager manager);
}
