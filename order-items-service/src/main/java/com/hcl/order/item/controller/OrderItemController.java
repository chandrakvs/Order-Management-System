package com.hcl.order.item.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.order.item.dto.OrderItemDto;
import com.hcl.order.item.service.OrderItemService;

@RestController
@RequestMapping(path="/orderitems")
@CrossOrigin(origins = "*")
public class OrderItemController {
	
	private static Logger LOGGER = LogManager.getLogger(OrderItemController.class);
	
	@Autowired
	private OrderItemService orderItemService;
	
	@PostMapping("")
	public ResponseEntity<String> saveOrderItem(@Valid @RequestBody OrderItemDto orderItemDto)
	{
		LOGGER.info("OrderItemController : saveOrderItem() is started.");
		orderItemService.saveOrderItem(orderItemDto);
		LOGGER.info("OrderItemController : saveOrderItem() is ended.");
		return new ResponseEntity<String>("Order Item saved successfully.", HttpStatus.CREATED);
	}
	
	@GetMapping("")
	public ResponseEntity<Object> listOrderItems()
	{
		LOGGER.info("OrderItemController : listOrderItems()  is started.");
		List<OrderItemDto> orderItemDtos=orderItemService.listOrderItems();
		LOGGER.info("OrderItemController : listOrderItems()  is ended.");
		if(orderItemDtos.size()==0)
		 return new ResponseEntity<Object>("Order Items are not available.",HttpStatus.OK);
		else
		 return new ResponseEntity<Object>(orderItemDtos,HttpStatus.OK);	
	}
	
	@GetMapping("/{orderItemIds}")
	public ResponseEntity<Object> listSelectedOrderItems(@PathVariable String orderItemIds)
	{   
		LOGGER.info("OrderItemController : listSelectedOrderItems()  is started.");
	    List<Integer> orderItemIdsList = Stream.of(orderItemIds.split(",")).map(Integer::parseInt).collect(Collectors.toList());
		List<OrderItemDto> orderItemDtos=orderItemService.listSelectedOrderItems(orderItemIdsList);
		LOGGER.info("OrderItemController : listSelectedOrderItems() is ended.");
		if(orderItemDtos.size()==0)
			 return new ResponseEntity<Object>("Selected Order Items are not available.",HttpStatus.OK);
		else
			 return new ResponseEntity<Object>(orderItemDtos,HttpStatus.OK);
	}	

}
