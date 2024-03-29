package edu.wit.dcsn.comp2000.queueapp.config;

/**@author Zachary Shelton, Taken from a previous project*/
public abstract class Parser<T> {

    private Class clazz;

    public Parser(final Class clazz) {
        this.clazz = clazz;
    }

    public abstract T parse(final String input);

    public Class getClazz() {
        return clazz;
    }
}
