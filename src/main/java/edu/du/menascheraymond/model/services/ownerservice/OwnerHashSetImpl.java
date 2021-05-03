/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 */
package edu.du.menascheraymond.model.services.ownerservice;

import edu.du.menascheraymond.model.domain.Owner;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author raymondmenasche
 */
public class OwnerHashSetImpl implements OwnerService {
    private Set<Owner> owners = new HashSet<>();

    @Override
    public boolean add(Owner owner) {
        return owners.add(owner);
    }

    @Override
    public boolean remove(Owner owner) {
        return owners.remove(owner);
    }

    @Override
    public boolean remove(String ownerId) {
        boolean rv = false;
        for (Owner o: owners) {
            if (o.getOwnerId().equals(ownerId)) {
                rv = owners.remove(o);
                break;
            }
        }
        return rv;
    }

    @Override
    public Owner find(String ownerId) {
        Owner rv = null;
        for (Owner o: owners) {
            if (o.getOwnerId().equals(ownerId)) {
                rv = o;
                break;
            }
        }
        return rv;
    }

    @Override
    public boolean isPresent(Owner owner) {
        return owners.contains(owner);
    }

    @Override
    public boolean isPresent(String ownerId) {
        boolean rv = false;
        for (Owner o: owners) {
            if (o.getOwnerId().equals(ownerId)) {
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
        return owners.size();
    }

    @Override
    public void dump() {
        for (Owner o: owners) {
            System.out.println(o.toString());
        }
    }
    
}
