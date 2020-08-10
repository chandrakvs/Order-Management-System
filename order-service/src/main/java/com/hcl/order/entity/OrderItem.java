package com.hcl.order.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_item")
public class OrderItem {
	
	@Id
	@Column(name="order_item_id" , nullable=false)
	private int orderItemId;
	@Column(name="product_code" , nullable=false)
	private String productCode;
	@Column(name="product_name" , nullable=false)
	private String productName;
	@Column(name="quantity" , nullable=false)
	private int quantity;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
	

	public OrderItem() {
		
	}

	public OrderItem(String productCode, String productName, int quantity, Order order) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.quantity = quantity;
		this.order = order;
	}
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

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
