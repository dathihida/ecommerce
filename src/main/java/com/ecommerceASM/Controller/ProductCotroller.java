package com.ecommerceASM.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductCotroller {
	@GetMapping("/product")
	public String product() {
		return "index";
	}
}
