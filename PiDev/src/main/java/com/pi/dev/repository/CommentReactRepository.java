package com.pi.dev.repository;

import com.pi.dev.models.Comment;
import com.pi.dev.models.CommentReact;
import com.pi.dev.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentReactRepository extends JpaRepository<CommentReact, Long> {
    List<CommentReact> findAllByReactComment(Comment reactComment);



    @Query("delete from CommentReact cr where cr.reactOwner.userId =: userId AND cr.reactComment.commentId =: commentId")
    void removeReactFromPost(@Param("commentId") Long commentId,@Param("userId") Long userId);
}
