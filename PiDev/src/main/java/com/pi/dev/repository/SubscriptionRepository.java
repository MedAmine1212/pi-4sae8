package com.pi.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pi.dev.models.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

}
