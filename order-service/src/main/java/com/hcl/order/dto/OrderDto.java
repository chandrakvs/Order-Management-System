package com.hcl.order.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class OrderDto {
	
	
	private int orderId;
	
	@NotEmpty(message = "Customer name is mandatory")
    @Size(min=2, message="Customer name should have at least 2 characters")
	private String customerName;
	
	@NotNull(message = "Order Date should not be null.")
	private Date orderDate;
	
	@NotEmpty(message = "Shipping Address is mandatory")
    @Size(min=2, message="shipping Address should have at least 2 characters")
	private String shippingAddress;
	
	
	@NotNull
    @Digits(integer = 5, fraction = 2)
    @Positive(message = "Total value should be number")
	private double total;
	
	private List<OrderItemDto> orderItems;
	
	public List<OrderItemDto> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItemDto> orderItems) {
		this.orderItems = orderItems;
	}
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
}
