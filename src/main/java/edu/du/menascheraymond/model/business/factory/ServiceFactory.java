/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * ServiceFactory.java
 */
package edu.du.menascheraymond.model.business.factory;

import java.lang.reflect.Constructor;
import edu.du.menascheraymond.model.services.TypeCollectionService;

/**
 *
 * @author raymondmenasche
 */
public class ServiceFactory {
    
    public TypeCollectionService getService(String serviceClass) throws Exception {
        Class<TypeCollectionService> claz = (Class<TypeCollectionService>)Class.forName(serviceClass);
        if (claz != null) {
            Constructor<TypeCollectionService> constructor = claz.getConstructor(new Class[0]);
            TypeCollectionService service = constructor.newInstance();
            return service;
        }
        return null;
    }
}
