/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 */
package edu.du.menascheraymond.model.services.vehicleservice;

import edu.du.menascheraymond.model.domain.Vehicle;
import edu.du.menascheraymond.model.services.TypeCollectionService;
import java.util.List;

/**
 *
 * @author raymondmenasche
 */
public interface VehicleService extends TypeCollectionService<Vehicle> {
    /**
     * Adds a Vehicle object to the Collection.
     * @param vehicle
     * @return False if item was not added to collection.
     */
    public boolean add(Vehicle vehicle);
    
    /**
     * Removes the Vehicle object from the collection.
     * @param vehicle
     * @return False if the item was not present or removed.
     */
    public boolean remove(Vehicle vehicle);
    
    /**
     * Removes Vehicle by vehicleId.
     * @param vehicleId
     * @return False if item not in collection.
     */
    public boolean remove(String vehicleId);
    
    /**
     * Removes all Vehicle objects from collection with matching ownerId
     * @param ownerId
     * @return False if no objects were removed.
     */
    public boolean removeByOwnerId(String ownerId);
    
    /**
     * Retrieves Vehicle object from collection by vehicleId.
     * @param vehicleId
     * @return Vehicle object.
     */
    public Vehicle find(String vehicleId);
    
    /**
     * Checks if Vehicle object exists in collection.
     * @param vehicle
     * @return True if present in collection.
     */
    public boolean isPresent(Vehicle vehicle);
    
    /**
     * Checks if Vehicle object exists by vehicleId.
     * @param vehicleId
     * @return True if object is present in collection.
     */
    public boolean isPresent(String vehicleId);
    
    /**
     * Retrieves a List of all the Vehicle object's ids.
     * @return ArrayList of Strings. 
     */
    public List<String> getIds();
}
