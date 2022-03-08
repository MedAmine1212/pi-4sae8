package com.pi.dev.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pi.dev.models.Training;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

    @Query("select c from Training c where c.startDate >= :threeDaysAgoDate")
    List<Training> findAllWithDateAfter(@Param("threeDaysAgoDate") Date threeDaysAgoDate);

	@Query("SELECT c FROM Training c WHERE c.startDate BETWEEN ?1 AND ?2 ") 
	List<Training> retrieveTrainingsBetWeenDates(Date startDate,Date endDate); 	
	
		

}
