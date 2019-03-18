package com.sb2.samples;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.sb2.samples.entities.Customer;

/**
 * @author karthikmekala
 *
 */
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles="h2test")
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class Sb2GradleSamplesApplicationTests {

	/**
	 * 
	 */
	private static final boolean VALUE = false;
	
	
	/**
	 * 
	 */
	private RestTemplate restTemaplate = null;
	//private Gson gson = null;
	
	@LocalServerPort
	private int randomPort;
	
	/**
	 * 
	 */
	public Sb2GradleSamplesApplicationTests() {
		super();
	}
	
	
	@Before
	public void init() {
		this.restTemaplate  = new RestTemplate();
		//this.gson = new Gson();
	}



	/**
	 * 
	 */
	@Test
	public void contextLoads() {
		Assert.assertTrue(!VALUE);
	}
	
	
	@Test
	public void testGetAllCustomers() throws URISyntaxException {
		final String baseUrl = "http://localhost:"+randomPort+"/gradle/api/customers";
		URI uri = new URI(baseUrl);		
		ResponseEntity<String> result = this.restTemaplate.getForEntity(uri, String.class);
		Assert.assertEquals(200, result.getStatusCodeValue());
	}
	
	
	@Test
	@Ignore
	public void testGetCustomerById() throws URISyntaxException {
		final String baseUrl = "http://localhost:"+randomPort+"/gradle/api/customers/9999";
		URI uri = new URI(baseUrl);		
		ResponseEntity<String> result = this.restTemaplate.getForEntity(uri, String.class);
		Assert.assertEquals(404, result.getStatusCodeValue());
	}
	
	@Test
	public void testSaveCustomer() throws URISyntaxException {
		final String baseUrl = "http://localhost:"+randomPort+"/gradle/api/customers";
		URI uri = new URI(baseUrl);		
		Customer customer = new Customer();
		customer.setFirstName("Random..");
		ResponseEntity<String> result = this.restTemaplate.postForEntity(uri, customer, String.class);
		Assert.assertEquals(201, result.getStatusCodeValue());
	}
	
}
