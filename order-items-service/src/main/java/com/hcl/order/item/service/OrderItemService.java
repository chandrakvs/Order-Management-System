package com.hcl.order.item.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.order.item.dao.OrderItemRepository;
import com.hcl.order.item.dto.OrderItemDto;
import com.hcl.order.item.entity.OrderItem;
import com.hcl.order.item.exception.DataBaseException;

@Service
public class OrderItemService {
	
	private static Logger LOGGER = LogManager.getLogger(OrderItemService.class);
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	public void saveOrderItem(OrderItemDto orderItemDto) {
		LOGGER.debug("OrderItemService : "+"ProductName : "+orderItemDto.getProductName()+"ProductCode : "+orderItemDto.getProductCode()+"Quantity : "+orderItemDto.getQuantity());
		OrderItem orderItem=new OrderItem();
		orderItem.setProductName(orderItemDto.getProductName());
		orderItem.setProductCode(orderItemDto.getProductCode());
		orderItem.setQuantity(orderItemDto.getQuantity());
		try {
		orderItemRepository.save(orderItem);
		}catch(Exception exception) {
			LOGGER.error("OrderItemService : "+exception);
		    throw new DataBaseException("Failed to save order Items");
		}
	}
	
	public List<OrderItemDto> listOrderItems() {
		List<OrderItemDto> orderItemDtos = new ArrayList<>();
		List<OrderItem> orderItems=orderItemRepository.findAll();
		if(orderItems.size()!=0) {
			orderItems.forEach(orderItem -> {
				OrderItemDto orderItemDto = new OrderItemDto();
				orderItemDto.setOrderItemId(orderItem.getOrderItemId());
				orderItemDto.setProductName(orderItem.getProductName());
				orderItemDto.setProductCode(orderItem.getProductCode());
				orderItemDto.setQuantity(orderItem.getQuantity());
				orderItemDtos.add(orderItemDto);
			});
		}
		return orderItemDtos;
}		
		
	public List<OrderItemDto> listSelectedOrderItems(List<Integer> orderItemIds) {
		    LOGGER.debug("OrderItemService : "+"Selected order items : "+orderItemIds);
			List<OrderItemDto> orderItemDtos = new ArrayList<>();
			if(orderItemRepository.count()!=0) {
				List<OrderItem> orderItems=orderItemRepository.findAllById(orderItemIds);
				if(orderItems.size()!=0) {
					orderItems.forEach(orderItem -> {
						OrderItemDto orderItemDto = new OrderItemDto();
						orderItemDto.setOrderItemId(orderItem.getOrderItemId());
						orderItemDto.setProductName(orderItem.getProductName());
						orderItemDto.setProductCode(orderItem.getProductCode());
						orderItemDto.setQuantity(orderItem.getQuantity());
						orderItemDtos.add(orderItemDto);
					});
					
				}
		    }
			return orderItemDtos;
   }
}

