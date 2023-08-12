package com.ecommerceASM.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerceASM.dao.OrderDAO;
import com.ecommerceASM.dao.OrderDetailDAO;
import com.ecommerceASM.entity.Order;
import com.ecommerceASM.entity.OrderDetail;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderDAO orderDAO;
	
	@Autowired
	OrderDetailDAO orderDetailDAO;
	
	@Override
	public Order create(JsonNode orderData) {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		Order order = mapper.convertValue(orderData, Order.class);
		orderDAO.save(order);
		
		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
		List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"),type)
				.stream().peek(d-> d.setOrder(order)).collect(Collectors.toList());
		
		orderDetailDAO.saveAll(details);
		return order;
	}

	@Override
	public Order findById(Long id) {
		// TODO Auto-generated method stub
		return orderDAO.findById(id).get();
	}

	@Override
	public List<Order> findByUsername(String username) {
		// TODO Auto-generated method stub
		return orderDAO.findByUsername(username);
	}

}
