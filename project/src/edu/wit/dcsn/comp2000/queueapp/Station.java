package edu.wit.dcsn.comp2000.queueapp;

import com.pearson.carrano.ArrayQueue;
import com.pearson.carrano.QueueInterface;

public class Station {

    private QueueInterface<Passenger> passengerQueue = new ArrayQueue<>();

    private int id;

    public Station(final int id) {
        this.id = id;
    }
}
