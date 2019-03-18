package com.sb2.samples.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.sb2.samples.entities.Customer;
import com.sb2.samples.repositories.CustomerRepository;

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
	public CustomerRepository customerRepo;
	
	/**
	 * 
	 */
	@Autowired
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
	public ResponseEntity<Customer> getById(@PathVariable("customerId") String customerId){
		final Optional<Customer> customerPage = this.customerRepo.findById(Long.parseLong(customerId));
		if(!customerPage.isPresent())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(customerPage.get());   // NOPMD by karthikmekala on 18/3/19 4:42 PM
	}
	
	
	/**
	 * @param payload
	 * @return
	 */
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> save(final @RequestBody String payload){
		final Customer customer = this.gson.fromJson(payload, Customer.class);
		return ResponseEntity.created(null).body(this.customerRepo.save(customer));   // NOPMD by karthikmekala on 18/3/19 4:42 PM
	}
	
	
}


