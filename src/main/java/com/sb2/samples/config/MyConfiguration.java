package com.sb2.samples.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;

@Configuration
public class MyConfiguration {

	@Bean
	public Gson gson() {
		return new Gson();
	}
}
