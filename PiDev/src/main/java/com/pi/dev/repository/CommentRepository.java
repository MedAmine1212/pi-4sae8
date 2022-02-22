package com.pi.dev.repository;

import com.pi.dev.models.Comment;
import com.pi.dev.models.Post;
import com.pi.dev.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByCommentOwner(User user);

    @Query(value = "select * from Comment LIMIT ?1", nativeQuery = true)
    List<Comment> getFirstTenComment(int commentCount);
}
