package com.ecommerceASM.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerceASM.entity.OrderDetail;


public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long>{
}