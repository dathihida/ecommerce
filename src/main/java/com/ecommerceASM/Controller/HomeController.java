package com.ecommerceASM.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerceASM.entity.Product;
import com.ecommerceASM.service.ProductService;

@Controller
public class HomeController {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping("/shop")
	public String shop() {
		return "shop";
	}
	
	@RequestMapping("signup")
	public String signup() {
		return "signup";
	}
	
//	@RequestMapping("/products/{id}")
//	public Product detail(@RequestBody @PathVariable("id") Integer id) {
//		return productService.findById(id);
//	}
}
