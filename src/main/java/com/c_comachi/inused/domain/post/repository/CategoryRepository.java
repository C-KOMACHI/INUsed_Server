package com.c_comachi.inused.domain.post.repository;

import com.c_comachi.inused.domain.post.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findById(Long categoryId);
}
