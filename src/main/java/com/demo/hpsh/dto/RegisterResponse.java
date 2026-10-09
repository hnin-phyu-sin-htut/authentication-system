package com.demo.hpsh.dto;

public record RegisterResponse(
			Long id,
			String username,
			String email,
			String roleName
		) {

}
