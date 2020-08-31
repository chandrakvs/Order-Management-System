package com.hcl.order.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hcl.order.dto.OrderItemDto;

@FeignClient(name="OrderItem",url="localhost:8081")
public interface OrderItemServiceFeignClient {
	
	@RequestMapping(value = "/orderitems/{orderItemIds}", method = RequestMethod.GET)
	public List<OrderItemDto> listSelectedOrderItems(@PathVariable(value="orderItemIds") String orderItemIds);
}
