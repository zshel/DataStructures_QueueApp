package edu.wit.dcsn.comp2000.queueapp;

public class Train {

    private int maxPassengers;


    public Train(final int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public void setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }
}
