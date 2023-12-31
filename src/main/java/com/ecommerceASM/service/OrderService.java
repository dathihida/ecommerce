package com.ecommerceASM.service;

import java.util.List;

import com.ecommerceASM.entity.Order;
import com.fasterxml.jackson.databind.JsonNode;


public interface OrderService {
	public Order create(JsonNode orderData) ;
	
	public Order findById(Long id) ;
	
	public List<Order> findByUsername(String username) ;
}
