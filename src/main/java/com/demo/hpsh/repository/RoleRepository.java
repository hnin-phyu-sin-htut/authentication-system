package com.demo.hpsh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.hpsh.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Optional<Role> findByRoleName(String roleName);

}
