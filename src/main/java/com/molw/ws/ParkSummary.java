package com.molw.ws;

import com.molw.data.SimplePark;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import org.geotools.data.*;
import org.geotools.data.postgis.PostgisNGDataStoreFactory;

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
    public ArrayList<SimplePark> getParksSummary() throws Exception{
        ArrayList<SimplePark> simpleParks = new ArrayList<SimplePark>();

        //Set up the spark properties
        HashMap<String,String> params= new HashMap<String,String>();
        params.put("dbtype", "postgis");
        params.put("host",System.getenv("POSTGIS_BIRDS_REPLICA_SERVICE_HOST"));
        params.put("port", "5432");
        params.put("user", "userxaok5" );
        params.put("passwd","R7xnmB8tkAaFEA0mm8fg");
        params.put("schema", "public");
        params.put("database","birds");

        logger.info("After properties - You Bet");
        params.forEach((key, value) -> logger.info("Key: " + key + " Value: " + value));

        try {

            PostgisNGDataStoreFactory factory = new PostgisNGDataStoreFactory();
            DataStore datastore = factory.createDataStore(params);

        } catch (Exception e){
            logger.info("!!!!! Threw exception " + e.getCause() + " :: " + e.getMessage());
        }


            



        SparkSession spark = SparkSession.builder()
                .appName("Analyzing Parks In The Birds")
                .config("spark.sql.crossJoin.enabled", "true")
                .master("spark://sparky:7077")
                .getOrCreate();

        Dataset<Row> df = spark.read()
                .format("geomesa")
                .options(params)
                .option("geomesa.feature", "birdobs")
                .load();
        df.createOrReplaceTempView("birdobs");

        Dataset<Row> numberOfObsDF = spark.sql("select count(observation_count) from birdobs");
        numberOfObsDF.show();

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
