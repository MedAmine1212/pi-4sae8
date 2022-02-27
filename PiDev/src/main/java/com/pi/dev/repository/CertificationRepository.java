package com.pi.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pi.dev.models.Certification;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, Long> {

}
