package edu.wit.dcsn.comp2000.queueapp;

import java.util.ArrayList;
import java.util.List;


/**@author Dan Adler*/
public class Route {

    private String name;

    private int length;

    public Route(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    private List<Station> stations = new ArrayList<>();

    public List<Station> getStations() {
        return stations;
    }

    public String getName() {
        return name;
    }
}
