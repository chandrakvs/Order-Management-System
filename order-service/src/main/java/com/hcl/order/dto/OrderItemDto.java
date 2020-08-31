package com.hcl.order.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class OrderItemDto {
	
	@NotNull
    @Digits(integer = 3, fraction = 2)
    @Positive(message = "Order item id  should be number")
	private int orderItemId;
	
	private String productCode;
	private String productName;
	private int quantity;
}
