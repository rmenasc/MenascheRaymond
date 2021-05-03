/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * Command.java
 */
package edu.du.menascheraymond.model.business.command;

import java.util.LinkedHashMap;
import java.util.List;
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
     * Retrieves a List of Maps with command type, action, and object data.
     * @return List of Maps
     */
    public List<Map<String,String>> getCommands();
    
    /**
     * Sends a command in a List of Maps collection to be sent by the API
     * @param cmns
     * @return True if successfully sent the request. 
     */
    public boolean sendCommands(List<LinkedHashMap<String,String>> cmns);
}
