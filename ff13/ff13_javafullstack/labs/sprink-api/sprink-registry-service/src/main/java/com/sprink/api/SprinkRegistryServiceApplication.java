package com.sprink.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SprinkRegistryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprinkRegistryServiceApplication.class, args);
	}

}
