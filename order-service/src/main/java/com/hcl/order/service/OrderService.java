package com.hcl.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.order.dao.OrderRepository;
import com.hcl.order.dto.OrderDto;
import com.hcl.order.dto.OrderItemDto;
import com.hcl.order.entity.Order;
import com.hcl.order.entity.OrderItem;
import com.hcl.order.exception.OrdersNotFoundException;
import com.hcl.order.feignclient.OrderItemServiceFeignClient;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired(required=true)
	private OrderItemServiceFeignClient orderItemsServiceFeignClient;
	
	public void createOrder(OrderDto orderDto) {
	
	List<OrderItemDto> orderItemsDtos=orderItemsServiceFeignClient.listOrderItems();
	List<OrderItemDto> orderedItemsDtos=new ArrayList<>();
	List<OrderItemDto> orderedItems = orderDto.getOrderItems();
	
	for(OrderItemDto orderedItem:orderedItems) {
		for(OrderItemDto orderItemDto:orderItemsDtos)
			if(orderItemDto.getOrderItemId()==orderedItem.getOrderItemId())
			{
			orderedItemsDtos.add(orderItemDto);
			break;
			}
	}
	
	
	if(Objects.nonNull(orderDto))
	{
		Order order=new Order();
		order.setCustomerName(orderDto.getCustomerName());
		order.setOrderDate(orderDto.getOrderDate());
		order.setShippingAddress(orderDto.getShippingAddress());
		order.setTotal(orderDto.getTotal());
		
	    List<OrderItem> orderedItemsList=new ArrayList<>();
	    for(OrderItemDto orderItemDto:orderedItemsDtos) {
	    	OrderItem orderItem=new OrderItem();	
	    orderItem.setOrderItemId(orderItemDto.getOrderItemId());
	    orderItem.setProductCode(orderItemDto.getProductCode());
	    orderItem.setProductName(orderItemDto.getProductName());
	    orderItem.setQuantity(orderItemDto.getQuantity());
	    orderItem.setOrder(order);
	    orderedItemsList.add(orderItem);
	    
	    }
	
	    order.setOrderItems(orderedItemsList);
	    orderRepository.save(order);
	  }
	
	}
	
	public List<OrderDto> ordersList(){
		
		Iterable<Order> ordersList=orderRepository.findAll();
		List<OrderDto> orderDtos = new ArrayList<>();
		if(ordersList.iterator().hasNext()) {
		for(Order order: ordersList) {
			OrderDto orderDto = new OrderDto();
			orderDto.setOrderId(order.getOrderId());
			orderDto.setCustomerName(order.getCustomerName());
			orderDto.setOrderDate(order.getOrderDate());
			orderDto.setShippingAddress(order.getShippingAddress());
			orderDto.setTotal(order.getTotal());
			List<OrderItem> orderedItems = order.getOrderItems();
			List<OrderItemDto> orderedItemDtos = new ArrayList<>();
			for (OrderItem orderItem:orderedItems) {
				OrderItemDto orderItemDto = new OrderItemDto();
				orderItemDto.setOrderItemId(orderItem.getOrderItemId());
				orderItemDto.setProductName(orderItem.getProductName());
				orderItemDto.setProductCode(orderItem.getProductCode());
				orderItemDto.setQuantity(orderItem.getQuantity());
				orderedItemDtos.add(orderItemDto);
			}
			orderDto.setOrderItems(orderedItemDtos);
			orderDtos.add(orderDto);
		}
		
		return orderDtos;
	}else {
		throw new OrdersNotFoundException("Orders are not available.");
	}
  }
}
