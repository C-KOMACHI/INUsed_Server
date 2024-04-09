package com.c_comachi.inused.domain.notice.repository;

import com.c_comachi.inused.domain.notice.entity.NoticeEntity;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {
    Optional<NoticeEntity> findById(Long id);
    boolean existsById(Long id);

    List<NoticeEntity> findAllByOrderByCreatedAtDesc();

}
