package com.hcl.order.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.order.dto.OrderDto;
import com.hcl.order.service.OrderService;

@RestController
@RequestMapping(path="/orders")
public class OrderController {

	private static Logger LOGGER = LogManager.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("")
	public ResponseEntity<String> createOrder(@Valid @RequestBody OrderDto orderDto) {
		LOGGER.info("OrderController : createOrder() is started.");
		ResponseEntity<String> responseEntity = orderService.createOrder(orderDto);
		LOGGER.info("OrderController : createOrder() is ended.");
		return responseEntity; 
		
	}
	
	@GetMapping("")
	public  ResponseEntity<Object> ordersList(){
		LOGGER.info("OrderController : ordersList() is started.");
		List<OrderDto> orderDtos = orderService.ordersList();
		LOGGER.info("OrderController : ordersList() is ended.");
		if(orderDtos.size()==0)
			 return new ResponseEntity<Object>("Orders are not available.",HttpStatus.OK);
		else
			 return new ResponseEntity<Object>(orderDtos,HttpStatus.OK);
		
	}

}
