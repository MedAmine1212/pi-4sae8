package com.pi.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pi.dev.models.CharitySubscription;

@Repository
public interface CharitySubscriptionRepository extends JpaRepository<CharitySubscription,Long> {

}
