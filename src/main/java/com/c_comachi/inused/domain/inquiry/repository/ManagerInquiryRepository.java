package com.c_comachi.inused.domain.inquiry.repository;

import com.c_comachi.inused.domain.inquiry.entity.ManagerInquiryEntity;
import com.c_comachi.inused.domain.inquiry.entity.UserInquiryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ManagerInquiryRepository extends JpaRepository<ManagerInquiryEntity, Long> {

    @Query("SELECT mi FROM ManagerInquiryEntity mi WHERE mi.inquiry = :inquiry")
    Optional<ManagerInquiryEntity> findByUserInquiryId(@Param("inquiry") UserInquiryEntity inquiry);


    @Query("SELECT EXISTS (SELECT mi FROM ManagerInquiryEntity mi WHERE mi.inquiry = :inquiry)")
    boolean existsByUserInquiryId(@Param("inquiry") UserInquiryEntity inquiry);
}
