package edu.wit.dcsn.comp2000.queueapp.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**@author Zachary Shelton, Taken from a previous project*/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ConfigTarget {

    String name();


}
