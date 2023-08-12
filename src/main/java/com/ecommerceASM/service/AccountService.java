package com.ecommerceASM.service;

import java.util.List;

import com.ecommerceASM.entity.Account;


public interface AccountService {
	public List<Account> findAll() ;
	public Account findById(String username) ;
	public List<Account> getAdministrators() ;
}
