package com.pi.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pi.dev.models.Jackpots;


@Repository
public interface JackpotsRepository extends JpaRepository<Jackpots , Long> {

}
