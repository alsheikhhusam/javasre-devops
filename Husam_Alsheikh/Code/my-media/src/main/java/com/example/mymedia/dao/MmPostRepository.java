package com.example.mymedia.dao;

import com.example.mymedia.models.MmPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface MmPostRepository extends JpaRepository<MmPost, Integer> {
    @Modifying
    @Query(value="insert into mm_likes (post_id, user_id) values (:postId, :userId)", nativeQuery = true)
    void likePost(@Param("postId") int postId, @Param("userId") int userId);
}
