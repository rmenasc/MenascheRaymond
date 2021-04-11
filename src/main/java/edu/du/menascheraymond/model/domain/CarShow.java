/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 */
package edu.du.menascheraymond.model.domain;

import java.time.LocalDate;
import java.util.Objects;

/**
 * CarShow Domain Class.
 * @author raymond
 */
public class CarShow {
    private String carShowID;
    private String carShowTitle;
    private LocalDate carShowDate;
    private boolean isSanctioned;
    
    /**
     * Constructors below.
     * 1 Overloaded Constructor.
     */
    public CarShow() {
        this.carShowID = "";
        this.carShowTitle = "";
        this.carShowDate = LocalDate.now();
        this.isSanctioned = false;
    }
    
    public CarShow(String carShowID, String carShowTitle, LocalDate carShowDate,
            boolean isSanctioned) {
        this.carShowID = carShowID;
        this.carShowTitle = carShowTitle;
        this.carShowDate = carShowDate;
        this.isSanctioned = isSanctioned;
    }
    
    /**
     * Builder design pattern constructor.
     */
    public static class Builder {
        private String carShowID;
        private String carShowTitle;
        private LocalDate carShowDate;
        private boolean isSanctioned;
        
        public Builder(String carShowID) {
            this.carShowID = carShowID;
        }
        
        public Builder withCarShowTitle(String carShowTitle) {
            this.carShowTitle = carShowTitle;
            
            return this;
        }
        
        public Builder withCarShowDate(LocalDate carShowDate) {
            this.carShowDate = carShowDate;
            
            return this;
        }
        
        public Builder isSanctioned(boolean isSanctioned) {
            this.isSanctioned = isSanctioned;
            
            return this;
        }
        
        public CarShow build() {
            CarShow carShow = new CarShow();
            carShow.carShowID = this.carShowID;
            carShow.carShowTitle = this.carShowTitle;
            carShow.carShowDate = this.carShowDate;
            carShow.isSanctioned = this.isSanctioned;
            
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
    public String getCarShowID() {
        return carShowID;
    }

    public String getCarShowTitle() {
        return carShowTitle;
    }

    public LocalDate getCarShowDate() {
        return carShowDate;
    }

    public boolean isSanctioned() {
        return isSanctioned;
    }

    /**
     * Setters Below.
     */
    public void setCarShowID(String carShowID) {
        this.carShowID = carShowID;
    }

    public void setCarShowTitle(String carShowTitle) {
        this.carShowTitle = carShowTitle;
    }

    public void setCarShowDate(LocalDate carShowDate) {
        this.carShowDate = carShowDate;
    }

    public void setIsSanctioned(boolean isSanctioned) {
        this.isSanctioned = isSanctioned;
    }
    
    public void setIsSanctioned(String isSanctioned) throws Exception {
        if(isSanctioned.toUpperCase().equals("Y") || 
                isSanctioned.toUpperCase().equals("YES")) {
            this.isSanctioned = true;
        } else if (isSanctioned.toUpperCase().equals("N") ||
                isSanctioned.toUpperCase().equals("NO")) {
            this.isSanctioned = false;
        } else {
            throw new Exception("Invalid input string");
        }
    }
    
    public void setIsSanctioned(char isSanctioned) throws Exception {
        if(isSanctioned == 'y' || isSanctioned == 'Y') {
            this.isSanctioned = true;
        } else if(isSanctioned == 'n' || isSanctioned == 'N') {
            this.isSanctioned = false;
        } else {
            throw new Exception("Invalid character input");
        }
    }
    
    public void setIsSanctioned(int isSanctioned) throws Exception {
        if(isSanctioned == 1) {
            this.isSanctioned = true;
        } else if(isSanctioned == 0) {
            this.isSanctioned = false;
        } else {
            throw new Exception("Invalid input integer");
        }
    }

    /**
     * Override methods Below.
     * @return 
     */
    @Override
    public String toString() {
        return "CarShow{" + "carShowID=" + carShowID +
                ", carShowTitle=" + carShowTitle +
                ", carShowDate=" + carShowDate +
                ", isSanctioned=" + isSanctioned + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.carShowID);
        hash = 71 * hash + Objects.hashCode(this.carShowTitle);
        hash = 71 * hash + Objects.hashCode(this.carShowDate);
        hash = 71 * hash + (this.isSanctioned ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CarShow other = (CarShow) obj;
        if (this.isSanctioned != other.isSanctioned) {
            return false;
        }
        if (!Objects.equals(this.carShowID, other.carShowID)) {
            return false;
        }
        if (!Objects.equals(this.carShowTitle, other.carShowTitle)) {
            return false;
        }
        if (!Objects.equals(this.carShowDate, other.carShowDate)) {
            return false;
        }
        return true;
    }

}
