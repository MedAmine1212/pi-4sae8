package com.pi.dev.repository;

import com.pi.dev.models.Post;
import com.pi.dev.models.PostLike;
import com.pi.dev.models.Room;
import com.pi.dev.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    
}
