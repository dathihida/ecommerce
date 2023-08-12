package com.ecommerceASM.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerceASM.entity.Role;


public interface RoleDAO extends JpaRepository<Role, String> { }
