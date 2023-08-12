package com.ecommerceASM.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	@RequestMapping("/admin/index")
	public String index() {
		return "admin/index";
	}
	@RequestMapping("/admin/form-product")
	public String table() {
		return "admin/form-product";
	}
	
	@RequestMapping("/admin/roles")
	public String roles() {
		return "admin/roles";
	}
	
	@RequestMapping("/admin/chart")
	public String chart() {
		return "admin/chart";
	}
}
