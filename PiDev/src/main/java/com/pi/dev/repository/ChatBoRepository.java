package com.pi.dev.repository;

import com.pi.dev.models.ChatBot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatBoRepository extends JpaRepository<ChatBot, Long> {

    @Query(value = "select ch from ChatBot ch where LOWER(ch.response) LIKE ?1")
    List<ChatBot> findAllContainingWord(String text);
}
