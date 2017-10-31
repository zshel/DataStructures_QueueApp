package edu.wit.dcsn.comp2000.queueapp;

import java.util.ArrayList;
import java.util.List;

/**@author Zachary Shelton*/
public class Train {

    private int maxPassengers;

    private List<Passenger> passengerList = new ArrayList<>();

    private Station lastVistedStation;

    private Route currentRoute;

    private int currentLocation;

    private int id;


    public Train(final int id, final int maxPassengers, final int currentLocation, final Route currentRoute) {
        this.id = id;
        this.maxPassengers = maxPassengers;
        this.currentLocation = currentLocation;
        this.currentRoute = currentRoute;
    }

    public int getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(int currentLocation) {
        this.currentLocation = currentLocation;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public Station getLastVistedStation() {
        return lastVistedStation;
    }

    public void setLastVistedStation(Station lastVistedStation) {
        this.lastVistedStation = lastVistedStation;
    }

    public Route getCurrentRoute() {
        return currentRoute;
    }

    public void setCurrentRoute(Route currentRoute) {
        this.currentRoute = currentRoute;
    }

    public void setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    public int getId() {
        return id;
    }

    public boolean addPassengers(final List<Passenger> passengers) {
        for(final Passenger p : passengers) {
            if (addPassenger(p)) {
                continue;
            }
            return  false;
        }
        return false;
    }

    public boolean addPassenger(final Passenger passenger) {
        if(passengerList.size() >= maxPassengers) {
            return false;
        }
        passengerList.add(passenger);
        return true;
    }
}
