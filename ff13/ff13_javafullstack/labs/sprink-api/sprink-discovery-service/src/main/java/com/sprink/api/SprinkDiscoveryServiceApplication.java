package com.sprink.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@EnableDiscoveryClient
@SpringBootApplication
public class SprinkDiscoveryServiceApplication { // implements CommandLineRunner {

//	@Autowired
//	DiscoveryClient discoveryClient;

	public static void main(String[] args) {
		SpringApplication.run(SprinkDiscoveryServiceApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println(discoveryClient);
//		ServiceInstance serviceInstance = discoveryClient.getInstances("sprink-menu-service").get(0);
//		URI baseUri = serviceInstance.getUri();
//		URI menuItemsUri = new URI(baseUri.toString() + "/sprink/api/items");
//		System.out.println(baseUri);
//		RestTemplate client = new RestTemplate();
//		Object response = client.getForObject(menuItemsUri, Object.class);
//		System.out.println(response);
//	}
}
