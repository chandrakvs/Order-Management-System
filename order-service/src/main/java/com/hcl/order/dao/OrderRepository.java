package com.hcl.order.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hcl.order.entity.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer>{
	
	

}
