package com.hcl.order.feignclient;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.hcl.order.dto.OrderItemDto;

@FeignClient(name="order-items-service")
public interface OrderItemServiceFeignClient {
	@RequestMapping(value = "/orderitems/selectedorderitems", method = RequestMethod.GET)
	public List<OrderItemDto> listSelectedOrderItems(@RequestParam("orderItemIds") List<Integer> orderItemIds);
}
