package com.tutego.date4u;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@ConfigurationPropertiesScan
public class Date4uApplication {
	public static void main(String[] args) {
		SpringApplication.run(Date4uApplication.class, args);
	}
}
