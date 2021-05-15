/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * PropertiesService.java
 */
package edu.du.menascheraymond.model.services.properties;

import edu.du.menascheraymond.model.services.PersistenceService;
import java.util.Properties;

/**
 * Interface for Properties type services. 
 * @author raymondmenasche
 */
public interface PropertiesService extends PersistenceService {
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
