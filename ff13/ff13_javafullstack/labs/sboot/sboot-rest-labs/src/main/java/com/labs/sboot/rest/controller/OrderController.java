package com.labs.sboot.rest.controller;

import com.labs.sboot.rest.model.Order;
import com.labs.sboot.rest.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    ApplicationContext ctx;

//    Create Order 	POST	https://localhost:8080/sprink/orders
//    Update Order 	PUT		https://localhost:8080/sprink/orders/{id}
//    Delete Order 	DELETE	https://localhost:8080/sprink/orders/{id}
//    View Order 		GET		https://localhost:8080/sprink/orders/{id}
//    View All		GET		https://localhost:8080/sprink/orders

//    @RequestMapping(path = "/orders", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseMessage> create(@RequestBody  Order order) throws URISyntaxException {

        System.out.println(order);
        // TODO : Call Service and Dao to persist
        ResponseMessage response = new ResponseMessage("Success", "Order created successfully");
        ResponseEntity<ResponseMessage> res = ResponseEntity.created(new URI("http://localhost:8080/orders/" + order.getId())).body(response);
//        ResponseEntity<ResponseMessage> res1 = new ResponseEntity<>(response, HttpStatus.CREATED);
        return res;
    }

    @GetMapping
    public ResponseEntity<Order> get(@RequestParam(required = false) String id, @RequestParam(required = false) String type) {
        System.out.println("Order ID is " + id);
        System.out.println("Order type is " + type);

        Order order = ctx.getBean("order", Order.class);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> get(@PathVariable String id, @RequestBody Order order) {
        System.out.println("Order ID is " + id);
        System.out.println(order);
        // TODO : Call Service and Dao to update order
        ResponseMessage response = new ResponseMessage("Success", "Order updated successfully");
        ResponseEntity<ResponseMessage> res = ResponseEntity.ok(response);
        return res;
    }

}
