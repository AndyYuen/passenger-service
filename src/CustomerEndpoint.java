package com.redhat.rhoar.springboot.customer.rest;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.redhat.rhoar.springboot.customer.CustomerRepository;
import com.redhat.rhoar.springboot.customer.model.Customer;


@Path("/")
@Component
public class CustomerEndpoint {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerEndpoint.class);

    @Autowired
    private CustomerRepository customerService;

    @GET
    @Path("/customer/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomer(@PathParam("customerId") String customerId) {
        Customer customer = customerService.findOne(customerId);
        if (customer == null) {
            throw new NotFoundException();
        } else {
            return customer;
        }
    }
    
    @GET
    @Path("/customers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getCustomers() {
    	Iterable<Customer> it = customerService.findAll();
        if (!it.iterator().hasNext()) {
            throw new NotFoundException();
        } else {
            return ( List<Customer>) it;
        }
    }
    
    @POST
    @Path("/customer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCustomer(Customer customer) throws Exception{
        customerService.save(customer);
        return Response.status(200).build();
    }

}