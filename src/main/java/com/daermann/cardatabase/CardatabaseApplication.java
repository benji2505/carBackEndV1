package com.daermann.cardatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.daermann.cardatabase.domain.Car;
import com.daermann.cardatabase.domain.CarRepository;
import com.daermann.cardatabase.domain.Owner;
import com.daermann.cardatabase.domain.OwnerRepository;
import com.daermann.cardatabase.domain.User;
import com.daermann.cardatabase.domain.UserRepository;

// remove parent class extension when going back to local deployment
@SpringBootApplication
public class CardatabaseApplication extends SpringBootServletInitializer {
	
	private static final Logger logger= LoggerFactory.getLogger(CardatabaseApplication.class);

	@Autowired
	private CarRepository repository;
	@Autowired
	private OwnerRepository orepository;
	@Autowired
	private UserRepository userRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CardatabaseApplication.class, args);
		
		logger.info("Hello Spring Boot");
	}
	
	//remove for local deployment
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CardatabaseApplication.class);
	}
	
	@Bean 
	CommandLineRunner runner() {
		return args -> {
			Owner owner1 = new Owner("John","Johnson");
			orepository.save(owner1);
			Owner owner2 = new Owner("Mary","Robinson");
			orepository.save(owner2);
			repository.save(new Car("Ford","Mustang","Red","ADF-1121",owner1,2017,59000));
			repository.save(new Car("Nissan","Leaf","White","SSJ-3002",owner2,2014,29000));
			repository.save(new Car("Toyota","Prius","Silver","KKO-0212",owner2,2018,39000));
			userRepository.save(new User("user", "$2a$04$1.YhMIgNX/8TkCKGFUONWO1waedKhQ5KrnB30fl0Q01QKqmzLf.Zi","USER"));
			userRepository.save((new User("admin", "$2a$04$KNLUwOWHVQZVpXyMBNc7JOzbLiBjb9Tk9bP7KNcPI12ICuvzXQQKG", "ADMIN")));
		};
	}

}
