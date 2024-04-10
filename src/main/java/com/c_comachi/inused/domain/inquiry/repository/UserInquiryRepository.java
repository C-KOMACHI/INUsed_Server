package com.c_comachi.inused.domain.inquiry.repository;

import com.c_comachi.inused.domain.inquiry.entity.UserInquiryEntity;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserInquiryRepository extends JpaRepository<UserInquiryEntity, Long> {

    Optional<UserInquiryEntity> findById(Long userInquiryId);

    @Query("SELECT ui FROM UserInquiryEntity ui WHERE ui.user = :user ORDER BY ui.createdAt DESC")
    List<UserInquiryEntity> findListByUser(@Param("user") UserEntity user);

}
