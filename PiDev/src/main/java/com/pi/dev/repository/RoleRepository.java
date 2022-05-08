package com.pi.dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pi.dev.models.ERole;
import com.pi.dev.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	  Optional<Role> findByName(ERole name);

}
