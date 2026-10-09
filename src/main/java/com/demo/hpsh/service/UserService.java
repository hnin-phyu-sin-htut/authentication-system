package com.demo.hpsh.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.hpsh.dto.LoginRequest;
import com.demo.hpsh.dto.LoginResponse;
import com.demo.hpsh.dto.RegisterRequest;
import com.demo.hpsh.dto.RegisterResponse;
import com.demo.hpsh.entity.Role;
import com.demo.hpsh.entity.User;
import com.demo.hpsh.repository.RoleRepository;
import com.demo.hpsh.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	
	public RegisterResponse register(RegisterRequest registerRequest) {
		if (userRepository.findByUsername(registerRequest.username()).isPresent()) {
			throw new RuntimeException("User already exists.");
		}
		User user = new User();
		user.setUsername(registerRequest.username());
		user.setPassword(passwordEncoder.encode(registerRequest.password()));
		user.setEmail(registerRequest.email());
		
		Role role = roleRepository.findByRoleName(registerRequest.roleName())
				.orElseGet(() -> {
					Role newRole = new Role();
					newRole.setRoleName(registerRequest.roleName());
					return roleRepository.save(newRole);
				});
		user.setRole(role);
		User savedUser = userRepository.save(user);
		return mapToDto(savedUser);	
	}
	
	public LoginResponse login(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
		String token = jwtService.generateJwtToken(authentication.getName());
		return new LoginResponse(token);
	}
	
	public RegisterResponse mapToDto(User user) {
		return new RegisterResponse(
				user.getId(), 
				user.getUsername(), 
				user.getEmail(), 
				user.getRole().getRoleName()
				);
	}

}
