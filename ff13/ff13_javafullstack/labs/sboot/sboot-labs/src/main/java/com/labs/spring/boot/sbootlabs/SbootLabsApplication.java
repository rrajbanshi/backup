package com.labs.spring.boot.sbootlabs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
// @ComponentScan
// @Configuration
// @EnableAutoConfiguration
public class SbootLabsApplication implements CommandLineRunner {

	@Autowired
	ApplicationContext ctx;

	public static void main(String[] args) {
		SpringApplication.run(SbootLabsApplication.class, args);

		System.out.println("Hello Spring Boot!!!");
	}


	@Override
	public void run(String... args) throws Exception {

		System.out.println(ctx.getBeanDefinitionCount());

		System.out.println("Inside run method...");

//		for (String beanName:ctx.getBeanDefinitionNames()) {
//			System.out.println(beanName);
//		}

		Order order = ctx.getBean("order", Order.class);
		System.out.println(order);
	}
}
