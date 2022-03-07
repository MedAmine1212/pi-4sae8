package com.pi.dev.repository;

import java.util.Optional;

import com.pi.dev.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
  
  @Query("SELECT c FROM User c WHERE c.email = ?1")
  
  public User findByEmail(String email); 
   
  public User findByResetPasswordToken(String token);

}
