package com.hcl.order.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class OrderItemsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderItemsServiceApplication.class, args);
	}

}
