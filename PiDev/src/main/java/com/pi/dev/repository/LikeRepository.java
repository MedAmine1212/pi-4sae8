package com.pi.dev.repository;

import com.pi.dev.models.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface LikeRepository extends JpaRepository<PostLike, Long> {


    @Query("delete from PostLike l where (l.likeOwner.userId = ?1) AND (l.post.postId = ?2)")

    void removeLikeFromPost(Long postId, Long userId);
}
