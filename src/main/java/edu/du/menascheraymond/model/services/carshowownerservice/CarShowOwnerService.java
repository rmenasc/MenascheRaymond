/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * CarShowOwnerService.java
 */
package edu.du.menascheraymond.model.services.carshowownerservice;

import edu.du.menascheraymond.model.domain.CarShowOwner;
import edu.du.menascheraymond.model.services.Service;

/**
 * Interface for the CarShowOwner service.
 * @author raymondmenasche
 */
public interface CarShowOwnerService extends Service<CarShowOwner> {
    
    /**
     * Adds a CarShowOwner object to the Collection.
     * @param carShowOwner
     * @return False if item was not added to collection.
     */
    @Override
    public boolean add(CarShowOwner carShowOwner);
    
    /**
     * Removes the CarShowOwner object from the collection.
     * @param carShowOwner
     * @return False if the item was not present or removed.
     */
    @Override
    public boolean remove(CarShowOwner carShowOwner);
    
    /**
     * Removes CarShowOwner object by ownerId and carShowId.
     * @param ownerId
     * @param carShowId
     * @return False if item not in collection.
     */
    public boolean remove(String ownerId, String carShowId);
    
    /**
     * Removes all CarShowOwner objects with matching ownerId.
     * @param ownerId
     * @return False if no CarShowOwner objects have ownerId.
     */
    public boolean removeByOwnerId(String ownerId);
    
    /**
     * Removes all CarShowOwner object with matching carShowId.
     * @param carShowId
     * @return False if no CarShowOwner objects have carShowId.
     */
    public boolean removeByCarShowId(String carShowId);
    
    /**
     * Retrieves CarShowOwner object from collection by ownerId and carShowId.
     * @param ownerId
     * @param carShowId
     * @return CarShowOwner object.
     */
    public CarShowOwner find(String ownerId, String carShowId);
    
    /**
     * Checks if carShowOwner object exists in collection.
     * @param carShowOwner
     * @return True if present in collection.
     */
    @Override
    public boolean isPresent(CarShowOwner carShowOwner);
    
    /**
     * Checks if CarShow Owner object exists by matching ownerId and carShowId.
     * @param ownerId
     * @param carShowId
     * @return True if object is present in collection.
     */
    public boolean isPresent(String ownerId, String carShowId);
    
}
