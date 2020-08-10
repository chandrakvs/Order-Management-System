package com.hcl.order.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.hcl.order.dto.OrderItemDto;

@FeignClient(name="OrderItem",url="localhost:8081")
public interface OrderItemServiceFeignClient {
	
	@GetMapping("/orderitems/list-order-items")
	public List<OrderItemDto> listOrderItems();
}
