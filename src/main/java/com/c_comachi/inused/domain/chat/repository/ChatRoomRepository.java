package com.c_comachi.inused.domain.chat.repository;

import com.c_comachi.inused.domain.chat.entity.ChatRoom;
import com.c_comachi.inused.domain.inquiry.entity.ManagerInquiryEntity;
import com.c_comachi.inused.domain.inquiry.entity.UserInquiryEntity;
import com.c_comachi.inused.domain.post.entity.PostEntity;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findById(Long chatroomId);

    @Query("SELECT cr FROM ChatRoom cr WHERE cr.sender = :user OR cr.receiver = :user ORDER BY cr.lastModifiedTime DESC")
    List<ChatRoom> findAllByUser(@Param("user") UserEntity user);

    List<ChatRoom> findAllByReceiverEmailAndPost(String email, PostEntity post);

}

