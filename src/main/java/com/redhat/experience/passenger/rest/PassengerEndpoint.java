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
    @Path("/passengers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Passenger> getPassengers() throws IOException, JSONException {
    	List<Passenger> list = jsonReader.readFromResource();
    	return list;
    }
    
    @POST
    @Path("/passengers")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addVipPassengers(List<Passenger> passengerList) {
    	for (Passenger passenger : passengerList) {
    		System.out.println(passenger);
    		System.out.println("----------------------------------------------------");
    	}
        return Response.status(200).build();
    }

}