package com.ecommerceASM.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerceASM.dao.AccountDAO;
import com.ecommerceASM.dao.AuthorityDAO;
import com.ecommerceASM.entity.Account;
import com.ecommerceASM.entity.Authority;
import com.ecommerceASM.service.AuthorityService;
@Service
public class AuthoritiesServiceImpl implements AuthorityService{

	@Autowired
	AuthorityDAO authorityDAO;
	
	@Autowired
	AccountDAO accountDAO;
	
	@Override
	public List<Authority> findAll() {
		// TODO Auto-generated method stub
		return authorityDAO.findAll();
	}

	@Override
	public Authority create(Authority auth) {
		// TODO Auto-generated method stub
		return authorityDAO.save(auth);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		authorityDAO.deleteById(id);
	}

	@Override
	public List<Authority> findAuthoritiesOfAdministrators() {
		// TODO Auto-generated method stub
		List<Account> accounts = accountDAO.getAdministrators();
		return authorityDAO.authoritiesOf(accounts);
	}

}
