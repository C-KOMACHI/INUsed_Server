package com.c_comachi.inused.domain.users.repository;

import com.c_comachi.inused.domain.users.entity.UserEntity;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @EntityGraph(attributePaths = "authority")
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findById(Long id);

    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);

}
