package com.hcl.order.item.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.order.item.dto.OrderItemDto;
import com.hcl.order.item.service.OrderItemService;

@RestController
@RequestMapping(path="/orderitems")
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
	
	@GetMapping("/selectedorderitems")
	public ResponseEntity<Object> listSelectedOrderItems(@RequestParam("orderItemIds") List<Integer> orderItemIds)
	{   
		LOGGER.info("OrderItemController : listSelectedOrderItems()  is started.");
		List<OrderItemDto> orderItemDtos=orderItemService.listSelectedOrderItems(orderItemIds);
		LOGGER.info("OrderItemController : listSelectedOrderItems() is ended.");
    	return new ResponseEntity<Object>(orderItemDtos,HttpStatus.OK);
	}	

}
