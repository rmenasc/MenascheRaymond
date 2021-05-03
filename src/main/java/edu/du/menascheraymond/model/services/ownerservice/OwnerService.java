/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 */
package edu.du.menascheraymond.model.services.ownerservice;

import edu.du.menascheraymond.model.domain.Owner;
import edu.du.menascheraymond.model.services.Service;

/**
 *
 * @author raymondmenasche
 */
public interface OwnerService extends Service<Owner> {
    /**
     * Adds a Owner object to the Collection.
     * @param owner
     * @return False if item was not added to collection.
     */
    public boolean add(Owner owner);
    
    /**
     * Removes the CarShowOwner object from the collection.
     * @param owner
     * @return False if the item was not present or removed.
     */
    public boolean remove(Owner owner);
    
    /**
     * Removes Owner object by ownerId.
     * @param ownerId
     * @return False if item not in collection.
     */
    public boolean remove(String ownerId);
    
    /**
     * Retrieves Owner object from collection by ownerId.
     * @param ownerId
     * @return Owner object.
     */
    public Owner find(String ownerId);
    
    /**
     * Checks if Owner object exists in collection.
     * @param owner
     * @return True if present in collection.
     */
    public boolean isPresent(Owner owner);
    
    /**
     * Checks if Owner object exists by matching ownerId.
     * @param ownerId
     * @return True if object is present in collection.
     */
    public boolean isPresent(String ownerId);
    
}
