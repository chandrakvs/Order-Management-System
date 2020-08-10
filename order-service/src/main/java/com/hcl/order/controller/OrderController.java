package com.hcl.order.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.order.dto.OrderDto;
import com.hcl.order.service.OrderService;

@RestController
@RequestMapping(path="/orders")
@CrossOrigin(origins = "*")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/create-order")
	public ResponseEntity<String> createOrder(@Valid @RequestBody OrderDto orderDto) {
		
		orderService.createOrder(orderDto);
		return new ResponseEntity<String>("Order created successfully.", HttpStatus.OK);
		
	}
	
	@GetMapping("/orders-list")
	public  List<OrderDto> ordersList(){
		
		List<OrderDto> orderDtos = orderService.ordersList();
		
		return orderDtos;
		
	}

}
