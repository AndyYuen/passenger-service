package com.redhat.experience.passenger.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
	static String SEPARATOR = "####################################################################################";

    
    @GET
    @Path("/passengers/flight/{flightNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Passenger> getPassengers(@PathParam("flightNumber") String flightNumber) throws IOException, JSONException {
    	System.out.println(SEPARATOR);
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
    	System.out.println(SEPARATOR);
    	System.out.println("Most important passenger list follows...");
    	for (Passenger passenger : passengerList) {
    		System.out.println("----------------------------------------------------");
    		System.out.println(passenger);
    	}
        return Response.status(201).build();
    }
    
    // new interfaces for demo'ing external REST Service calls
    @POST
    @Path("/passengers/flight/vip/{flightNumber}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addVipPassenger(@PathParam("flightNumber") String flightNumber, Passenger passenger) {
    	System.out.println(SEPARATOR);
    	System.out.println("addVipPassengers invoked with Flight Number: " + flightNumber);

    	System.out.println("Vip----------------------------------------------------");
    	System.out.println(passenger);

        return Response.status(201).build();
    }
    
    @POST
    @Path("/passengers/flight/sort/{count}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Passenger>  sortPassengers(List<Passenger> passengerList, @PathParam("count") long count) {
    	System.out.println(SEPARATOR);
    	System.out.println("sorting passenger by PCV ");
    	System.out.println("Most important passenger list follows...");

    	sortSmallestFirst(passengerList);
		if (count > passengerList.size()) count = passengerList.size();
		List<Passenger> topList = new ArrayList<Passenger>();
		for (int i = 0; i < count; i++) {
			topList.add(passengerList.get(i));
		}
    	for (Passenger passenger : topList) {
    		System.out.println("----------------------------------------------------");
    		System.out.println(passenger);
    	}
		return topList;

    }
    
	public void sortSmallestFirst(List<Passenger> passengers) {
		Collections.sort(passengers, new SortbyPcv());
	}
	
	class SortbyPcv implements Comparator<Passenger>
	{
	    // Used for sorting in ascending order of
	    // PCV
	    public int compare(Passenger a, Passenger b)
	    {
	        return (int) (a.getPCV() - b.getPCV());
	    }
	}

}