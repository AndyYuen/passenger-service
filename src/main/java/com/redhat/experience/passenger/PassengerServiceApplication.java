package com.redhat.experience.passenger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@ComponentScan(basePackageClasses = PassengerEndpoint.class)
public class PassengerServiceApplication {

    public static void main(String[] args) {
    	SpringApplication.run(PassengerServiceApplication.class, args);
    }

}
