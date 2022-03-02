package com.pi.dev.repository;

import com.pi.dev.models.Post;
import com.pi.dev.models.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LikeRepository extends JpaRepository<PostLike, Long> {


    @Query("delete from PostLike l where l.likeOwner.userId =: postId AND l.likePost.postId =: postId")

    void removeLikeFromPost(@Param("postId") Long postId,@Param("userId")  Long userId);

    List<PostLike> findAllByLikePost(Post p);
}
