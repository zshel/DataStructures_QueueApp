package edu.wit.dcsn.comp2000.queueapp.config;

/**@author Zachary Shelton*/
public class ConfigImpl implements Config {

    @ConfigTarget(name = "ticks")
    private int ticks;

    @ConfigTarget(name = "seed")
    private long seed;

    @ConfigTarget(name = "routeLength")
    private int routeLength;

    @ConfigTarget(name = "routeStyle")
    private String routeStyle;

    @ConfigTarget(name = "stationsAt")
    private int[] stationsAt;

    @ConfigTarget(name = "trainsAt")
    private String[] trainsAt;

    @ConfigTarget(name = "trainCapacity")
    private int trainCapacity;

    @ConfigTarget(name = "initialPassengers")
    private int initialPassengers;

    @ConfigTarget(name = "initialPassengersList")
    private String[] initialPassengersList;

    @ConfigTarget(name = "iterationPassengers")
    private int iterationPassengers;

    public int getTicks() {
        return ticks;
    }

    public long getSeed() {
        return seed;
    }

    public int getRouteLength() {
        return routeLength;
    }

    public String getRouteStyle() {
        return routeStyle;
    }

    public int[] getStationsAt() {
        return stationsAt;
    }

    public String[] getTrainsAt() {
        return trainsAt;
    }

    public int getTrainCapacity() {
        return trainCapacity;
    }

    public int getInitialPassengers() {
        return initialPassengers;
    }

    public int getIterationPassengers() {
        return iterationPassengers;
    }

    public String[] getInitialPassengersList() {
        return initialPassengersList;
    }
}
