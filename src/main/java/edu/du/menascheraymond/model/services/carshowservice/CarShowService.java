/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 */
package edu.du.menascheraymond.model.services.carshowservice;

import edu.du.menascheraymond.model.domain.CarShow;
import edu.du.menascheraymond.model.services.TypeCollectionService;

/**
 *
 * @author raymondmenasche
 */
public interface CarShowService extends TypeCollectionService<CarShow> {
    /**
     * Adds a CarShowOwner object to the Collection.
     * @param carShow
     * @return False if item was not added to collection.
     */
    @Override
    public boolean add(CarShow carShow);
    
    /**
     * Removes the CarShowOwner object from the collection.
     * @param carShow
     * @return False if the item was not present or removed.
     */
    @Override
    public boolean remove(CarShow carShow);
    
    /**
     * Removes CarShow object by ownerId and carShowId.
     * @param carShowId
     * @return False if item not in collection.
     */
    public boolean remove(String carShowId);
    
    /**
     * Retrieves CarShow object from collection by carShowId.
     * @param carShowId
     * @return CarShow object.
     */
    public CarShow find(String carShowId);
    
    /**
     * Checks if carShow object exists in collection.
     * @param carShow
     * @return True if present in collection.
     */
    @Override
    public boolean isPresent(CarShow carShow);
    
    /**
     * Checks if CarShow object exists by matching carShowId.
     * @param carShowId
     * @return True if object is present in collection.
     */
    public boolean isPresent(String carShowId);
    
}
