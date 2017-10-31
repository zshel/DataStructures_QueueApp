package edu.wit.dcsn.comp2000.queueapp;


import com.pearson.carrano.QueueInterface;

/**@author Eddie Lorenzana*/
public class Station {


    private QueueInterface<Passenger> passengerQueue = new ArrayQueue<>();
	
    private int id;

    private int location;

    public Station(int id, int location) {
        this.id = id;
        this.location = location;
    }

    public QueueInterface<Passenger> getPassengerQueue() {
        return passengerQueue;
    }

    public void dequeueToTrain(Train train) {
        while(!passengerQueue.isEmpty()) {
            Passenger passenger = passengerQueue.dequeue();
            passenger.setStatus(Status.ON_TRAIN);
            if(!train.addPassenger(passenger)) {
                System.out.println("Max people reached in train!");
                break;
            }
        }
    }


    public int getId() {
        return id;
    }

    public int getLocation() {
        return location;
    }
}
