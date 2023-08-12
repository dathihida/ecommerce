package com.ecommerceASM.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerceASM.entity.Category;


public interface CategoryDAO extends JpaRepository<Category, String>{
}
