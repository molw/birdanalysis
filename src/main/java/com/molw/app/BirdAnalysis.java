package com.molw.app;

import io.thorntail.Thorntail;

import javax.ws.rs.ApplicationPath;

import javax.ws.rs.core.Application;

/*
TODO find out where the doc is on main and if this means all requests go to this method that start Thorntail
A little confused
What if didn't load the JAX-RS and @ApplicationPath - then what?
 */

@ApplicationPath("/")
public class BirdAnalysis extends Application {
    public static void main(String[] args) throws Exception {
        Thorntail.main(args);
    }

    
}
