/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 */
package edu.du.menascheraymond.model.services.carshowservice;

import edu.du.menascheraymond.model.domain.CarShow;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author raymondmenasche
 */
public class CarShowHashSetImpl implements CarShowService {
    private Set<CarShow> carShows = new HashSet<>();

    @Override
    public boolean add(CarShow carShow) {
        return carShows.add(carShow);
    }

    @Override
    public boolean remove(CarShow carShow) {
        return carShows.remove(carShow);
    }

    @Override
    public boolean remove(String carShowId) {
        boolean rv = false;
        Iterator<CarShow> i = carShows.iterator();
        while (i.hasNext()) {
            CarShow c = i.next();
            if (c.getCarShowId().equals(carShowId)) {
                i.remove();
                rv = true;
            }
        }
        return rv;
    }

    @Override
    public CarShow find(String carShowId) {
        CarShow rv = null;
        for (CarShow c: carShows) {
            if (c.getCarShowId().equals(carShowId)) {
                rv = c;
                break;
            }
        }
        return rv;
    }

    @Override
    public boolean isPresent(CarShow carShow) {
        return carShows.contains(carShow);
    }

    @Override
    public boolean isPresent(String carShowId) {
        boolean rv = false;
        for (CarShow c: carShows) {
            if (c.getCarShowId().equals(carShowId)) {
                rv = true;
                break;
            }
        }
        return rv;
    }

    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public int size() {
        return carShows.size();
    }

    @Override
    public void dump() {
        for (CarShow c: carShows) {
            System.out.println(c.toString());
        }
    }

    @Override
    public List<String> getIds() {
        List<String> rv = new ArrayList<>();
        for (CarShow cs: carShows) {
            rv.add(cs.getCarShowId());
        }
        return rv;
    }
    
}
