package com.ecommerceASM;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import com.ecommerceASM.entity.Account;
import com.ecommerceASM.service.AccountService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	AccountService accountService;
	
	@Autowired
	UserDetailsService userDetailsService;
//	@Autowired(required = true)
//	BCryptPasswordEncoder pe;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService);
//		auth.userDetailsService(username ->{
//			try {
//				Account user = accountService.findById(username);
//				String password = pe.encode(user.getPassword());
////				String password = getPasswordEncoder().encode(user.getPassword());
//				String[] role = user.getAuthorities().stream()
//						.map(er-> er.getRole().getId())
//						.collect(Collectors.toList()).toArray(new String[0]);
//				return User.withUsername(username).password(password).roles(role).build();
//			} catch (Exception e) {
//				// TODO: handle exception
//				throw new UsernameNotFoundException(username+ "not found");
//			}
//		});
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/order/**", "/signin/**").authenticated()
			.antMatchers("/admin/**").hasAnyRole("DIRE", "GUEST")
			.antMatchers("rest/authorities").hasRole("DIRE")
			.anyRequest().permitAll();
		
		http.formLogin()
			.loginPage("/security/login/form")
			.loginProcessingUrl("/security/login")
			.defaultSuccessUrl("/security/login/success")
			.failureUrl("/security/login/failure");
		
		http.rememberMe()
			.tokenValiditySeconds(86400);
		
		http.exceptionHandling()
			.accessDeniedPage("/security/access/denied");
		
		http.logout()
		.logoutUrl("/security/logout")
		.logoutSuccessUrl("/security/logout/success")
		.addLogoutHandler(new SecurityContextLogoutHandler())
		.clearAuthentication(true);
		
//		http.oauth2Login()
//			.loginPage("/auth/login/form")//dia chi chuyen ve form dang nhap
//			.defaultSuccessUrl("/oauth2/login/success", true)//dang thanh cong chuyen ve
//			.failureUrl("/auth/login/error")//dang that bai
//			.authorizationEndpoint()
//			.baseUri("/oauth2/autorization");//khai vao form 
	}
	
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/rsx/**", "/api/**");
	}
}
