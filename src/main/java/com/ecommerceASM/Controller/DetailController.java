package com.ecommerceASM.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerceASM.service.ProductService;

@Controller()
public class DetailController {
	@Autowired
	ProductService productService;

	@RequestMapping("/product/detail/{id}")
	public String detail(@PathVariable("id") Integer id, Model model, HttpServletRequest httpServletRequest) {
		model.addAttribute("product1", productService.findById(id));
		return "detail";
	}
}
