package com.molw.ws;

import com.molw.data.SimplePark;

import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

/*
This method gets all the counts of all the birds in the different parks and summarizes by park
 */
@Path("/parksummary")

public class ParkSummary {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<SimplePark> getParksSummary(){
        ArrayList<SimplePark> simpleParks = new ArrayList<SimplePark>();

        return simpleParks;

    }


}

/*
Another method to break out counts by park by year
        yr1 yr2 yr3
park1
park2
park3

 */
