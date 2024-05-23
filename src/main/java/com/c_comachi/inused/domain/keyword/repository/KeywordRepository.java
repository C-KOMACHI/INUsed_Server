package com.c_comachi.inused.domain.keyword.repository;

import com.c_comachi.inused.domain.keyword.entity.KeywordEntity;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeywordRepository extends JpaRepository<KeywordEntity, Long> {

    List<KeywordEntity> findAllByUser(UserEntity user);
}
