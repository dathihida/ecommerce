package com.ecommerceASM.Controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerceASM.service.AccountService;

@Controller
public class SecurityController {
	
	@Autowired
	AccountService accountService;
	
	
	@RequestMapping("/security/login/form")
	public String loginForm() {
		return "signin";
	}
	
	@RequestMapping("/security/login/success")
	public String loginSuccess(Model model) {
		model.addAttribute("message", "Đăng nhập thành công");
		return "cart";
	}
	
	@RequestMapping("/security/login/failure")
	public String loginFailure(Model model) {
		model.addAttribute("message", "Đăng nhập thất bại");
		return "signin";
	}
	
	@RequestMapping("/security/access/denied")
	public String accessDenied(Model model) {
		model.addAttribute("message", "Không có thẩm quyền truy cập");
		return "signin";
	}
	
	@RequestMapping("/security/logout/success")
	public String logoutSuccess(Model model) {
		model.addAttribute("message", "Đăng xuất thành công");
		return "signin";
	}
	
//	@RequestMapping("oauth2/login/success")
//	public String success(OAuth2AuthenticationToken oauth2) {
////		accountService
//		return "forward:/security/login/success";
//	}
}