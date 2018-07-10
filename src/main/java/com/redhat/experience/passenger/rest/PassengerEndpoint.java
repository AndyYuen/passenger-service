package com.redhat.experience.passenger.rest;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.springframework.stereotype.Component;


import com.redhat.experience.passenger.model.JsonPassengerReader;
import com.redhat.experience.passenger.model.Passenger;


@Component
@Path("/")
public class PassengerEndpoint {
	JsonPassengerReader jsonReader = new JsonPassengerReader("passenger.json");

    
    @GET
    @Path("/passengers/flight/{flightNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Passenger> getPassengers(@PathParam("flightNumber") String flightNumber) throws IOException, JSONException {
    	System.out.println("getPassengers invoked Flight Number: " + flightNumber);
    	System.out.println("Returning canned passenger list...");
    	List<Passenger> list = jsonReader.readFromResource();
    	return list;
    }
    
    @POST
    @Path("/passengers/flight/{flightNumber}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addVipPassengers(@PathParam("flightNumber") String flightNumber, List<Passenger> passengerList) {
    	System.out.println("addVipPassengers invoked with Flight Number: " + flightNumber);
    	System.out.println("Most important passenger list follows...");
    	for (Passenger passenger : passengerList) {
    		System.out.println("----------------------------------------------------");
    		System.out.println(passenger);
    	}
        return Response.status(201).build();
    }

}