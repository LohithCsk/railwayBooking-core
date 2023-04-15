package com.example.railway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.railway.model.User;
import com.example.railway.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@GetMapping("/authenticate")
	public int login(@RequestBody User user) throws Exception {
		System.out.println("im in controller");
		int result;
		result=loginService.authenticate(user);
		return result;
	}
	
	
}
