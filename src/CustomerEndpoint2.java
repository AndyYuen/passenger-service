package com.redhat.rhoar.springboot.customer.rest;

import java.util.List;

import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redhat.rhoar.springboot.customer.CustomerRepository;
import com.redhat.rhoar.springboot.customer.model.Customer;


@RestController
@Component
public class CustomerEndpoint {

//    private static final Logger LOG = LoggerFactory.getLogger(CustomerEndpoint.class);

    @Autowired
    private CustomerRepository customerService;

    @RequestMapping(value = "/customer/{customerId}", method = RequestMethod.GET)
    public Customer getCustomer(@PathVariable("customerId") String customerId) {
        Customer customer = customerService.findOne(customerId);
        if (customer == null) {
            throw new NotFoundException();
        } else {
            return customer;
        }
    }
    
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public List<Customer> getCustomers() {
    	Iterable<Customer> it = customerService.findAll();
        if (!it.iterator().hasNext()) {
            throw new NotFoundException();
        } else {
            return ( List<Customer>) it;
        }
    }
    
    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public ResponseEntity<String> addCustomer(Customer customer){
        customerService.save(customer);
        return ResponseEntity.status(200).build();
    }

}