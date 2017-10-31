package edu.wit.dcsn.comp2000.queueapp;

/**@author Dan Adler, Zachary Shelton*/
public class Passenger {

    private Station destination;

    private Status status;

    public Passenger(final Station destination, final Status status) {
        this.destination = destination;
        this.status = status;
    }

    public Station getDestination() {
        return destination;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
enum Status {
    ON_TRAIN, IN_STATION;
}
