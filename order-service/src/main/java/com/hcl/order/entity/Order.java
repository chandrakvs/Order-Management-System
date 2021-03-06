package com.hcl.order.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="order_id" , nullable=false)
	private int orderId;
	@Column(name="customer_name" , nullable=false)
	private String customerName;
	@Column(name="order_date" , nullable=false)
	private Date orderDate;
	@Column(name="shipping_address" , nullable=false)
	private String shippingAddress;
	@Column(name="total" , nullable=false)
	private double total;
	
	private String orderItemIds;
	
	public Order(String customerName, Date orderDate, String shippingAddress, double total) {
		this.customerName = customerName;
		this.orderDate = orderDate;
		this.shippingAddress = shippingAddress;
		this.total = total;
	}

}
