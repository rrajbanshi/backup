package com.labs.spring.boot.sbootlabs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

//@ComponentScan(basePackages = {"com.labs.spring.core"})
@Configuration
public class AppConfig {

    @Bean
    public Order order() {
        Order order = new Order();
        order.setId(100);
        order.setStatus("CONFIRMED");
        order.setTotalAmount(1000);
        order.setOrderBy("Pratap");
        order.setItems(Arrays.asList(samosa(), chat()));
        return order;
    }

    @Bean
    public Item samosa() {
        Item item = new Item();
        item.setId(1);
        item.setName("samosa");
        item.setPrice(20);
        item.setQuantity(20);
        item.setCusine("Indian");
        item.setVeg(true);
        return item;
    }

    @Bean
    public Item chat() {
        Item item = new Item();
        item.setId(1);
        item.setName("chat");
        item.setPrice(30);
        item.setQuantity(20);
        item.setCusine("Indian");
        item.setVeg(true);
        return item;
    }
}
