package edu.wit.dcsn.comp2000.queueapp;

import java.util.ArrayList;
import java.util.List;

public class Train {

    private int maxPassengers;

    private List<Passenger> passengerList = new ArrayList<>();

    private Station lastVistedStation;


    public Train(final int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public void setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    public boolean addPassenger(final Passenger passenger) {
        if(passengerList.size() >= maxPassengers) {
            return false;
        }
        passengerList.add(passenger);
        return true;
    }
}
