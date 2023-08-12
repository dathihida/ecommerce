package com.ecommerceASM.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerceASM.dao.AccountDAO;
import com.ecommerceASM.entity.Account;
import com.ecommerceASM.service.AccountService;

@Service
public class AccountServiceImpL implements AccountService{

	@Autowired
	AccountDAO accountDAO;
	
	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return accountDAO.findAll();
	}

	@Override
	public Account findById(String username) {
		// TODO Auto-generated method stub
		return accountDAO.findById(username).get();
	}

	@Override
	public List<Account> getAdministrators() {
		// TODO Auto-generated method stub
		return accountDAO.getAdministrators();
	}
	
}
