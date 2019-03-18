package com.sb2.samples.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;

/**
 * @author karthikmekala
 *
 */
@Configuration
public class MyConfiguration {

	/**
	 * @return
	 */
	@Bean
	public Gson gson() {
		return new Gson();
	}
}
