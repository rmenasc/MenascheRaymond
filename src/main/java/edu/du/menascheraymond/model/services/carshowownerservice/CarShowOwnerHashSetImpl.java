/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 */
package edu.du.menascheraymond.model.services.carshowownerservice;

import edu.du.menascheraymond.model.domain.CarShowOwner;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author raymondmenasche
 */
public class CarShowOwnerHashSetImpl implements CarShowOwnerService {
    private Set<CarShowOwner> carShowOwners = new HashSet<>();

    @Override
    public boolean add(CarShowOwner carShowOwner) {
        return carShowOwners.add(carShowOwner);
    }

    @Override
    public boolean remove(CarShowOwner carShowOwner) {
        return carShowOwners.remove(carShowOwner);
    }

    @Override
    public boolean remove(String ownerId, String carShowId) {
        boolean rv = false;
        for (CarShowOwner cso: carShowOwners) {
            if (cso.getOwnerId().equals(ownerId) && cso.getCarShowId()
                    .equals(carShowId)) {
                rv = carShowOwners.remove(cso);
                break;
            }
        }
        return rv;
    }

    @Override
    public boolean removeByOwnerId(String ownerId) {
        boolean rv = false;
        Iterator<CarShowOwner> i = carShowOwners.iterator();
        while (i.hasNext()) {
            CarShowOwner cso = i.next();
            if (cso.getOwnerId().equals(ownerId)) {
                i.remove();
                rv = true;
            }
        }
        return rv;
    }

    @Override
    public boolean removeByCarShowId(String carShowId) {
        boolean rv = false;
        Iterator<CarShowOwner> i = carShowOwners.iterator();
        while (i.hasNext()) {
            CarShowOwner cso = i.next();
            if (cso.getCarShowId().equals(carShowId)) {
                i.remove();
                rv = true;
            }
        }
        return rv;
    }

    @Override
    public CarShowOwner find(String ownerId, String carShowId) {
        CarShowOwner rv = null;
        for (CarShowOwner cso: carShowOwners) {
            if (cso.getOwnerId().equals(ownerId) && cso.getCarShowId()
                    .equals(carShowId)) {
                rv = cso;
                break;
            }
        }
        return rv;
    }

    @Override
    public boolean isPresent(CarShowOwner carShowOwner) {
        return carShowOwners.contains(carShowOwner);
    }

    @Override
    public boolean isPresent(String ownerId, String carShowId) {
        boolean rv = false;
        for (CarShowOwner cso: carShowOwners) {
            if (cso.getOwnerId().equals(ownerId) && cso.getCarShowId()
                    .equals(carShowId)) {
                rv = true;
                break;
            }
        }
        return rv;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public int size() {
        return carShowOwners.size();
    }

    @Override
    public void dump() {
        for (CarShowOwner cso: carShowOwners) {
            System.out.println(cso.toString());
        }
    }
    
}
