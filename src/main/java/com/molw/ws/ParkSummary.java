package com.molw.ws;

import com.molw.data.SimplePark;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import org.geotools.data.*;

import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

/*
This method gets all the counts of all the birds in the different parks and summarizes by park
 */
@Path("/parksummary")

public class ParkSummary {
    static Logger logger = Logger.getLogger(ParkSummary.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<SimplePark> getParksSummary(){
        ArrayList<SimplePark> simpleParks = new ArrayList<SimplePark>();

        //Set up the spark properties
        HashMap<String,String> hashMap= new HashMap<String,String>();
        hashMap.put("postgis.host","postgis-birds-replica");
        hashMap.put("posgis.port", "5432");
        hashMap.put("postgis.user", "userxaok5" );
        hashMap.put("postgis.password","R7xnmB8tkAaFEA0mm8fg");
        hashMap.put("postgis.db","birds");

        logger.info("After properties");

        try {

            DataStore ds = DataStoreFinder.getDataStore(hashMap);
            logger.info("hey there " + ds.getTypeNames());
            

        } catch (Exception e){
            logger.info("Exception " + e.getMessage());
        }

        /*
        SparkSession spark = SparkSession.builder()
                .appName("Analyzing Parks In The Birds")
                .config("spark.sql.crossJoin.enabled", "true")
                .master("local[*]")
                .getOrCreate();

        Dataset<Row> df = spark.read()
                .format("geomesa")
                .options(hashMap)
                .option("geomesa.feature", "birdobs")
                .load();
        df.createOrReplaceTempView("birdobs");

        Dataset<Row> numberOfObsDF = spark.sql("select count(observation_count) from birdobs");
        numberOfObsDF.show();
        */
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
