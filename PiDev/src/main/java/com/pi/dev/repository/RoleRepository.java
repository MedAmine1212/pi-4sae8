package com.pi.dev.repository;

import java.util.Optional;

import com.pi.dev.models.ERole;
import com.pi.dev.models.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
  Optional<Role> findByName(ERole name);
}
