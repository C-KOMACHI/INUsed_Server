package com.c_comachi.inused.domain.chat.repository;

import com.c_comachi.inused.domain.chat.entity.ChatMessage;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    List<ChatMessage> findAllByRoomId(Long roomId);

    void deleteAllByRoomId(Long roomId);

    //ChatMessage findTopByRoomNumberOrderByCreatedTimeDesc(Long roomNumber);

    //List<ChatMessage> findAllByRoomNumber(Long roomNumber);
}
