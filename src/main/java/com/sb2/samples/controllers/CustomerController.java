package com.sb2.samples.controllers;

import java.util.Optional;

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

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author karthikmekala
 *
 */
@RestController
@RequestMapping("customers")
@NoArgsConstructor
public class CustomerController {

	/**
	 * 
	 */
	//private static final Logger LOGGER = Logger.getLogger(CustomerController.class);
	
	/**
	 * 
	 */
	@Autowired
	@Getter
	public CustomerRepository customerRepo;
	
	/**
	 * 
	 */
	@Autowired
	@Getter
	public Gson gson;

	
	/**
	 * @return
	 */
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Customer>> getAll(){
	//	LOGGER.info(CustomerController.class.getName()+" getAll(...) called");
		final Pageable pageable  = PageRequest.of(0, 10);
		final Page<Customer> customerPage = this.customerRepo.findAll(pageable);
		return ResponseEntity.ok(customerPage);
	}
	
	/**
	 * @param id
	 * @return
	 */
	@GetMapping(value="{customerId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> getById(final long customerId){
		final Optional<Customer> customerPage = this.customerRepo.findById(customerId);
		//final Customer customer = customerPage.get();
		return ResponseEntity.ok(customerPage.get());
	}
	
	
	/**
	 * @param payload
	 * @return
	 */
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> save(final @RequestBody String payload){
		final Customer customer = this.gson.fromJson(payload, Customer.class);
		return ResponseEntity.ok(this.customerRepo.save(customer));
	}
	
	
}


