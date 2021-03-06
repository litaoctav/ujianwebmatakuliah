package com.kuliah.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import com.kuliah.main.entity.AdminUser;
import com.kuliah.main.repository.AdminUserRepository;

@Controller
public class HomePage {
	
	@Autowired
	AdminUserRepository adminUserRepository;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		
		return "login.html";
		
	}
	
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("adminuser", new AdminUser());
		return "login.html";
		
	}
	
	@PostMapping("/login")
	public String loginPage(AdminUser adminUser) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodePassword = passwordEncoder.encode(adminUser.getPassword());
		adminUser.setPassword(encodePassword);
		System.out.println(adminUserRepository.toString());
		adminUserRepository.save(adminUser);
		return "login.html";
	}
	
	

}
