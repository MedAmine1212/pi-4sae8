package com.pi.dev.repository;

import java.util.List;

import com.pi.dev.models.Contributor;
import com.pi.dev.models.Meeting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long>{

    List <Meeting> findAllByConsultedBy(Contributor cont);
    
}
