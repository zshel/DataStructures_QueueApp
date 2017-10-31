package edu.wit.dcsn.comp2000.queueapp;

import edu.wit.dcsn.comp2000.queueapp.config.ConfigHandler;
import edu.wit.dcsn.comp2000.queueapp.config.ConfigImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Dan Adler, Zachary Shelton, Eddie Lorenzana
 */
public class Main {

    /**/
    public static void main(final String... args) throws IOException, IllegalAccessException {
        final File logFile = new File("out.log");
        if (!logFile.exists()) {
            logFile.createNewFile();
        }
        final FileOutputStream fileOutputStream = new FileOutputStream(logFile);
        final SplitOutputStream splitOutputStream = new SplitOutputStream(System.out, fileOutputStream);
        final PrintStream printStream = new PrintStream(splitOutputStream);
        System.setOut(printStream);
        final ConfigImpl config = (ConfigImpl) ConfigHandler.loadConfig(new File("TrainSimulation.config"), new ConfigImpl());
        System.out.println(String.format("Ticks: %d", config.getTicks()));
        final Route inboundRoute = new Route("inbound", config.getRouteLength());
        final Route outboundRoute = new Route("outbound", config.getRouteLength());
        final List<Train> trains = new ArrayList<>();
        final int[] locations = new int[config.getStationsAt().length];
        for (int i = 0; i < config.getStationsAt().length; i++) {
            final int location = config.getStationsAt()[i];
            locations[i] = location;
        }
        final int[] locationsSorted = doSort(locations);
        int id = 1;
        for (final int i : locationsSorted) {
            System.out.println(String.format("Added station %d to inbound with location %d", id, i));
            inboundRoute.getStations().add(new Station(id, i));
            id++;
        }
        for (int i = inboundRoute.getStations().size() - 1; i >= 0; i--) {
            final Station station = inboundRoute.getStations().get(i);
            final Station outboundStation = new Station(station.getId(), config.getRouteLength() - station.getLocation());
            System.out.println(String.format("Added station %d to outbound with location %d", outboundStation.getId(), outboundStation.getLocation()));
            outboundRoute.getStations().add(outboundStation);
        }
        int index = -1;
        for (final String s : config.getTrainsAt()) {
            final String[] split = s.split("/");
            final int capacity = split.length >= 3 ? Integer.parseInt(split[2]) : config.getTrainCapacity();
            index++;
            if (split[1].equals("in")) {
                trains.add(new Train(index, capacity, Integer.parseInt(split[0]), inboundRoute));
                continue;
            }
            if (split[1].equals("out")) {
                trains.add(new Train(index, capacity, Integer.parseInt(split[0]), outboundRoute));
            }
        }
        for (final Train t : trains) {
            System.out.println(String.format("[%s] %d,%d", t.getCurrentRoute().getName(), t.getCurrentLocation(), t.getMaxPassengers()));
        }
        final Random random = new Random(config.getSeed());
        populateStation(config.getInitialPassengers(), random, inboundRoute, outboundRoute);
        for (int i = 0; i < config.getTicks(); i++) {
            for (final Train t : trains) {
                if (t.getCurrentLocation() == t.getCurrentRoute().getLength()) {
                    if (t.getCurrentRoute().getName().equals("inbound")) {
                        System.out.println(String.format("Train %d reached end of inbound, switching to outbound", t.getId()));
                        t.setCurrentRoute(outboundRoute);
                        t.setCurrentLocation(0);
                    } else {
                        System.out.println(String.format("Train %d reached end of outbound, switching to inbound", t.getId()));
                        t.setCurrentRoute(inboundRoute);
                        t.setCurrentLocation(0);
                    }
                } else {
                    t.setCurrentLocation(t.getCurrentLocation() + 1);
                }
                for (final Station s : t.getCurrentRoute().getStations()) {
                    if (t.getCurrentLocation() != s.getLocation()) {
                        continue;
                    }
                    System.out.println(String.format("Train %d reached station %d on route: %s", t.getId(), s.getId(), t.getCurrentRoute().getName()));
                    final List<Passenger> tempList = new ArrayList<>();
                    for (final Passenger p : t.getPassengerList()) {
                        if (p.getDestination().getId() != s.getId()) {
                            continue;
                        }
                        tempList.add(p);

                    }
                    if (tempList.size() > 0) {
                        System.out.println(String.format("%d passenger(s) with the destination %d removed!", tempList.size(), s.getId()));
                    }
                    t.getPassengerList().removeAll(tempList);
                    s.dequeueToTrain(t);
                    t.setLastVistedStation(s);
                }
            }
            populateStation(config.getIterationPassengers(), random, inboundRoute, outboundRoute);
//            populateStation(config.getIterationPassengers(), random, outboundRoute, inboundRoute);
        }

    }

    private static void populateStation(final int amount, final Random random, final Route route, final Route otherRoute) {
        for (int j = amount; j > 0; j--) {

            final boolean switchRoute = random.nextBoolean();
            Station station;
            if (switchRoute) {
                station = route.getStations().get(random.nextInt(route.getStations().size()));
            } else {
                station = otherRoute.getStations().get(random.nextInt(route.getStations().size()));
            }

            Station destination = route.getStations().get(random.nextInt(route.getStations().size()));
            station.getPassengerQueue().enqueue(new Passenger(destination, Status.IN_STATION));
        }
    }

    public static int[] doSort(final int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[index]) {
                    index = j;
                }
            }
            int smallerNumber = arr[index];
            arr[index] = arr[i];
            arr[i] = smallerNumber;
        }
        return arr;
    }
}
