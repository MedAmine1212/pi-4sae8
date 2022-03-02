package com.pi.dev.repository;

import com.pi.dev.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "select * from Post LIMIT ?1", nativeQuery = true)
    List<Post> getFirstTenPosts(int postCount);

    @Query(value = "select * from Post p where LOWER(p.post_title) LIKE %:text% OR LOWER(p.post_content) LIKE %:text%", nativeQuery = true)
    List<Post> findPostsByText(@Param("text") String text);


//    @Query(value = "select pst.postFiles from Post pst where pst.postId =: postId")
//    List<String> findFilesByPost(@Param("postId") Long postId);


//    List<Post> geTenMostPopular();
}
