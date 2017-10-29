package edu.wit.dcsn.comp2000.queueapp;

import edu.wit.dcsn.comp2000.queueapp.config.ConfigHandler;
import edu.wit.dcsn.comp2000.queueapp.config.ConfigImpl;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(final String... args) throws IOException, IllegalAccessException {
//    	System.out.println(new File("").getAbsolutePath());
        final ConfigImpl config = (ConfigImpl) ConfigHandler.loadConfig(new File("TrainSimulation.config"), new ConfigImpl());
        System.out.println(String.format("Ticks: %d", config.getTicks()));
        for(final String s: config.getInitialPassengersList()) {
            System.err.println(s);
        }
    }
}
