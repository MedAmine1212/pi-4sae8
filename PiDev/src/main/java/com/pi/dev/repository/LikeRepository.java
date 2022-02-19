package com.pi.dev.repository;

import com.pi.dev.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {


    @Query("delete from Like l where (l.likeOwner.userId = ?1) AND (l.post.postId = ?2)")

    void removeLikeFromPost(Long postId, Long userId);
}
