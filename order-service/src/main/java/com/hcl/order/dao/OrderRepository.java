package com.hcl.order.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hcl.order.entity.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer>{
	
	@Query("select order.orderItemIds from Order order")
	List<String> getOrderItemIds();

}
