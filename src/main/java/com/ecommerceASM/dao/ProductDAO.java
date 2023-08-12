package com.ecommerceASM.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecommerceASM.entity.Product;


public interface ProductDAO extends JpaRepository<Product, Integer>{

	@Query("SELECT p FROM Product p WHERE p.category.id=?1")
	List<Product> findByCategoryId(String cid);
	
	@Query("SELECT p FROM Product p WHERE p.price between ?1 and ?2")
	List<Product> findProductByPrice(Double min, Double max);
}
