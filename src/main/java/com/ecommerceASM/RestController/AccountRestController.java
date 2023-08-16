package com.ecommerceASM.RestController;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerceASM.entity.Account;
import com.ecommerceASM.service.AccountService;
@CrossOrigin("*")
@RestController
public class AccountRestController {
	@Autowired
	AccountService accountService;
	
	@RequestMapping("/rest/accounts")
	public List<Account> getAccounts(@RequestParam("admin") Optional<Boolean> admin){
		if(admin.orElse(false)) {
			return accountService.getAdministrators();
		}
		return accountService.findAll();
	}
	
	@GetMapping("rest/newaccounts")
	public ResponseEntity<List<Account>> getAll(){
		return ResponseEntity.ok(accountService.findAll());
	}
	
	@PostMapping("rest/newaccounts")
	public ResponseEntity<Account> create(@RequestBody Account account){
		accountService.create(account);
		return ResponseEntity.ok(account);
	}
}
