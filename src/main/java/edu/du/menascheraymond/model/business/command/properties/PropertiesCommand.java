/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * PropertiesCommand.java
 */
package edu.du.menascheraymond.model.business.command.properties;

import edu.du.menascheraymond.model.business.command.Command;
import java.util.Properties;

/**
 * Interface for the Properties package.
 * @author raymondmenasche
 */
public interface PropertiesCommand extends Command {
    
    /**
     * Takes a Properties object and calls the sendCommand method.
     * @param d
     * @return True if successfully send data.
     */
    public boolean sendProperties(Properties d);
    
    /**
     * Retrieves the Properties object after calling the getCommand method.
     * @return Properties object. 
     */
    public Properties getProperties();
}
