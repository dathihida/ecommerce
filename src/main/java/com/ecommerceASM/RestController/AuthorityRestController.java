package com.ecommerceASM.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerceASM.Impl.AccountServiceImpL;
import com.ecommerceASM.Impl.AuthoritiesServiceImpl;
import com.ecommerceASM.Impl.RolesServiceImpl;
import com.ecommerceASM.dao.AuthorityDAO;
import com.ecommerceASM.entity.Authority;
import com.ecommerceASM.service.AuthorityService;

@CrossOrigin("*")
@RestController
@RequestMapping("rest/authorities")
public class AuthorityRestController {
	@Autowired
	AuthoritiesServiceImpl authorityService;
	
	@Autowired
	RolesServiceImpl rolesService;
	
	@Autowired
	AccountServiceImpL accountService;
	
	@Autowired
	AuthorityDAO adao;
	
	
	@GetMapping
	public List<Authority> findAll(@RequestParam("admin") Optional<Boolean> admin){
		if(admin.orElse(false)) {
			return authorityService.findAuthoritiesOfAdministrators();
		}
		return authorityService.findAll();
	}
	
	//day role
	@GetMapping("/rest/roleauthorities")
	public Map<String, Object> getAuthorities(){
		Map<String, Object> data = new HashMap<>();
		data.put("authorities", authorityService.findAll());
		data.put("roles", rolesService.findAll());
		data.put("accounts", accountService.findAll());
		return data;
	}
	
	@PostMapping
	public Authority post(@RequestBody Authority auth) {
		return authorityService.create(auth);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		authorityService.delete(id);
	}
	
	@PostMapping("/rest/roleauthorities")
	public Authority create(@RequestBody Authority authorities) {
		return adao.save(authorities);
	}
	
	@DeleteMapping("/rest/roleauthorities/{id}")
	public void deleteRole(@PathVariable("id") Integer id) {
		adao.deleteById(id);
	}
}
