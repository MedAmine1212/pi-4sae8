package com.pi.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pi.dev.models.Events;


@Repository
public interface EventsRepository extends JpaRepository<Events,Long> {

}
