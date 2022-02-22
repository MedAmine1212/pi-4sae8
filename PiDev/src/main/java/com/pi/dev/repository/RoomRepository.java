package com.pi.dev.repository;

import com.pi.dev.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("select rm from Room rm where rm.subject like '%:subject%'")
    List<Room> findOneBySubject(@Param("subject") String subject);
}
