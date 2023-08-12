package com.ecommerceASM.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerceASM.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@RequestMapping("/order/checkout")
	public String checkout() {
		return "checkout";
	}
	
	@RequestMapping("/order/detail/{id}")
	public String detail(@PathVariable("id") Long id, Model model, HttpServletRequest httpServletRequest) {
		model.addAttribute("order1", orderService.findById(id));
		
		String username = httpServletRequest.getRemoteUser();
		model.addAttribute("orders", orderService.findByUsername(username));
		return "listdetail";
	}
}
