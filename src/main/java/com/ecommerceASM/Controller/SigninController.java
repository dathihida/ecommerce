package com.ecommerceASM.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SigninController {
	@RequestMapping("/signin/index")
	public String signin() {
		return "index";
	}
}
