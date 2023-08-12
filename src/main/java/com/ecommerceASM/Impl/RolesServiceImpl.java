package com.ecommerceASM.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerceASM.dao.RoleDAO;
import com.ecommerceASM.entity.Role;
import com.ecommerceASM.service.RoleService;
@Service
public class RolesServiceImpl implements RoleService{

	@Autowired
	RoleDAO roleDAO;
	
	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return roleDAO.findAll();
	}

}
