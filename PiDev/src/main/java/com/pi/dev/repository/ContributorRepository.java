package com.pi.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pi.dev.models.Contributor;

public interface ContributorRepository extends JpaRepository<Contributor, Long>{

}
