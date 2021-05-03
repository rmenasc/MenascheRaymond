/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * ServiceFactory.java
 */
package edu.du.menascheraymond.model.business.factory;

import edu.du.menascheraymond.model.services.Service;
import java.lang.reflect.Constructor;

/**
 *
 * @author raymondmenasche
 */
public class ServiceFactory {
    
    public Service getService(String serviceClass) throws Exception {
        Class<Service> claz = (Class<Service>)Class.forName(serviceClass);
        if (claz != null) {
            Constructor<Service> constructor = claz.getConstructor(new Class[0]);
            Service service = constructor.newInstance();
            return service;
        }
        return null;
    }
}
