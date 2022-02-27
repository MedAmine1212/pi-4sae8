package com.pi.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pi.dev.models.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
