package com.ecommerceASM.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/shop")
	public String shop() {
		return "shop";
	}
	
	@RequestMapping("/detail")
	public String detail() {
		return "detail";
	}
}
