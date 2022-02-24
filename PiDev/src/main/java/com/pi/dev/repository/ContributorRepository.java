package com.pi.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pi.dev.models.Contributor;
@Repository
public interface ContributorRepository extends JpaRepository<Contributor, Long>{

}
