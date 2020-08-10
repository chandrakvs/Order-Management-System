package com.hcl.order.item.controller;

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

import com.hcl.order.item.dto.OrderItemDto;
import com.hcl.order.item.service.OrderItemService;

@RestController
@RequestMapping(path="/orderitems")
@CrossOrigin(origins = "*")
public class OrderItemController {
	
	@Autowired
	private OrderItemService orderItemService;
	
	@PostMapping("/create-order-item")
	public ResponseEntity<String> saveOrderItem(@Valid @RequestBody OrderItemDto orderItemDto)
	{
		orderItemService.saveOrderItem(orderItemDto);
		return new ResponseEntity<String>("Order Item saved successfully.", HttpStatus.OK);
	}
	
	@GetMapping("/list-order-items")
	public List<OrderItemDto> listOrderItems()
	{
		List<OrderItemDto> orderItemDtos=orderItemService.listOrderItems();
		return orderItemDtos;
	}
	
	

}
