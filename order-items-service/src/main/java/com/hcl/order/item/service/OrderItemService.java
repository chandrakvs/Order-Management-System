package com.hcl.order.item.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.order.item.dao.OrderItemRepository;
import com.hcl.order.item.dto.OrderItemDto;
import com.hcl.order.item.entity.OrderItem;
import com.hcl.order.item.exception.OrderItemsNotFoundException;

@Service
public class OrderItemService {
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	public void saveOrderItem(OrderItemDto orderItemDto) {
		OrderItem orderItem=new OrderItem();
		orderItem.setOrderItemId(orderItemDto.getOrderItemId());
		orderItem.setProductName(orderItemDto.getProductName());
		orderItem.setProductCode(orderItemDto.getProductCode());
		orderItem.setQuantity(orderItemDto.getQuantity());
		orderItemRepository.save(orderItem);
		}
	
	public List<OrderItemDto> listOrderItems() {
		List<OrderItemDto> orderItemDtos = new ArrayList<>();
		Iterable<OrderItem> orderItems=orderItemRepository.findAll();
		if(orderItems.iterator().hasNext()) {
		for(OrderItem orderItem:orderItems) {
			OrderItemDto orderItemDto = new OrderItemDto();
			orderItemDto.setOrderItemId(orderItem.getOrderItemId());
			orderItemDto.setProductName(orderItem.getProductName());
			orderItemDto.setProductCode(orderItem.getProductCode());
			orderItemDto.setQuantity(orderItem.getQuantity());
			orderItemDtos.add(orderItemDto);
		}
		return orderItemDtos;
		
	}else {
		throw new OrderItemsNotFoundException("Order items are not available.");
	}
  }
}
