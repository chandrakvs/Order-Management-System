package com.hcl.order.item.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class OrderItemDto {
	
     private int orderItemId;
     
     @NotNull
     @NotEmpty(message = "Product code is mandatory")
     @Size(min=2, message="Product code should have at least 2 characters")
     private String productCode;
     
     @NotNull
     @NotEmpty(message = "Product name is mandatory")
     @Size(min=2, message="Product name should have at least 2 characters")
     private String productName;
     
     @NotNull
     @Digits(integer = 5, fraction = 2)
     @Positive
     private int quantity;     
	
	public int getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
     
}
