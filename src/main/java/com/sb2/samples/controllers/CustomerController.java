package com.sb2.samples.controllers;

import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.sb2.samples.entities.Customer;
import com.sb2.samples.repositories.CustomerRepository;

@RestController
@RequestMapping("customers")
public class CustomerController {

	private final static Logger LOGGER = Logger.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private Gson gson;

	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Customer>> getAll(){
		Pageable pageable  = PageRequest.of(0, 10);
		Page<Customer> customerPage = this.customerRepository.findAll(pageable);
		return ResponseEntity.ok(customerPage);
	}
	
	@GetMapping(value="{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> getById(long id){
		Optional<Customer> customerPage = this.customerRepository.findById(id);
		Customer customer = customerPage.get();
		return ResponseEntity.ok(customer);
	}
	
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> save(@RequestBody String payload){
		Customer customer = this.gson.fromJson(payload, Customer.class);
		customer  = this.customerRepository.save(customer);
		return ResponseEntity.ok(customer);
	}
	
	
}


