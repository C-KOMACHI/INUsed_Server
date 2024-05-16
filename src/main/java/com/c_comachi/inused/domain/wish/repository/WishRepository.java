package com.c_comachi.inused.domain.wish.repository;

import com.c_comachi.inused.domain.wish.entity.WishEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishRepository extends JpaRepository<WishEntity, Long> {
    Optional<WishEntity> findByUserIdAndPostId(Long userId, Long postId);

    @Query("SELECT CASE WHEN COUNT(we) > 0 THEN TRUE ELSE FALSE END FROM WishEntity we WHERE we.user.email = :email AND we.post.id = :postId")
    boolean existsByUserAndPost(@Param("email") String email, @Param("postId") Long postId);
}
