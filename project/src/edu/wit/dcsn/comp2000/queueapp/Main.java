package edu.wit.dcsn.comp2000.queueapp;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(final String... args) throws IOException, IllegalAccessException {
        final ConfigImpl config = (ConfigImpl) ConfigHandler.loadConfig(new File("TrainSimulation.config"), new ConfigImpl());
        System.out.println(String.format("Ticks: %d", config.getTicks()));
        for(final String s: config.getInitialPassengersList()) {
            System.err.println(s);
        }
    }
}
