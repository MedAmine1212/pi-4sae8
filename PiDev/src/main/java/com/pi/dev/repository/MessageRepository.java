package com.pi.dev.repository;

import com.pi.dev.models.Message;
import com.pi.dev.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByRoom(Room room);
}
