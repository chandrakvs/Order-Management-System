package com.hcl.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hcl.order.dao.OrderRepository;
import com.hcl.order.dto.OrderDto;
import com.hcl.order.dto.OrderItemDto;
import com.hcl.order.entity.Order;
import com.hcl.order.exception.DataBaseException;
import com.hcl.order.feignclient.OrderItemServiceFeignClient;

@Service
public class OrderService {
	
	private static Logger LOGGER = LogManager.getLogger(OrderService.class);
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemServiceFeignClient orderItemsServiceFeignClient;
	
	public ResponseEntity<String> createOrder(OrderDto orderDto) {

	List<OrderItemDto> orderedItemsDtos=orderItemsServiceFeignClient.listSelectedOrderItems(orderDto.getOrderItemIds());
		
	if(orderedItemsDtos.size()!=0) {
		LOGGER.debug("OrderService : "+"CustomerName : "+orderDto.getCustomerName()+"OrderDate : "+orderDto.getOrderDate()+"ShippingAddress : "+orderDto.getShippingAddress()+"OrderTotal : "+orderDto.getTotal()+"OrderItemIds : "+orderDto.getOrderItemIds().toString());
		Order order=new Order();
		order.setCustomerName(orderDto.getCustomerName());
		order.setOrderDate(orderDto.getOrderDate());
		order.setShippingAddress(orderDto.getShippingAddress());
		order.setTotal(orderDto.getTotal());
	    order.setOrderItemIds(orderDto.getOrderItemIds().toString());
	    try {
	    orderRepository.save(order);
	    }
	    catch(Exception exception) {
	    	LOGGER.error("OrderService : "+exception);
	    	throw new DataBaseException("Failed to save order.");
	    }
	    return new ResponseEntity<String>("Order created successfully.", HttpStatus.CREATED);
	  }else {
		return new ResponseEntity<String>("Order Item details are not available.", HttpStatus.BAD_REQUEST);
	  }
	
	}
	
	public List<OrderDto> ordersList(){
		
		Iterable<Order> ordersList=orderRepository.findAll();
		
		List<OrderDto> orderDtos = new ArrayList<>();
		
		if(ordersList.iterator().hasNext()) {
			
			String orderItemIds = String.join(",", orderRepository.getOrderItemIds());
			LOGGER.debug("OrderService : "+"OrderItemIds : "+orderItemIds);
			List<OrderItemDto> orderedItemsDtos=orderItemsServiceFeignClient.listSelectedOrderItems(orderItemIds);
			
			ordersList.forEach(order -> {
				OrderDto orderDto = new OrderDto();
				orderDto.setOrderId(order.getOrderId());
				orderDto.setCustomerName(order.getCustomerName());
				orderDto.setOrderDate(order.getOrderDate());
				orderDto.setShippingAddress(order.getShippingAddress());
				orderDto.setTotal(order.getTotal());
				List<OrderItemDto> orderedItemDtos = new ArrayList<>();
				List<Integer> orderItemIdsList = Stream.of(order.getOrderItemIds().split(",")).map(Integer::parseInt).collect(Collectors.toList());
				orderedItemsDtos.forEach(orderItemDto -> { if(orderItemIdsList.contains(orderItemDto.getOrderItemId())) {
					orderedItemDtos.add(orderItemDto);
				    }
				  }
				);
				orderDto.setOrderItems(orderedItemDtos);
				orderDtos.add(orderDto);
			}
			
		);
	}		
		return orderDtos;
  }
}
