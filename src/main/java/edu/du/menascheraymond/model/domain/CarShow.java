/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * Author: Raymond G Menasche
 * File: CarShow.java
 */
package edu.du.menascheraymond.model.domain;

import java.time.LocalDate;
import java.util.Objects;

/**
 * CarShow Domain Class.
 * @author raymond
 */
public class CarShow {
    private String carShowId;     // required
    private String carShowTitle;  // optional
    private LocalDate carShowDate;// required - default date of creation
    private boolean sanctioned;   // required - default false
    
    /**
     * Constructor.
     */
    public CarShow() {
        carShowId = "";
        carShowTitle = "";
        carShowDate = LocalDate.now();
        sanctioned = false;
    }
    
    /**
     * Overloaded Constructor.
     * @param carShowId
     * @param carShowTitle
     * @param carShowDate
     * @param isSanctioned 
     */
    public CarShow(String carShowId, String carShowTitle, LocalDate carShowDate,
            boolean isSanctioned) {
        this.carShowId = carShowId;
        this.carShowTitle = carShowTitle;
        this.carShowDate = carShowDate;
        this.sanctioned = isSanctioned;
    }
    
    /**
     * Builder design pattern constructor.
     */
    public static class Builder {
        private String carShowId;
        private String carShowTitle;
        private LocalDate carShowDate;
        private boolean sanctioned;
        
        /**
         * Builder method for CarShow class.
         * Requires: carShowId.
         * Optional: carShowTitle, carShowDate, isSanctioned.
         * @param carShowId String
         * @param carShowTitle String
         */
        public Builder(String carShowId, String  carShowTitle) {
            this.carShowId = carShowId;
            this.carShowTitle = carShowTitle;
        }
        
        public Builder withCarShowDate(LocalDate carShowDate) {
            this.carShowDate = carShowDate;
            
            return this;
        }
        
        public Builder isSanctioned(boolean isSanctioned) {
            this.sanctioned = isSanctioned;
            
            return this;
        }
        
        public CarShow build() {
            CarShow carShow = new CarShow();
            carShow.carShowId = this.carShowId;
            carShow.carShowTitle = this.carShowTitle;
            if (this.carShowDate == null) {
                carShow.carShowDate = LocalDate.now();
            } else {
                carShow.carShowDate = this.carShowDate;
            }
            carShow.sanctioned = this.sanctioned;
            
            return carShow;
        }
    }

    /**
     * Methods Below.
     * 0 methods.
     */
    
    
    
    /**
     * Getters Below.
     */
    public String getCarShowId() {
        return carShowId;
    }

    public String getCarShowTitle() {
        return carShowTitle;
    }

    public LocalDate getCarShowDate() {
        return carShowDate;
    }

    public boolean isSanctioned() {
        return sanctioned;
    }

    /**
     * Setters Below.
     */
    public void setCarShowId(String carShowId) {
        this.carShowId = carShowId;
    }

    public void setCarShowTitle(String carShowTitle) {
        this.carShowTitle = carShowTitle;
    }

    public void setCarShowDate(LocalDate carShowDate) {
        this.carShowDate = carShowDate;
    }

    public void setSanctioned(boolean isSanctioned) {
        this.sanctioned = isSanctioned;
    }
    
    public void setSanctioned(String isSanctioned) {
        if(isSanctioned.toUpperCase().equals("Y") || 
                isSanctioned.toUpperCase().equals("YES")) {
            this.sanctioned = true;
        } else if (isSanctioned.toUpperCase().equals("N") ||
                isSanctioned.toUpperCase().equals("NO")) {
            this.sanctioned = false;
        } else {
            throw new IllegalArgumentException("Invalid input string");
        }
    }
    
    public void setSanctioned(char isSanctioned) {
        if(isSanctioned == 'y' || isSanctioned == 'Y') {
            this.sanctioned = true;
        } else if(isSanctioned == 'n' || isSanctioned == 'N') {
            this.sanctioned = false;
        } else {
            throw new IllegalArgumentException("Invalid character input");
        }
    }
    
    public void setSanctioned(int isSanctioned) {
        if(isSanctioned == 1) {
            this.sanctioned = true;
        } else if(isSanctioned == 0) {
            this.sanctioned = false;
        } else {
            throw new IllegalArgumentException("Invalid input integer");
        }
    }

    /**
     * Override methods Below.
     * @return 
     */
    @Override
    public String toString() {
        return "CarShow{" + "carShowID=" + carShowId +
                ", carShowTitle=" + carShowTitle +
                ", carShowDate=" + carShowDate +
                ", isSanctioned=" + sanctioned + '}';
    }

    @Override
    public int hashCode() {
        int hash = Objects.hash(carShowId, carShowTitle, carShowDate, sanctioned);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        boolean rv = false;
        if(obj instanceof CarShow) {
            CarShow c = (CarShow)obj;
            if(c.getCarShowId().equals(carShowId) 
                    && c.getCarShowTitle().equals(carShowTitle)
                    && c.getCarShowDate().equals(carShowDate)
                    && c.isSanctioned() == sanctioned) {
                rv = true;
            }
        }
        return rv;
    }

}
