package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class HanaTaxApplication {

	public static void main(String[] args) {
		SpringApplication.run(HanaTaxApplication.class, args);
	}

}
