package com.pi.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pi.dev.models.JackpotEvents;


@Repository
public interface JackpotEventRepository extends JpaRepository<JackpotEvents,Long> {

}
