package com.pi.dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pi.dev.models.CharitySubscription;

@Repository
public interface CharitySubscriptionRepository extends JpaRepository<CharitySubscription,Long> {
	
	@Query("SELECT c from CharitySubscription c where c.score = (SELECT max(f.score) from CharitySubscription f )")
	
    List<CharitySubscription> CompreScore();


}
