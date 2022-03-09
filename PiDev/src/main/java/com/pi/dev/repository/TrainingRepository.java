package com.pi.dev.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pi.dev.models.CertifStats;
import com.pi.dev.models.Training;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

    @Query("select c from Training c where c.startDate Between CURRENT_DATE AND :threeDaysAgoDate")
    List<Training> findAllWithDateAfter(@Param("threeDaysAgoDate") Date threeDaysAgoDate);

	@Query("SELECT c FROM Training c WHERE c.startDate BETWEEN ?1 AND ?2 ") 
	List<Training> retrieveTrainingsBetWeenDates(Date startDate,Date endDate); 	
	
	@Query("Select c.trainingId,count(x) as cnt  from Training c, Certification x "
	+"where c.trainingId = x.training.trainingId and x.Succeeded = true group By c.trainingId")
	List<Object[]> MostCertifiedTrainings();
		

}
