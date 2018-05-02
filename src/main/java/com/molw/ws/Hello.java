package com.molw.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;

@Path("/healthz")
public class Hello {

    @GET
    @Produces({"application/json"})
    public HashMap amIHealthy(){
        HashMap results = new HashMap();
        results.put("Healthy", "You Betcha");
        return results;
    }
    
}
