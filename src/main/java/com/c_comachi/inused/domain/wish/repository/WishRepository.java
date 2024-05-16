package com.c_comachi.inused.domain.wish.repository;

import com.c_comachi.inused.domain.wish.entity.WishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishRepository extends JpaRepository<WishEntity, Long> {
    boolean existsByUserIdAndPostId(Long userId, Long postId);
    Optional<WishEntity> findByUserIdAndPostId(Long userId, Long postId);
}
