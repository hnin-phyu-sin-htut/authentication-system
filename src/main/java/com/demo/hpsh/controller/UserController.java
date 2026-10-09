package com.demo.hpsh.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.hpsh.dto.LoginRequest;
import com.demo.hpsh.dto.LoginResponse;
import com.demo.hpsh.dto.RegisterRequest;
import com.demo.hpsh.dto.RegisterResponse;
import com.demo.hpsh.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	@PostMapping("/register")
	public RegisterResponse register(@RequestBody RegisterRequest registerRequest) {
		return userService.register(registerRequest);
	}
	
	@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginRequest loginRequest) {
		return userService.login(loginRequest);
	}

}
