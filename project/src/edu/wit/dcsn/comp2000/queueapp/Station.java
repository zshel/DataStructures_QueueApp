package edu.wit.dcsn.comp2000.queueapp;

import java.util.ArrayList;
import java.util.List;

import com.pearson.carrano.ArrayQueue;
import com.pearson.carrano.QueueInterface;

public class Station {
	
	private QueueInterface<Passenger> IncomingQueue = new ArrayQueue<>();
    
    private QueueInterface<Passenger> OutgoingQueue = new ArrayQueue<>();
    
	
    private int Stationid;

    public Station(final int id) {
        this.Stationid = id;
    }
    
    public void enterStation(Passenger passenger){
    	IncomingQueue.enqueue(passenger);
    }
    
    public void leaveStation(Train passenger){
    	OutgoingQueue.dequeue();
    }
    
}
