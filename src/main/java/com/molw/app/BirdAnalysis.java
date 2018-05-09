package com.molw.app;

import io.thorntail.Thorntail;

import javax.ws.rs.ApplicationPath;

import javax.ws.rs.core.Application;

/* to force a change */

@ApplicationPath("/")
public class BirdAnalysis extends Application {
    public static void main(String[] args) throws Exception {
        Thorntail.main(args);
    }

    
}
