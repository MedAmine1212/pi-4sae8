package com.pi.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pi.dev.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
