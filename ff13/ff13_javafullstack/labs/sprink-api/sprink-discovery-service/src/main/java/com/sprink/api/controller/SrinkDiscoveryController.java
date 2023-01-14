package com.sprink.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/discovery/items")
public class SrinkDiscoveryController {

    Logger log = LoggerFactory.getLogger(SrinkDiscoveryController.class);
    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping
    public Object getMenuItems() throws URISyntaxException {
        ServiceInstance serviceInstance = discoveryClient.getInstances("sprink-menu-service").get(0);
        URI baseUri = serviceInstance.getUri();
        log.info("Base URI - {}", baseUri);
        URI menuItemsUri = new URI(baseUri.toString() + "/sprink/api/items");
        log.info("Menu Items URI - {}", baseUri);
        RestTemplate client = new RestTemplate();
        Object response = client.getForObject(menuItemsUri, Object.class);
        log.info(response.toString());
        return response;
    }
}
